package com.example.location;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class compareClass {
    DatabaseReference referenceDoes;
    DatabaseReference referenceSilencer;
    DatabaseReference referenceAlarm;
    public void toDoListCompare(double latitude,double longitude){
        referenceDoes= FirebaseDatabase.getInstance().getReference().child("DoesApp");
        referenceDoes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //set code to retrieve data and and replace layout
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    MyDoes myDoes=dataSnapshot1.getValue(MyDoes.class);
                    String latitude=myDoes.latitudeDoes;
                    String longitude=myDoes.longitudeDoes;



                }



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //set code to show an error
                /*Toast.makeText(compareClass.this,"No data",Toast.LENGTH_SHORT).show();*/
            }
        });
    }
    public void silencerCompare(double latitude,double longitude){
        referenceSilencer= FirebaseDatabase.getInstance().getReference().child("Silencer");
        referenceSilencer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //set code to retrieve data and and replace layout
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    MyDoes myDoes=dataSnapshot1.getValue(MyDoes.class);
                    String latitude=myDoes.latitudeDoes;
                    String longitude=myDoes.longitudeDoes;



                }



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //set code to show an error
                /*Toast.makeText(compareClass.this,"No data",Toast.LENGTH_SHORT).show();*/
            }
        });

    }
    public void alarmCompare(double latitude,double longitude){
        referenceAlarm= FirebaseDatabase.getInstance().getReference().child("Alarm");
        referenceAlarm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //set code to retrieve data and and replace layout
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    MyDoes myDoes=dataSnapshot1.getValue(MyDoes.class);
                    String latitude=myDoes.latitudeDoes;
                    String longitude=myDoes.longitudeDoes;



                }



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //set code to show an error
                /*Toast.makeText(compareClass.this,"No data",Toast.LENGTH_SHORT).show();*/
            }
        });

    }
}
