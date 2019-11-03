package com.example.location;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditTaskDesk extends AppCompatActivity {
    EditText titleDoes,dateDoes;
    TextView locationDoes;
    Button btnSaveUpdate,btnDelete;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titleDoes=findViewById(R.id.titledoes);
        dateDoes=findViewById(R.id.datedoes);
        locationDoes=findViewById(R.id.locationdoes);

        btnSaveUpdate=findViewById(R.id.btnSaveUpdate);
        btnDelete=findViewById(R.id.btnDelete);

        //get value from previous page
        titleDoes.setText(getIntent().getStringExtra("titleDoes"));
        dateDoes.setText(getIntent().getStringExtra("dateDoes"));

        final String keykeydoes=getIntent().getStringExtra("keyDoes");
        reference= FirebaseDatabase.getInstance().getReference().child("DoesApp").child("Does"+keykeydoes);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent a =new Intent(EditTaskDesk.this,toDoList.class);
                            startActivity(a);
                            Toast.makeText(getApplicationContext(),"Delete Successful!",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Delete Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        /*btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert data to database

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titleDoes").setValue(titleDoes.getText().toString());
                        dataSnapshot.getRef().child("dateDoes").setValue(dateDoes.getText().toString());
                        dataSnapshot.getRef().child("keyDoes").setValue(keykeydoes);

                        Intent a=new Intent(EditTaskDesk.this,MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });*/
    }
}
