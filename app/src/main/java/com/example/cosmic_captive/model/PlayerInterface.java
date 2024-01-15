package com.example.cosmic_captive.model;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

public interface PlayerInterface {
    float getMaxHealth();
    float getHealth();
    float getPosX();
    float getPosY();
    String getName();
    Weapon getWeapon();


    void setHealth(float health);


    void updatePosition(float newX, float newY);
    void setScreenDimensions(int screenWidth, int screenHeight);

   
    boolean canMove(float newX, float newY, Context context, ConstraintLayout layout);
    boolean canMove(float newX, float newY);

}
