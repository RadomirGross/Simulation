package com.gross.simulation.entity.creatures.herbivores;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.entity.staticEntity.Empty;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.map.GameMap;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public abstract class Herbivore extends Creature {
    public Herbivore(int health, int speed) {
        super(health, speed);
    }

    public void makeMove(GameMap gameMap, int startX, int startY) {
        Coordinate startPosition = new Coordinate(startX, startY);
        // System.out.println(gameMap.getGameMap().get(startPosition).getClass());
        int[][] BFSGrid = buildBFSGrid(gameMap);
        // printMap(BFSGrid);
        calculateDistance(BFSGrid);
        // printMap(BFSGrid);
        Coordinate closestGrass = findClosestGrass(BFSGrid);
        // Grass grass=((Grass)gameMap.getGameMap().get(closestGrass));
        // if (grass!=null)
        // System.out.println(grass.getHealth());
        //  else System.out.println("grass=null");
        if (isGrassNearby(closestGrass, startPosition))
            reduceGrassHealthRemoveIfDepletedAndAddNewGrassIfNoneLeft(gameMap, BFSGrid, closestGrass);
        else {
            Coordinate[] wayToGrass = findWayToGrass(BFSGrid, findClosestGrass(BFSGrid));
            moveHerbivore(gameMap, wayToGrass, startX, startY, speed);
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

    public void moveHerbivore(GameMap gameMap, Coordinate[] wayToGrass, int startX, int startY, int speed) {
        if (wayToGrass == null)
            return;
        if (wayToGrass.length - 1 < speed)
            speed = wayToGrass.length - 1;
        gameMap.getGameMap().put(new Coordinate(startX, startY), new Empty());
        gameMap.getGameMap().put(wayToGrass[speed], this);
    }

    public Coordinate[] findWayToGrass(int[][] intMap, Coordinate grass) {
        if (grass == null)
            return null;
        Coordinate cellNearGrassWithMinValue = findMinimumNeighborValue(intMap, grass);
        int grassStartValue = intMap[cellNearGrassWithMinValue.getY()][cellNearGrassWithMinValue.getX()];
        Coordinate[] wayToGrass = new Coordinate[grassStartValue + 1];
        wayToGrass[grassStartValue] = cellNearGrassWithMinValue;
        for (int i = grassStartValue - 1; i >= 1; i--) {
            wayToGrass[i] = findMinimumNeighborValue(intMap, wayToGrass[i + 1]);
        }
        return wayToGrass;
    }

    public Coordinate findClosestGrass(int[][] intMap) {
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate result = null;
        int valueSteps = Integer.MAX_VALUE;
        for (int y = 0; y < intMap.length; y++)
            for (int x = 0; x < intMap[0].length; x++) {
                int z = intMap[y][x];
                if (z == -2)
                    queue.add(new Coordinate(x, y));
            }
        while (!queue.isEmpty()) {
            Coordinate grass = queue.poll();
            Coordinate current = findMinimumNeighborValue(intMap, grass);
            if (current != null && intMap[current.getY()][current.getX()] < valueSteps) {
                result = grass;
                valueSteps = intMap[current.getY()][current.getX()];
            }
        }
        return result;
    }


    public Boolean isGrassNearby(Coordinate grass, Coordinate creature) {
        if (grass == null)
            return false;
        return (Math.abs(grass.getX() - creature.getX()) == 1 && grass.getY() == creature.getY())
                || (Math.abs(grass.getY() - creature.getY()) == 1 && grass.getX() == creature.getX());

    }

    public Coordinate addRandomGrass(GameMap gameMap) {
        boolean added = false;
        Coordinate randomCoordinate = null;
        while (!added) {
            Random random = new Random();
            int randomX = random.nextInt(gameMap.getWidth());
            int randomY = random.nextInt(gameMap.getHeight());
            randomCoordinate = new Coordinate(randomX, randomY);
            if (gameMap.getGameMap().get(randomCoordinate) instanceof Empty) {
                gameMap.getGameMap().put(randomCoordinate, new Grass());
                added = true;
            }
        }
        return randomCoordinate;
    }


    public void reduceGrassHealthRemoveIfDepletedAndAddNewGrassIfNoneLeft(GameMap gameMap, int[][] BFSGrid, Coordinate closestGrass) {
        Entity entity = gameMap.getGameMap().get(closestGrass);
        Grass grass = (Grass) entity;
        grass.setHealth(grass.getHealth() - 5);
        if (grass.getHealth() <= 0) {
            gameMap.getGameMap().put(closestGrass, new Empty());
            BFSGrid[closestGrass.getY()][closestGrass.getX()] = -4;
            if (findClosestGrass(BFSGrid) == null) {
                Coordinate newGrass = addRandomGrass(gameMap);
                BFSGrid[newGrass.getY()][newGrass.getX()] = -4;
            }
        }
    }


}
