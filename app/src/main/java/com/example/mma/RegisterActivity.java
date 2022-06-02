package com.example.mma;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


//CESAR PAREDES /////////////////////////////////////////////////////////////////

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    //EditTExt variables where im planning to store the editText layout these EditText variables
    EditText usernameBtn;
    EditText passwordBtn;
    EditText firstNameBtn;
    EditText lastNameBtn;
    EditText emailBtn;


    //register button, planning to store the the register button from layout to here
    Button registerBtn;


    //this variable could be used to store the values on database, I will store the input values from user
    // that are inside the EditText layouts into these variables
    String username;
    String password;
    String firstName;
    String lastName;
    String email;

    //this is for the dropdown
    Spinner dropdown;
    String[] items;
    String selectedMembership; //membership selected by user for registration



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //database conection to firebase
        mAuth = FirebaseAuth.getInstance();

        //dropdown//////////////////
        dropdown = findViewById(R.id.membershipSpinner);

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
                 selectedMembership = dropdown.getSelectedItem().toString();

                 if(selectedMembership.equalsIgnoreCase("BJJ $30"))
                     selectedMembership = "BJJ";
                 else if(selectedMembership.equalsIgnoreCase("MUAY-THAI $30"))
                     selectedMembership = "MUAY-THAI";
                 else if(selectedMembership.equalsIgnoreCase("BOXING $30"))
                     selectedMembership = "BOXING";
                 else if(selectedMembership.equalsIgnoreCase("WRESTLING $30"))
                     selectedMembership = "WRESTLING";
                 else if(selectedMembership.equalsIgnoreCase("ALL ACCESS MMA $50"))
                     selectedMembership = "MMA";



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });








        Intent loginHomeIntent = getIntent();

        //store the EditText layouts from .xml to these EditText variables
        usernameBtn = (EditText) findViewById(R.id.editText_registerUsername);
        passwordBtn = (EditText) findViewById(R.id.editText_registerPassword);
        firstNameBtn = (EditText) findViewById(R.id.editText_registerFirstName);
        lastNameBtn = (EditText) findViewById(R.id.editText_registerLastName);
        emailBtn = (EditText) findViewById(R.id.editText_registerEmail);

        //Stores the the register button from .xml to this button
         registerBtn = (Button) findViewById(R.id.button_registerRegister);



        //when we click register it does 3 things, it display the confirmation screen to user
        //and register the user in the database
        //and sends a confirmation email(not done yet)
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //im passing all values from the buttons to the strings, so values are ready for the confirmation screen
                username = usernameBtn.getText().toString().trim();
                password = passwordBtn.getText().toString().trim();
                firstName = firstNameBtn.getText().toString().trim();
                lastName = lastNameBtn.getText().toString().trim();
                email = emailBtn.getText().toString().trim();



                //VALIDATIONS FOR USER INPUT
                //validates that every entry has a value
                if(username.equalsIgnoreCase("")||password.equalsIgnoreCase("")||firstName.equalsIgnoreCase("")||lastName.equalsIgnoreCase("")||email.equalsIgnoreCase(""))
                    Toast.makeText(RegisterActivity.this, "Failed to register! Complete all entries!", Toast.LENGTH_LONG).show();
                else if(username.length()<3)
                    Toast.makeText(RegisterActivity.this, "Failed to register! Username too small!", Toast.LENGTH_LONG).show();
                else if(password.length()<7)
                    Toast.makeText(RegisterActivity.this, "Failed to register! Password too short, min 8 characters!", Toast.LENGTH_LONG).show();
                else if(firstName.length()<2 || lastName.length()<2)
                    Toast.makeText(RegisterActivity.this, "Failed to register! Name too small!", Toast.LENGTH_LONG).show();
                else if(!email.contains("@") || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    Toast.makeText(RegisterActivity.this, "Failed to register! Enter a valid email!", Toast.LENGTH_LONG).show();
                else if(selectedMembership.equalsIgnoreCase("MEMBERSHIPS"))
                    Toast.makeText(RegisterActivity.this, "Failed to register! Enter a valid membership!", Toast.LENGTH_LONG).show();
                else
                    registerUser();


            }



        });



    }

    //THIS WILL REGISTER THE USER IN THE FIREBASE DATABASE
    private void registerUser(){


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        //if the user have been registered, we send the user info to database
                        if(task.isSuccessful()){

                            //here i create a user object from the user java class i have created
                            User user = new User(username, password, firstName, lastName, email, selectedMembership,"");

                            //we call the firebase database object
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //if user have been registered successfully to the database
                                    if(task.isSuccessful()){

                                        //if its successful i will show the screen confirmation to the user
                                        //and also we create an intent object, to pass data from this class to Confirmation_registration class
                                        //it will show the user a confirmation screen
                                        Intent intent = new Intent(RegisterActivity.this, Confirmation_registration.class);

                                        intent.putExtra("USERNAME", username);
                                        intent.putExtra("PASSWORD", password);
                                        intent.putExtra("FIRSTNAME", firstName);
                                        intent.putExtra("LASTNAME", lastName);
                                        intent.putExtra("EMAIL", email);
                                        intent.putExtra("MEMBERSHIP", selectedMembership);

                                        startActivity(intent);//starts the activity with intent

                                    }

                                    else{
                                        Toast.makeText(RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });
    }





}