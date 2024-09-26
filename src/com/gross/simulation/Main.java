package com.gross.simulation;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.herbivores.Rabbit;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.entity.staticEntity.Rock;
import com.gross.simulation.entity.staticEntity.Tree;
import com.gross.simulation.map.GameMap;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        GameMap gameMap=new GameMap(10,10);


            gameMap.fillGameMap();
            gameMap.renderGameMap();
            Rabbit rabbit=new Rabbit();
         gameMap.getGameMap().put(new Coordinate(4,4),rabbit);
         gameMap.getGameMap().put(new Coordinate(6,4),new Grass());
        gameMap.getGameMap().put(new Coordinate(9,9),new Grass());
        gameMap.getGameMap().put(new Coordinate(6,9),new Tree());
        gameMap.getGameMap().put(new Coordinate(6,8),new Tree());
        gameMap.getGameMap().put(new Coordinate(6,7),new Tree());
        gameMap.getGameMap().put(new Coordinate(6,6),new Tree());
        gameMap.getGameMap().put(new Coordinate(6,5),new Tree());

        gameMap.getGameMap().put(new Coordinate(6,3),new Tree());
        gameMap.getGameMap().put(new Coordinate(6,2),new Tree());
        gameMap.getGameMap().put(new Coordinate(6,1),new Tree());

        while (true) {
            gameMap.renderGameMap();
            gameMap.nextTurn();
            try {
                Thread.sleep(1000);  // Можно изменить время задержки в миллисекундах
            } catch (InterruptedException e) {
                e.printStackTrace();  // В случае прерывания
            }
        }


    }
}