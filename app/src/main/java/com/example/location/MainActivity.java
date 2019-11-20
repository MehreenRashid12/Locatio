package com.example.location;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnSet;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSet = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = Integer.parseInt(editText.getText().toString());
                Intent i = new Intent(MainActivity.this,Alarm1.class);
                PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),0,i,0);
                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+time*1000,pi);
            }
        });
    }
}
