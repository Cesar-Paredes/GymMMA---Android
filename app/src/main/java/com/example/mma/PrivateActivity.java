package com.example.mma;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PrivateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{

    ///TOOLBAR (MR)
    private Toolbar toolbar;

    ///OBJ FOR DATE SELECTIONS
    private TextView dateTextDate;
    private TextView dateTextWeekday;
    private TextView dateTextClass;
    private TextView textViewUsername;
    private EditText editTextRequests;


    ///FIREBASE
    String userEmail;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser     currentUser;
    String emailFromDatabase="";

    //////////////// ON CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);

        ///INTENT (MR)
        Intent loginHomeIntent = getIntent();

        ///TOOLBAR (MR)
        toolbar=findViewById(R.id.mmaToolBar);
        setSupportActionBar(toolbar);

        ///DATE SELECTION (MR)
        dateTextDate = findViewById(R.id.dateTextDate);
        dateTextWeekday = findViewById(R.id.dateTextWeekday);
        dateTextClass = findViewById(R.id.dateTextClass);
        textViewUsername = findViewById(R.id.textViewUsername);
        editTextRequests = findViewById(R.id.editTextRequests);

        findViewById(R.id.dateDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        ///FIREBASE INSERT
        Button btn_insert = findViewById(R.id.buttonPrivateSubmit);
        DOAPrivateBooking doa = new DOAPrivateBooking();

        btn_insert.setOnClickListener(v->
        {
                    if(dateTextClass.getText().toString().equals("-----------------------------------------------------")){
                        Toast.makeText(this, "Please select a valid class", Toast.LENGTH_SHORT).show();
                    }
                    else if(dateTextClass.getText().toString().equals("----PICK A TIME-TRAINER-CLASS----")){
                        Toast.makeText(this, "Please select a valid class", Toast.LENGTH_SHORT).show();
                    }
                    else if(dateTextClass.getText().toString().equals("----NO CLASSES----")){
                        Toast.makeText(this, "Please select a valid class", Toast.LENGTH_SHORT).show();
                    }
                    else if(dateTextWeekday.getText().length()==0){
                        Toast.makeText(this, "Please select a valid date", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        PrivateBooking pb = new PrivateBooking(
                                textViewUsername.getText().toString(),
                                dateTextDate.getText().toString(),
                                dateTextClass.getText().toString(),
                                editTextRequests.getText().toString()
                        );
                        doa.add(pb).addOnSuccessListener(suc ->
                        {
                            Toast.makeText(this, "Booked", Toast.LENGTH_SHORT).show();
                            Intent homePrivateIntent = new Intent(PrivateActivity.this, PrivateActivity.class);
                            startActivity(homePrivateIntent);

                        }).addOnFailureListener(er -> {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                        });
                    }
        });


        ////FIREBASE GET EMAIL
        getEmailCurrentUser();


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
        Intent intent = new Intent(PrivateActivity.this,c);
        startActivity(intent);
    }



    //////////////// DATE PICKER
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        String userMembership = "ALL";
        String date = dayOfMonth + "-" + (month+1) + "-" + year;
        dateTextDate.setText(date);
        SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
        String weekday="";

        try {
            Date date1 = inFormat.parse(date);
            SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
            weekday = outFormat.format(date1);
            dateTextWeekday.setText(weekday);
            Spinner spinner = findViewById(R.id.spinner);

            switch(weekday){
                case "Monday":
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.privateMonday, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(this);
                    break;
                case "Tuesday":
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.privateTuesday, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter2);
                    spinner.setOnItemSelectedListener(this);
                    break;
                default:
                    ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.privateNoClasses, android.R.layout.simple_spinner_item);
                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter3);
                    spinner.setOnItemSelectedListener(this);
                    dateTextClass.setText("ERROR");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        dateTextClass.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    ////FIREBASE READ EMAIL
    public void getEmailCurrentUser(){
        //current user
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);

        //initialize the firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        //Gets the current user
        databaseReference = firebaseDatabase.getReference("Users").child(currentUser.getUid());

        //reference for our database, gets the current users value
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    emailFromDatabase = String.valueOf(snapshot.child("email").getValue());
                    textViewUsername.setText(emailFromDatabase);
                }
                else
                    Toast.makeText(PrivateActivity.this,"no data!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PrivateActivity.this,"Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    ////VIEW BOOKINGS
    public void viewBookings(View view){
        Intent i = new Intent(PrivateActivity.this,PrivateBookingResultActivity.class);
        startActivity(i);
    }

///
}
