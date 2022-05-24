package com.example.mma;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

public class PrivateBookingResultActivity extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView recyclerView;
    DatabaseReference database;
    PrivateBookingAdapter myAdapter;
    ArrayList<PrivateBooking> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privatebooking);
        Intent loginHomeIntent = getIntent();
        toolbar=findViewById(R.id.mmaToolBar);
        setSupportActionBar(toolbar);

        ///FIREBASE -> RECYCLERVIEW
        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("PrivateBooking");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new PrivateBookingAdapter(this,list);
        recyclerView.setAdapter(myAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PrivateBooking user = dataSnapshot.getValue(PrivateBooking.class);
                    if(user.getUsername().equals("gsp@gmail.com")){
                        list.add(user);
                    }
                }
                myAdapter.notifyDataSetChanged();
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
        Intent intent = new Intent(PrivateBookingResultActivity.this,c);
        startActivity(intent);
    }





 ///
}

