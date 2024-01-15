package com.example.cosmic_captive.model;



public class PowerUp {
    private float x;
    private float y;
    private int type;
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;

    public PowerUp(float x, float y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public int getType() {
        return type;
    }

}
