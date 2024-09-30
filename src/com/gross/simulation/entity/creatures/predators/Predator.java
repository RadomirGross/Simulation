package com.gross.simulation.entity.creatures.predators;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.entity.creatures.herbivores.Cow;
import com.gross.simulation.entity.creatures.herbivores.Herbivore;
import com.gross.simulation.entity.creatures.herbivores.Horse;
import com.gross.simulation.entity.creatures.herbivores.Rabbit;
import com.gross.simulation.entity.staticEntity.Empty;
import com.gross.simulation.map.GameMap;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public abstract class Predator extends Creature {
    public Predator(int health,int speed,int power)
    {
        super(health,speed,power);
    }

    public void makeMove(GameMap gameMap, int startX, int startY) {
        Coordinate startPosition = new Coordinate(startX, startY);
        // System.out.println(gameMap.getGameMap().get(startPosition).getClass());
        int[][] BFSGrid = buildBFSGrid(gameMap);
         //printMap(BFSGrid);
        calculateDistance(BFSGrid);
        // printMap(BFSGrid);
        Coordinate closestHerbivore = findClosestHerbivore(BFSGrid);
        // Grass grass=((Grass)gameMap.getGameMap().get(closestGrass));
        // if (grass!=null)
        // System.out.println(grass.getHealth());
        //  else System.out.println("grass=null");
        if (isHerbivoreNearby(closestHerbivore, startPosition))
            reduceHerbivoreHealthRemoveIfDepletedAndAddNewHerbivoreIfNoneLeft(gameMap, BFSGrid, closestHerbivore);
        else {
            Coordinate[] wayToGrass = findWayToHerbivore(BFSGrid, findClosestHerbivore(BFSGrid));
            movePredator(gameMap, wayToGrass, startX, startY, speed);
        }
    }

    public void printMap(int[][] gameMap) {

        for (int y = 0; y < gameMap.length; y++) {
            for (int x = 0; x < gameMap[0].length; x++) {
                System.out.print(gameMap[y][x] + " ");
            }
            System.out.println();
        }
    }

    public void movePredator(GameMap gameMap, Coordinate[] wayToGrass, int startX, int startY, int speed) {
        if (wayToGrass == null)
            return;
        if (wayToGrass.length - 1 < speed)
            speed = wayToGrass.length - 1;
        gameMap.getGameMap().put(new Coordinate(startX, startY), new Empty());
        gameMap.getGameMap().put(wayToGrass[speed], this);
    }

    public Coordinate[] findWayToHerbivore(int[][] intMap, Coordinate herbivore) {
        if (herbivore == null)
            return null;
        Coordinate cellNearHerbivoreWithMinValue = findMinimumNeighborValue(intMap, herbivore);
        int predatorStartValue = intMap[cellNearHerbivoreWithMinValue.getY()][cellNearHerbivoreWithMinValue.getX()];
        Coordinate[] wayToGrass = new Coordinate[predatorStartValue + 1];
        wayToGrass[predatorStartValue] = cellNearHerbivoreWithMinValue;
        for (int i = predatorStartValue - 1; i >= 1; i--) {
            wayToGrass[i] = findMinimumNeighborValue(intMap, wayToGrass[i + 1]);
        }
        return wayToGrass;
    }

    public Coordinate findClosestHerbivore(int[][] intMap) {
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate result = null;
        int valueSteps = Integer.MAX_VALUE;
        for (int y = 0; y < intMap.length; y++)
            for (int x = 0; x < intMap[0].length; x++) {
                int z = intMap[y][x];
                if (z == -1)
                    queue.add(new Coordinate(x, y));
            }
        while (!queue.isEmpty()) {
            Coordinate herbivore = queue.poll();
            Coordinate current = findMinimumNeighborValue(intMap, herbivore);
            if (current != null && intMap[current.getY()][current.getX()] < valueSteps) {
                result = herbivore;
                valueSteps = intMap[current.getY()][current.getX()];
            }
        }
        return result;
    }




    public Boolean isHerbivoreNearby(Coordinate herbivore, Coordinate creature) {
        if (herbivore == null)
            return false;
        return (Math.abs(herbivore.getX() - creature.getX()) == 1 && herbivore.getY() == creature.getY())
                || (Math.abs(herbivore.getY() - creature.getY()) == 1 && herbivore.getX() == creature.getX());

    }

    public Coordinate addRandomHerbivore(GameMap gameMap) {
        boolean added = false;
        Random random = new Random();
        Coordinate randomCoordinate = null;
        while (!added) {
            int randomX = random.nextInt(gameMap.getWidth());
            int randomY = random.nextInt(gameMap.getHeight());
            randomCoordinate = new Coordinate(randomX, randomY);
            if (gameMap.getGameMap().get(randomCoordinate) instanceof Empty) {
                gameMap.getGameMap().put(randomCoordinate, createRandomHerbivore());
                added = true;
            }
        }
        return randomCoordinate;
    }

public Herbivore createRandomHerbivore()
{
    Random random = new Random();
    int type = random.nextInt(3);
    if (type==0)
        return new Rabbit();
    if (type==1)
        return new Cow();
    return new Horse();
}
    public void reduceHerbivoreHealthRemoveIfDepletedAndAddNewHerbivoreIfNoneLeft(GameMap gameMap, int[][] BFSGrid, Coordinate closestHerbivore) {
        Entity entity = gameMap.getGameMap().get(closestHerbivore);
        Herbivore herbivore = (Herbivore) entity;
        int power =this.power;
        herbivore.setHealth(herbivore.getHealth() - power);
        if (herbivore.getHealth() <= 0) {
            gameMap.getGameMap().put(closestHerbivore, new Empty());
            BFSGrid[closestHerbivore.getY()][closestHerbivore.getX()] = -4;
            if (findClosestHerbivore(BFSGrid) == null) {
                Coordinate newHerbivore = addRandomHerbivore(gameMap);
                BFSGrid[newHerbivore.getY()][newHerbivore.getX()] = -4;
            }
        }


    }}
