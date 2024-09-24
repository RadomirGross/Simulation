package com.gross.simulation.entity.creatures.herbivores;

import com.gross.simulation.enums.HerbivoreType;
import com.gross.simulation.map.GameMap;

public class Cow extends Herbivore {
    private final String icon="🐮";
        //🐮


    public String getIcon() {
        return icon;
    }

    public Cow() {
       setSpeed(1);
       setHealth(100);
       setType(HerbivoreType.COW);
    }

    @Override
    public void makeMove(GameMap gameMap, int x, int y) {
    }
}
