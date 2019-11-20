package com.example.location;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.location.LocationResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;
import static com.example.location.NotificationHandler.CHANNEL_1_ID;

public class BackgroundService extends BroadcastReceiver {


    public static String ACTION_PROCESS_UPDATE = "com.example.location.UPDATE_LOCATION";
    Double latitude = 0.0;
    Double longitude = 0.0;
    int notifyId1 = 1234;
    int notifyId2 = 5678;
    int notifyId3 = 9678;

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
                        @RequiresApi(api = Build.VERSION_CODES.O)
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
                                if(loc.distanceTo(location) <= 300.0) {
                                    flag = true;
                                    String ar1=myDoes.locationDoes;


                                    String ar2=myDoes.getTitleDoes();
                                    String ar33=myDoes.dateDoes;
                                    sendOnChannel1(ar1,ar2+" "+ar33);
                                }



                            }
                            //callMeMayBe(context, flag);
                            if(flag){
                                Toast.makeText(context, "HIIIIIII", Toast.LENGTH_SHORT).show();



                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //set code to show an error

                        }
                    });









                }
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendOnChannel1(String title,String message) {


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "some_channel_id";


        Notification notification = new Notification.Builder(MainActivity.this)
                .setContentTitle(title)

                .setContentText(message)
                .setSmallIcon(R.drawable.to_do_list_icon_notification)
                .setChannelId(CHANNEL_1_ID)
                .build();
        // This code is for going to app on clicking the notification.
        notification.contentIntent= PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        notificationManager.notify(notifyId1, notification);
        notifyId1++;
    }

//    void callMeMayBe(Context context,Boolean flag){
//        if(flag){
//            Toast.makeText(context, "HIIIIIII", Toast.LENGTH_SHORT).show();
//
//        }
//    }
}