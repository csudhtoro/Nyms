package com.example.nyms.models;

public class GuessModel {
    private String guessNumber;
    private String guessName;
    private String guessRelation;
    private double guessScore;
    private boolean expanded;


    public GuessModel(String guessNumber, String guessName, String guessRelation, double guessScore) {
        this.guessNumber = guessNumber;
        this.guessName = guessName;
        this.guessRelation = guessRelation;
        this.guessScore = guessScore;
        this.expanded = false;
    }

    public String getGuessNumber() {return guessNumber;}
    public String getGuessName() {return guessName;}
    public String getGuessRelation() {return guessRelation;}
    public double getGuessScore() {return guessScore;}
    public boolean getExpanded() {return expanded;}
    public void setExpanded(boolean expanded) {this.expanded = expanded;}
}
