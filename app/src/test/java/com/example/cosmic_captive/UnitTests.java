package com.example.cosmic_captive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.cosmic_captive.data.Melee;
import com.example.cosmic_captive.data.enemy.Enemy1;
import com.example.cosmic_captive.data.enemy.Enemy2;
import com.example.cosmic_captive.data.enemy.Enemy3;
import com.example.cosmic_captive.data.enemy.Enemy4;
import com.example.cosmic_captive.model.CurrentRuns;
import com.example.cosmic_captive.model.Player;
import com.example.cosmic_captive.model.PowerUp;
import com.example.cosmic_captive.model.Score;
import com.example.cosmic_captive.viewmodels.PowerUpView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import com.example.cosmic_captive.data.enemy.EnemyFactory;
import com.example.cosmic_captive.model.CurrentRuns;
import com.example.cosmic_captive.model.Player;
import com.example.cosmic_captive.model.PowerUp;
import com.example.cosmic_captive.model.Score;
import com.example.cosmic_captive.data.enemy.Enemies;
import com.example.cosmic_captive.model.Weapon;
import com.example.cosmic_captive.data.enemy.EnemyFactory;
import com.example.cosmic_captive.viewmodels.EnemyView;
public class  UnitTests {
    @Before
    public void setup() {
        Player.reset();
        CurrentRuns.reset();

    }

    //Yeonsoo
    @Test
    public void testGetPlayerNonNull() {
        Player player = Player.getPlayer("John", 100, 0);
        assertNotNull(player.toString());
    }

    @Test
    public void testStartingWeapon() {
        Player player = Player.getPlayer("John", 100, 0);
        assertEquals("Rusty Pipe", player.getWeapon().getName());
        assertEquals(2.0f, player.getWeapon().getDamage(), 0.001);
    }

