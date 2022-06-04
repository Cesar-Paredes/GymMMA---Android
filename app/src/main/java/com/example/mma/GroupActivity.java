package com.example.mma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


//CESAR PAREDES////////////////////

public class GroupActivity extends AppCompatActivity {

    private Toolbar toolbar;




    String membershipFromDatabase;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //manipulate user in firebase
    FirebaseUser currentUser;


    Activity binding;

//    TextView memberText;

    Button bookBtn;

    Spinner dropdown;
    String [] items;

    String scheduleDate;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        toolbar=findViewById(R.id.mmaToolBar);
        setSupportActionBar(toolbar);
        Intent loginHomeIntent = getIntent();


        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //initialize the firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Users");




        bookBtn = findViewById(R.id.bookBtn);

        getMembershipCurrentUser();

        //dropdown//////////////////
        dropdown = findViewById(R.id.scheduleSpinner);
        Schedule schedule = new Schedule();

        //this button will post the schedule info in the current user
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //this is the updated info that will go to our database
                HashMap user = new HashMap();

                user.put("schedule", scheduleDate); //puts the shedule date got it from the spinner dropdown

//                //initialize the firebase database
//                firebaseDatabase = FirebaseDatabase.getInstance();

                //goes to our table in database
//                databaseReference = firebaseDatabase.getReference("Users");


                //updates the info with HashMap user
                databaseReference.child(currentUser.getUid()).updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if(task.isSuccessful()){


                            Toast.makeText(GroupActivity.this,"Booked successfully!",Toast.LENGTH_SHORT).show();


                        }
                        else
                            Toast.makeText(GroupActivity.this,"Failed to Book",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }



