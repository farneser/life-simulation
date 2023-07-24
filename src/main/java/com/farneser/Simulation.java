package com.farneser;

public class Simulation extends Thread{

    private Map _map;
    private boolean _isStarted;

    private int _turnCounter;

    public Simulation(Map map){
        _map = map;
        _turnCounter = 0;
    }

    public int getTurnCounter(){
        return _turnCounter;
    }

    public void nextTurn(){
        _turnCounter++;
        _map.render();
    }

    @Override
    public void run() {
        while (_isStarted) {
            this.nextTurn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public void startSimulation(){
        _isStarted = true;
        start();
    }
    public void pauseSimulation(){
        _isStarted = false;
    }
}
