package com.farneser.utils;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Entity;
import com.farneser.entity.Grass;
import com.farneser.entity.Rock;
import com.farneser.entity.Tree;
import com.farneser.entity.creature.Herbivore;
import com.farneser.entity.creature.Predator;
import com.farneser.services.path_finder.BfsPathFinderService;

import java.util.Random;

public class EntityFactory {

    public final Map map;

    public EntityFactory(Map map) {
        this.map = map;
    }

    public Entity getRandomEntity(Coordinates coordinates, Map map) {
        int a = new Random().nextInt(5);

        switch (a) {
            case 0 -> {
                return new Grass(coordinates);
            }
            case 1 -> {
                return new Rock(coordinates);
            }
            case 2 -> {
                return new Tree(coordinates);
            }
            case 3 -> {
                return new Herbivore(coordinates, new Random().nextInt(5), new Random().nextInt(5), new BfsPathFinderService(), map);
            }
            case 4 -> {
                return new Predator(coordinates, new Random().nextInt(5), new Random().nextInt(5), new BfsPathFinderService(), map);
            }
        }

        return null;
    }
}
