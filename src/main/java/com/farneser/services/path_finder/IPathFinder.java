package com.farneser.services.path_finder;

import com.farneser.Coordinates;

import java.util.Deque;

public interface IPathFinder {
    Deque<Coordinates> findPathTo(Coordinates from, Coordinates to);
}
