package com.example.jsshostel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class staffActivity extends AppCompatActivity {
   RecyclerView recyclerView;

   ArrayList<staffMembers> staffMembersArrayList;
   MyAdapter myAdapter;
    //ProgressDialog progressDialog;
    FirebaseDatabase fbase = FirebaseDatabase.getInstance();
    DatabaseReference root = fbase.getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("fetching data....");


        recyclerView = findViewById(R.id.staffView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        staffMembersArrayList = new ArrayList<staffMembers>();
        myAdapter = new MyAdapter(staffActivity.this, staffMembersArrayList);

        recyclerView.setAdapter(myAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                staffMembers staffMembersmodel = dataSnapshot.getValue(staffMembers.class);
                staffMembersArrayList.add(staffMembersmodel);
            }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       //EventChangeListener();




    }

//    private void EventChangeListener() {
//
//        db.collection("staff").orderBy("name", Query.Direction.ASCENDING)
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                      if(error != null){
//
//                          if(progressDialog.isShowing())
//                              progressDialog.dismiss();
//
//                          Log.e("Firestore error",error.getMessage());
//                          return;
//                      }
//
//                      for(DocumentChange dc : value.getDocumentChanges()){
//
//                          if(dc.getType()==DocumentChange.Type.ADDED){
//
//                              staffMembersArrayList.add(dc.getDocument().toObject(staffMembers.class));
//
//                          }
//
//
//
//                          myAdapter.notifyDataSetChanged();
//
//                          if(progressDialog.isShowing())
//                              progressDialog.dismiss();
//
//                      }
//
//
//
//                    }
//                });
//
//    }
}





















