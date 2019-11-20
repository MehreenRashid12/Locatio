package com.example.location;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.location.LocationResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;
import static com.example.location.NotificationHandler.CHANNEL_1_ID;
import static com.example.location.NotificationHandler.CHANNEL_2_ID;
import static com.example.location.NotificationHandler.CHANNEL_3_ID;

public class BackgroundService extends BroadcastReceiver {


    public static String ACTION_PROCESS_UPDATE = "com.example.location.UPDATE_LOCATION";
    Double latitude = 0.0;
    Double longitude = 0.0;
    int notifyId1 = 1234;
    int notifyId2 = 5678;
    int notifyId3 = 9678;
    NotificationManagerCompat notificationManagerCompat;

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

                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("DoesApp");
                    reference.addValueEventListener(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //set code to retrieve data and and replace layout
                            Log.d("RAHAT", "TODO_DATABASE");

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

                                    String ar1=myDoes.locationDoes;


                                    String ar2=myDoes.getTitleDoes();
                                    String ar33=myDoes.dateDoes;
                                    String title=ar1;
                                    String message=ar2+" "+ar33;
                                    sendOnChannel1(ar1,ar2+" "+ar33,context);
                                }



                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //set code to show an error

                        }
                    });

                    DatabaseReference reference1= FirebaseDatabase.getInstance().getReference().child("Silencer");
                    reference1.addValueEventListener(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //set code to retrieve data and and replace layout
                            Log.d("RAHAT", "SILENT_DATABASE");

                            Boolean flag = false;
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                MySilencers mySilencers = dataSnapshot1.getValue(MySilencers.class);
                                String scurLat = mySilencers.latitude;
                                String scurLon = mySilencers.longitude;


                                Double curLat = Double.parseDouble(scurLat);
                                Double curLon = Double.parseDouble(scurLon);

                                Location loc = new Location("YAMIN");
                                loc.setLatitude(curLat);
                                loc.setLongitude(curLon);
                                Log.d("RAHAT", "" + loc.distanceTo(location));
                                if(loc.distanceTo(location) <= 300.0) {

                                    AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

                                    audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);


//
//                                    String ar1=myDoes.locationDoes;
//
//
//                                    String ar2=myDoes.getTitleDoes();
//                                    String ar33=myDoes.dateDoes;
//                                    String title=ar1;
//                                    String message=ar2+" "+ar33;
                                    String location=mySilencers.getSilencerTitle();
                                    String str="Your phone have been set to silent.";

                                    sendOnChannel2(location,str,context);
                                }



                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //set code to show an error

                        }
                    });


                    DatabaseReference reference2= FirebaseDatabase.getInstance().getReference().child("Alarm");
                    reference2.addValueEventListener(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //set code to retrieve data and and replace layout
                            Log.d("RAHAT", "ALARM_DATABASE");

                            Boolean flag = false;
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                MyAlarms myAlarms = dataSnapshot1.getValue(MyAlarms.class);
                                String scurLat = myAlarms.latitude;
                                String scurLon = myAlarms.longitude;


                                Double curLat = Double.parseDouble(scurLat);
                                Double curLon = Double.parseDouble(scurLon);

                                Location loc = new Location("YAMIN");
                                loc.setLatitude(curLat);
                                loc.setLongitude(curLon);
                                Log.d("RAHAT", "" + loc.distanceTo(location));
                                if(loc.distanceTo(location) <= 300.0) {

                                    MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
                                    mediaPlayer.start();

                                    String location=myAlarms.alarmTitle;
                                    String str="Your alarm is ringing.";

                                    sendOnChannel3(location,str,context);
                                }



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
    public void sendOnChannel1(String title,String message,Context context) {

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);



        String channelId = "some_channel_id";


        Notification notification = new Notification.Builder(context)
                .setContentTitle(title)

                .setContentText(message)
                .setSmallIcon(R.drawable.to_do_list_notification)
                .setChannelId(CHANNEL_1_ID)
                .build();
        // This code is for going to app on clicking the notification.
        notification.contentIntent= PendingIntent.getActivity(context, 0,
                new Intent(context, homeScreenActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        notificationManager.notify(notifyId1, notification);
        notifyId1++;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendOnChannel2(String title,String message,Context context) {

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);






        Notification notification = new Notification.Builder(context)
                .setContentTitle(title)

                .setContentText(message)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setChannelId(CHANNEL_2_ID)
                .build();
        // This code is for going to app on clicking the notification.
        notification.contentIntent= PendingIntent.getActivity(context, 0,
                new Intent(context, homeScreenActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        notificationManager.notify(notifyId2, notification);
        notifyId2++;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendOnChannel3(String title,String message,Context context) {

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);






        Notification notification = new Notification.Builder(context)
                .setContentTitle(title)

                .setContentText(message)
                .setSmallIcon(R.drawable.ic_stat_name111)
                .setChannelId(CHANNEL_3_ID)
                .build();
        // This code is for going to app on clicking the notification.
        notification.contentIntent= PendingIntent.getActivity(context, 0,
                new Intent(context, homeScreenActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        notificationManager.notify(notifyId3, notification);
        notifyId3++;
    }


//    void callMeMayBe(Context context,Boolean flag){
//        if(flag){
//            Toast.makeText(context, "HIIIIIII", Toast.LENGTH_SHORT).show();
//
//        }
//    }
}