package com.example.cosmic_captive.model;


public class Score implements Comparable<Score> {
    private int score;
    private String name;
    private String date;

    public Score(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public String getDate() {
        return date;
    }

    @Override
    public int compareTo(Score b) {
        return this.score - b.getScore();
    }

    @Override
    public String toString() {
        String temp = new String(name);
        if (temp.length() > 14) {
            temp = temp.substring(0, 14);
        }
        String ret = String.format("%-15s", temp);
        ret += String.format("%-15d", score);
        ret += String.format("%-15s", date);

        return ret;
    }
}
