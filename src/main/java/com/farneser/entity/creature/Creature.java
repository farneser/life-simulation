package com.farneser.entity.creature;

import com.farneser.Coordinates;
import com.farneser.entity.Entity;
import com.farneser.services.path_finder.IPathFinder;

public abstract class Creature extends Entity {
    public final int speed;
    public final int hp;
    public final IPathFinder pathFinder;
    public Creature(Coordinates coordinates, int speed, int hp, IPathFinder pathFinder) {
        super(coordinates);
        this.speed = speed;
        this.hp = hp;
        this.pathFinder = pathFinder;
    }

    abstract public void makeMove(Coordinates currentCell);

    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public String toString() {
        return "Creature\n\tspeed: " + speed + "\n\thp: " + hp + "\n\tcoordinates: " + getCoordinates();
    }
}
