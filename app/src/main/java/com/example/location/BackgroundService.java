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
    DatabaseReference reference;

    @Override
    public void onReceive(final Context context, Intent intent) {

        if(intent!= null){

            String action = intent.getAction();
            if(ACTION_PROCESS_UPDATE.equals(action)){
                LocationResult result = LocationResult.extractResult(intent);
                if(result!=null){

                    Location location = result.getLastLocation();
                    final double latitude = location.getLatitude();
                    final double longitude = location.getLongitude();

                    reference= FirebaseDatabase.getInstance().getReference().child("DoesApp");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //set code to retrieve data and and replace layout
                            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                            {
                                MyDoes myDoes=dataSnapshot1.getValue(MyDoes.class);
                                String latitudeDoes=myDoes.latitudeDoes;
                                String longitudeDoes=myDoes.longitudeDoes;
                                double latDoes = Double.valueOf(latitudeDoes);
                                double lonDoes = Double.valueOf(longitudeDoes);

                                if(latitude>(latDoes-0.001) && latitude<(latDoes+0.001) && longitude>(lonDoes-0.001) && longitude<(lonDoes+0.001)){
                                    Toast.makeText(context,"You have reached",Toast.LENGTH_SHORT).show();

                                }



                            }




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }


                       /* @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //set code to show an error
                            Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_SHORT).show();
                        }*/
                    });
                }
            }
        }
    }
}
