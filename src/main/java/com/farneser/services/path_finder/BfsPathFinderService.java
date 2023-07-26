package com.farneser.services.path_finder;

import com.farneser.Coordinates;
import com.farneser.Map;

import java.util.Deque;

public class BfsPathFinderService extends PathFinderService {

    public BfsPathFinderService(Map map) {
        super(map);
    }

    @Override
    public Deque<Coordinates> findPathTo(Coordinates from, Coordinates to) {
        System.out.println("find path from + " + from.getX() + "-" + from.getY() + " to " + to.getX() + "-" + to.getY());
        return null;
    }
}
