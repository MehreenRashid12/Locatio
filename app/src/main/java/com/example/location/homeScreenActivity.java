package com.example.location;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class homeScreenActivity extends AppCompatActivity {
    ImageButton silencerButton;
    ImageButton alarmButton;
    ImageButton toDoListButton;
    ImageButton exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        silencerButton = (ImageButton)findViewById(R.id.geoSilencerButtonID);
        alarmButton = (ImageButton)findViewById(R.id.geoAlarmButtonID);
        toDoListButton=findViewById(R.id.toDoListButtonID);
        exitButton=findViewById(R.id.exitButtonID);

        silencerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent silencerIntent = new Intent(homeScreenActivity.this,phoneSilencer.class);
                startActivity(silencerIntent);
            }
        });

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alarmIntent = new Intent(homeScreenActivity.this,alarm.class);
                startActivity(alarmIntent);
            }
        });
        toDoListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDOIntent = new Intent(homeScreenActivity.this,toDoList.class);
                startActivity(toDOIntent);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    onBackPressed();
                }
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        finish();
    }
}
