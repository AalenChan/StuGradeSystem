package com.majie.stugrade.ui.score;

public class ScoreEntity {
    private int id;
    private long date;
    private double studyScore;
    private double exerciseScore;
    private double restScore;
    private double otherScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getStudyScore() {
        return studyScore;
    }

    public void setStudyScore(double studyScore) {
        this.studyScore = studyScore;
    }

    public double getExerciseScore() {
        return exerciseScore;
    }

    public void setExerciseScore(double exerciseScore) {
        this.exerciseScore = exerciseScore;
    }

    public double getRestScore() {
        return restScore;
    }

    public void setRestScore(double restScore) {
        this.restScore = restScore;
    }

    public double getOtherScore() {
        return otherScore;
    }

    public void setOtherScore(double otherScore) {
        this.otherScore = otherScore;
    }
}
