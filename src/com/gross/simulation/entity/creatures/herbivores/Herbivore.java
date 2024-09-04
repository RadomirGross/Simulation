package com.gross.simulation.entity.creatures.herbivores;

import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.enums.HerbivoreType;

public abstract class Herbivore extends Creature {
private HerbivoreType type;



    public HerbivoreType getType() {
        return type;
    }

    public void setType(HerbivoreType type) {
        this.type = type;
    }
}
