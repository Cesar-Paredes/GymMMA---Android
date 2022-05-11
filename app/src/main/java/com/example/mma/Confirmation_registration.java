package com.example.mma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Confirmation_registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_registration);

        //Cesar Paredes - Start/////////////////////////////////
        Intent intent = getIntent();

        String username = intent.getStringExtra("USERNAME");
        String password = intent.getStringExtra("PASSWORD");
        String firstName = intent.getStringExtra("FIRSTNAME");
        String lastName = intent.getStringExtra("LASTNAME");
        String email = intent.getStringExtra("EMAIL");

        //display the data
        TextView display = (TextView) findViewById(R.id.displayData);

        display.setText("USERNAME: " + username + "\n" +
                        "PASSWORD: " + password  + "\n" +
                        "FIRST NAME: " + firstName   + "\n" +
                        "LAST NAME: " + lastName  + "\n" +
                        "EMAIL: " + email);

        //Cesar Paredes - End/////////////////////////////////

    }
}