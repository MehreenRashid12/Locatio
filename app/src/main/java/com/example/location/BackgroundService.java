package com.example.location;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.location.LocationResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BackgroundService extends BroadcastReceiver {

    public static String ACTION_PROCESS_UPDATE = "com.example.currentlocation.UPDATE_LOCATION";

    @Override
    public void onReceive(final Context context, Intent intent) {

        if (intent != null) {

            String action = intent.getAction();
            if (ACTION_PROCESS_UPDATE.equals(action)) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    Location location = result.getLastLocation();
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    final String lati = String.valueOf(latitude);
                    String longi = String.valueOf(longitude);
                    String loc = lati + "/" + longi;
                    Toast.makeText(context, loc, Toast.LENGTH_SHORT).show();
                    final String latiti = lati;
                    final String longigi = longi;
                    System.out.println(latiti + longigi);


                }
            }
        }
    }
}
