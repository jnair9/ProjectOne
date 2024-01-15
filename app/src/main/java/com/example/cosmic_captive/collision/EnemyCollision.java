package com.example.cosmic_captive.collision;

import android.graphics.RectF;


import com.example.cosmic_captive.model.Player;
import com.example.cosmic_captive.viewmodels.EnemyView;
import com.example.cosmic_captive.viewmodels.WeaponView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnemyCollision {

    private List<EnemyView> enemies;
    private List<Integer> collided;

    public EnemyCollision(List<EnemyView> enemies) {
        this.enemies = enemies;
        collided = new ArrayList<Integer>();
    }

    public boolean checkCollision(Player player, float playerx, float playery) {
        RectF playerRect = new RectF(playerx, playery, playerx + player.WIDTH,
                playery + player.HEIGHT);


        for (int i = 0; i < enemies.size(); i++) {
            EnemyView v = enemies.get(i);
            RectF wall = new RectF(v.getX(), v.getY(), v.getX() + v.getenemyWidth(),
                    v.getY() + v.getenemyHeight());

            if (wall.intersect(playerRect)) {
                collided.add(i);
            }
        }
        return collided.size() != 0;
    }
    public synchronized boolean checkCollision(WeaponView wv) {
        RectF playerRect = new RectF(wv.getregx(), wv.getregy(), wv.getregx() + wv.WIDTH,
                wv.getregy() + wv.HEIGHT);

        for (int i = 0; i < enemies.size(); i++) {
            EnemyView v = enemies.get(i);
            RectF wall = new RectF(v.getX(), v.getY(), v.getX() + v.getenemyWidth(),
                    v.getY() + v.getenemyHeight());

            if (wall.intersect(playerRect)) {
                collided.add(i);
            }
        }
        return collided.size() != 0;
    }
    public List<Integer> getCollided() {
        Collections.sort(collided);
        Collections.reverse(collided);
        return collided;
    }

}
