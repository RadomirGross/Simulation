package com.gross.simulation.entity.creatures.herbivores;
import com.gross.simulation.map.GameMap;

public class Horse extends Herbivore {
    private final String icon="🐴";
    public String getIcon() {
        return icon;
    }
    public Horse() {

     super(100,5);
    }



}
