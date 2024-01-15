package com.example.cosmic_captive.model;

public class PowerUpSpeed extends PlayerDecorator {
    private float speedMultiplier;
    public PowerUpSpeed(PlayerInterface decoratorPlayer, float speedMultiplier) {
        super(decoratorPlayer);
        this.speedMultiplier = speedMultiplier;
    }
    @Override
    public void updatePosition(float newX, float newY) {

        float deltaX = newX - decoratedPlayer.getPosX();
        float deltaY = newY - decoratedPlayer.getPosY();

        float boostedX = decoratedPlayer.getPosX() + (deltaX * speedMultiplier);
        float boostedY = decoratedPlayer.getPosY() + (deltaY * speedMultiplier);

        if (decoratedPlayer.canMove(boostedX, boostedY)) {
            decoratedPlayer.updatePosition(boostedX, boostedY);
        }
    }
    public float bonusSpeed() {
        return 0;
    }



}
