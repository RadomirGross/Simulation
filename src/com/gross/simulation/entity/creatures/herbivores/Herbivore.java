package com.gross.simulation.entity.creatures.herbivores;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.entity.staticEntity.Empty;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.enums.HerbivoreType;
import com.gross.simulation.map.GameMap;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public abstract class Herbivore extends Creature {
    private HerbivoreType type;

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
        Coordinate cellNearGrassWithMinValue=findMinimumNeighborValue(intMap,grass);
        int grassStartValue = intMap[cellNearGrassWithMinValue.getY()][cellNearGrassWithMinValue.getX()];
        Coordinate[] wayToGrass = new Coordinate[grassStartValue + 1];
        wayToGrass[grassStartValue] = cellNearGrassWithMinValue;
        for (int i = grassStartValue-1; i >= 1; i--) {
            wayToGrass[i] = findMinimumNeighborValue(intMap, wayToGrass[i+1]);
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
            Coordinate grass=queue.poll();
            Coordinate current = findMinimumNeighborValue(intMap, grass);
            if (current != null && intMap[current.getY()][current.getX()] < valueSteps) {
                result = grass;
                valueSteps = intMap[current.getY()][current.getX()];
            }
        }
        return result;
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
                if (intMap[newY][newX] < minValue && intMap[newY][newX] > 0) {
                    minValue = intMap[newY][newX];
                    result = new Coordinate(newX, newY);
                }
        }
        return result;
    }

    public Boolean isGrassNearby(Coordinate grass, Coordinate creature) {
        return (Math.abs(grass.getX() - creature.getX()) == 1 && grass.getY() == creature.getY())
                || (Math.abs(grass.getY() - creature.getY()) == 1 && grass.getX() == creature.getX());
    }

    public Coordinate addRandomGrass(GameMap gameMap)
    {
        boolean added=false;
        Coordinate randomCoordinate=null;
        while (!added)
        {
            Random random = new Random();
            int randomX = random.nextInt(gameMap.getWidth());  // Случайное значение X в диапазоне от 0 до ширины карты
            int randomY = random.nextInt(gameMap.getHeight()); // Случайное значение Y в диапазоне от 0 до высоты карты
            randomCoordinate = new Coordinate(randomX, randomY);
            if (gameMap.getGameMap().get(randomCoordinate) instanceof Empty)
            {
                gameMap.getGameMap().put(randomCoordinate, new Grass());
                added=true;
            }
        }
        return randomCoordinate;
    }

    public HerbivoreType getType() {
        return type;
    }

    public void setType(HerbivoreType type) {
        this.type = type;
    }
}
