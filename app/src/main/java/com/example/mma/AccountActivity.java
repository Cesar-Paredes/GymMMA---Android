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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;



//CESAR PAREDES///////////////////////////////////////////////

public class AccountActivity extends AppCompatActivity {



    String firstName="";
    String lastName="";
    String userName="";
    String password="";
    String email="";
    String membership="";

    AuthCredential credentials;



    String updatedMembership ="";

    TextView firstnameText;
    TextView lastnameText;
    TextView usernameText;
    TextView passwordText;
    TextView emailText;

    TextView newPassText;
    String newPass;


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
        Button delete = findViewById(R.id.deleteAccount);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updatePasswordAuth();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this,DeleteAccount.class);
                startActivity(intent);
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
                    membershipToSpinner();//reads membership variable we got from database in the getAccountDetails() method and store it in the spinner


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

        //so here layout and String variables get updated, user will see updated data now

        //PASS TEXTVIEW FROM LAYOUT TO HERE
        firstnameText = (TextView) findViewById(R.id.firstNameText);
        lastnameText = (TextView) findViewById(R.id.lastNameText);
        usernameText = (TextView) findViewById(R.id.usernameText);
        emailText = (TextView) findViewById(R.id.emailText);
        passwordText = (TextView) findViewById(R.id.passwordText);
        newPassText = (TextView) findViewById(R.id.passwordText2) ;




        //now we pass the TextViews to the our strings variables
        firstName = firstnameText.getText().toString().trim();
        lastName = lastnameText.getText().toString().trim();
        userName = usernameText.getText().toString().trim();
        password = passwordText.getText().toString().trim();
        newPass = newPassText.getText().toString().trim();
        email = emailText.getText().toString().trim();






//        button2 = (Button) findViewById(R.id.button2);


        //this is the updated info that will go to our database
        HashMap user = new HashMap();

        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("username", userName);
        user.put("membership", membership);

        if(newPass.equalsIgnoreCase("")||newPass.equalsIgnoreCase(null))
            user.put("password", password);
        else{
            user.put("password", newPass);
            Toast.makeText(AccountActivity.this,"newPass!",Toast.LENGTH_SHORT).show();
        }



        //initialize the firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        //goes to our table in database
        databaseReference = firebaseDatabase.getReference("Users");

        //updates the info with HashMap user
        databaseReference.child(currentUser.getUid()).updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful()){


                    Toast.makeText(AccountActivity.this,"Updated successfully!",Toast.LENGTH_SHORT).show();


                }
                else
                    Toast.makeText(AccountActivity.this,"Failed to Update",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void updatePasswordAuth(){

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        newPassText = (TextView) findViewById(R.id.passwordText2);
        newPass = newPassText.getText().toString();


        //we put the email with the old passs that we want to change
        credentials = EmailAuthProvider.getCredential(email,password);

        if(newPass.equalsIgnoreCase(null)||newPass.equalsIgnoreCase("")){
            updateFields();
        }
        else
        {
            currentUser.reauthenticate(credentials).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    //if able to connect
                    if(task.isSuccessful()){

                        currentUser.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                //if password is updated
                                if (task.isSuccessful()){

                                    //the new password becomes the password;
                                    password = newPass;
                                    updateFields();

                                    //retrieve data from the just updated database and display new updated data in the app
//                                getAccountDetails();
                                    Toast.makeText(AccountActivity.this,"Credentials Updated!",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(AccountActivity.this,"something went wrong!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else
                        Toast.makeText(AccountActivity.this,"credentials not updated!",Toast.LENGTH_SHORT).show();
                }
            });

        }
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
        Intent intent2 = new Intent(AccountActivity.this,c);
        startActivity(intent2);
    }


}