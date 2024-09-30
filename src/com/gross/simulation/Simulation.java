package com.gross.simulation;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.entity.creatures.herbivores.Cow;
import com.gross.simulation.entity.creatures.herbivores.Rabbit;
import com.gross.simulation.entity.creatures.predators.Wolf;
import com.gross.simulation.entity.staticEntity.Empty;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.entity.staticEntity.Rock;
import com.gross.simulation.entity.staticEntity.Tree;
import com.gross.simulation.map.GameMap;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
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

    public void turnActions() {
        Set<Entity> movedEntity = new HashSet<>();
        for (Map.Entry<Coordinate, Entity> arg : gameMap.getGameMap().entrySet()) {
            if (arg.getValue() instanceof Creature && !movedEntity.contains(arg.getValue())) {
                Creature creature = (Creature) arg.getValue();
                movedEntity.add(arg.getValue());
                creature.makeMove(gameMap, arg.getKey().getX(), arg.getKey().getY());
            }
        }

    }

    public void slowSimulation(int ms)
    {
        try {
            Thread.sleep(ms);  // Можно изменить время задержки в миллисекундах
        } catch (InterruptedException e) {
            e.printStackTrace();  // В случае прерывания
        }
    }

    public void renderGameMap() {
        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                System.out.print(gameMap.getGameMap().get(new Coordinate(x, y)).getIcon() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void fillGameMap() {
        Random random = new Random();
        for (int y = 0; y < gameMap.getHeight(); y++)
            for (int x = 0; x < gameMap.getWidth(); x++){
        Entity entity = null;
        if (random.nextInt(80) == 1)
            entity = new Wolf();
        else if (random.nextInt(30) == 1)
            entity = new Cow();
        else if (random.nextInt(30) == 1)
            entity = new Rabbit();
        else if (random.nextInt(30) == 1)
            entity = new Grass();
        else if (random.nextInt(20) == 1)
            entity=new Tree();
        else if (random.nextInt(20) == 1)
            entity=new Rock();
         else entity=new Empty();
                    gameMap.getGameMap().put(new Coordinate(x, y), entity);
    }}

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
