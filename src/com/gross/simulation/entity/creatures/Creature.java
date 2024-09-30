package com.gross.simulation.entity.creatures;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.herbivores.Herbivore;
import com.gross.simulation.entity.creatures.predators.Predator;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.entity.staticEntity.Rock;
import com.gross.simulation.entity.staticEntity.StaticEntity;
import com.gross.simulation.entity.staticEntity.Tree;
import com.gross.simulation.map.GameMap;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Creature extends Entity {
    public int health;
    public int speed;
    public int power;
public Creature(int health, int speed)
{
    this.health = health;
    this.speed = speed;
}

public Creature(int health,int speed, int power)
{
    this.health = health;
    this.speed = speed;
    this.power = power;
}
    public abstract void makeMove(GameMap gameMap,int startX,int startY);

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int[][] buildBFSGrid (GameMap gameMap) {
        int[][] intMap = new int[gameMap.getWidth()][gameMap.getHeight()];
        for (int y = 0; y < gameMap.getWidth(); y++) {
            for (int x = 0; x < gameMap.getHeight(); x++) {
                if (gameMap.getGameMap().get(new Coordinate(x, y)) == this)
                    intMap[y][x] = 0;
                else if (gameMap.getGameMap().get(new Coordinate(x, y)) instanceof Grass)
                    intMap[y][x] = -2;
                else if (gameMap.getGameMap().get(new Coordinate(x, y)) instanceof StaticEntity)
                    intMap[y][x] = -3;
                else if (gameMap.getGameMap().get(new Coordinate(x, y)) instanceof Herbivore)
                    intMap[y][x] = -1;
                else if (gameMap.getGameMap().get(new Coordinate(x, y)) instanceof Predator)
                    intMap[y][x] = -5;
                else intMap[y][x] = -4;
            }
        }
        return intMap;
    }
    public void calculateDistance (int [][] intMap)
    {
        int width = intMap.length;
        int height = intMap[0].length;

        Queue<Coordinate> queue = new LinkedList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (intMap[y][x] == 0)
                    queue.add(new Coordinate(x, y));
            }
        }

        int [][] directions= {{0,1},{0,-1},{1,0},{-1,0}};

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            int x=current.getX();
            int y=current.getY();
            int currentDistance = intMap[y][x];

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];


                if (newX >=0 && newX < width && newY >=0 && newY < height)
                {
                    if (intMap[newY][newX] == -4)
                    { intMap[newY][newX]=currentDistance+1;
                        queue.add(new Coordinate(newX, newY));}
                }
            }
        }

    }
    public Coordinate findMinimumNeighborValue(int[][] intMap, Coordinate grass) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minValue = Integer.MAX_VALUE;
        int width = intMap.length;
        int height = intMap[0].length;
        Coordinate result = null;
        for (int[] direction : directions) {
            int newX = grass.getX() + direction[1];
            int newY = grass.getY() + direction[0];
            if (newX >= 0 && newX < width && newY >= 0 && newY < height)
                if (intMap[newY][newX] < minValue && intMap[newY][newX] >= 0) {
                    minValue = intMap[newY][newX];
                    result = new Coordinate(newX, newY);
                }
        }
        return result;
    }
}
