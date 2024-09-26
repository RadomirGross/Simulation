package com.gross.simulation.entity.creatures.herbivores;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.staticEntity.Empty;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.map.GameMap;

import java.util.Random;


public class Rabbit extends Herbivore {
    private final String icon = "🐰";
    public int health = 20;
    public int speed = 2;

    public String getIcon() {
        return icon;
    }

    @Override
    public void makeMove(GameMap gameMap, int startX, int startY) {
        int[][] BFSGrid = buildBFSGrid(gameMap);

//        printMap(BFSGrid);
        calculateDistance(BFSGrid);
//        printMap(BFSGrid);
        Coordinate closestGrass = findClosestGrass(BFSGrid);
        if (isGrassNearby(closestGrass, new Coordinate(startX, startY))) {
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
        } else {
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
}
