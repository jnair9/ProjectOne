package com.example.cosmic_captive.model;

import java.util.ArrayList;
import java.util.Collections;

public class CurrentRuns {
    private static ArrayList<Score> scores;

    private static volatile CurrentRuns currentRuns;

    private CurrentRuns() {
        scores = new ArrayList<Score>();
    }

    public static CurrentRuns getCurrentRuns() {
        if (currentRuns == null) {
            synchronized (ScoreBoard.class) {
                if (currentRuns == null) {
                    currentRuns = new CurrentRuns();
                }
            }
        }
        return currentRuns;
    }


    public static Score getEntry(int index) {
        return scores.get(index);
    }

    public static void addEntry(String name, int score, String date) {
        scores.add(new Score(name, score, date));
    }

    public static ArrayList<String> sortAndToString() {
        Collections.sort(scores);
        Collections.reverse(scores);

        ArrayList<String> ret = new ArrayList<String>();
        for (Score i : scores) {
            ret.add(i.toString());
        }
        return ret;
    }

    public static ArrayList<Score> getScores() {
        ArrayList<Score> copiedScores = new ArrayList<>();
        for (Score score : scores) {
            copiedScores.add(new Score(score.getName(), score.getScore(), score.getDate()));
        }
        return copiedScores;
    }

    // This method is for testing purposes only
    public static void reset() {
        if (scores != null) {
            scores.clear();
        }
        currentRuns = null;
    }
}

