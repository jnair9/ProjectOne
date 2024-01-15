package com.example.cosmic_captive.collision;

import com.example.cosmic_captive.model.Player;
import android.content.Context;
import android.graphics.RectF;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import androidx.constraintlayout.widget.ConstraintLayout;

public class WallCollision implements Collision {

    private List<View> walls = new ArrayList<>();

    public WallCollision(Context context, ConstraintLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (!child.getClass().toGenericString().contains("cosmic_captive")) {
                String resourceName = context.getResources().getResourceEntryName(child.getId());
                if (resourceName != null && resourceName.startsWith("wall")) {
                    walls.add(child);
                }
            }
        }
    }

    public boolean checkCollision(Player player, float playerx, float playery) {
        RectF playerRect = new RectF(playerx, playery, playerx + player.WIDTH,
                playery + player.HEIGHT);
        for (View v : walls) {
            RectF wall = new RectF(v.getX(), v.getY(), v.getX() + v.getWidth(),
                    v.getY() + v.getHeight());
            if (wall.intersect(playerRect)) {
                return true;
            }
        }
        return false;
    }
}
