package com.farneser.services.path_finder;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Entity;
import com.farneser.entity.Grass;
import com.farneser.entity.Rock;
import com.farneser.entity.Tree;
import com.farneser.entity.creature.Herbivore;
import com.farneser.entity.creature.Predator;
import com.farneser.services.path_finder.bfs_path_finder.BfsPathFinderService;
import com.farneser.services.render.console.ConsoleRenderService;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public abstract class PathFinderService implements IPathFinder {
    protected final Map _map;

    public PathFinderService(Map map) {
        _map = map;
    }

    public <T extends Entity> Coordinates isEntityNear(Coordinates coordinates, Class<T> type) {

        var coordinatesToCheck = coordinatesToCheck(coordinates);
        AtomicReference<Coordinates> result = new AtomicReference<>(null);
        coordinatesToCheck.forEach(coordinatesAround -> {
            var entity = _map.getEntityAt(coordinatesAround);
            if (entity == null) return;
            if (entity.getClass() == type) {
                result.set(entity.getCoordinates());
            }
        });

        return result.get();
    }

    public <T extends Entity> Coordinates isEntityNear(Coordinates coordinates, Class<T>[] types) {

        for (var type : types) {
            var entityCoordinates = isEntityNear(coordinates, type);

            if (entityCoordinates != null){
                return entityCoordinates;
            }

        }

        return null;
    }

    protected Set<Coordinates> emptyEntitiesNear(Coordinates coordinates) {
        var emptyCoordinates = new HashSet<Coordinates>();

        coordinatesToCheck(coordinates).forEach(coordinatesAround -> {
            if (_map.getEntityAt(coordinatesAround) == null) {
                emptyCoordinates.add(coordinatesAround);
            }
        });

        return emptyCoordinates;
    }

    protected Set<Coordinates> coordinatesToCheck(Coordinates coordinates) {
        var result = new HashSet<Coordinates>();

        for (var x = -1; x <= 1; x++) {
            for (var y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                var coordinateToCheck = new Coordinates(coordinates.getX() + x, coordinates.getY() + y);
                if (coordinateToCheck.getX() >= _map.width || coordinateToCheck.getX() < 0 || coordinateToCheck.getY() >= _map.height || coordinateToCheck.getY() < 0)
                    continue;
                result.add(coordinateToCheck);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        var bfs = new BfsPathFinderService(new Map(5, 5, new ConsoleRenderService()));

        bfs.coordinatesToCheck(new Coordinates(2, 2)).forEach(System.out::println);
        bfs._map.render();

        System.out.println(bfs.isEntityNear(new Coordinates(0, 0), Grass.class));
        System.out.println(bfs.isEntityNear(new Coordinates(0, 0), Rock.class));
        System.out.println(bfs.isEntityNear(new Coordinates(0, 0), Predator.class));
        System.out.println(bfs.isEntityNear(new Coordinates(0, 0), Tree.class));
        System.out.println(bfs.isEntityNear(new Coordinates(0, 0), Herbivore.class));
        bfs.emptyEntitiesNear(new Coordinates(2, 2)).forEach(System.out::println);

    }
}
