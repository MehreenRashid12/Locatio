package com.example.location;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



import android.os.Bundle;

public class toDoList extends AppCompatActivity {
    TextView titlePage,subTitlePage,endPage;
    Button btnAddNew,btnMap;
    DatabaseReference reference;

    RecyclerView ourdoes;
    ArrayList<MyDoes> list;
    DoesAdapter doesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        titlePage=findViewById(R.id.titlePageID);
        subTitlePage=findViewById(R.id.subtitlePageID);
        endPage=findViewById(R.id.endPage);
        btnAddNew=findViewById(R.id.btnAddNew);
        btnMap=findViewById(R.id.maptodolistbutton);
        /*deleteButton=findViewById(R.id.deletebutton);*/

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a =new Intent(toDoList.this,NewTaskAdd.class);
                startActivity(a);
            }
        });

        //working with data
        ourdoes=findViewById(R.id.ourDoes);
        ourdoes.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<MyDoes>();

        //get data from firebase

        reference= FirebaseDatabase.getInstance().getReference().child("DoesApp");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //set code to retrieve data and and replace layout
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    MyDoes p=dataSnapshot1.getValue(MyDoes.class);
                    list.add(p);


                }
                doesAdapter =new DoesAdapter(toDoList.this,list);
                ourdoes.setAdapter(doesAdapter);
                doesAdapter.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //set code to show an error
                Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_SHORT).show();
            }
        });




    }
}
