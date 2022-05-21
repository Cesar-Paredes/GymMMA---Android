package com.example.mma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Confirmation_registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_registration);

        //Cesar Paredes - Start/////////////////////////////////


        Intent intent = getIntent();

        //we receive the data from the register_activity and store it in String variables
        String username = intent.getStringExtra("USERNAME");
        String password = "******";
        String firstName = intent.getStringExtra("FIRSTNAME");
        String lastName = intent.getStringExtra("LASTNAME");
        String email = intent.getStringExtra("EMAIL");
        String membership = intent.getStringExtra("MEMBERSHIP");

        //display the data
        TextView display = (TextView) findViewById(R.id.displayData);




        display.setText("USERNAME:   " + username + "\n" +
                        "PASSWORD:   " + password  + "\n" +
                        "FIRST NAME: " + firstName   + "\n" +
                        "LAST NAME:  " + lastName  + "\n" +
                        "EMAIL:            " + email+ "\n" +
                        "MEMBERSHIP: " + membership);

        //this will change the screen automatically after a certain time, it will go to home page
        int delay = 5000;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Confirmation_registration.this, HomeActivity.class);
                startActivity(intent);
            }
        }, delay);


        //Cesar Paredes - End/////////////////////////////////

    }
}