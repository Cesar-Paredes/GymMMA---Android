package com.example.mma;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent loginHomeIntent = getIntent();
        toolbar=findViewById(R.id.mmaToolBar);
        setSupportActionBar(toolbar);
    }

    //////////////// TOP NAV BAR MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.menuHome:
                Toast.makeText(this,"Welcome home!",Toast.LENGTH_SHORT).show();
                Intent homeHomeIntent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(homeHomeIntent);
                return true;

            case R.id.menuSchedule:
                Toast.makeText(this,"See this weeks schedule!",Toast.LENGTH_SHORT).show();
                clickMenuNavigate(ScheduleActivity.class);
                return true;

                case R.id.menuBookGroup:
                Toast.makeText(this,"Book a group class!",Toast.LENGTH_SHORT).show();
                clickMenuNavigate(GroupActivity.class);
                return true;

            case R.id.menuBookPrivate:
                Toast.makeText(this,"Book a private class!",Toast.LENGTH_SHORT).show();
                clickMenuNavigate(PrivateActivity.class);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void clickMenuNavigate(Class c){
        Intent intent = new Intent(HomeActivity.this,c);
        startActivity(intent);
    }



    //////HOME PAGE ACTIVITY BUTTONS
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