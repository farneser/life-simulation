package com.farneser;

import com.farneser.services.render.console.ConsoleRenderService;

public class App {
    public static void main(String[] args) {
        var simulation = new Simulation(new Map(30, 15, new ConsoleRenderService()));

        simulation.startSimulation();

    }
}
