package com.farneser.entity;

import com.farneser.Coordinates;

public class Grass extends Entity implements IDevoured {
    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String toString() {
        return super.toString() + "\n\ttype: grass";
    }

    @Override
    public int getFoodFromDamage(int damage) {
        return 15 + (damage % 10);
    }

    @Override
    public int getHealth() {
        return 0;
    }
}
