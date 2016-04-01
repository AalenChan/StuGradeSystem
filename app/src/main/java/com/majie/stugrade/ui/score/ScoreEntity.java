package com.majie.stugrade.ui.score;

public class ScoreEntity {
    private int id;
    private long date;
    private double studyTime;
    private double sportTime;
    private double restTime;
    private double otherTime;
    private double score;

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

    public double getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(double studyTime) {
        this.studyTime = studyTime;
    }

    public double getSportTime() {
        return sportTime;
    }

    public void setSportTime(double sportTime) {
        this.sportTime = sportTime;
    }

    public double getRestTime() {
        return restTime;
    }

    public void setRestTime(double restTime) {
        this.restTime = restTime;
    }

    public double getOtherTime() {
        return otherTime;
    }

    public void setOtherTime(double otherTime) {
        this.otherTime = otherTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