    //Jason
    @Test
    public void testGetEntryOutOfBounds() {
        CurrentRuns.getCurrentRuns();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            CurrentRuns.getEntry(5);
        });
    }

    @Test
    public void testSameScoreOrdering() {
        CurrentRuns.getCurrentRuns();
        CurrentRuns.addEntry("Alice", 100, "01-01-2023");
        CurrentRuns.addEntry("Bob", 100, "02-01-2023");
        ArrayList<String> sortedScores = CurrentRuns.sortAndToString();

        assertEquals("Alice          100            01-01-2023     ", sortedScores.get(1));
        assertEquals("Bob            100            02-01-2023     ", sortedScores.get(0));
    }

    @Test
    public void testNamePaddingInToString() {
        CurrentRuns.getCurrentRuns();
        CurrentRuns.addEntry("A", 100, "01-01-2023");
        Score score = CurrentRuns.getEntry(0);
        assertEquals("A              100            01-01-2023     ", score.toString());
    }

    //Daniel
    @Test
    public void testGetPlayerReturnsSingletonInstance() {
        Player player1 = Player.getPlayer("John", 100, 0);
        Player player2 = Player.getPlayer("Doe", 50, 0);

        assertSame(player1, player2);
    }

    @Test
    public void testGetPlayerInitializesCorrectly() {
        String name = "John";
        int health = 100;
        int difficulty = 3;
        Player player = Player.getPlayer(name, health, difficulty);

        assertEquals(name, player.getName());
        assertEquals(health, player.getHealth(),0.001);
    }

    //Bryce
    @Test
    public void testDamage1() {
        Player player = Player.getPlayer("John", 100, 0);
        player.setHealth(-100);
        assertEquals(0, player.getHealth(),0.001);
    }

    @Test
    public void testDamage2() {
        Player player = Player.getPlayer("John", 100, 0);
        player.setHealth(player.getHealth() + 40);
        assertEquals(100, player.getHealth(),0.001);
    }

    @Test
    public void testDamage3() {
        Player player = Player.getPlayer("John", 100, 0);
        player.setHealth(player.getHealth() - 80);
        player.setHealth(player.getHealth() + 30);
        assertEquals(50, player.getHealth(), 0.001);
    }

    //Jordan
    @Test
    public void testSingletonPattern() {
        CurrentRuns instance1 = CurrentRuns.getCurrentRuns();
        CurrentRuns instance2 = CurrentRuns.getCurrentRuns();
        assertSame(instance1, instance2);
    }

    @Test
    public void testAddAndGetEntry() {
        CurrentRuns.getCurrentRuns();
        CurrentRuns.addEntry("Alice", 100, "01-01-2023");
        Score score = CurrentRuns.getEntry(0);
        assertEquals("Alice", score.getName());
        assertEquals(100, score.getScore());
        assertEquals("01-01-2023", score.getDate());
    }

    @Test
    public void testSortAndToString() {
        CurrentRuns.getCurrentRuns();
        CurrentRuns.addEntry("Alice", 100, "01-01-2023");
        CurrentRuns.addEntry("Bob", 150, "02-01-2023");
        CurrentRuns.addEntry("Charlie", 50, "03-01-2023");

        ArrayList<String> sortedScores = CurrentRuns.sortAndToString();

        assertEquals("Charlie        50             03-01-2023     ", sortedScores.get(2));
        assertEquals("Alice          100            01-01-2023     ", sortedScores.get(1));
        assertEquals("Bob            150            02-01-2023     ", sortedScores.get(0));
    }
    @Test
    public void testMoveWithinScreen() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(800,600);
        boolean result = player.canMove(400, 300);
        assertTrue(result);
    }
    @Test
    public void testMoveOutsideLeftScreen() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(800,600);
        boolean result = player.canMove(-10, 30);
        assertFalse(result);
    }
    @Test
    public void testMoveOutsideRightScreen() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(800,600);
        boolean result = player.canMove(1000, 30);
        assertFalse(result);
    }
    @Test
    public void testMoveOutsideTopScreen() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(800,600);
        boolean result = player.canMove(400, -1000);
        assertFalse(result);
    }
    @Test
    public void testMoveOutsideBottomScreen() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(800,600);
        boolean result = player.canMove(400, 1000);
        assertFalse(result);
    }

    @Test
    public void testMoveToTopLeftCorner() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(800,600);
        boolean result = player.canMove(0, 0);
        assertTrue(result);
    }

    @Test
    public void testMoveToTopRightCorner() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(800,600);
        boolean result = player.canMove(700, 0);
        assertTrue(result);
    }

    @Test
    public void testMoveToBottomLeftCorner() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(800,600);
        boolean result = player.canMove(0, 500);
        assertTrue(result);
    }

    @Test
    public void testMoveToBottomRightCorner() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(800,600);
        boolean result = player.canMove(700, 500);
        assertTrue(result);
    }
    @Test
    public void testCannotMoveToWall() {
        Player player = Player.getPlayer("Randy", 100, 0);
        player.setScreenDimensions(100,100);
        player.updatePosition(0,0);
        player.canMove(200,200);
        float y = player.getPosY();
        float x = player.getPosX();
        boolean result = y == x;
        assertTrue(result);
    }
    @Test
    public void testPowerUpPosition() {
        PowerUp powerUp = new PowerUp(50,50,1);
        boolean result = powerUp.getX() == 50 && powerUp.getY() == 50;
        assertTrue(result);
    }
    @Test
    public void correctPowerUpSpeed() {
        PowerUp powerUp = new PowerUp(50,50,1);
        boolean result = powerUp.getType() == 1;
        assertTrue(result);
    }
    @Test
    public void correctPowerUpHealth() {
        PowerUp powerUp = new PowerUp(50,50,2);
        boolean result = powerUp.getType() == 2;
        assertTrue(result);
    }
    @Test
    public void correctPowerUpShield() {
        PowerUp powerUp = new PowerUp(50, 50, 3);

        boolean result = powerUp.getType() == 3;
        assertTrue(result);
    }
    @Test
    public void testCreateWeapon() {
        Weapon weapon = new Melee("Sword", 10.0f, 1);
        assertEquals("Sword", weapon.getName());
        assertEquals(10.0f, weapon.getDamage(), 0.0f);
    }
    @Test
    public void testEnemyDeath() {
        Enemies enemy = EnemyFactory.getEnemy(1);
        enemy.setHealth(0);
        assertTrue(enemy.getHealth() <= 0);
    }
    @Test
    public void testEnemyDamaged() {
        Enemies enemy = EnemyFactory.getEnemy(1);
        enemy.setHealth(100);
        enemy.setHealth((int)enemy.getHealth() - 10);
        assertTrue(enemy.getHealth() == 90);
    }
    @Test
    public void testEnemyFactoryType1() {
        Enemies enemy = EnemyFactory.getEnemy(1);
        assertTrue(Enemy1.class.isInstance(enemy));
    }
    @Test
    public void testEnemyFactoryType2() {
        Enemies enemy = EnemyFactory.getEnemy(2);
        assertTrue(Enemy2.class.isInstance(enemy));
    }
    @Test
    public void testEnemyFactoryType3() {
        Enemies enemy = EnemyFactory.getEnemy(3);
        assertTrue(Enemy3.class.isInstance(enemy));
    }
    @Test
    public void testEnemyFactoryType4() {
        Enemies enemy = EnemyFactory.getEnemy(4);
        assertTrue(Enemy4.class.isInstance(enemy));
    }

}