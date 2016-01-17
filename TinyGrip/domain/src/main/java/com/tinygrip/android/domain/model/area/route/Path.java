package com.tinygrip.android.domain.model.area.route;

public class Path {
    private final float x;
    private final float y;

    public Path(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Path Details *****\n");
        stringBuilder.append("x=" + this.getX() + "\n");
        stringBuilder.append("y=" + this.getY() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
