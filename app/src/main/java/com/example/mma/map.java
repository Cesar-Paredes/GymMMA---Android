package com.example.mma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize fragment
        Fragment fragment=new MapFragment();

        // Open fragment
        //framelayout is from activity_map.xml
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.frameLayout,fragment)
                .commit();
    }

//    //////////////// TOP NAV BAR MENU
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//
//            case R.id.menuHome:
//                Toast.makeText(this,"Welcome home!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(HomeActivity.class);
//                return true;
//
//            case R.id.menuSchedule:
//                Toast.makeText(this,"See this weeks schedule!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(ScheduleActivity.class);
//                return true;
//
//            case R.id.menuBookGroup:
//                Toast.makeText(this,"Book a group class!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(GroupActivity.class);
//                return true;
//
//            case R.id.menuBookPrivate:
//                Toast.makeText(this,"Book a private class!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(PrivateActivity.class);
//                return true;
//
//            case R.id.menuAccount:
//                Toast.makeText(this,"View your account details!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(AccountActivity.class);
//                return true;
//
//            case R.id.menuAbout:
//                Toast.makeText(this,"Get to know us!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(AboutActivity.class);
//                return true;
//
//            case R.id.menuContact:
//                Toast.makeText(this,"Contact us!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(ContactActivity.class);
//                return true;
//
//            case R.id.menuNews:
//                Toast.makeText(this,"See whats happening in MMA!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(NewsActivity.class);
//                return true;
//
//            case R.id.menuTrainers:
//                Toast.makeText(this,"View our trainers!",Toast.LENGTH_SHORT).show();
//                clickMenuNavigate(TrainersActivity.class);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }
//
//    public void clickMenuNavigate(Class c){
//        Intent intent = new Intent(map.this,c);
//        startActivity(intent);
//    }



}