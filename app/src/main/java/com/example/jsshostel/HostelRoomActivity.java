package com.example.jsshostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HostelRoomActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase fStore = FirebaseDatabase.getInstance();
    private DatabaseReference root = fStore.getReference().child("rooms");
    private RoomsAdapter adapter;
    private ArrayList<RoomsModel> rlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_room);

        recyclerView = findViewById(R.id.roomsrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rlist = new ArrayList<>();
        adapter = new RoomsAdapter(this, rlist);

        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    RoomsModel roomsmodel = dataSnapshot.getValue(RoomsModel.class);
                    rlist.add(roomsmodel);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}