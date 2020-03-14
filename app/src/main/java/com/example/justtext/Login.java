package com.example.justtext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText loginEmail,loginPassword;
    private Button btn_login;
    private FirebaseAuth mFirebaseAuth;
    private TextView tv_gotoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);
        mFirebaseAuth = FirebaseAuth.getInstance();
        tv_gotoRegister = findViewById(R.id.tv_gotoRegister);

        tv_gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email = loginEmail.getText().toString();
                password = loginPassword.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this,"login failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
