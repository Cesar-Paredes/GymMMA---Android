package com.example.mma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent loginHomeIntent = getIntent();
    }


    public void clickSchedule(View view) {
        Intent homeScheduleIntent = new Intent(HomeActivity.this, ScheduleActivity.class);
        startActivity(homeScheduleIntent);
    }

    public void clickGroup(View view) {
        Intent homeGroupIntent = new Intent(HomeActivity.this, GroupActivity.class);
        startActivity(homeGroupIntent);
    }

    public void clickPrivate(View view) {
        Intent homePrivateIntent = new Intent(HomeActivity.this, PrivateActivity.class);
        startActivity(homePrivateIntent);
    }

    public void clickAccount(View view) {
        Intent homeAccountIntent = new Intent(HomeActivity.this, AccountActivity.class);
        startActivity(homeAccountIntent);
    }

    public void clickAbout(View view) {
        Intent homeAboutIntent = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(homeAboutIntent);
    }

    public void clickContact(View view) {
        Intent homeContactIntent = new Intent(HomeActivity.this, ContactActivity.class);
        startActivity(homeContactIntent);
    }

    public void clickNews(View view) {
        Intent homeNewsIntent = new Intent(HomeActivity.this, NewsActivity.class);
        startActivity(homeNewsIntent);
    }

    public void clickTrainers(View view) {
        Intent homeTrainersIntent = new Intent(HomeActivity.this, TrainersActivity.class);
        startActivity(homeTrainersIntent);
    }
}