package com.example.mma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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


//CESAR PAREDES////////////////////

public class GroupActivity extends AppCompatActivity {

    private Toolbar toolbar;

    Button BJJBtn;
    Button muayThaiBtn;
    Button wrestlingBtn;
    Button boxingBtn;
    Button allAccessMMABtn;

    String membershipFromDatabase="";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //manipulate user in firebase
    FirebaseUser currentUser;


    Activity binding;

    TextView memberText;

    Button button2;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        toolbar=findViewById(R.id.mmaToolBar);
        setSupportActionBar(toolbar);
        Intent loginHomeIntent = getIntent();



        


        getMembershipCurrentUser();
//        customizedLayoutForCurrentUser();
//        delete();


    }



    public void getMembershipCurrentUser(){
        //current user
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        memberText = (TextView) findViewById(R.id.membershipText);
//        button2 = (Button) findViewById(R.id.button2);


        //initialize the firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Gets the current user
        databaseReference = firebaseDatabase.getReference("Users").child(currentUser.getUid());


        //reference for our database, gets the current users value for membership
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    membershipFromDatabase = String.valueOf(snapshot.child("membership").getValue());
                    memberText.setText(membershipFromDatabase);
                }
                else
                    Toast.makeText(GroupActivity.this,"no data!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GroupActivity.this,"Error!",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void update(){
        //current user
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        memberText = (TextView) findViewById(R.id.membershipText);
//        button2 = (Button) findViewById(R.id.button2);


        //initialize the firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Gets the current user
        databaseReference = firebaseDatabase.getReference("Users").child(currentUser.getUid());


        //reference for our database, gets the current users value for membership
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
//                    membershipFromDatabase = String.valueOf(snapshot.child("username").getRef().updateChildren());
                    memberText.setText(membershipFromDatabase);
                }
                else
                    Toast.makeText(GroupActivity.this,"no data!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GroupActivity.this,"Error!",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void delete(){
        //current user
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        memberText = (TextView) findViewById(R.id.membershipText);
//        button2 = (Button) findViewById(R.id.button2);


        //initialize the firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Gets the current user
        databaseReference = firebaseDatabase.getReference("Users").child(currentUser.getUid());


        //reference for our database, gets the current users value for membership
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    membershipFromDatabase = String.valueOf(snapshot.child("username").getRef().removeValue());
                    memberText.setText(membershipFromDatabase);
                }
                else
                    Toast.makeText(GroupActivity.this,"no data!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GroupActivity.this,"Error!",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void customizedLayoutForCurrentUser(){


        //buttons from .xml to here
        BJJBtn = (Button) findViewById(R.id.BJJ);
        wrestlingBtn = (Button) findViewById(R.id.wresting);
        boxingBtn = (Button) findViewById(R.id.boxing);
        muayThaiBtn = (Button) findViewById(R.id.muayThai);
        allAccessMMABtn = (Button) findViewById(R.id.allAcceesMMA);

        //now we redirect the user to the right layout according to its membership
        //so the user can schedule a class from his membership
        //compares the membership from database
        if(membershipFromDatabase.equalsIgnoreCase("BJJ")){
            //it goes to GroupActivity BJJ layout, user only able to schedule a class in BJJ
            //we use the same GroupActivity for all of them, just make the BJJ button visible here,
            //or instead i can right the was show a dropdown for next week BJJ classes, just gotta set up all times
            //we are having the bjj classes in the week
            BJJBtn.setVisibility(View.VISIBLE);

        }
        else if(membershipFromDatabase.equalsIgnoreCase("Boxing")){
            //it goes to GroupActivity boxing layout, user only able to schedule a class in boxing
            boxingBtn.setVisibility(View.VISIBLE);
        }
        else if(membershipFromDatabase.equalsIgnoreCase("muay-thai")){
            //it goes to GroupActivity muay-thai layout, user only able to schedule a class in muay-thai
            muayThaiBtn.setVisibility(View.VISIBLE);
        }
        else if(membershipFromDatabase.equalsIgnoreCase("wrestling")){
            //it goes to GroupActivity wrestling layout, user only able to schedule a class in wrestling
            wrestlingBtn.setVisibility(View.VISIBLE);
        }
        //in database "all access mma" is just MMA
        else if(membershipFromDatabase.equalsIgnoreCase("MMA")){
            //it goes to GroupActivity MMA layout, user only able to schedule a class in MMA
            allAccessMMABtn.setVisibility(View.VISIBLE);
        }
    }




    //////////////// TOP NAV BAR MENU/// MAT RUDY  ////////
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
        /////////////////////////////////////end of Nav bar////////////////////////////////////////////////


    }

    public void clickMenuNavigate(Class c){
        Intent intent = new Intent(GroupActivity.this,c);
        startActivity(intent);
    }


}