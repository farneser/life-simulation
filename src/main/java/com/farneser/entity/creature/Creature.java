package com.farneser.entity.creature;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Entity;
import com.farneser.services.path_finder.IPathFinder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Creature extends Entity {
    protected int speed;
    protected int hp;
    public final IPathFinder pathFinder;
    protected final Map _map;

    public Creature(Coordinates coordinates, IPathFinder pathFinder, Map map) {
        super(coordinates);
        this.speed = 5;
        this.hp = 5;
        this.pathFinder = pathFinder;
        _map = map;
    }

    public Creature(Coordinates coordinates, int speed, int hp, IPathFinder pathFinder, Map map) {
        this(coordinates, pathFinder, map);
        this.speed = speed;
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    abstract public void makeMove();

    public boolean isAlive() {
        return hp > 0;
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

            entitiesNear.forEach((coordinates, grass) -> {
                var path = pathFinder.findPathTo(_coordinates, grass.getCoordinates());

                if (path != null && pathToEntity.get().size() > path.size()) {
                    nearestEntity.set(grass);
                    nearestPath.set(path);
                }
            });

            if (pathToEntity.get() != null && nearestPath.get().size() > pathToEntity.get().size()) {
                nearestPath.set(pathToEntity.get());
            }
        }

        for (var i = 0; i < Math.min(speed, nearestPath.get().size()); i++) {
            _map.moveEntity(_coordinates, nearestPath.get().pop());
        }
    }

    @Override
    public String toString() {
        return "Creature\n\tspeed: " + speed + "\n\thp: " + hp + "\n\tcoordinates: " + getCoordinates();
    }
}
