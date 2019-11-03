//NewTaskAdd
package com.example.location;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskAdd extends AppCompatActivity {
    String valueLatitude,valueLongitude;
    TextView titlepage,addtitle,adddate;
    EditText titledoes,datedoes;
    TextView locationdoes;
    Button btnSaveTask,btnCancel,btnMaps;
    DatabaseReference reference;
    Integer doesNum=new Random().nextInt();
    String keydoes=Integer.toString(doesNum);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_add);


        titlepage=findViewById(R.id.titlePage);

        addtitle=findViewById(R.id.addtitle);
        adddate=findViewById(R.id.adddate);

        titledoes=findViewById(R.id.titledoes);
        datedoes=findViewById(R.id.datedoes);

        btnSaveTask=findViewById(R.id.btnSaveTask);
        btnCancel=findViewById(R.id.btnCancel);
        btnMaps=findViewById(R.id.maptodolistbutton);

        locationdoes=findViewById(R.id.locationdoes);
        reference= FirebaseDatabase.getInstance().getReference().child("DoesApp").child("Does"+doesNum);


        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert data to database

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titleDoes").setValue(titledoes.getText().toString());
                        dataSnapshot.getRef().child("dateDoes").setValue(datedoes.getText().toString());
                        dataSnapshot.getRef().child("locationDoes").setValue(locationdoes.getText().toString());
                        dataSnapshot.getRef().child("keyDoes").setValue(keydoes);
                        dataSnapshot.getRef().child("latitudeDoes").setValue(valueLatitude);
                        dataSnapshot.getRef().child("longitudeDoes").setValue(valueLongitude);
                        Intent a=new Intent(NewTaskAdd.this,toDoList.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(NewTaskAdd.this,toDoList.class);
                startActivity(a);

            }
        });
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewTaskAdd.this,MapsActivity.class);

                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {


            String value=data.getStringExtra("keyText");
            valueLatitude=data.getStringExtra("keyLatitude");
            valueLongitude=data.getStringExtra("keyLongitude");

            locationdoes.setText(value);
        }

    }
}
