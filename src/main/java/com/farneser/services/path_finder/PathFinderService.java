package com.farneser.services.path_finder;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.services.render.console.ConsoleRenderService;

import java.util.HashSet;
import java.util.Set;

public abstract class PathFinderService implements IPathFinder {
    protected final Map _map;

    public PathFinderService(Map map) {
        _map = map;
    }

    public boolean isGrassNear(Coordinates coordinates) {

        var cells = coordinatesToCheck(coordinates);


        return false;
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

    }
}
