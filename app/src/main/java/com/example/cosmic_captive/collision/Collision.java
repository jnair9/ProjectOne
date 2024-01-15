package com.example.cosmic_captive.collision;

import com.example.cosmic_captive.model.Player;

public interface Collision {
    //public int screen;
    boolean checkCollision(Player player,  float playerx, float playery);

}
