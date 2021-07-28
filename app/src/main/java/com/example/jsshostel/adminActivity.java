package com.example.jsshostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jsshostel.R;

public class adminActivity extends AppCompatActivity {
    private Button mess;
    private Button staff;
    private Button rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mess = findViewById(R.id.messAdmin);
        staff = findViewById(R.id.staffAdmin);
        rooms = findViewById(R.id.roomsAdmin);


        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminActivity.this, adminMess.class));

            }


        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminActivity.this, adminStaff.class));

            }
        });

        rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminActivity.this, adminrooms.class));

            }
        });



    }
}