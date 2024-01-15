package com.example.cosmic_captive.views.Movement;


import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cosmic_captive.model.Player;


public class Down {

    public static boolean moveDown(Player player, Context context, ConstraintLayout layout) {
        float newPosition = player.getPosY();
        newPosition += player.getVelocity();
        if (player.canMove(player.getPosX(), newPosition, context, layout)) {
            player.updatePosition(player.getPosX(), newPosition);
            return true;
        }
        return false;
    }
}
