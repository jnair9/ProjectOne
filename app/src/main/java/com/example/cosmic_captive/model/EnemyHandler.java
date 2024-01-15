package com.example.cosmic_captive.model;

import android.content.Context;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cosmic_captive.collision.EnemyCollision;
import com.example.cosmic_captive.data.enemy.Enemies;
import com.example.cosmic_captive.data.enemy.EnemyFactory;
import com.example.cosmic_captive.viewmodels.EnemyView;
import com.example.cosmic_captive.viewmodels.WeaponView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EnemyHandler {
    private List<Enemies> enemies;
    private List<EnemyView> enemyViews;
    private Player player;
    private int screenHeight;
    private int screenWidth;
    private int difficulty;
    private volatile boolean lock;
    private ConstraintLayout gamelayout;

    public EnemyHandler(Context context, int currentroom, int screenWidth, int screenHeight,
                        int difficulty, ConstraintLayout gamelayout) {
        player = Player.getPlayer("Should never be seen", 0, 0);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.difficulty = difficulty;
        this.enemies = new ArrayList<>();
        this.enemyViews = new ArrayList<>();
        this.gamelayout = gamelayout;
        lock = false;

        // Add enemies based on the current room
        switch (currentroom) {
        case 0:
            addEnemies(context, 1, 2);
            break;
        case 1:
            addEnemies(context, 1, 2);
            addEnemies(context, 2, 1);
            break;
        case 2:
            addEnemies(context, 2, 3);
            addEnemies(context, 3, 2);
            break;
        case 3:
            addEnemies(context, 2, 2);
            addEnemies(context, 3, 2);
            addEnemies(context, 4, 3);
            break;
        default:
            break;
        }

        Random random = new Random();

        float spawnAreaWidth = screenWidth / 3;
        float spawnAreaHeight = screenHeight / 4;
        float spawnAreaX = (screenWidth - spawnAreaWidth) / 2;
        float spawnAreaY = 0;
        for (int i = 0; i < enemies.size(); i++) {
            Enemies enemy = enemies.get(i);
            EnemyView enemyView = enemyViews.get(i);
            float enemyX = spawnAreaX + random.nextFloat() * spawnAreaWidth;
            float enemyY = spawnAreaY + random.nextFloat() * spawnAreaHeight;

            enemyX = Math.max(enemyX, spawnAreaX);
            enemyX = Math.min(enemyX, spawnAreaX + spawnAreaWidth - Enemies.WIDTH);
            enemyY = Math.max(enemyY, spawnAreaY);
            enemyY = Math.min(enemyY, spawnAreaY + spawnAreaHeight - Enemies.HEIGHT);

            enemy.updatePosition(enemyX, enemyY);
            enemyView.updatePosition(enemy);
        }
    }

    private synchronized void addEnemies(Context context, int enemyClass, int count) {
        for (int i = 0; i < count; i++) {
            try {
                Enemies enemy = EnemyFactory.getEnemy(enemyClass);
                enemy.setScreenDimensions(screenWidth, screenHeight);
                enemy.changeDamage(10 / difficulty);
                enemy.changeSpeed(10 / difficulty);
                enemies.add(enemy);

                EnemyView enemyView = new EnemyView(context, enemy);
                enemyViews.add(enemyView);
                gamelayout.addView(enemyView);
            } catch (RuntimeException e) {
                throw new RuntimeException("Error instantiating enemy class", e);
            }
        }
    }

    public void moveEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemies enemy = enemies.get(i);
            enemy.moveEnemy(player.getPosX(), player.getPosY());
            enemyViews.get(i).updatePosition(enemy);
        }
    }

    public synchronized void checkCollision() {
        synchronized (enemyViews) {
            EnemyCollision ec = new EnemyCollision(enemyViews);
            if (ec.checkCollision(player, player.getPosX(), player.getPosY())) {
                for (int index : ec.getCollided()) {
                    enemies.get(index).damagePlayer(player, player.getResistance());
                }
            }
        }
    }
    public synchronized int checkCollision(WeaponView wv) {
        int total = 0;
        synchronized (enemyViews) {
            synchronized (enemies) {
                EnemyCollision ec = new EnemyCollision(enemyViews);
                if (ec.checkCollision(wv)) {
                    total = ec.getCollided().size();
                    for (int index : ec.getCollided()) {
                        enemies.remove(index);
                        EnemyView ev = enemyViews.get(index);
                        gamelayout.removeView(ev);
                        if (ev.getParent() != null) {
                            ((ViewGroup) ev.getParent()).removeView(ev);
                        }
                        enemyViews.remove(index);
                    }
                }
            }
        }
        return total;
    }

    public synchronized void removeViews() {
        for (EnemyView view : enemyViews) {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }
    }
}
