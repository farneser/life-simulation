package com.farneser.entity.creature;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Entity;
import com.farneser.services.path_finder.IPathFinder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Creature extends Entity {
    protected int _speed;
    protected int _hp;
    public final IPathFinder pathFinder;
    protected final Map _map;

    public Creature(Coordinates coordinates, IPathFinder pathFinder, Map map) {
        super(coordinates);
        this._speed = 5;
        this._hp = 5;
        this.pathFinder = pathFinder;
        _map = map;
    }

    public Creature(Coordinates coordinates, int speed, int hp, IPathFinder pathFinder, Map map) {
        this(coordinates, pathFinder, map);
        this._speed = speed;
        this._hp = hp;
    }

    public int getSpeed() {
        return _speed;
    }

    public int getHp() {
        return _hp;
    }

    abstract public void makeMove();

    public boolean isAlive() {
        return _hp > 0;
    }

    public <T extends Entity> void makeMove(Class<T>[] target) {

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
        var stepsCount = 0;
        while (!nearestPath.get().isEmpty() && stepsCount <= _speed){
            _map.moveEntity(_coordinates, nearestPath.get().pop());
            _hp--;
            stepsCount++;
        }
    }

    @Override
    public String toString() {
        return "Creature\n\tspeed: " + _speed + "\n\thp: " + _hp + "\n\tcoordinates: " + getCoordinates();
    }
}
