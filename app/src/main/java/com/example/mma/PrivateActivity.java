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
                        Toast.makeText(this, "Please enter a valid class", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                        });
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



///
}
