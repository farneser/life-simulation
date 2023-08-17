package com.farneser;

import com.farneser.entity.creature.Creature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Simulation extends Thread {

    private final Map _map;
    private boolean _isStarted;
    private final Logger _logger = Logger.getLogger(Simulation.class.getName());
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
        _logger.log(Level.INFO, "Next Turn!!!");

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
        _logger.log(Level.INFO, "Game Started!!!");

        _isStarted = true;
        start();
    }

    public void pauseSimulation() {
        _logger.log(Level.INFO, "Game Paused!!!");

        _isStarted = false;
    }
}
