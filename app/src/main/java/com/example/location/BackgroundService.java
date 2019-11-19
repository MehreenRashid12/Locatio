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

    public static String ACTION_PROCESS_UPDATE = "com.example.location.UPDATE_LOCATION";
    Double latitude = 0.0;
    Double longitude = 0.0;

    @Override
    public void onReceive(final Context context, Intent intent) {

        if (intent != null) {

            String action = intent.getAction();
            if (ACTION_PROCESS_UPDATE.equals(action)) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    Location location = result.getLastLocation();
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    String lati = String.valueOf(latitude);
                    String longi = String.valueOf(longitude);

                    Intent comp = new Intent(context,compareActivity.class);
                    comp.putExtra("curLatitude",lati);
                    comp.putExtra("curLongitude",longi);
                    comp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(comp);


                }
            }
        }

    }
}