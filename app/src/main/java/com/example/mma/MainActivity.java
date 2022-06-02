package com.example.mma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //CESAR PAREDES - START/////////////////////////////////////

    private FirebaseAuth mAuth;

    EditText usernameBtn;
    EditText passwordBtn;
    EditText emailBtn;

    Button loginBtn;

    String username;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //button from layout to here
        loginBtn = findViewById(R.id.button_mainLogin);

        //TexView from layout to EditText variables
        usernameBtn = (EditText) findViewById(R.id.editText_mainUsername);
        passwordBtn = (EditText) findViewById(R.id.editText_mainPassword);
        emailBtn = (EditText) findViewById(R.id.editText_mainUsername);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //passing values to String variables
                username = usernameBtn.getText().toString().trim();
                password = passwordBtn.getText().toString();
                email = emailBtn.getText().toString().trim();

                if(email.equalsIgnoreCase(null) || email.equalsIgnoreCase("") || password.equalsIgnoreCase(null) || password.equalsIgnoreCase(""))
                    Toast.makeText(MainActivity.this, "Try again!", Toast.LENGTH_LONG).show();

                else
                    credentials();

            }
        });
    }



    //login button set up, from here to homeActivity
    //when user clicks login it will call this method
    public void credentials(){

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_LONG).show();

                    Intent loginHomeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(loginHomeIntent);
                }
                else
                    Toast.makeText(MainActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();

            }
        });

    }


    //register button onClick, it will go to the RegisterActivity so user can proceed to create an account
    public void clickRegister(View view){
        Intent loginRegisterIntent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(loginRegisterIntent);
    }


}