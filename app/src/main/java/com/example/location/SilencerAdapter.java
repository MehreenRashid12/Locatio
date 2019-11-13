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

public class SilencerAdapter extends RecyclerView.Adapter<SilencerAdapter.MyViewHolder> {

    Context context;
    ArrayList<MySilencers> mySilencers;

    public SilencerAdapter(Context c, ArrayList<MySilencers> p){
        context =c;
        mySilencers=p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.name_of_phone_silencers,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        myViewHolder.silencerTitle.setText(mySilencers.get(position).getSilencerTitle());

        myViewHolder.location.setText(mySilencers.get(position).getLocation());
        final String getKeyDoes=mySilencers.get(position).getKey();




        myViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                mySilencers.remove(position);
                notifyItemRemoved(position);
                FirebaseDatabase.getInstance().getReference().child("Silencer").child("Silencer"+getKeyDoes).setValue(null);
                notifyDataSetChanged();
                Toast.makeText(context, "Delete Successful!", Toast.LENGTH_SHORT).show();






            }
        });




    }

    @Override
    public int getItemCount() {
        return mySilencers.size();
    }

    class MyViewHolder extends ViewHolder{
        TextView silencerTitle,location;
        ImageButton deleteButton;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            deleteButton=(ImageButton) itemView.findViewById(R.id.deletebutton);
            silencerTitle= (TextView)itemView.findViewById(R.id.silencerTitle);

            location=itemView.findViewById(R.id.silencerLocation);


        }
    }

}
