package com.example.jsshostel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.MyViewHolder> {

    ArrayList<RoomsModel> rlist;
    Context context;

    public RoomsAdapter(Context context, ArrayList<RoomsModel> rlist) {

        this.rlist = rlist;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.roomscard, parent, false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RoomsModel roomsModel = rlist.get(position);
        holder.bed.setText(roomsModel.getBed());
        holder.floor.setText(roomsModel.getFloor());
        holder.number.setText(roomsModel.getNumber());

    }

    @Override
    public int getItemCount() {
        return rlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bed, floor, number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bed = itemView.findViewById(R.id.bedtype);
            floor = itemView.findViewById(R.id.roomfloor);
            number = itemView.findViewById(R.id.number);
        }
    }

}
