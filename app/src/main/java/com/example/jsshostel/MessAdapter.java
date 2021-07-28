package com.example.jsshostel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessAdapter  extends RecyclerView.Adapter<MessAdapter.MyViewHolder> {

    ArrayList<messModel> mlist;
    Context context;
    public MessAdapter(Context context, ArrayList<messModel> mlist){

        this.mlist = mlist;
        this.context = context;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return  new MyViewHolder(v);
    }

    @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        messModel messModel = mlist.get(position);
        holder.lunch.setText(messModel.getLunch());
        holder.breakfast.setText(messModel.getBreakfast());
        holder.dinner.setText(messModel.getDinner());


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView day, breakfast, lunch, dinner;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            breakfast = itemView.findViewById(R.id.roomfloor);
            lunch = itemView.findViewById(R.id.bedtype);
            dinner = itemView.findViewById(R.id.dinner);
        }
    }
}
