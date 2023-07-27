package com.farneser.entity;

import com.farneser.Coordinates;

public class Tree extends Entity {
    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String toString() {
        return super.toString() + "\n\ttype: tree";
    }
}
