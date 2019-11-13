package com.example.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class phoneSilencer extends AppCompatActivity {

    TextView titlePage,subTitlePage,endPage;
    Button btnAddNew,btnMap;
    DatabaseReference reference;

    RecyclerView oursilencers;
    ArrayList<MySilencers> list;
    SilencerAdapter silencerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_silencer);
        titlePage=findViewById(R.id.titlePageID);
        subTitlePage=findViewById(R.id.subtitlePageID);
        endPage=findViewById(R.id.endPage);
        btnAddNew=findViewById(R.id.btnAddNew);
        btnMap=findViewById(R.id.maptodolistbutton);
        /*deleteButton=findViewById(R.id.deletebutton);*/

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a =new Intent(phoneSilencer.this,NewSilencerAdd.class);
                startActivity(a);
            }
        });

        //working with data
        oursilencers=findViewById(R.id.ourSilencers);
        oursilencers.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<MySilencers>();

        //get data from firebase

        reference= FirebaseDatabase.getInstance().getReference().child("Silencer");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                //set code to retrieve data and and replace layout
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    MySilencers p=dataSnapshot1.getValue(MySilencers.class);
                    list.add(p);


                }
                silencerAdapter=new SilencerAdapter(phoneSilencer.this,list);
                oursilencers.setAdapter(silencerAdapter);
                silencerAdapter.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //set code to show an error
                Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_SHORT).show();
            }
        });




    }


    @Override
    public void onBackPressed(){
        finish();
        Intent moveback =
                new Intent(phoneSilencer.this, homeScreenActivity.class);
        startActivity(moveback);

    }
}
