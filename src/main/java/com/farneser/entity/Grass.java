package com.farneser.entity;

import com.farneser.Coordinates;

public class Grass extends Entity{
    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String toString() {
        return super.toString() + "\n\ttype: grass";
    }
}
