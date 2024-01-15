package com.example.cosmic_captive.model;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cosmic_captive.collision.WallCollision;
import com.example.cosmic_captive.data.Direction;
import com.example.cosmic_captive.data.Melee;
import com.example.cosmic_captive.viewmodels.PlayerView;
import com.example.cosmic_captive.viewmodels.WeaponView;

import java.time.LocalDateTime;

public class Player implements PlayerInterface {
    private String name;
    private float health;
    private float maxHealth;
    private float posX;
    private float posY;
    private Weapon weapon;
    private int screenWidth;
    private int screenHeight;
    private Direction dir;
    private int killed;
    private int difficulty;
    private int velocity = 50;
    private LocalDateTime ldt;

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    private static volatile Player player;
    private float resistance = 1;

    private Player(String name, int health, int difficulty) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        weapon = new Melee("Rusty Pipe", 2, 1);
        dir = Direction.UP;
        killed = 0;
        ldt = LocalDateTime.now();
        this.difficulty = difficulty;
    }

    public static Player getPlayer(String name, int health, int difficulty) {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player(name, health, difficulty);
                }
            }
        }
        return player;
    }
    public synchronized void attack(Context context, ConstraintLayout gameLayout, EnemyHandler eh) {
        LocalDateTime temp = LocalDateTime.now();
        if (temp.minusNanos(500000000).isBefore(ldt)) {
            return;
        }
        ldt = temp;
        WeaponView wv;
        Animation an;
        switch (dir) {
        case UP:
            wv = new WeaponView(context, posX, posY - 50);
            an = new TranslateAnimation(wv.getX() + 50, wv.getX() - 50, wv.getY(), wv.getY());
            break;
        case DOWN:
            wv = new WeaponView(context, posX, posY + 100);
            an = new TranslateAnimation(wv.getX() + 50, wv.getX() - 50, wv.getY(), wv.getY());
            break;
        case LEFT:
            wv = new WeaponView(context, posX - 100, posY);
            an = new TranslateAnimation(wv.getX(), wv.getX(), wv.getY() + 50, wv.getY() - 50);
            break;
        case RIGHT:
            wv = new WeaponView(context, posX + 100, posY);
            an = new TranslateAnimation(wv.getX(), wv.getX(), wv.getY() + 50, wv.getY() - 50);
            break;
        default:
            wv = new WeaponView(context, posX, posY);
            an = new TranslateAnimation(wv.getX(), wv.getX(), wv.getY() + 50, wv.getY() - 50);
            break;
        }

        killed += eh.checkCollision(wv);

        gameLayout.addView(wv);
        wv.setRotation(0);
        wv.invalidate();
        an.setDuration(1000);
        wv.setAnimation(an);
        gameLayout.removeView(wv);
    }
    public void setDirection(Direction dir) {
        this.dir = dir;
    }
    public static void destroy() {
        player = null;
    }
    @Override
    public float getMaxHealth() {
        return maxHealth;
    }
    @Override
    public float getHealth() {
        return health;
    }

    public float getPosX() {
        return posX;
    }
    public float getPosY() {
        return posY;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public void updatePosition(float newX, float newY) {
        posX = newX;
        posY = newY;
    }
    public boolean canMove(float newX, float newY, Context context, ConstraintLayout layout) {
        WallCollision wc = new WallCollision(context, layout);

        if (wc.checkCollision(player, newX, newY)) {
            return false;
        }

        return true;
    }
    public boolean canMove(float newX, float newY) {
        // Check boundaries
        if (newX < 0 || newX + PlayerView.WIDTH > screenWidth) {
            return false;
        }
        if (newY < 0 || newY + PlayerView.HEIGHT > screenHeight) {
            return false;
        }
        return true;
    }

    public void setScreenDimensions(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setHealth(float health) {
        if (health <= this.maxHealth && health > 0) {
            this.health = health;
        } else if (health <= 0) {
            this.health = 0;
        } else {
            this.health = maxHealth;
        }
    }
    public int getVelocity() {
        return velocity;
    }
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public float getResistance() {
        return resistance;
    }

    public void setResistance(float resistance) {
        this.resistance = resistance;
    }
    public int getKilled() {
        return killed;
    }




    // This method is for testing purposes only
    public static void reset() {
        player = null;
    }
}
