package com.example.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewSilencerAdd extends AppCompatActivity {

    String valueLatitude,valueLongitude;
    TextView titlepage,addtitle;
    EditText silencertitle;
    TextView locationdoes;
    Button btnSaveTask,btnCancel,btnMaps;
    DatabaseReference reference;
    Integer doesNum=new Random().nextInt();
    String key=Integer.toString(doesNum);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_silencer_add);


        titlepage=findViewById(R.id.titlePage);

        addtitle=findViewById(R.id.addtitle);


        silencertitle=findViewById(R.id.silencerTitle);


        btnSaveTask=findViewById(R.id.btnSaveTask);
        btnCancel=findViewById(R.id.btnCancel);
        btnMaps=findViewById(R.id.mapsilencerbutton);

        locationdoes=findViewById(R.id.locationdoes);
        reference= FirebaseDatabase.getInstance().getReference().child("Silencer").child("Silencer"+doesNum);


        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert data to database

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("silencerTitle").setValue(silencertitle.getText().toString());

                        dataSnapshot.getRef().child("location").setValue(locationdoes.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(key);
                        dataSnapshot.getRef().child("latitude").setValue(valueLatitude);
                        dataSnapshot.getRef().child("longitude").setValue(valueLongitude);
                        Intent a=new Intent(NewSilencerAdd.this,phoneSilencer.class);
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
                Intent a=new Intent(NewSilencerAdd.this,phoneSilencer.class);
                startActivity(a);

            }
        });
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewSilencerAdd.this,MapsActivity.class);

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


    @Override
    public void onBackPressed(){
        Intent moveback =
                new Intent(NewSilencerAdd.this, phoneSilencer.class);
        startActivity(moveback);
        finish();
    }
}
