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

import java.util.ArrayList;

public class ScheduleResultActivity extends AppCompatActivity {
    ///TOOLBAR OBJ (MR)
    private Toolbar toolbar;

    ///TEXTVIEW OBJ TO HOLD RECEIVED DAY FROM SCHEDULE ACTIVITY (MR)
    TextView receive_day;

   ///GLOBAL ARRAY TO HOLD DAY DATA ONCE FOUND (MR)
    String[][] arr = new String[0][0];

    ////////////////////////////////////////////////////

    ///ON CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduleresult);

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

        ///SWITCH CASE BASED ON DAY SELECTED (MR)
        switch (day){
            case "Monday":
                arr = Schedule.arrMonday;
                break;
            case "Tuesday":
                arr = Schedule.arrTuesday;
                break;
            case "Wednesday":
                arr = Schedule.arrWednesday;
                break;
            case "Thursday":
                arr = Schedule.arrThursday;
                break;
            case "Friday":
                arr = Schedule.arrFriday;
                break;
            case "Saturday":
                arr = Schedule.arrSaturday;
                break;
            case "Sunday":
                arr = Schedule.arrSunday;
                break;
            default:
        }

        ///LOOP TO GENERATE EACH BLOCK TO EXAMPLE LIST (MR)
        for(int i = 0; i<arr.length;i++) {
            String time = arr[i][0];
            String name = arr[i][1];
            String trainer = arr[i][2];
            String data = createScheduleBlock(time, name, trainer);
            int pic = R.drawable.ic_boxing_small_red;

            switch(name){
                case("BOXING"):
                    pic = R.drawable.ic_boxing_small_red;
                    break;
                case("MUAY THAI"):
                    pic = R.drawable.ic_kickboxing_small_red;
                    break;
                case("BJJ"):
                    pic = R.drawable.ic_bjj_small_red;
                    break;
                default:
                    pic = R.drawable.ic_private_small_red;
            }
            exampleList.add(new ScheduleBlock(pic, data));
        }

        ///ADAPTER CREATION AND RECYCLER VIEW INPUT
        Adapter = new ScheduleAdapter(exampleList);
        RecyclerView = findViewById(R.id.recyclerView);
        LayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(LayoutManager);
        RecyclerView.setAdapter(Adapter);


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

//            case R.id.menuContact:
//                Toast.makeText(this,"Contact us!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(ContactActivity.class);
//                return true;

//            case R.id.menuNews:
//                Toast.makeText(this,"See whats happening in MMA!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(NewsActivity.class);
//                return true;

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