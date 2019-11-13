package com.example.location;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyAlarms> myAlarms;

    public AlarmAdapter(Context c, ArrayList<MyAlarms> p){
        context =c;
        myAlarms=p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.name_of_alarms,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        myViewHolder.alarmTitle.setText(myAlarms.get(position).getAlarmTitle());

        myViewHolder.location.setText(myAlarms.get(position).getLocation());
        final String getKeyDoes=myAlarms.get(position).getKey();




        myViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                myAlarms.remove(position);
                notifyItemRemoved(position);
                FirebaseDatabase.getInstance().getReference().child("Alarm").child("Alarm"+getKeyDoes).setValue(null);
                notifyDataSetChanged();
                Toast.makeText(context, "Delete Successful!", Toast.LENGTH_SHORT).show();






            }
        });




    }

    @Override
    public int getItemCount() {
        return myAlarms.size();
    }

    class MyViewHolder extends ViewHolder{
        TextView alarmTitle,location;
        ImageButton deleteButton;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            deleteButton=(ImageButton) itemView.findViewById(R.id.deletebutton);
            alarmTitle= (TextView)itemView.findViewById(R.id.alarmTitle);

            location=itemView.findViewById(R.id.alarmLocation);


        }
    }

}
