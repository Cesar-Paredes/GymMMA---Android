package com.example.mma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    //Cesar Paredes - start////////
    //this variable could be used to store the values on database
    String username;
    String password;
    String firstName;
    String lastName;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Intent loginHomeIntent = getIntent();


        //Cesar Paredes - Start /////////////////////////////
        //store the buttons
        EditText usernameBtn = (EditText) findViewById(R.id.editText_registerUsername);
        EditText passwordBtn = (EditText) findViewById(R.id.editText_registerPassword);
        EditText firstNameBtn = (EditText) findViewById(R.id.editText_registerFirstName);
        EditText lastNameBtn = (EditText) findViewById(R.id.editText_registerLastName);
        EditText emailBtn = (EditText) findViewById(R.id.editText_registerEmail);

        //Stores the the register button
        Button registerBtn = (Button) findViewById(R.id.button_registerRegister);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //im passing all values from the buttons to the strings
                username = usernameBtn.getText().toString();
                password = passwordBtn.getText().toString();
                firstName = firstNameBtn.getText().toString();
                lastName = lastNameBtn.getText().toString();
                email = emailBtn.getText().toString();

                //here we create an intent object, to pass data from this class to Confirmation_registration class
                Intent intent = new Intent(RegisterActivity.this, Confirmation_registration.class);

                intent.putExtra("USERNAME", username);
                intent.putExtra("PASSWORD", password);
                intent.putExtra("FIRSTNAME", firstName);
                intent.putExtra("LASTNAME", lastName);
                intent.putExtra("EMAIL", email);

                startActivity(intent);//starts the activity with intent
            }
            //Cesar Paredes - End/////////////////////////////////
        });



    }




}