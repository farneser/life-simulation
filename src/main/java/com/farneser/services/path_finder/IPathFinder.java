package com.farneser.services.path_finder;

import com.farneser.Coordinates;

import java.util.Deque;

public interface IPathFinder {
    /**
     Method to find path from to
     @param from define start coordinates of path
     @param to define end coordinates of path
     @return the path from "from" coordinate to "to" coordinate or null if path not exists
     */
    Deque<Coordinates> findPathTo(Coordinates from, Coordinates to);
}
