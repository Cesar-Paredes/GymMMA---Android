package com.example.mma;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;

    //added by Cesar Paredes
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseUser user;
    String membershipFromDatabase;



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

    //sends the user to the selection of choice, from home menu page
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

//            case R.id.menuContact:
//                Toast.makeText(this,"Contact us!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(ContactActivity.class);
//                return true;

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


//    public void getMembershipFromDatabase(){
//
//        //added by Cesar Paredes//
//        //reference for our database, gets the current users value for membership
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //we store the user membership from the database to this String variable
//                membershipFromDatabase = snapshot.child("membership").getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(HomeActivity.this,"Error!",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        GroupActivity groupActivity = new GroupActivity();
//        groupActivity.userSelection(membershipFromDatabase);
//    }


////
}