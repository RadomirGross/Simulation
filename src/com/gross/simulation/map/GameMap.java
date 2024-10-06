package com.gross.simulation.map;

import com.gross.simulation.entity.Coordinate;
import com.gross.simulation.entity.Entity;
import com.gross.simulation.entity.creatures.herbivores.Cow;
import com.gross.simulation.entity.creatures.herbivores.Herbivore;
import com.gross.simulation.entity.creatures.herbivores.Horse;
import com.gross.simulation.entity.creatures.herbivores.Rabbit;
import com.gross.simulation.entity.creatures.predators.Bear;
import com.gross.simulation.entity.creatures.predators.Lion;
import com.gross.simulation.entity.creatures.predators.Predator;
import com.gross.simulation.entity.creatures.predators.Wolf;
import com.gross.simulation.entity.staticEntity.*;

import java.util.*;

public class GameMap {

    private final Map<Coordinate, Entity> gameMap;
    private final int width;
    private final int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        gameMap = new HashMap<>(width * height);
    }

    public Coordinate addEntityOnRandomCell(GameMap gameMap, Entity entity) {
        boolean added = false;
        Coordinate randomCoordinate = null;
        while (!added) {
            Random random = new Random();
            int randomX = random.nextInt(gameMap.getWidth());
            int randomY = random.nextInt(gameMap.getHeight());
            randomCoordinate = new Coordinate(randomX, randomY);
            if (!gameMap.hasEntityAt(randomCoordinate)) {
                gameMap.putEntity(randomCoordinate, entity);
                added = true;
            }
        }
        return randomCoordinate;
    }

    public Predator createRandomPredator() {
        Random random = new Random();
        int type = random.nextInt(3);
        if (type == 0)
            return new Wolf();
        else if (type == 1)
            return new Bear();
        else
            return new Lion();
    }
    public Herbivore createRandomHerbivore()
    {
        Random random = new Random();
        int type = random.nextInt(3);
        if (type==0)
            return new Rabbit();
        if (type==1)
            return new Cow();
        return new Horse();
    }
    public StaticEntity createRandomStaticEntity()
    {
        Random random = new Random();
        int type = random.nextInt(2);
        if (type==0)
            return new Rock();
        else
            return new Tree();

    }
    public void deleteEntity(Coordinate coordinate) {
        gameMap.remove(coordinate);
    }

    public Entity getEntity(Coordinate coordinate) {
        return gameMap.get(coordinate);
    }

    public void putEntity(Coordinate coordinate, Entity entity) {
        gameMap.put(coordinate, entity);
    }

    public Set<Map.Entry<Coordinate, Entity>> getAllKeysAndValues() {
        return gameMap.entrySet();
    }

    public Set<Entity> getAllEntities() {
        return new HashSet<>(gameMap.values());
    }

    public Set<Coordinate> getAllKeys() {
        return gameMap.keySet();
    }

    public boolean hasEntityAt(Coordinate coordinate) {
        Entity entity = gameMap.get(coordinate);
        return entity != null && !(entity instanceof Empty);
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public int checkNumberOfEntities(Class<? extends Entity> entityClass)
    {
      Set<Entity> entities= getAllEntities();
        long count = entities.stream()
                .filter(entity -> entityClass.isInstance(entity))
                .count();
        return Integer.parseInt(String.valueOf(count));
    }
}
