package com.farneser.services.path_finder.bfs_path_finder;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.services.path_finder.PathFinderService;
import com.farneser.services.render.console.ConsoleRenderService;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class BfsPathFinderService extends PathFinderService {

    public BfsPathFinderService(Map map) {
        super(map);
    }

    @Override
    public Deque<Coordinates> findPathTo(Coordinates coordinatesFrom, Coordinates coordinatesTo) {

//        System.out.println("find path from + " + coordinatesFrom.getX() + "-" + coordinatesFrom.getY() + " to " + coordinatesTo.getX() + "-" + coordinatesTo.getY());

        var queue = new ArrayDeque<Coordinates>();

        queue.add(coordinatesFrom);

        var visitedNodes = new HashMap<Coordinates, Coordinates>();

        while (!queue.isEmpty()) {
            var currentNode = queue.pop();

            if (currentNode == coordinatesTo) {
                break;
            }

            var entitiesNear = emptyEntitiesNear(currentNode);

            if (coordinatesToCheck(currentNode).stream().anyMatch(c -> c.getX() == coordinatesTo.getX() && c.getY() == coordinatesTo.getY())) {
                entitiesNear.add(coordinatesTo);
            }

            entitiesNear.forEach(nextNode -> {

                if (visitedNodes.get(nextNode) == null) {
                    queue.add(nextNode);
                    visitedNodes.put(nextNode, currentNode);
                }

            });

        }

        var path = new ArrayDeque<Coordinates>();

        var currentNode = coordinatesTo;

        while (currentNode != coordinatesFrom) {

            currentNode = visitedNodes.get(currentNode);

            if (currentNode == null){
                return null;
            }

            if (currentNode != coordinatesFrom){
                path.push(currentNode);
            }
        }

        return path;
    }

    public static void main(String[] args) {
        var bfs = new BfsPathFinderService(new Map(25, 25, new ConsoleRenderService()));

        var queue = bfs.findPathTo(new Coordinates(5, 5), new Coordinates(20, 24));

        System.out.println("queue");

        while (!queue.isEmpty()) {
            System.out.println(queue.pop());
        }

    }
}
