package com.example.cosmic_captive.data.enemy;

public class EnemyFactory {
    public static Enemies getEnemy(int type) {
        if (type == 1) {
            return new Enemy1();
        } else if (type == 2) {
            return new Enemy2();
        }  else if (type == 3) {
            return new Enemy3();
        } else if (type == 4) {
            return new Enemy4();
        }
        return null;
    }
}
