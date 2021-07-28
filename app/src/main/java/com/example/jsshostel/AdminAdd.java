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

public class AdminAdd extends AppCompatActivity {
    private EditText aname, aage, arole;
    private Button add;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);

        aname = findViewById(R.id.nameAd);
        aage = findViewById(R.id.ageAd);
        arole = findViewById(R.id.roleAd);
        add = findViewById(R.id.addAd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameAd = aname.getText().toString();
                String ageAd = aage.getText().toString();
                String roleAd = arole.getText().toString();

                HashMap<String, String> userMap = new HashMap<>();

                userMap.put("name", nameAd);
                userMap.put("age", ageAd);
                userMap.put("role", roleAd);

                root.child("member").setValue(userMap);

                startActivity(new Intent(AdminAdd.this, adminStaff.class));
            }
        });


    }
}