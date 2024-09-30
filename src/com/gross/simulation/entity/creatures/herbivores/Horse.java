package com.gross.simulation.entity.creatures.herbivores;
public class Horse extends Herbivore {
    private final String icon = "🐴";

    public String getIcon() {
        return icon;
    }

    public Horse() {
        super(50, 4);
    }


}
