package com.example.location;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.location.LocationResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BackgroundService extends BroadcastReceiver {

    public static String ACTION_PROCESS_UPDATE = "com.example.location.UPDATE_LOCATION";
    Double latitude = 0.0;
    Double longitude = 0.0;

    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.d("RAHAT","Called");
        if (intent != null) {

            String action = intent.getAction();
            if (ACTION_PROCESS_UPDATE.equals(action)) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    final Location location = result.getLastLocation();
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    String lati = String.valueOf(latitude);
                    String longi = String.valueOf(longitude);

                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("DoesApp");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //set code to retrieve data and and replace layout
                            Log.d("RAHAT", "DATABASE");

                            Boolean flag = false;
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                MyDoes myDoes = dataSnapshot1.getValue(MyDoes.class);
                                String scurLat = myDoes.latitudeDoes;
                                String scurLon = myDoes.longitudeDoes;


                                Double curLat = Double.parseDouble(scurLat);
                                Double curLon = Double.parseDouble(scurLon);

                                Location loc = new Location("YAMIN");
                                loc.setLatitude(curLat);
                                loc.setLongitude(curLon);
                                Log.d("RAHAT", "" + loc.distanceTo(location));
                                if(loc.distanceTo(location) <= 1000.0) flag = true;



                            }
                            callMeMayBe(context, flag);

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //set code to show an error

                        }
                    });






//                    Intent comp = new Intent(context,compareActivity.class);
//                    comp.putExtra("curLatitude",lati);
//                    comp.putExtra("curLongitude",longi);
//                    comp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(comp);


                }
            }
        }

    }

    void callMeMayBe(Context context,Boolean flag){
        if(flag){
            Toast.makeText(context, "HIIIIIII", Toast.LENGTH_SHORT).show();

        }
    }
}