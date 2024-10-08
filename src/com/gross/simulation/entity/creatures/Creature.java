package com.gross.simulation.entity.creatures;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.herbivores.Herbivore;
import com.gross.simulation.entity.creatures.predators.Predator;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.entity.staticEntity.StaticEntity;
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

    public Coordinate findMinimumNeighborValue(int[][] intMap, Coordinate grass) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minValue = Integer.MAX_VALUE;
        int width = intMap.length;
        int height = intMap[0].length;
        Coordinate result = null;
        for (int[] direction : directions) {
            int newX = grass.getX() + direction[0];
            int newY = grass.getY() + direction[1];
            if (newX >= 0 && newX < width && newY >= 0 && newY < height)
                if (intMap[newY][newX] < minValue && intMap[newY][newX] >= 0) {
                    minValue = intMap[newY][newX];
                    result = new Coordinate(newX, newY);
                }
        }
        return result;
    }

}
