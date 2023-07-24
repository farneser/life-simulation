package com.farneser.services.render;

import com.farneser.Map;

public abstract class RenderService implements IRender {
    private final Map _map;

    public RenderService(Map map) {
        _map = map;
    }

    public void render() {
        render(_map);
    }
}
