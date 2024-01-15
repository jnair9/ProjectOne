package com.example.cosmic_captive.views.Movement;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cosmic_captive.model.Player;

public class Right {
    public static boolean moveRight(Player player, Context context, ConstraintLayout layout) {
        float newPosition = player.getPosX();
        newPosition += player.getVelocity();
        if (player.canMove(newPosition, player.getPosY(), context, layout)) {
            player.updatePosition(newPosition, player.getPosY());
            return true;
        }
        return false;
    }
}
