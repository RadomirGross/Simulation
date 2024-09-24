package com.gross.simulation.entity.creatures.herbivores;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.staticEntity.Grass;
import com.gross.simulation.entity.staticEntity.Rock;
import com.gross.simulation.entity.staticEntity.StaticEntity;
import com.gross.simulation.entity.staticEntity.Tree;
import com.gross.simulation.map.GameMap;

import java.util.LinkedList;
import java.util.Queue;

public class Rabbit extends Herbivore {
    private final String icon = "🐰";
    public int health = 20;
    public int speed = 2;

    public String getIcon() {
        return icon;
    }

    @Override
    public void makeMove(GameMap gameMap, int startX,int StartY) {
        int[][] intMap = new int[gameMap.getWidth()][gameMap.getHeight()];
        for (int y = 0; y < gameMap.getWidth(); y++) {
            for (int x = 0; x < gameMap.getHeight(); x++) {
                if (gameMap.getGameMap().get(new Coordinate(x, y)) == this)
                    intMap[y][x] = 0;
                else if (gameMap.getGameMap().get(new Coordinate(x, y)) instanceof Grass)
                    intMap[y][x] = -2;
                else if (gameMap.getGameMap().get(new Coordinate(x, y)) instanceof Tree)
                    intMap[y][x] = -3;
                else  intMap[y][x] = -4;


            }
        }

      printMap(intMap);
  calculateDistance(intMap);
printMap(intMap);

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



    public void printMap(int [][] gameMap)
    {
        for (int y = 0; y < gameMap.length; y++) {
            for (int x = 0; x < gameMap[0].length; x++) {
                System.out.print(gameMap[y][x] + " ");
            }
            System.out.println();
        }
    }
}
