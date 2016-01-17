package com.tinygrip.android.data.entity.area.route;

public class PathEntity {
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Path Entity Details *****\n");
        stringBuilder.append("x=" + this.getX() + "\n");
        stringBuilder.append("y=" + this.getY() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
