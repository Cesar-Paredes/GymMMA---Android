package com.example.mma;

public class PrivateBooking {

    private String username;
    private String date;
    private String course;
    private String requests;

    public PrivateBooking(){}

    public PrivateBooking(String username, String date, String course, String requests){
        this.username = username;
        this.date = date;
        this.course = course;
        this.requests = requests;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getRequests() {
        return requests;
    }
    public void setRequests(String requests) {
        this.requests = requests;
    }
//
}
