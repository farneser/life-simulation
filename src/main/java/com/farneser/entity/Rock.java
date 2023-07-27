package com.farneser.entity;

import com.farneser.Coordinates;

public class Rock extends Entity {
    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String toString() {
        return super.toString() + "\n\ttype: rock";
    }
}
