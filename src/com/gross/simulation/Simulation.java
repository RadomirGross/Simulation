package com.gross.simulation;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.entity.creatures.herbivores.Herbivore;
import com.gross.simulation.entity.staticEntity.Empty;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.map.GameMap;

import java.util.HashSet;
import java.util.Set;

public class Simulation {

    private final GameMap gameMap;
    private int counter = 0;

    public Simulation(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void initActions() {
        fillGameMap();

    }

    public void start() {
        final boolean[] pause = {false};
        final boolean[] running = {true};
        Thread inputThread = new Thread(new InputHandler(running, pause));
        inputThread.start();
        while (running[0]) {
            if (!pause[0]) {
                System.out.println("Текущий ход симуляции: " + getCounter());
                setCounter(getCounter() + 1);
                 renderGameMap();
                turnActions();
            }
            slowSimulation(10);  // Задержка между ходами
        }
    }

    public void turnActions() {
        Set<Entity> movedEntity = new HashSet<>();
        Set<Coordinate> allKeys = new HashSet<>(gameMap.getAllKeys());
        for (Coordinate coordinate : allKeys) {
            Entity entity = gameMap.getEntity(coordinate);
            if (entity instanceof Creature && !movedEntity.contains(entity)) {
                Creature creature = (Creature) entity;
                movedEntity.add(entity);
                creature.makeMove(gameMap, coordinate.getX(), coordinate.getY());
            }
        }
      if (gameMap.checkNumberOfEntities(Herbivore.class)<3)
          gameMap.addEntityOnRandomCell(gameMap, gameMap.createRandomHerbivore());
    }

    public void slowSimulation(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void renderGameMap() {
        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                if(gameMap.hasEntityAt(new Coordinate(x, y)))
                System.out.print(gameMap.getEntity(new Coordinate(x, y)).getIcon() + " ");
                else {System.out.print(new Empty().getIcon()+" ");}
                if(gameMap.getEntity(new Coordinate(x, y))instanceof Empty)
                    System.out.println("DANGER");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void fillGameMap() {
       gameMap.addEntityOnRandomCell(gameMap, gameMap.createRandomPredator());
        for (int i = 0; i <6 ; i++)
            gameMap.addEntityOnRandomCell(gameMap, gameMap.createRandomHerbivore());
        for (int i = 0; i <10 ; i++)
            gameMap.addEntityOnRandomCell(gameMap, gameMap.createRandomStaticEntity());
        for (int i = 0; i < 3; i++) {
            gameMap.addEntityOnRandomCell(gameMap, new Grass());
        }

            }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
