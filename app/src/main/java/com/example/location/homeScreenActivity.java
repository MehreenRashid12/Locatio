package com.example.location;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class homeScreenActivity extends AppCompatActivity {
    ImageButton silencerButton;
    ImageButton alarmButton;
    ImageButton toDoListButton;
    ImageButton exitButton;
    private int LOCATION_PERMISSION_CODE=133;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        silencerButton = (ImageButton)findViewById(R.id.geoSilencerButtonID);
        alarmButton = (ImageButton)findViewById(R.id.geoAlarmButtonID);
        toDoListButton=findViewById(R.id.toDoListButtonID);
        exitButton=findViewById(R.id.exitButtonID);

        if(ContextCompat.checkSelfPermission(homeScreenActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(homeScreenActivity.this,"Permission granted already",Toast.LENGTH_SHORT).show();
        }
        else{
            requestLocationPermission();
        }

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

    private void requestLocationPermission(){

        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(this)
                    .setTitle("Location permission")
                    .setMessage("Allow Locatio app to access this devices location?")
                    .setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(homeScreenActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    LOCATION_PERMISSION_CODE);


                        }
                    })
                    .setNegativeButton("DENY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            /*Toast.makeText(this,"This app cannot run without Location permission",Toast.LENGTH_SHORT);*/
                        }
                    }).create().show();


        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==LOCATION_PERMISSION_CODE){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(homeScreenActivity.this,"Permission Granted",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(homeScreenActivity.this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }



}
