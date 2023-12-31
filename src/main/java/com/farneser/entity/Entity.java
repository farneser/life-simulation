package com.farneser.entity;

import com.farneser.Coordinates;

public abstract class Entity {

    protected Coordinates _coordinates;

    public Entity(Coordinates coordinates) {
        _coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return _coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        _coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Entity\n\tcoordinates: " + getCoordinates();
    }
}
