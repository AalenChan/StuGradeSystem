package com.majie.stugrade.ui.score;

public class ScoreEntity {
    private int id;
    private long date;
    private float studyScore;
    private float exerciseScore;
    private float restScore;
    private float otherScore;

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

    public float getStudyScore() {
        return studyScore;
    }

    public void setStudyScore(float studyScore) {
        this.studyScore = studyScore;
    }

    public float getExerciseScore() {
        return exerciseScore;
    }

    public void setExerciseScore(float exerciseScore) {
        this.exerciseScore = exerciseScore;
    }

    public float getRestScore() {
        return restScore;
    }

    public void setRestScore(float restScore) {
        this.restScore = restScore;
    }

    public float getOtherScore() {
        return otherScore;
    }

    public void setOtherScore(float otherScore) {
        this.otherScore = otherScore;
    }
}
