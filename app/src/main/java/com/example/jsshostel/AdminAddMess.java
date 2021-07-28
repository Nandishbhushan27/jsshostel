package com.example.jsshostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminAddMess extends AppCompatActivity {

    private EditText mday, mbf,ml,md;
    private Button  madd;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("mess");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_mess);

        mbf = findViewById(R.id.mAddB);
        ml = findViewById(R.id.mAddL);
        md = findViewById(R.id.mAddD);

        madd = (Button) findViewById(R.id.mADD);

        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bf = mbf.getText().toString();
                String l = ml.getText().toString();
                String d = md.getText().toString();

                HashMap<String, String> userMap = new HashMap<>();
                userMap.put("breakfast",bf);
                userMap.put("lunch",l);
                userMap.put("dinner",d);

                root.push().setValue(userMap);    //uid


                startActivity(new Intent(AdminAddMess.this, adminMess.class ));



            }
        });



    }
}