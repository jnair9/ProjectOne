package com.example.cosmic_captive.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.cosmic_captive.R;


public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomelayout);
        Button startBtn = findViewById(R.id.startButton);
        ImageButton exitBtn = findViewById(R.id.exitButton);
        mediaPlayer = MediaPlayer.create(this, R.raw.intromusic);
        mediaPlayer.setLooping(true);

        startBtn.setOnClickListener(v -> {
            Intent game = new Intent(MainActivity.this, ConfigActivity.class);
            startActivity(game);
            finish();
        });
        exitBtn.setOnClickListener(v -> {
            finish();
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}