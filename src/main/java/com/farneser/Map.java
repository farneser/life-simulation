package com.farneser;

import com.farneser.entity.Entity;
import com.farneser.services.render.IRender;
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

        var entityFactory = new EntityFactory(this);
        var random = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (random.nextInt(10) != 0) continue;
                var coordinates = new Coordinates(x, y);
                _entities.put(coordinates, entityFactory.getRandomEntity(coordinates));
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

    public void removeEntity(Coordinates coordinates) {
        _entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        var entity = getEntityAt(from);

        removeEntity(from);
        setEntityAt(to, entity);
    }

    public void render() {
        _render.render(this);
    }
}
