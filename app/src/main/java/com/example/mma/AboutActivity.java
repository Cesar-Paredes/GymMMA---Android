package com.example.mma;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {
    ///TOP TOOLBAR OBJ (MR)
    private Toolbar toolbar;


    //////////////// ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ///INTENT
        Intent loginHomeIntent = getIntent();

        ///TOP TOOLBAR (MR)
        toolbar=findViewById(R.id.mmaToolBar);
        setSupportActionBar(toolbar);
    }

    //////////////// TOP NAV BAR MENU (MR)
    ///CREATE MENU (MR)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    ///SELECT ITEM FROM MENU - CASE TO REDIRECT TO PAGE (MR)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.menuHome:
                Toast.makeText(this,"Welcome home!",Toast.LENGTH_SHORT).show();
                clickMenuNavigate(HomeActivity.class);
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

            case R.id.menuAccount:
                Toast.makeText(this,"View your account details!",Toast.LENGTH_SHORT).show();
                clickMenuNavigate(AccountActivity.class);
                return true;

            case R.id.menuAbout:
                Toast.makeText(this,"Get to know us!",Toast.LENGTH_SHORT).show();
                clickMenuNavigate(AboutActivity.class);
                return true;

            case R.id.menuContact:
                Toast.makeText(this,"Contact us!",Toast.LENGTH_SHORT).show();
                clickMenuNavigate(ContactActivity.class);
                return true;

            case R.id.menuNews:
                Toast.makeText(this,"See whats happening in MMA!",Toast.LENGTH_SHORT).show();
                clickMenuNavigate(NewsActivity.class);
                return true;

            case R.id.menuTrainers:
                Toast.makeText(this,"View our trainers!",Toast.LENGTH_SHORT).show();
                clickMenuNavigate(TrainersActivity.class);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    ///METHOD USED FOR MENU SELECT INTENT REDIRECTION (MR)
    public void clickMenuNavigate(Class c){
        Intent intent = new Intent(AboutActivity.this,c);
        startActivity(intent);
    }


    //////////////// IMPLICIT MAP BUTTON (MR)
    public void clickMap(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(("https://goo.gl/maps/RRVFAd44smfRkCXQ6")));
        startActivity(intent);
    }


///
}