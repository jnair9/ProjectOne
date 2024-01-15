package com.example.cosmic_captive.model;

public class PowerUpHealth extends PlayerDecorator {
    private float speedMultiplier;
    private float healthMultiplier;
    public PowerUpHealth(PlayerInterface decoratorPlayer, float healthMultiplier) {
        super(decoratorPlayer);
        this.healthMultiplier = healthMultiplier;
    }

}
