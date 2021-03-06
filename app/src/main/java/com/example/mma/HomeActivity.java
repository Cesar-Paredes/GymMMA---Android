package com.example.mma;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent loginHomeIntent = getIntent();
        toolbar=findViewById(R.id.mmaToolBar);
        setSupportActionBar(toolbar);
        resultTextView = findViewById(R.id.resultTextView);

        try {
            DownloadTask task = new DownloadTask();
            String encodedCityname = URLEncoder.encode("montreal", "UTF-8");

            task.execute("https://api.openweathermap.org/data/2.5/weather?q=" + encodedCityname + "&appid=c03b91e5ecb2c579c013ae5486510bcb");

            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Could not find weather :(",
                    Toast.LENGTH_SHORT).show();
        }


    }

    //////////////// TOP NAV BAR MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    //sends the user to the selection of choice, from home menu page
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
        Intent intent = new Intent(HomeActivity.this,c);
        startActivity(intent);
    }



    //////HOME PAGE ACTIVITY BUTTONS
    public void clickSchedule(View view) {
        Intent homeScheduleIntent = new Intent(HomeActivity.this, ScheduleActivity.class);
        startActivity(homeScheduleIntent);
    }

    public void clickGroup(View view) {


        Intent homeGroupIntent = new Intent(HomeActivity.this, GroupActivity.class);
        startActivity(homeGroupIntent);
    }

    public void clickPrivate(View view) {
        Intent homePrivateIntent = new Intent(HomeActivity.this, PrivateActivity.class);
        startActivity(homePrivateIntent);
    }

    public void clickAccount(View view) {
        Intent homeAccountIntent = new Intent(HomeActivity.this, AccountActivity.class);
        startActivity(homeAccountIntent);
    }

    public void clickAbout(View view) {
        Intent homeAboutIntent = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(homeAboutIntent);
    }

    public void clickContact(View view) {
        Intent homeContactIntent = new Intent(HomeActivity.this, ContactActivity.class);
        startActivity(homeContactIntent);
    }

    public void clickNews(View view) {
        Intent homeNewsIntent = new Intent(HomeActivity.this, NewsActivity.class);
        startActivity(homeNewsIntent);
    }

    public void clickTrainers(View view) {
        Intent homeTrainersIntent = new Intent(HomeActivity.this, TrainersActivity.class);
        startActivity(homeTrainersIntent);
    }


//    public void getMembershipFromDatabase(){
//
//        //added by Cesar Paredes//
//        //reference for our database, gets the current users value for membership
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //we store the user membership from the database to this String variable
//                membershipFromDatabase = snapshot.child("membership").getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(HomeActivity.this,"Error!",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        GroupActivity groupActivity = new GroupActivity();
//        groupActivity.userSelection(membershipFromDatabase);
//    }


    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpsURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Could not find weather :(",
                        Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.i("JSON", s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");
                Log.i("Weather Content", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);

                String message = "";

                for(int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);

//                    Log.i("main", jsonPart.getString("main"));
//                    Log.i("description", jsonPart.getString("description"));

                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");

                    if(!main.equals("") && !description.equals("")) {
                        message += main + ":" + description + "\r\n";
                    }
                }

                if(!message.equals("")) {
                    resultTextView.setText(message);
                } else {
                    Toast.makeText(getApplicationContext(), "Could not find weather :(",
                            Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Could not find weather :(",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}