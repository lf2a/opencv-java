package com.github.lf2a;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean found() {
        if (x == 0 && y == 0)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Position{x=%s, y=%s}", this.x, this.y);
    }
}
