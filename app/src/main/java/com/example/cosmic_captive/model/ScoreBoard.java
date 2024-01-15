package com.example.cosmic_captive.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ScoreBoard {

    private static final int SIZE = 20;
    private static ArrayList<Score> scores;
    private static boolean exit;

    private static volatile ScoreBoard scoreBoard;

    private ScoreBoard(Context context) {
        scores = new ArrayList<Score>();
        exit = false;

        String ret = "";
        try {
            InputStream inputStream = context.openFileInput("highestscore.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString).append("\n");
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (Exception e) {
            Log.e("ScoreBoard", "File not found: " + e.toString());
        }

        System.out.println(ret);

        Scanner sc = new Scanner(ret);

        while (sc.hasNext()) {
            scores.add(new Score(sc.next(), sc.nextInt(), sc.next()));
        }
        sc.close();
    }

    public static ScoreBoard getScoreBoard(Context context) {
        if (scoreBoard == null) {
            synchronized (ScoreBoard.class) {
                if (scoreBoard == null) {
                    scoreBoard = new ScoreBoard(context);
                }
            }
        }
        return scoreBoard;
    }

    public void addScore(String name, int score, String date, Context context) {
        scores.add(new Score(name, score, date));
        addToFile(context);
    }
    private void addToFile(Context context) {
        Collections.sort(scores);
        Collections.reverse(scores);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput("highestscore.txt", Context.MODE_PRIVATE));
            for (int i = 0; i < SIZE; i++) {
                if (i < scores.size()) {
                    String data = scores.get(i).toString();
                    data += "\n";
                    outputStreamWriter.write(data);
                }
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public ArrayList<String> getScores() {
        Collections.sort(scores);
        Collections.reverse(scores);

        ArrayList<String> ret = new ArrayList<String>();
        for (int i = 0; i < SIZE; i++) {
            if (i < scores.size() && scores.get(i).getScore() > 0) {
                ret.add(scores.get(i).toString());
            }
        }
        return ret;
    }

    public boolean getExit() {
        return exit;
    }

    public void setExit(boolean bol) {
        exit = bol;
    }


}
