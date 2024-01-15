package com.example.cosmic_captive.data;

import com.example.cosmic_captive.model.Weapon;

public class Range extends Weapon {
    private double firerate;


    public Range(String name, float damage, double firerate) {
        super(name, damage);
        this.firerate = firerate;
    }
}
