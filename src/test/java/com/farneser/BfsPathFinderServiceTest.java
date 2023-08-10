package com.farneser;

import com.farneser.entity.Grass;
import com.farneser.entity.Tree;
import com.farneser.entity.creature.Herbivore;
import com.farneser.services.path_finder.bfs_path_finder.BfsPathFinderService;
import com.farneser.services.render.console.ConsoleRenderService;
import junit.framework.TestCase;

import java.util.ArrayDeque;
import java.util.Deque;

public class BfsPathFinderServiceTest extends TestCase {

    private final Map _map;

    public BfsPathFinderServiceTest(String testName) {
        super(testName);

        _map = new Map(15, 15, new ConsoleRenderService());

        _map.setEntityAt(new Coordinates(1, 0), new Tree(new Coordinates(0, 0)));
        _map.setEntityAt(new Coordinates(0, 1), new Tree(new Coordinates(0, 0)));
        _map.setEntityAt(new Coordinates(1, 1), new Tree(new Coordinates(0, 0)));

        _map.setEntityAt(new Coordinates(0, 0), new Herbivore(new Coordinates(0, 0), new BfsPathFinderService(_map), _map));
        _map.setEntityAt(new Coordinates(6, 0), new Herbivore(new Coordinates(0, 0), new BfsPathFinderService(_map), _map));

        _map.setEntityAt(new Coordinates(5, 5), new Grass(new Coordinates(0, 0)));

    }

    private Deque<Coordinates> findPath(Coordinates from, Coordinates to) {
        return _map.getCreatures().get(from).pathFinder.findPathTo(from, to);
    }

    public void testPositivePath() {
        var path = findPath(new Coordinates(6, 0), new Coordinates(5, 5));

        if (path == null) {
            fail();
            return;
        }

        var correctPath = new ArrayDeque<Coordinates>();
        correctPath.add(new Coordinates(7, 1));
        correctPath.add(new Coordinates(8, 2));
        correctPath.add(new Coordinates(7, 3));
        correctPath.add(new Coordinates(6, 4));

        if (correctPath.size() != path.size()) {
            fail();
            return;
        }

        while (!path.isEmpty()) {
            if (!path.pop().equals(correctPath.pop())) {
                fail();
                return;
            }
        }

        assertTrue(true);

    }

    public void testNegativePath() {
        _map.render();

        var path = findPath(new Coordinates(0, 0), new Coordinates(5, 5));

        if (path == null) {
            assertTrue(true);
        } else {
            fail();
        }

    }

    public void testStepsMove() {
        _map.render();
        var creature = _map.getCreatures().get(new Coordinates(6, 0));
        _map.render();

        var path = findPath(new Coordinates(6, 0), new Coordinates(5, 5));

        while (!path.isEmpty()) {
            _map.moveEntity(creature.getCoordinates(), path.pop());
        }
        _map.render();

    }

    public void testCreatureMakeMove() {
        _map.render();
        var creature = _map.getCreatures().get(new Coordinates(6, 0));

        creature.makeMove();
        _map.render();


    }


}
