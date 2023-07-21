package com.farneser.services.path_finder;

import com.farneser.Coordinates;
import com.farneser.Map;

import java.util.HashSet;
import java.util.Set;

public abstract class PathFinderService implements IPathFinder {

    private final Map _map;

    public PathFinderService(Map map) {
        _map = map;
    }

    protected Set<Coordinates> cellsToCheck(Coordinates coordinates) {
        var result = new HashSet<Coordinates>();

        for (var x = -1; x <= 1; x++) {
            for (var y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                result.add(new Coordinates(coordinates.getX() + x, coordinates.getY() + y));
            }
        }

        return result;
    }
}
