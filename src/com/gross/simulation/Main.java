package com.gross.simulation;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.creatures.herbivores.Rabbit;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.entity.staticEntity.Rock;
import com.gross.simulation.map.GameMap;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        GameMap gameMap=new GameMap(10,10);


            gameMap.fillGameMap();
            gameMap.renderGameMap();
         gameMap.getGameMap().put(new Coordinate(4,4),new Rabbit());
         gameMap.getGameMap().put(new Coordinate(6,4),new Grass());
        gameMap.renderGameMap();

    }
}