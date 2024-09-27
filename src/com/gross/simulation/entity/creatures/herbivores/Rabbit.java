package com.gross.simulation.entity.creatures.herbivores;

public class Rabbit extends Herbivore {
    private final String icon = "🐰";


    public Rabbit()
    {
        super(20,2);

    }
    public String getIcon() {
        return icon;
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
