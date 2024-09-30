package com.gross.simulation.map;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.Creature;
import com.gross.simulation.entity.creatures.herbivores.Herbivore;
import com.gross.simulation.entity.creatures.herbivores.Rabbit;
import com.gross.simulation.entity.staticEntity.Empty;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameMap {

        private Map<Coordinate, Entity> gameMap;
        private int width;
        private int height;

        public GameMap(int width, int height) {
            this.width = width;
            this.height = height;
            gameMap = new HashMap<>(width * height);
        }




        public Map<Coordinate, Entity> getGameMap() {
            return gameMap;
        }

        public void setGameMap(Map<Coordinate, Entity> gameMap) {
            this.gameMap = gameMap;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        /*public void fillGameMap() {
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                    this.gameMap.put(new Coordinate(x, y), new Empty());
        }*/


    }
