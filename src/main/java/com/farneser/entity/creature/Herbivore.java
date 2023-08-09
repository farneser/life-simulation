package com.farneser.entity.creature;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Grass;
import com.farneser.entity.Rock;
import com.farneser.services.path_finder.IPathFinder;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, int speed, int hp, IPathFinder pathFinder, Map map) {
        super(coordinates, speed, hp, pathFinder, map);
    }

    @Override
    public void makeMove() {
        makeMove(new Class[]{Grass.class, Rock.class});
    }

    @Override
    public String toString() {
        return super.toString() + "\n\ttype: herbivore";
    }
}
