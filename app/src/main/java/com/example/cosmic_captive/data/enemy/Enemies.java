package com.example.cosmic_captive.data.enemy;

import com.example.cosmic_captive.model.Player;
import com.example.cosmic_captive.viewmodels.PlayerView;

public abstract class Enemies {
    protected float x;
    protected float y;
    protected float speed;
    protected float health;
    protected float damage;
    protected int screenWidth;
    protected int screenHeight;
    protected int sprite;

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    public Enemies(float x, float y, float health, float damage, float speed, int sprite) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.sprite = sprite;
    }
    public void changeDamage(int multiplier) {
        damage *= multiplier;
    }

    public void changeSpeed(int multiplier) {
        speed *= multiplier;
    }

    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }

    public int getSprite() {
        return sprite;
    }

    public void moveEnemy(float playerX, float playerY) {
        float diffX = Math.abs(playerX - this.x);
        float diffY = Math.abs(playerY - this.y);
        if (diffX > diffY) {
            if (playerX > this.x) {
                if (canMove(this.x + this.speed, this.y)) {
                    this.x += this.speed;
                }
            } else {
                if (canMove(this.x - this.speed, this.y)) {
                    this.x -= this.speed;
                }
            }
        } else {
            if (playerY > this.y) {
                if (canMove(this.x, this.y + this.speed)) {
                    this.y += this.speed;
                }
            } else {
                if (canMove(this.x, this.y - this.speed)) {
                    this.y -= this.speed;
                }
            }
        }
    }

    public void damagePlayer(Player player, float resistance) {
        player.setHealth(player.getHealth() - damage * resistance);
    }

    public float getHealth() {
        return health;
    }

    public float getDamage() {
        return damage;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void updatePosition(float newX, float newY) {
        x = newX;
        y = newY;
    }

    public boolean canMove(float newX, float newY) {
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

    //For testing only
    public float getSpeed() {
        return speed;
    }
}
