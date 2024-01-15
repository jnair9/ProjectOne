package com.example.cosmic_captive.viewmodels;


import com.example.cosmic_captive.model.Player;

public class CurrentScore {
    private static int currentscore;
    private static int time;
    private static volatile CurrentScore score;

    private CurrentScore() {
        time = 1000;
    }

    public static CurrentScore getScore() {
        if (score == null) {
            synchronized (CurrentScore.class) {
                if (score == null) {
                    score = new CurrentScore();
                }
            }
        }
        return score;
    }

    public static int getCurrentScore() {
        return currentscore;
    }
    public static void updateCurrentScoreBy(Player player) {
        currentscore = (int) ((time + player.getHealth())
                * player.getKilled() / player.getDifficulty());
    }
    public static void decreaseTime() {
        time -= 1;
    }
    public static void setCurrentscore(int s) {
        currentscore = s;
    }

    @Override
    public String toString() {
        return Integer.toString(currentscore);
    }

}
