package com.example.jsshostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button login;

    private FirebaseAuth auth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        login = findViewById(R.id.loginLogin);
         fstore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                loginUser(txt_email,txt_password);
            }
        });

    }

    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
              checkUserAccessLevel(authResult.getUser().getUid());
                //startActivity(new Intent(LoginActivity.this, adminActivity.class));


                //finish();




            }



        });



    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fstore.collection("Users").document(uid);
        //extract the data from document
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess:  " + documentSnapshot.getData());
                //identify user access level

                if (documentSnapshot.getString("isAdmin") != null) {
                    startActivity(new Intent(getApplicationContext(), adminActivity.class));
                   finish();
                }

                if (documentSnapshot.getString("isUser") != null ){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }







            }



        });
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(FirebaseAuth.getInstance().getCurrentUser() != null){
//           startActivity(new Intent(getApplicationContext(),MainActivity.class));
//           finish();
//
//        }
//
//    }
}