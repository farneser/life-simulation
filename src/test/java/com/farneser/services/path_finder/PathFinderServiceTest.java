package com.farneser.services.path_finder;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Grass;
import com.farneser.entity.creature.Herbivore;
import com.farneser.services.path_finder.bfs_path_finder.BfsPathFinderService;
import com.farneser.services.render.console.ConsoleRenderService;
import junit.framework.TestCase;

import java.util.Deque;

public class PathFinderServiceTest extends TestCase {

    private final Map _map;

    public PathFinderServiceTest(String testName) {
        super(testName);

        _map = new Map(15, 15, new ConsoleRenderService(true));


        _map.setEntityAt(new Coordinates(6, 5), new Herbivore(new Coordinates(0, 0), new BfsPathFinderService(_map), _map));
        _map.setEntityAt(new Coordinates(8, 5), new Herbivore(new Coordinates(0, 0), new BfsPathFinderService(_map), _map));

        _map.setEntityAt(new Coordinates(5, 5), new Grass(new Coordinates(0, 0)));

    }

    public void testIsEntityNearPositive() {

        var pathFinder = new PathFinderService(_map) {
            @Override
            public Deque<Coordinates> findPathTo(Coordinates from, Coordinates to) {
                return null;
            }
        };

        var coordinates = pathFinder.isEntityNear(new Coordinates(6, 5), Grass.class);

        assertEquals(coordinates, new Coordinates(5, 5));

    }

    public void testIsEntityNearNegative() {

        var pathFinder = new PathFinderService(_map) {
            @Override
            public Deque<Coordinates> findPathTo(Coordinates from, Coordinates to) {
                return null;
            }
        };

        var coordinates = pathFinder.isEntityNear(new Coordinates(8, 5), Grass.class);

        assertNull(coordinates);

    }
}
