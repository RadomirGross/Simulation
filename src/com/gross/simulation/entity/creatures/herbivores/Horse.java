package com.gross.simulation.entity.creatures.herbivores;

import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.enums.HerbivoreType;

public class Horse extends Herbivore {
    private final String icon="🐴";
    public String getIcon() {
        return icon;
    }
    public Horse() {
        setType(HerbivoreType.HORSE);
        setSpeed(10);
        setHealth(100);
    }

    @Override
    public void makeMove() {

    }
}
