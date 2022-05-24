package com.example.mma;

public class Schedule {
    private String course;
    private String trainer;
    private int time;
    private String day;

    public Schedule(){}

    public Schedule(String course, String trainer, int time, String day){
        this.course = course;
        this.trainer = trainer;
        this.time = time;
        this.day = day;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public String getTrainer() {
        return trainer;
    }
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
//
}

