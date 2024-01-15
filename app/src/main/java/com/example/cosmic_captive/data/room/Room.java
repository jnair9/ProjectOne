package com.example.cosmic_captive.data.room;

public abstract class Room {
    private float playerx;
    private float playery;
    private int layout;
    protected int[] enemies;

    public Room(float x, float y, int layout, int[] enemies) {
        this.playerx = x;
        this.playery = y;
        this.layout = layout;
        this.enemies = enemies;
    }
}
