package com.gross.simulation;
import com.gross.simulation.map.GameMap;

public class Main {

    public static void main(String[] args) {
        GameMap gameMap = new GameMap(10, 10);
        Simulation simulation = new Simulation(gameMap);
        simulation.fillGameMap();

        final boolean[] pause = {false};
        final boolean[] running = {true};

        InputHandler inputHandler = new InputHandler(running, pause);
        Thread inputThread = new Thread(inputHandler);

        inputThread.start();

        while (running[0]) {
            if (!pause[0]) {
                System.out.println("Текущий ход симуляции: " + simulation.getCounter());
                simulation.setCounter(simulation.getCounter() + 1);
                simulation.renderGameMap();
                simulation.turnActions();
            }
            simulation.slowSimulation(800);  // Задержка между ходами
        }


    }
}


