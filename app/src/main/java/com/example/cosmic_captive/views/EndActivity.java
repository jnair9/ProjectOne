package com.example.cosmic_captive.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cosmic_captive.model.CurrentRuns;
import com.example.cosmic_captive.R;
import com.example.cosmic_captive.model.ScoreBoard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

public class EndActivity extends AppCompatActivity {

    private CurrentRuns current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endinglayout);
        Button restartGame = findViewById(R.id.restartButton);
        Button endGame = findViewById(R.id.exitGameButton);
        Button leaderboard = findViewById(R.id.allTimeHighs);

        current = CurrentRuns.getCurrentRuns();
        String name = getIntent().getStringExtra("name");
        int score = getIntent().getIntExtra("score", 0);
        int screen = getIntent().getIntExtra("screen", 0);
        String win = "YOU LOSE";
        if (screen == 4) {
            win = "YOU WON!!!";
        } else {
            score = 0;
        }
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Calendar obj = Calendar.getInstance();
        String date = formatter.format(obj.getTime());
        current.addEntry(name, score, date);
        ScoreBoard sboard = ScoreBoard.getScoreBoard(this);
        sboard.addScore(name, score, date, this);

        restartGame.setOnClickListener(view -> {
            Intent restartIntent = new Intent(EndActivity.this, ConfigActivity.class);
            startActivity(restartIntent);
            finish();
        });
        endGame.setOnClickListener(view -> {
            finish();
        });
        leaderboard.setOnClickListener(view -> {
            Intent lead = new Intent(EndActivity.this, AllTimeHighActivity.class);
            startActivity(lead);
            if (sboard.getExit()) {
                finish();
            }
        });

        TextView condition = (TextView) findViewById(R.id.endcondition);
        condition.setText(win.toCharArray(), 0, win.length());

        TextView curr = (TextView) findViewById(R.id.currentrun);
        String scoreStr = "Run Score: " + score;
        curr.setText(scoreStr.toCharArray(), 0, scoreStr.length());

        ArrayList<String> scores = CurrentRuns.sortAndToString();

        ListView scoreboard = findViewById(R.id.lvScoreboard);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.listitem, scores);
        scoreboard.setAdapter(adapter);
    }
}
