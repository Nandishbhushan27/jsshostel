package com.example.jsshostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
private EditText email;
private EditText password;
private Button register;
private FirebaseAuth auth;
private FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email);
        password = findViewById(R.id.edtPassword);
        register = findViewById(R.id.register_r);
        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String txt_email = email.getText().toString();
               String txt_password = password.getText().toString();


               if (TextUtils.isEmpty(txt_email)|| TextUtils.isEmpty(txt_password)){
                   Toast.makeText( Register.this, "enter credentials", Toast.LENGTH_SHORT).show();

               }
               else if (txt_password.length()<8){
                   Toast.makeText(Register.this, "password too short", Toast.LENGTH_SHORT).show();

               }
               else
               {
                   registerUser(txt_email, txt_password);
               }
           }
       });

    }

    private void registerUser(String email, String password) {

        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = auth.getCurrentUser();
                Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                DocumentReference df = fstore.collection("Users").document(user.getUid());
                Map<String, Object> userInfo = new HashMap<>();

                userInfo.put("Email", email);



                userInfo.put("isUser", "1");

                df.set(userInfo);
                startActivity(new Intent(Register.this, MainActivity.class));
                finish();
            }
        });
//        {
//            @Override
//
//            public void onComplete(@NonNull Task<AuthResult> task) {
//            if (task.isSuccessful()){
//                FirebaseUser user = auth.getCurrentUser();
//                Toast.makeText(Register.this, "registering user successfully ", Toast.LENGTH_SHORT).show();
//                DocumentReference df = fstore.collection("Users").document(user.getUid());
//                Map<String,Object> userinfo = new HashMap<>();
//                userinfo.put("email",email);
//                userinfo.put("password",password);
//
//                //admin/user
//                userinfo.put("isUser","1");
//                df.set(userinfo);
//
//
//                startActivity(new Intent(Register.this, MainActivity.class));    //INTENT
//                finish();
//
//            }
//            else{
//                Toast.makeText(Register.this, "user not registered successfully",Toast.LENGTH_SHORT).show();
//            }
//            }
//        });
    }
}

