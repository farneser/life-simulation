package com.farneser.entity.creature;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Grass;
import com.farneser.entity.IDevoured;
import com.farneser.services.path_finder.IPathFinder;

public class Herbivore extends Creature implements IDevoured {
    public Herbivore(Coordinates coordinates, IPathFinder pathFinder, Map map) {
        super(coordinates, pathFinder, map);
    }

    public Herbivore(Coordinates coordinates, int speed, int hp, IPathFinder pathFinder, Map map) {
        super(coordinates, speed, hp, pathFinder, map);
    }

    @Override
    public void makeMove() {
        makeMove(new Class[]{Grass.class});
    }

    @Override
    public String toString() {
        return super.toString() + "\n\ttype: herbivore";
    }

    @Override
    public int getFoodFromDamage(int damage) {
        _healthPoints -= damage;

        return (damage / 2) + (_healthPoints % 5);

    }

    @Override
    public int getHealth() {
        return _healthPoints;
    }
}
