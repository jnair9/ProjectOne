
package com.example.cosmic_captive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.example.cosmic_captive.data.enemy.Enemies;
import com.example.cosmic_captive.data.enemy.Enemy1;
import com.example.cosmic_captive.data.enemy.Enemy2;
import com.example.cosmic_captive.data.enemy.Enemy3;
import com.example.cosmic_captive.data.enemy.Enemy4;
public class EnemiesTest {
    @Test
    public void testEnemyStaysWithinBoundsOnMovement() {
        Enemies enemy = new Enemy1();
        enemy.setScreenDimensions(1000, 1000);
        enemy.updatePosition(0, 0);
        enemy.moveEnemy(2000, 2000);
        assertTrue(enemy.getX() <= 1000 && enemy.getY() <= 1000);
    }
    @Test
    public void testMovementBoundaryConditionLeft() {
        Enemies enemy = new Enemy1();
        enemy.setScreenDimensions(1000, 1000);
        assertFalse(enemy.canMove(-1, 500));
    }
    @Test
    public void testMovementBoundaryConditionRight() {
        Enemies enemy = new Enemy1();
        enemy.setScreenDimensions(1000, 1000);
        assertFalse(enemy.canMove(1001, 500));
    }
    @Test
    public void testMovementBoundaryConditionTop() {
        Enemies enemy = new Enemy1();
        enemy.setScreenDimensions(1000, 1000);
        assertFalse(enemy.canMove(500, -1));
    }
    @Test
    public void testMovementBoundaryConditionBottom() {
        Enemies enemy = new Enemy1();
        enemy.setScreenDimensions(1000, 1000);
        assertFalse(enemy.canMove(500, 1001));
    }
    @Test
    public void testDirectPositionUpdate() {
        Enemies enemy = new Enemy1();
        enemy.updatePosition(300, 300);
        assertEquals(300, enemy.getX(), 0.01);
        assertEquals(300, enemy.getY(), 0.01);
    }
    @Test
    public void testEnemy1Damage() {
        Enemy1 enemy = new Enemy1();
        float damage = enemy.getDamage();
        int correctDamage = 1;
        boolean result = damage == correctDamage;
        assertTrue(result);
    }
    @Test
    public void testEnemy2Damage() {
        Enemy2 enemy = new Enemy2();
        float damage = enemy.getDamage();
        int correctDamage = 2;
        boolean result = damage == correctDamage;
        assertTrue(result);
    }
    @Test
    public void testEnemy3Damage() {
        Enemy3 enemy = new Enemy3();
        float damage = enemy.getDamage();
        int correctDamage = 3;
        boolean result = damage == correctDamage;
        assertTrue(result);
    }
    @Test
    public void testEnemy4Damage() {
        Enemy4 enemy = new Enemy4();
        float damage = enemy.getDamage();
        int correctDamage = 4;
        boolean result = damage == correctDamage;
        assertTrue(result);
    }
}