        private void correspondingSchedule () {


            items = new String[]{"BOOK A CLASS"};


        if (membershipFromDatabase.equalsIgnoreCase("BJJ")) {
            //list of items for the spinner
            items = new String[]{"BOOK A CLASS",
                    Schedule.arrWednesday[1][0]+ "h " + Schedule.arrWednesday[1][1] + " " + Schedule.arrWednesday[1][2]+ " Wednesday",
                    Schedule.arrThursday[3][0]+ "h " + Schedule.arrThursday[3][1]+ " " +Schedule.arrThursday[3][2]+ " Thursday",
                    Schedule.arrFriday[3][0]+ "h " +  Schedule.arrFriday[3][1] + " " + Schedule.arrFriday[3][2] + " Friday",
                    Schedule.arrSaturday[1][0]+ "h " + Schedule.arrSaturday[1][1]+ " "  + Schedule.arrSaturday[1][2]+ " Saturday" ,
                    Schedule.arrSunday[1][0] + "h "+ Schedule.arrSunday[1][1]+ " " + Schedule.arrSunday[1][2]+ " Sunday" };

                replaceFragment(new BJJSchedule());



        } else if (membershipFromDatabase.equalsIgnoreCase("boxing")) {
            items = new String[]{"BOOK A CLASS",
                    Schedule.arrMonday[0][0]+ "h " + Schedule.arrMonday[0][1] + " "+Schedule.arrMonday[0][2] + " Monday",
                    Schedule.arrTuesday[0][0]+ "h " + Schedule.arrTuesday[0][1] + " "+Schedule.arrTuesday[0][2] + " Tuesday",
                    Schedule.arrThursday[1][0] + "h "+Schedule.arrThursday[1][1]+ " " +Schedule.arrThursday[1][2] + " Thursday",
                    Schedule.arrFriday[1][0]+ "h " +Schedule.arrFriday[1][1]+ " " +Schedule.arrFriday[1][2] + " Friday" ,
                    Schedule.arrSaturday[0][0]+ "h " + Schedule.arrSaturday[0][1] + " "+Schedule.arrSaturday[0][2] + " Saturday",
                    Schedule.arrSunday[0][0] + "h "+ Schedule.arrSunday[0][1]+ " " +Schedule.arrSunday[0][2] + " Sunday"};

                replaceFragment(new BoxingFragment());


        } else if (membershipFromDatabase.equalsIgnoreCase("Muay-Thai")) {
            items = new String[]{"BOOK A CLASS",
                    Schedule.arrMonday[1][0] +"h " + " " + Schedule.arrMonday[1][1] + " " + Schedule.arrMonday[1][2] + " Monday",
                    Schedule.arrTuesday[1][0] + "h "+ Schedule.arrTuesday[1][1]+ " " + Schedule.arrTuesday[1][2]+ " Tuesday",
                    Schedule.arrWednesday[0][0]+ "h " + Schedule.arrThursday[2][1]+ " " + Schedule.arrFriday[2][2] + " Friday"};

                replaceFragment(new MuayThaiFragment());


        } else if (membershipFromDatabase.equalsIgnoreCase("WRESTLING")) {
            items = new String[]{"BOOK A CLASS",
                    Schedule.arrThursday[0][0] +"h "+ Schedule.arrThursday[0][1]+ " " +Schedule.arrThursday[0][2]+ " Thursday",
                    Schedule.arrFriday[0][0]+"h " + Schedule.arrFriday[0][1]+ " " + Schedule.arrFriday[0][2]+ " Friday",
                    Schedule.arrSaturday[2][0]+"h " +Schedule.arrSaturday[2][1]+ " " +Schedule.arrSaturday[2][2] + " Saturday",
                    Schedule.arrSunday[2][0]+"h " + Schedule.arrSunday[2][1]+ " " + Schedule.arrSunday[2][2] + " Sunday"};

                replaceFragment(new WrestlingFragment());


        } else {
            items = new String[]{"BOOK A CLASS",
                    //BJJ
                    Schedule.arrWednesday[1][0]+ "h " + Schedule.arrWednesday[1][1] + " " + Schedule.arrWednesday[1][2]+ " Wednesday",
                    Schedule.arrThursday[3][0]+ "h " + Schedule.arrThursday[3][1]+ " " +Schedule.arrThursday[3][2]+ " Thursday",
                    Schedule.arrFriday[3][0]+ "h " +  Schedule.arrFriday[3][1] + " " + Schedule.arrFriday[3][2] + " Friday",
                    Schedule.arrSaturday[1][0]+ "h " + Schedule.arrSaturday[1][1]+ " "  + Schedule.arrSaturday[1][2]+ " Saturday" ,
                    Schedule.arrSunday[1][0] + "h "+ Schedule.arrSunday[1][1]+ " " + Schedule.arrSunday[1][2]+ " Sunday",
                    //boxing
                    Schedule.arrMonday[0][0]+ "h " + Schedule.arrMonday[0][1] + " "+Schedule.arrMonday[0][2] + " Monday",
                    Schedule.arrTuesday[0][0]+ "h " + Schedule.arrTuesday[0][1] + " "+Schedule.arrTuesday[0][2] + " Tuesday",
                    Schedule.arrThursday[1][0] + "h "+Schedule.arrThursday[1][1]+ " " +Schedule.arrThursday[1][2] + " Thursday",
                    Schedule.arrFriday[1][0]+ "h " +Schedule.arrFriday[1][1]+ " " +Schedule.arrFriday[1][2] + " Friday" ,
                    Schedule.arrSaturday[0][0]+ "h " + Schedule.arrSaturday[0][1] + " "+Schedule.arrSaturday[0][2] + " Saturday",
                    Schedule.arrSunday[0][0] + "h "+ Schedule.arrSunday[0][1]+ " " +Schedule.arrSunday[0][2] + " Sunday",
                    //muaythai
                    Schedule.arrMonday[1][0] +"h " + " " + Schedule.arrMonday[1][1] + " " + Schedule.arrMonday[1][2] + " Monday",
                    Schedule.arrTuesday[1][0] + "h "+ Schedule.arrTuesday[1][1]+ " " + Schedule.arrTuesday[1][2]+ " Tuesday",
                    Schedule.arrWednesday[0][0]+ "h " +  Schedule.arrThursday[2][1]+ " " + Schedule.arrFriday[2][2] + " Friday",
                    //wrestling
                    Schedule.arrThursday[0][0] +"h "+ Schedule.arrThursday[0][1]+ " " +Schedule.arrThursday[0][2]+ " Thursday",
                    Schedule.arrFriday[0][0]+"h " + Schedule.arrFriday[0][1]+ " " + Schedule.arrFriday[0][2]+ " Friday",
                    Schedule.arrSaturday[2][0]+"h " +Schedule.arrSaturday[2][1]+ " " +Schedule.arrSaturday[2][2] + " Saturday",
                    Schedule.arrSunday[2][0]+"h " + Schedule.arrSunday[2][1]+ " " + Schedule.arrSunday[2][2] + " Sunday"};



                replaceFragment(new MMAFragment());


        }


        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        //program will always came back here as soon as we select the spinner dropdown
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                scheduleDate = dropdown.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

        //when it reach this part it closed, nothing
        private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

//gets the membership from database of the current user
        private void getMembershipCurrentUser () {
        //current user
//        currentUser = FirebaseAuth.getInstance().getCurrentUser();
//
////        memberText = (TextView) findViewById(R.id.membershipText);
////        button2 = (Button) findViewById(R.id.button2);
//
//
//        //initialize the firebase database
//        firebaseDatabase = FirebaseDatabase.getInstance();

        //Gets the current user
//        databaseReference = firebaseDatabase.getReference("Users").child(currentUser.getUid());


        //reference for our database, gets the current users value for membership
        databaseReference.child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    membershipFromDatabase = String.valueOf(snapshot.child("membership").getValue());
//                    memberText.setText(membershipFromDatabase);
                    correspondingSchedule();
                } else
                    Toast.makeText(GroupActivity.this, "no data!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GroupActivity.this, "Error!", Toast.LENGTH_SHORT).show();

            }
        });




    }



    public void update(){
        //current user
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

//        memberText = (TextView) findViewById(R.id.membershipText);
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
//                    memberText.setText(membershipFromDatabase);
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

//        memberText = (TextView) findViewById(R.id.membershipText);
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
//                    memberText.setText(membershipFromDatabase);
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

//    public void customizedLayoutForCurrentUser(){
//
//
//        //buttons from .xml to here
//        BJJBtn = (Button) findViewById(R.id.BJJ);
//        wrestlingBtn = (Button) findViewById(R.id.wresting);
//        boxingBtn = (Button) findViewById(R.id.boxing);
//        muayThaiBtn = (Button) findViewById(R.id.muayThai);
//        allAccessMMABtn = (Button) findViewById(R.id.allAcceesMMA);
//
//        //now we redirect the user to the right layout according to its membership
//        //so the user can schedule a class from his membership
//        //compares the membership from database
//        if(membershipFromDatabase.equalsIgnoreCase("BJJ")){
//            //it goes to GroupActivity BJJ layout, user only able to schedule a class in BJJ
//            //we use the same GroupActivity for all of them, just make the BJJ button visible here,
//            //or instead i can right the was show a dropdown for next week BJJ classes, just gotta set up all times
//            //we are having the bjj classes in the week
//            BJJBtn.setVisibility(View.VISIBLE);
//
//        }
//        else if(membershipFromDatabase.equalsIgnoreCase("Boxing")){
//            //it goes to GroupActivity boxing layout, user only able to schedule a class in boxing
//            boxingBtn.setVisibility(View.VISIBLE);
//        }
//        else if(membershipFromDatabase.equalsIgnoreCase("muay-thai")){
//            //it goes to GroupActivity muay-thai layout, user only able to schedule a class in muay-thai
//            muayThaiBtn.setVisibility(View.VISIBLE);
//        }
//        else if(membershipFromDatabase.equalsIgnoreCase("wrestling")){
//            //it goes to GroupActivity wrestling layout, user only able to schedule a class in wrestling
//            wrestlingBtn.setVisibility(View.VISIBLE);
//        }
//        //in database "all access mma" is just MMA
//        else if(membershipFromDatabase.equalsIgnoreCase("MMA")){
//            //it goes to GroupActivity MMA layout, user only able to schedule a class in MMA
//            allAccessMMABtn.setVisibility(View.VISIBLE);
//        }
//    }




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