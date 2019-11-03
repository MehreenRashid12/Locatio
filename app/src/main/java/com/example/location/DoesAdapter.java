package com.example.location;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class DoesAdapter extends RecyclerView.Adapter<DoesAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyDoes> myDoes;

    public DoesAdapter(Context c, ArrayList<MyDoes> p){
        context =c;
        myDoes=p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.name_of_does,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.titleDoes.setText(myDoes.get(position).getTitleDoes());
        myViewHolder.dateDoes.setText(myDoes.get(position).getDateDoes());
        myViewHolder.locationDoes.setText(myDoes.get(position).getLocationDoes());


        final String getTitleDoes=myDoes.get(position).getTitleDoes();
        final String getDateDoes=myDoes.get(position).getDateDoes();
        final String getKeyDoes=myDoes.get(position).getKeyDoes();
        final String getLocationDoes=myDoes.get(position).getLocationDoes();
        final String getLatitudeDoes=myDoes.get(position).getLatitudeDoes();
        final String getLongitudeDoes=myDoes.get(position).getLongitudeDoes();
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa=new Intent(context,EditTaskDesk.class);
                aa.putExtra("titleDoes",getTitleDoes);
                aa.putExtra("dateDoes",getDateDoes);
                aa.putExtra("keyDoes",getKeyDoes);
                aa.putExtra("locationDoes",getLocationDoes);
                aa.putExtra("latitudeDoes",getLocationDoes);
                aa.putExtra("longitudeDoes",getLocationDoes);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    class MyViewHolder extends ViewHolder{
        TextView titleDoes,dateDoes,keyDoes,locationDoes;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            titleDoes= (TextView)itemView.findViewById(R.id.titledoes);
            dateDoes= (TextView)itemView.findViewById(R.id.datedoes);
            locationDoes=itemView.findViewById(R.id.locationdoes);


        }
    }

}
