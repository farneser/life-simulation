package com.farneser.services.render.console;

import com.farneser.Coordinates;
import com.farneser.Map;
import com.farneser.entity.Entity;
import com.farneser.services.render.RenderService;

public class ConsoleRenderService extends RenderService {
    public ConsoleRenderService(Map map) {
        super(map);
    }

    @Override
    public void render(Map map) {
        for (var x = 0; x < map.width; x++) {
            for (var y = 0; y < map.width; y++) {
                var coordinates = new Coordinates(x, y);
                var entity = map.getEntityAt(coordinates);
                var renderText = "";

                if (entity == null){
                    renderText = "... ";
                }else{
                    renderText = "ent ";
                }

                System.out.print(renderText);
            }

            System.out.println();
        }
    }
}
