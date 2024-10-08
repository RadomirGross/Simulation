package com.gross.simulation;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.entity.creatures.herbivores.Herbivore;
import com.gross.simulation.entity.creatures.predators.Predator;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.entity.staticEntity.StaticEntity;
import com.gross.simulation.map.GameMap;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    GameMap gameMap;

    public BFS(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public int[][] buildBFSGrid (GameMap gameMap, Creature creature) {
        int[][] intMap = new int[gameMap.getWidth()][gameMap.getHeight()];
        for (int y = 0; y < gameMap.getWidth(); y++) {
            for (int x = 0; x < gameMap.getHeight(); x++) {
                if (gameMap.getEntity(new Coordinate(x, y)) == creature)
                    intMap[y][x] = Constants.CREATURE;
                else if (gameMap.getEntity(new Coordinate(x, y)) instanceof Grass)
                    intMap[y][x] = Constants.GRASS;
                else if (gameMap.getEntity(new Coordinate(x, y)) instanceof StaticEntity)
                    intMap[y][x] = Constants.STATIC_ENTITY;
                else if (gameMap.getEntity(new Coordinate(x, y)) instanceof Herbivore)
                    intMap[y][x] = Constants.HERBIVORE;
                else if (gameMap.getEntity(new Coordinate(x, y)) instanceof Predator)
                    intMap[y][x] = Constants.PREDATOR;
                else intMap[y][x] = Constants.EMPTY;
            }
        }
        return intMap;
    }
    public void calculateDistance (int [][] intMap) {
        int width = intMap.length;
        int height = intMap[0].length;

        Queue<Coordinate> queue = new LinkedList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (intMap[y][x] == Constants.CREATURE)
                    queue.add(new Coordinate(x, y));
            }
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            int x = current.getX();
            int y = current.getY();
            int currentDistance = intMap[y][x];

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                    if (intMap[newY][newX] == Constants.EMPTY) {
                        intMap[newY][newX] = currentDistance + 1;
                        queue.add(new Coordinate(newX, newY));
                    }
                }
            }
        }

    }}

