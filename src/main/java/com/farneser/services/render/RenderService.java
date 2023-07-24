package com.farneser.services.render;

import com.farneser.Map;

public abstract class RenderService implements IRender {
    private Map _map;

    public RenderService() {}

    public void setMap(Map map) {
        _map = map;
    }

    public void render() {
        render(_map);
    }
}
