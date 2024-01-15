package com.example.cosmic_captive.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.cosmic_captive.R;
import com.example.cosmic_captive.model.Player;

public class ConfigActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configurationlayout);
        Button startBtn = findViewById(R.id.startButton);

        startBtn.setOnClickListener(v -> {
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            String name;
            EditText nameInput = (EditText) findViewById(R.id.playerNameInput);
            int difficulty = 3;

            int id = difficultyRadioGroup.getCheckedRadioButtonId();
            if (id == R.id.radioMedium) {
                difficulty = 3;
            } else if (id == R.id.radioHard) {
                difficulty = 2;
            } else if (id == R.id.radioEasy) {
                difficulty = 4;
            }

            RadioGroup spriteRadioGroup = findViewById(R.id.characterSpriteGroup);
            int sprite = 1;

            id = spriteRadioGroup.getCheckedRadioButtonId();
            if (id == R.id.characterSprite1) {
                sprite = 1;
            } else if (id == R.id.characterSprite2) {
                sprite = 2;
            } else if (id == R.id.characterSprite3) {
                sprite = 3;
            }

            name = nameInput.getText().toString();

            if (!(name == null || name.isBlank() || name.isEmpty())) {
                Player.destroy();
                Intent game = new Intent(ConfigActivity.this, GameActivity.class);
                game.putExtra("difficulty", difficulty);
                game.putExtra("sprite", sprite);
                game.putExtra("name", name);
                startActivity(game);
                finish();
            }
        });
    }
}