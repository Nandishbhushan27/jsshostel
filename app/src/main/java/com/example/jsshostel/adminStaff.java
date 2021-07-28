package com.example.jsshostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class adminStaff extends AppCompatActivity {
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    ArrayList<staffMembers> staffMembersArrayList;
    FirebaseDatabase fbase = FirebaseDatabase.getInstance();
    DatabaseReference root = fbase.getReference().child("users");
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_staff);

        recyclerView = findViewById(R.id.staffadminlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        staffMembersArrayList = new ArrayList<staffMembers>();
        myAdapter = new MyAdapter(adminStaff.this, staffMembersArrayList);

        recyclerView.setAdapter(myAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    staffMembers staffMembers = dataSnapshot.getValue(staffMembers.class);
                    staffMembersArrayList.add(staffMembers);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add = findViewById(R.id.addAS);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminStaff.this, AdminAdd.class));
            }
        });





    }
}