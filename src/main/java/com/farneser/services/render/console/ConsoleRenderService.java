package com.farneser.services.render.console;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Entity;
import com.farneser.services.render.IRender;

import java.util.Random;

public class ConsoleRenderService implements IRender {
    public ConsoleRenderService() {
        super();
    }

    private void cleanConsole() {
        final var cleanString = "\033[H\033[2J";
        System.out.print(cleanString);
        System.out.flush();
    }

    @Override
    public void render(Map map) {
        final String combClear = "\033[H\033[2J";
        System.out.print(combClear);
        System.out.flush();

        for (var y = 0; y < map.height; y++) {
            for (var x = 0; x < map.width; x++) {
                var coordinates = new Coordinates(x, y);
                var entity = map.getEntityAt(coordinates);
                System.out.print(selectUnicodeSpriteForEntity(entity) + "\t");
            }

            System.out.println();
        }

        System.out.println();
    }

    private String selectUnicodeSpriteForEntity(Entity entity) {
        if (entity == null) return "..";

        var random = new Random();

        return switch (entity.getClass().getSimpleName()) {
            case "Grass" -> ConsoleSprites.GRASS[random.nextInt(ConsoleSprites.GRASS.length)];
            case "Rock" -> ConsoleSprites.ROCK[random.nextInt(ConsoleSprites.ROCK.length)];
            case "Tree" -> ConsoleSprites.TREE[random.nextInt(ConsoleSprites.TREE.length)];
            case "Herbivore" -> ConsoleSprites.HERBIVORE[random.nextInt(ConsoleSprites.HERBIVORE.length)];
            case "Predator" -> ConsoleSprites.Predator[random.nextInt(ConsoleSprites.Predator.length)];
            default -> "..";
        };
    }
}
