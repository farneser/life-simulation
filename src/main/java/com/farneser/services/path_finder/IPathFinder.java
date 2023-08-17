package com.farneser.services.path_finder;

import com.farneser.Coordinates;
import com.farneser.entity.Entity;

import java.util.Deque;

public interface IPathFinder {
    /**
     * Method to find path from to
     *
     * @param from define start coordinates of path
     * @param to   define end coordinates of path
     * @return the path from "from" coordinate to "to" coordinate or null if path not exists
     */
    Deque<Coordinates> findPathTo(Coordinates from, Coordinates to);

    /**
     * Method to get coordinates of nearest Entity by type near coordinates
     *
     * @param coordinates define coordinates to check
     * @param type define type to find
     * @return nearest coordinates of target type or null if Entity not exists
     */
    <T extends Entity> Coordinates isEntityNear(Coordinates coordinates, Class<T> type);

    /**
     * Method to get coordinates of nearest Entity by types near coordinates
     *
     * @param coordinates define coordinates to check
     * @param type define types to find
     * @return nearest coordinates of target type or null if Entity not exists
     */
    <T extends Entity> Coordinates isEntityNear(Coordinates coordinates, Class<T>[] type);
}
