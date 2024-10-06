package com.gross.simulation;
import com.gross.simulation.map.GameMap;

public class Main {

    public static void main(String[] args) {
        GameMap gameMap = new GameMap(10, 10);
        Simulation simulation = new Simulation(gameMap);
        simulation.initActions();
        simulation.start();

}}


