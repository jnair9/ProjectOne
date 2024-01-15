package com.example.cosmic_captive.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.cosmic_captive.collision.NextScreenCollision;
import com.example.cosmic_captive.data.Direction;
import com.example.cosmic_captive.model.PlayerInterface;
import com.example.cosmic_captive.model.PowerUp;
import com.example.cosmic_captive.model.EnemyHandler;
import com.example.cosmic_captive.viewmodels.CurrentScore;
import com.example.cosmic_captive.model.Player;
import com.example.cosmic_captive.R;

import com.example.cosmic_captive.viewmodels.PlayerView;
import com.example.cosmic_captive.viewmodels.PowerUpView;
import com.example.cosmic_captive.viewmodels.ScoreView;
import com.example.cosmic_captive.views.Movement.Down;
import com.example.cosmic_captive.views.Movement.Left;
import com.example.cosmic_captive.views.Movement.Right;
import com.example.cosmic_captive.views.Movement.Up;

public class GameActivity extends AppCompatActivity {
    private float posX;
    private float posY;
    private int screenWidth;
    private int screenHeight;
    private int difficulty;
    private int sprite;
    private Player player;
    private MediaPlayer musicPlayer;
    private ConstraintLayout gameLayout;
    private ScoreView scoreView;
    private CurrentScore currentScore;
    private Timer scoreTimer;
    private PlayerView playerView;
    private int currentroom;
    private Timer enemyMovementTimer;
    private PowerUp powerUp;
    private PowerUpView powerUpView;
    private boolean toggle = true;
    private EnemyHandler eh;
    private Direction dir;
    private ImageButton exitBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelayout);
        gameLayout = findViewById(R.id.gamelayout);
        currentroom = 0;

        musicPlayer = MediaPlayer.create(this, R.raw.gamescreenmusic);
        exitBtn = findViewById(R.id.gameexit);
        exitBtn.setOnClickListener(e -> exitGame());

        String name = getIntent().getStringExtra("name");
        difficulty = getIntent().getIntExtra("difficulty", 1);
        sprite = getIntent().getIntExtra("sprite", 1);
        player = Player.getPlayer(name, difficulty * 25, difficulty);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels - 200;

        posX = (screenWidth / 2) - 50;
        posY = (screenHeight / 2) - 100;
        player.setScreenDimensions(screenWidth, screenHeight);
        player.updatePosition(posX, posY);

        currentScore = CurrentScore.getScore();
        CurrentScore.setCurrentscore(1000);
        setScreenAtrributes();
        dir = Direction.UP;



        scoreView = new ScoreView(this);
        gameLayout.addView(scoreView);

        scoreTimer = new Timer();
        scoreTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CurrentScore.decreaseTime();
                        CurrentScore.updateCurrentScoreBy(player);
                        scoreView.invalidate();
                    }
                });
            }
        }, 5000, 5000);

        playerView = new PlayerView(this, posX, posY, sprite, dir);
        gameLayout.addView(playerView);
        eh = new EnemyHandler(this, currentroom, screenWidth, screenHeight, difficulty, gameLayout);
        startEnemyMovementTimer();
        spawnPowerUp(1);
    }
  


    private void updateProgressBar() {
        ProgressBar hb = (ProgressBar) findViewById(R.id.healthBar);
        if (hb == null) {
            return;
        }
        hb.setMax((int) player.getMaxHealth());
        hb.setProgress((int) ((player.getHealth())));

        TextView h = (TextView) findViewById(R.id.health);
        String healthtext = (((int) player.getHealth()) + "/"
                + ((int) player.getMaxHealth()));
        h.setText(healthtext.toCharArray(), 0, healthtext.length());
    }

    private void startEnemyMovementTimer() {
        enemyMovementTimer = new Timer();
        enemyMovementTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isFinishing() || isDestroyed()) {
                            return;
                        }

                        eh.moveEnemies();
                        eh.checkCollision();
                        updateProgressBar();
                        CurrentScore.updateCurrentScoreBy(player);
                        scoreView.invalidate();
                        if (player.getHealth() <= 0) {
                            exitGame();
                        }
                    }
                });
            }
        }, 0, 50);
    }


    private void setScreenAtrributes() {
        String name = player.getName();
        TextView displayname = (TextView) findViewById(R.id.mainname);
        if (displayname == null) {
            return;
        }
        displayname.setText(name.toCharArray(), 0, Math.min(name.length(), 10));
        TextView diff = (TextView) findViewById(R.id.maindifficulty);
        if (difficulty == 4) {
            diff.setText("Easy".toCharArray(), 0, 4);
        } else if (difficulty == 3) {
            diff.setText("Medium".toCharArray(), 0, 6);
        } else {
            diff.setText("Hard".toCharArray(), 0, 4);
        }

        ProgressBar hb = (ProgressBar) findViewById(R.id.healthBar);

        hb.setMax((int) player.getMaxHealth());
        hb.setProgress((int) ((player.getHealth())));

        TextView h = (TextView) findViewById(R.id.health);

        String healthtext = (((int) player.getHealth()) + "/"
                + ((int) player.getMaxHealth()));
        h.setText(healthtext.toCharArray(), 0, healthtext.length());

        exitBtn = findViewById(R.id.gameexit);
        exitBtn.setOnClickListener(e -> exitGame());
    }
    private void spawnPowerUp(int type) {
        float xPosition = (screenWidth / 2) - 100;
        float yPosition = (screenHeight / 2) + 350;
        powerUp = new PowerUp(xPosition, yPosition, type);
        powerUpView = new PowerUpView(this, powerUp);
        gameLayout.addView(powerUpView);
    }
    private boolean checkCollisionWithPowerUp(PowerUp powerUp) {
        PlayerInterface player = this.player;

        return Math.abs(player.getPosX() - powerUp.getX()) < Player.WIDTH / 2 + PowerUp.WIDTH / 2
                && Math.abs(player.getPosY() - powerUp.getY()) < Player.HEIGHT / 2
                + PowerUp.HEIGHT / 2;
    }
    private void removePowerUpView() {
        if (powerUpView != null && powerUpView.getParent() != null) {
            ((ViewGroup) powerUpView.getParent()).removeView(powerUpView);

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            Left.moveLeft(player, this, gameLayout);
            dir = Direction.LEFT;
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            Right.moveRight(player, this, gameLayout);
            dir = Direction.RIGHT;
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            Up.moveUp(player, this, gameLayout);
            dir = Direction.UP;
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            Down.moveDown(player, this, gameLayout);
            dir = Direction.DOWN;
            break;
        case KeyEvent.KEYCODE_X:
            player.attack(this, gameLayout, eh);
            CurrentScore.updateCurrentScoreBy(player);
            scoreView.invalidate();
            break;
        default:
            return false;
        }

        player.setDirection(dir);
        playerView.updatePosition(player, dir);
        if (checkCollisionWithPowerUp(powerUp) && toggle) {
            if (powerUp.getType() == 1) {
                player.setVelocity(75);
            } else if (powerUp.getType() == 2) {
                player.setHealth(player.getHealth() + 25);
            } else if (powerUp.getType() == 3) {
                player.setResistance(0.1f);
            }
            toggle = false;
            removePowerUpView();
        }

        // Next screen collision
        NextScreenCollision nsc = new NextScreenCollision(this, gameLayout);
        if (nsc.checkCollision(player, player.getPosX(), player.getPosY())) {
            toggle = true;
            if (scoreView.getParent() != null) {
                ((ViewGroup) scoreView.getParent()).removeView(scoreView);
            }

            if (playerView.getParent() != null) {
                ((ViewGroup) playerView.getParent()).removeView(playerView);
            }
            synchronized (eh) {
                eh.removeViews();
            }
            gameLayout.removeAllViews();
            currentroom += 1;
            if (currentroom == 4) {
                exitGame();
                return true;
            } else if (currentroom == 3) {
                setContentView(R.layout.gameroom3);
                gameLayout = findViewById(R.id.gameroom3);
            } else if (currentroom == 2) {
                setContentView(R.layout.gameroom2);
                gameLayout = findViewById(R.id.gameroom2);
            } else if (currentroom == 1) {
                setContentView(R.layout.gameroom1);
                gameLayout = findViewById(R.id.gameroom1);
            }

            gameLayout.addView(scoreView);
            gameLayout.addView(playerView);
            eh = new EnemyHandler(this, currentroom, screenWidth, screenHeight,
                    difficulty, gameLayout);
            player.updatePosition((screenWidth / 2) - 50, (screenHeight / 2) - 100);


            spawnPowerUp(currentroom);
            playerView.updatePosition(player, dir);
            setScreenAtrributes();
            updateProgressBar();
        }

        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (musicPlayer != null) {
            musicPlayer.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (enemyMovementTimer != null) {
            enemyMovementTimer.cancel();
            enemyMovementTimer.purge();
        }
        if (scoreTimer != null) {
            scoreTimer.cancel();
            scoreTimer.purge();
        }
        if (musicPlayer != null) {
            musicPlayer.stop();
            musicPlayer.release();
            musicPlayer = null;
        }
    }
    private void exitGame() {
        CurrentScore.updateCurrentScoreBy(player);

        Intent endIntent = new Intent(this, EndActivity.class);

        endIntent.putExtra("name", player.getName());
        endIntent.putExtra("score", CurrentScore.getCurrentScore());
        endIntent.putExtra("screen", currentroom);
        startActivity(endIntent);
        finish();
    }

}