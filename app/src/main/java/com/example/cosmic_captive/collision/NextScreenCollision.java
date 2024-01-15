package com.example.cosmic_captive.collision;

import android.content.Context;
import android.graphics.RectF;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cosmic_captive.R;
import com.example.cosmic_captive.model.Player;



public class NextScreenCollision implements Collision {

    private RectF exit;

    public NextScreenCollision(Context context, ConstraintLayout layout) {
        if (layout.getId() == (int) R.id.gamelayout) {
            exit = new RectF(500, 100, 600, 300);
        } else if (layout.getId() == (int) R.id.gameroom1) {
            exit = new RectF(500, 100, 600, 300);
        } else if (layout.getId() == (int) R.id.gameroom2) {
            exit = new RectF(500, 100, 600, 300);
        } else if (layout.getId() == (int) R.id.gameroom3) {
            exit = new RectF(500, 100, 600, 300);
        }
    }

    public boolean checkCollision(Player player, float playerx, float playery) {
        RectF playerRect = new RectF(playerx, playery, playerx + player.WIDTH,
                playery + player.HEIGHT);
        return playerRect.intersect(exit);
    }
}
