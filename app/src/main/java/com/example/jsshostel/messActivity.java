package com.example.jsshostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class messActivity extends AppCompatActivity {

    Date date=new Date();
    String dayWeekText = new SimpleDateFormat("EEEE").format(date);

    private TextView day;
    private RecyclerView recyclerView;
    private FirebaseDatabase fstore = FirebaseDatabase.getInstance();
    private DatabaseReference root =  fstore.getReference().child("mess").child(dayWeekText);
    private MessAdapter adapter;
    private ArrayList<messModel>  list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);

        day = (TextView) findViewById(R.id.dayofweek);
        day.setText(dayWeekText);

        recyclerView = findViewById(R.id.messlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list =  new ArrayList<>();
        adapter = new MessAdapter(this, list );

        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    messModel messModel = dataSnapshot.getValue(messModel.class);
                    list.add(messModel);
                }

             adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}











