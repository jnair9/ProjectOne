package com.example.cosmic_captive.model;


public abstract class Weapon {
    private String name;
    private float damage;

    public Weapon(String name, float damage) {
        this.name = name;
        this.damage = damage;
    }


    public float getDamage() {
        return damage;
    }
    public String getName() {
        return name;
    }
}
