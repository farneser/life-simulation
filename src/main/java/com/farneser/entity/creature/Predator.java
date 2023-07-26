package com.farneser.entity.creature;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.services.path_finder.IPathFinder;

public class Predator extends Creature {
    public Predator(Coordinates coordinates, int speed, int hp, IPathFinder pathFinder, Map map) {
        super(coordinates, speed, hp, pathFinder, map);
    }

    @Override
    public void makeMove(Coordinates currentCell) {
        System.out.println("predator moved");
    }

    @Override
    public String toString() {
        return super.toString() + "\n\ttype: predator";
    }
}
