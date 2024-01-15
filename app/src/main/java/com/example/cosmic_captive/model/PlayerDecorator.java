package com.example.cosmic_captive.model;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class PlayerDecorator implements PlayerInterface {
    protected PlayerInterface decoratedPlayer;
    public PlayerDecorator(PlayerInterface decoratedPlayer) {
        this.decoratedPlayer = decoratedPlayer;
    }

    @Override
    public float getMaxHealth() {
        return decoratedPlayer.getMaxHealth();
    }

    @Override
    public float getHealth() {
        return decoratedPlayer.getHealth();
    }

    @Override
    public float getPosX() {
        return decoratedPlayer.getPosX();
    }

    @Override
    public float getPosY() {
        return decoratedPlayer.getPosY();
    }

    @Override
    public String getName() {
        return decoratedPlayer.getName();
    }

    @Override
    public Weapon getWeapon() {
        return decoratedPlayer.getWeapon();
    }

    @Override
    public void setHealth(float health) {
        decoratedPlayer.setHealth(health);
    }

    @Override
    public void updatePosition(float newX, float newY) {
        decoratedPlayer.updatePosition(newX, newY);
    }

    @Override
    public void setScreenDimensions(int screenWidth, int screenHeight) {
        decoratedPlayer.setScreenDimensions(screenWidth, screenHeight);
    }

    @Override
    public boolean canMove(float newX, float newY, Context context, ConstraintLayout layout) {
        return decoratedPlayer.canMove(newX, newY, context, layout);
    }

    @Override
    public boolean canMove(float newX, float newY) {
        return decoratedPlayer.canMove(newX, newY);
    }
}
