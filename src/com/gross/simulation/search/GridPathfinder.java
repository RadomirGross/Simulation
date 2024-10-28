package com.gross.simulation.search;

import com.gross.simulation.Constants;
import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.entity.creatures.predators.Predator;

import java.util.LinkedList;
import java.util.Queue;

public class GridPathfinder {
int[][] grid;

public GridPathfinder(int[][] grid) {
    this.grid = grid;
}

    public Coordinate findMinimumNeighborValue(Coordinate cell) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minValue = Integer.MAX_VALUE;
        int width = grid.length;
        int height = grid[0].length;
        Coordinate result = null;
        for (int[] direction : directions) {
            int newX = cell.getX() + direction[0];
            int newY = cell.getY() + direction[1];
            if (newX >= 0 && newX < width && newY >= 0 && newY < height)
                if (grid[newY][newX] < minValue && grid[newY][newX] >= 0) {
                    minValue = grid[newY][newX];
                    result = new Coordinate(newX, newY);
                }
        }
        return result;
    }

    public Coordinate findClosestFood(Creature creature) {
        int food=Constants.GRASS;
        if (creature instanceof Predator)
            food= Constants.HERBIVORE;
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate result = null;
        int valueSteps = Integer.MAX_VALUE;
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++) {
                int z = grid[y][x];
                if (z == food)
                    queue.add(new Coordinate(x, y));
            }
        while (!queue.isEmpty()) {
            Coordinate grass = queue.poll();
            Coordinate current = findMinimumNeighborValue( grass);
            if (current != null && grid[current.getY()][current.getX()] < valueSteps) {
                result = grass;
                valueSteps = grid[current.getY()][current.getX()];
            }
        }
        return result;
    }
}
