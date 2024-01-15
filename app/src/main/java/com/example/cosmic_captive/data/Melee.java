package com.example.cosmic_captive.data;

import com.example.cosmic_captive.model.Weapon;

public class Melee extends Weapon {

    private double range;
    public Melee(String name, float damage, double range) {
        super(name, damage);
        this.range = range;
    }
}
