package com.farneser;

import java.util.Objects;

public class Coordinates {

    private int _x;
    private int _y;

    public Coordinates(int x, int y) {
        _x = x;
        _y = y;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return _x == that._x && _y == that._y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, _y);
    }
}
