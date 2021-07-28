package com.example.jsshostel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<staffMembers> staffMembersArrayList;

    public MyAdapter(Context context, ArrayList<staffMembers> staffMembersArrayList) {
        this.context = context;
        this.staffMembersArrayList = staffMembersArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.staff_item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        staffMembers staffMembers = staffMembersArrayList.get(position);

        holder.name.setText(staffMembers.getName());
        holder.role.setText(staffMembers.getRole());
        holder.age.setText(String.valueOf(staffMembers.getAge()));




    }

    @Override
    public int getItemCount() {

        return staffMembersArrayList.size() ;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, age, role;





        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            role = itemView.findViewById(R.id.role);

        }
    }
}
