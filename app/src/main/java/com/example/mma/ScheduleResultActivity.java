package com.example.mma;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScheduleResultActivity extends AppCompatActivity {
    ///TOOLBAR OBJ (MR)
    private Toolbar toolbar;

    ///TEXTVIEW OBJ TO HOLD RECEIVED DAY FROM SCHEDULE ACTIVITY (MR)
    TextView receive_day;

   ///GLOBAL ARRAY TO HOLD DAY DATA ONCE FOUND (MR)
    String[][] arr = new String[0][0];

    ///TEMP DATA (MR)
    String[][] arrMonday = {
            {"18", "KICKBOXING","Bob"},
            {"19", "BOXING","John"},
            {"20", "BJJ","Bill"}
    };

    String[][] arrTuesday = {
            {"18", "KICKBOXING","Bob"},
            {"19", "BJJ","Bill"},
    };

    ////////////////////////////////////////////////////

    ///ON CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduleresult);

        /////////////////FIREBASE
        DatabaseReference database;

        ///INTENT (MR)
        Intent scheduleResultIntent = getIntent();

        //TOOLBAR (MR)
        toolbar=findViewById(R.id.mmaToolBar);
        setSupportActionBar(toolbar);

        ///RECYCLER VIEW (MR)
        RecyclerView RecyclerView;
        ScheduleAdapter Adapter;
        RecyclerView.LayoutManager LayoutManager;
        ArrayList<ScheduleBlock> exampleList = new ArrayList<>();

        ///SCHEDULE DAY FROM SCHEDULE ACTIVITY (MR)
        receive_day = (TextView)findViewById(R.id.textViewDay);
        String day = scheduleResultIntent.getStringExtra("Day");
        receive_day.setText(day);

        ///ADAPTER CREATION AND RECYCLER VIEW INPUT (MR)
        Adapter = new ScheduleAdapter(exampleList);
        RecyclerView = findViewById(R.id.recyclerView);
        LayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(LayoutManager);
        RecyclerView.setAdapter(Adapter);


        ///FIREBASE (MR)
        database = FirebaseDatabase.getInstance().getReference("Schedule");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ScheduleBlock user = dataSnapshot.getValue(ScheduleBlock.class);
                    exampleList.add(user);
                }
                Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        Intent intent = new Intent(ScheduleResultActivity.this,c);
        startActivity(intent);
    }

    ///////////////////
    public String createScheduleBlock(String time, String className, String trainer){
        String space1 = "h\t\t\t";
        String space2 = "\n\t\t\t\t\t\t\t-";
        String value =time+space1+className+space2+trainer;
        return value;
    }
}