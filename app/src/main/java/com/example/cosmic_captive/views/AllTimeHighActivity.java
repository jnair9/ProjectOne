package com.example.cosmic_captive.views;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cosmic_captive.R;
import com.example.cosmic_captive.model.ScoreBoard;

import java.util.ArrayList;

public class AllTimeHighActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alltimehighs);
        Button back = findViewById(R.id.goback);
        Button endGame = findViewById(R.id.exit);
        ScoreBoard sc = ScoreBoard.getScoreBoard(this);

        back.setOnClickListener(view -> {
            finish();
        });
        endGame.setOnClickListener(view -> {
            sc.setExit(true);
            finish();
        });

        ArrayList<String> scores = sc.getScores();

        ListView scoreboard = findViewById(R.id.leaderboard);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.listitem, scores);

        scoreboard.setAdapter(adapter);
    }
}
