package com.example.location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class homeScreenActivity extends AppCompatActivity {
    ImageButton silencerButton;
    ImageButton alarmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        silencerButton = (ImageButton)findViewById(R.id.geoSilencerButtonID);
        alarmButton = (ImageButton)findViewById(R.id.geoAlarmButtonID);

        silencerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent silencerIntent = new Intent(homeScreenActivity.this,MapsActivity.class);
                startActivity(silencerIntent);
            }
        });

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alarmIntent = new Intent(homeScreenActivity.this,MapsActivity.class);
                startActivity(alarmIntent);
            }
        });

    }
}
