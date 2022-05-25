package com.example.mma;

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



//Cesar Paredes///////////////////////////////////////////////

public class AccountActivity extends AppCompatActivity {

    String firstName="";
    String lastName="";
    String userName="";
    String password="";
    String email="";
    String membership="";



    String updatedMembership ="";

    TextView firstnameText;
    TextView lastnameText;
    TextView usernameText;
    TextView passwordText;
    TextView emailText;


    Spinner dropdown;
    String[] items;



    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //manipulate user in firebase
    FirebaseUser currentUser;


    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Intent loginHomeIntent = getIntent();
        toolbar=findViewById(R.id.mmaToolBar);
        setSupportActionBar(toolbar);


        getAccountDetails();

        Button update = (Button) findViewById(R.id.UpdateBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateFields();

            }
        });

    }




    //gets values from the database and display it in the app
    public void getAccountDetails() {
        //current user
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

//        accountText = (TextView) findViewById(R.id.accountText);
//        button2 = (Button) findViewById(R.id.button2);


        //initialize the firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Gets the current user
        databaseReference = firebaseDatabase.getReference("Users").child(currentUser.getUid());


        //GET VALUES FROM DATABASE
        //reference for our database, gets the current users values
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    //gets data from database
                    userName = String.valueOf(snapshot.child("username").getValue());
                    firstName = String.valueOf(snapshot.child("firstName").getValue());
                    lastName = String.valueOf(snapshot.child("lastName").getValue());
                    password = String.valueOf(snapshot.child("password").getValue());
                    email = String.valueOf(snapshot.child("email").getValue());
                    membership = String.valueOf(snapshot.child("membership").getValue());


                    //PASS TEXTVIEW FROM LAYOUT TO HERE
                    firstnameText = (TextView) findViewById(R.id.firstNameText);
                    lastnameText = (TextView) findViewById(R.id.lastNameText);
                    usernameText = (TextView) findViewById(R.id.usernameText);
                    passwordText = (TextView) findViewById(R.id.passwordText);
                    emailText = (TextView) findViewById(R.id.emailText);



                    //set the values of the textviews
                    firstnameText.setText(firstName);
                    lastnameText.setText(lastName);
                    usernameText.setText(userName);
                    passwordText.setText(password);
                    emailText.setText(email);



                    //i put it here because this method onDataChange takes more time than others to execute
                    //so android would jump this whole onDataChange method to execute other methods below,
                    //and will execute it but executes last
                    membershipToSpinner();//reads membership variable we got from database in the other method and store it in the spinner


                } else
                    Toast.makeText(AccountActivity.this, "no data!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AccountActivity.this, "Error!", Toast.LENGTH_SHORT).show();

            }
        });
    }



    //reads membership variable we got from database in the other method and store it in the spinner
    public void membershipToSpinner(){
        //dropdown//////////////////
        dropdown = findViewById(R.id.membershipSpinnerAccount);


        //list of items for the spinner, it will show first the current membership user has
        items = new String[]{membership, "BJJ $30", "MUAY-THAI $30", "BOXING $30", "WRESTLING $30", "ALL ACCESS MMA $50"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);





        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        //select the users choice in the dropdown
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                   updatedMembership = dropdown.getSelectedItem().toString();

                                                   if(updatedMembership.equalsIgnoreCase("BJJ $30"))
                                                       membership = "BJJ";
                                                   else if(updatedMembership.equalsIgnoreCase("MUAY-THAI $30"))
                                                       membership = "MUAY-THAI";
                                                   else if(updatedMembership.equalsIgnoreCase("BOXING $30"))
                                                       membership = "BOXING";
                                                   else if(updatedMembership.equalsIgnoreCase("WRESTLING $30"))
                                                       membership = "WRESTLING";
                                                   else if(updatedMembership.equalsIgnoreCase("ALL ACCESS MMA $50"))
                                                       membership = "MMA";
                                               }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    //updates the database with new user input, all fields are able to change except for email
    public void updateFields(){

        //PASS TEXTVIEW FROM LAYOUT TO HERE
        firstnameText = (TextView) findViewById(R.id.firstNameText);
        lastnameText = (TextView) findViewById(R.id.lastNameText);
        usernameText = (TextView) findViewById(R.id.usernameText);
        passwordText = (TextView) findViewById(R.id.passwordText);
        emailText = (TextView) findViewById(R.id.emailText);

        //now we pass the TextViews to the our strings variables
        firstName = firstnameText.getText().toString().trim();
        lastName = lastnameText.getText().toString().trim();
        userName = usernameText.getText().toString().trim();
        password = passwordText.getText().toString().trim();


//        button2 = (Button) findViewById(R.id.button2);

        HashMap user = new HashMap();

        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("username", userName);
        user.put("password", password);
        user.put("membership", membership);


        //initialize the firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        //goers to our table in database
        databaseReference = firebaseDatabase.getReference("Users");

        //updates the info with HashMap user
        databaseReference.child(currentUser.getUid()).updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful()){
                    Toast.makeText(AccountActivity.this,"Success!",Toast.LENGTH_SHORT).show();

                    //retrieve data from the just updated database and display new updated data in the app
                    getAccountDetails();

                }
                else
                    Toast.makeText(AccountActivity.this,"Failed to Update",Toast.LENGTH_SHORT).show();

            }
        });

    }




    //spinner will set up the new updated membership
    public void setUpdatedMembership() {
        //dropdown//////////////////
        dropdown = findViewById(R.id.membershipSpinnerAccount);

        //list of items for the spinner
        items = new String[]{"MEMBERSHIPS", "BJJ $30", "MUAY-THAI $30", "BOXING $30", "WRESTLING $30", "ALL ACCESS MMA $50"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);


        //select the users choice in the dropdown
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updatedMembership = dropdown.getSelectedItem().toString();

                if (updatedMembership.equalsIgnoreCase("BJJ $30"))
                    updatedMembership = "BJJ";
                else if (updatedMembership.equalsIgnoreCase("MUAY-THAI $30"))
                    updatedMembership = "MUAY-THAI";
                else if (updatedMembership.equalsIgnoreCase("BOXING $30"))
                    updatedMembership = "BOXING";
                else if (updatedMembership.equalsIgnoreCase("WRESTLING $30"))
                    updatedMembership = "WRESTLING";
                else if (updatedMembership.equalsIgnoreCase("ALL ACCESS MMA $50"))
                    updatedMembership = "MMA";


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    public void clickMenuNavigate(Class c){
        Intent intent = new Intent(AccountActivity.this,c);
        startActivity(intent);
    }


}