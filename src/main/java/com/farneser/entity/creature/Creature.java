package com.farneser.entity.creature;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Entity;
import com.farneser.entity.IDevoured;
import com.farneser.services.path_finder.IPathFinder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Creature extends Entity {
    protected int _speed;
    protected int _healthPoints;
    public final IPathFinder pathFinder;
    protected final Map _map;
    private int _remainingSpeed;

    public Creature(Coordinates coordinates, IPathFinder pathFinder, Map map) {
        super(coordinates);
        this._speed = 5;
        this._healthPoints = 5;
        this.pathFinder = pathFinder;
        _map = map;
    }

    public Creature(Coordinates coordinates, int speed, int hp, IPathFinder pathFinder, Map map) {
        this(coordinates, pathFinder, map);
        this._speed = speed;
        this._healthPoints = hp;
    }

    public int getSpeed() {
        return _speed;
    }

    public int getHp() {
        return _healthPoints;
    }

    abstract public void makeMove();

    public boolean isAlive() {
        return _healthPoints > 0;
    }

    public <T extends Entity> void makeMove(Class<T>[] target) {
        _remainingSpeed = _speed;

        var nearestPath = new AtomicReference<Deque<Coordinates>>(new ArrayDeque<>());

        for (var entityType : target) {
            var entitiesNear = _map.getEntitiesOfType(entityType);

            if (entitiesNear.isEmpty()) {
                continue;
            }

            var nearestEntity = new AtomicReference<>(entitiesNear.entrySet().stream().findFirst().get().getValue());
            var pathToEntity = new AtomicReference<>(pathFinder.findPathTo(_coordinates, nearestEntity.get().getCoordinates()));

            entitiesNear.forEach((coordinates, entity) -> {
                var path = pathFinder.findPathTo(_coordinates, entity.getCoordinates());

                if (path != null && pathToEntity.get() != null && pathToEntity.get().size() >= path.size()) {
                    nearestEntity.set(entity);
                    nearestPath.set(path);
                }
            });

            if (pathToEntity.get() != null && nearestPath.get().size() > pathToEntity.get().size()) {
                nearestPath.set(pathToEntity.get());
            }
        }

        while (!nearestPath.get().isEmpty() && _remainingSpeed > 0) {
            _map.moveEntity(_coordinates, nearestPath.get().pop());
            _healthPoints--;
            _remainingSpeed--;
        }
    }

    protected int getRemainingSpeed() {
        return _remainingSpeed;
    }

    protected void eat(Coordinates coordinates){

        var entity = (IDevoured) _map.getEntityAt(coordinates);

        if (entity != null) {
            _healthPoints += entity.getFoodFromDamage(5);

            _map.removeEntity(coordinates);
            _map.moveEntity(_coordinates, coordinates);
        }
    }

    @Override
    public String toString() {
        return "Creature\n\tspeed: " + _speed + "\n\thp: " + _healthPoints + "\n\tcoordinates: " + getCoordinates();
    }
}
