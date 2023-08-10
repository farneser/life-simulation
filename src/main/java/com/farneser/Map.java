package com.farneser;

import com.farneser.entity.Entity;
import com.farneser.entity.creature.Creature;
import com.farneser.services.render.IRender;
import com.farneser.services.render.console.ConsoleRenderService;
import com.farneser.utils.EntityFactory;

import java.util.HashMap;
import java.util.Random;


public class Map {

    private final IRender _render;
    private final HashMap<Coordinates, Entity> _entities = new HashMap<>();
    public final int width;
    public final int height;

    public Map(int width, int height, IRender render) {
        _render = render;

        this.width = width;
        this.height = height;

    }

    public void fillMapWithRandomEntities() {

        var entityFactory = new EntityFactory(this);

        var random = new Random();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (random.nextInt(10) != 0) continue;
                var coordinates = new Coordinates(x, y);
                _entities.put(coordinates, entityFactory.getRandomEntity(coordinates, this));
            }
        }
    }

    public Entity getEntityAt(Coordinates coordinates) {
        return _entities.get(coordinates);
    }

    public void setEntityAt(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);

        _entities.put(coordinates, entity);
    }

    public <T extends Entity> HashMap<Coordinates, T> getEntitiesOfType(Class<T> type) {

        var entities = new HashMap<Coordinates, T>();

        _entities.forEach((coordinates, entity) -> {
            if (entity.getClass() == type) {
                entities.put(coordinates, (T) entity);
            }
        });

        return entities;

    }

    public HashMap<Coordinates, Creature> getCreatures() {

        var entities = new HashMap<Coordinates, Creature>();

        _entities.forEach((coordinates, entity) -> {
            if (entity instanceof Creature) {
                entities.put(coordinates, (Creature) entity);
            }
        });

        return entities;

    }

    public void removeEntity(Coordinates coordinates) {
        _entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        System.out.println("entity moved from " + from + " to " + to);
        var entity = getEntityAt(from);

        removeEntity(from);
        setEntityAt(to, entity);
    }

    public void render() {
        _render.render(this);
    }

    public static void main(String[] args) {
        var map = new Map(20, 10, new ConsoleRenderService());

        map.getCreatures().forEach((coordinates, creature) -> System.out.println(creature));
    }

    @Override
    public String toString() {
        return "Map\n\twidth " + width + "\n\theight " + height;
    }
}
