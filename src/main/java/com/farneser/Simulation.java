package com.farneser;

import com.farneser.entity.creature.Creature;

public class Simulation extends Thread {

    private final Map _map;
    private boolean _isStarted;

    private int _turnCounter;

    public Simulation(Map map) {
        _map = map;
        _map.fillMapWithRandomEntities();
        _turnCounter = 0;
    }

    public int getTurnCounter() {
        return _turnCounter;
    }

    public void nextTurn() {
        _turnCounter++;

        for (int x = 0; x < _map.width; x++) {

            for (int y = 0; y < _map.height; y++) {
                var coordinates = new Coordinates(x, y);
                var entity = _map.getEntityAt(coordinates);

                if (entity instanceof Creature) {

                    ((Creature) entity).makeMove();
                }
            }
        }

        _map.render();
    }

    @Override
    public void run() {
        while (_isStarted) {
            this.nextTurn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void startSimulation() {
        _isStarted = true;
        start();
    }

    public void pauseSimulation() {
        _isStarted = false;
    }
}
