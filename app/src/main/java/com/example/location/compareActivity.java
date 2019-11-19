package com.example.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class compareActivity extends AppCompatActivity {
    DatabaseReference referenceDoes;
    String curLat,curLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent comp = getIntent();
        //Double curLat = comp.getDoubleExtra("curLatitude", 0.0d);
        //Double curlon = comp.getDoubleExtra("curLongitude",0.0d);
        curLat="23.8103";
        curLon="90.4125";
        try {
            curLat = comp.getStringExtra("curLatitude");
            curLon = comp.getStringExtra("curLongitude");
        }catch (Exception e){}


        referenceDoes = FirebaseDatabase.getInstance().getReference().child("DoesApp");
        referenceDoes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    MyDoes myDoes;
                    myDoes = dataSnapshot1.getValue(MyDoes.class);
                    String slat = myDoes.latitudeDoes;
                    String slon = myDoes.longitudeDoes;
                    Double lat = Double.valueOf(slat);
                    Double lon = Double.valueOf(slon);
                    Double lati = Double.valueOf(curLat);
                    Double longi = Double.valueOf(curLon);

                    if (Math.abs(lati - lat) < 0.001 && Math.abs(longi - lon) < 0.001) {
                        Toast.makeText(compareActivity.this, "hiiiiiiii", Toast.LENGTH_SHORT).show();
                    }
                }

                    }
                catch (Exception e) {
                    System.out.println(e);
                }





                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
