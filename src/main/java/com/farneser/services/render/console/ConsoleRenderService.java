package com.farneser.services.render.console;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Entity;
import com.farneser.services.render.IRender;

public class ConsoleRenderService implements IRender {
    public ConsoleRenderService() {
        super();
    }

    @Override
    public void render(Map map) {
        for (var y = 0; y < map.height; y++) {
            for (var x = 0; x < map.width; x++) {
                var coordinates = new Coordinates(x, y);
                var entity = map.getEntityAt(coordinates);
                System.out.print(selectUnicodeSpriteForEntity(entity) + " ");
            }

            System.out.println();
        }
    }

    private String selectUnicodeSpriteForEntity(Entity entity) {
        if (entity == null) return "..";

        return switch (entity.getClass().getSimpleName()) {
            case "Grass" -> "\uD83C\uDF3F";
            case "Rock" -> "\uD83E\uDEA8";
            case "Tree" -> "\uD83C\uDF33";
            case "Herbivore" -> "\uD83D\uDC14";
            case "Predator" -> "\uD83D\uDC3A";
            default -> "..";
        };
    }
}
