package com.example.mma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickLogin(View view){
        Intent loginHomeIntent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(loginHomeIntent);
    }

    public void clickRegister(View view){
        Intent loginRegisterIntent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(loginRegisterIntent);
    }


}