package com.example.justtext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText editEmail,editPassword;
    private Button btn_register;
    private String email,password;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editEmail = findViewById(R.id.register_email);
        editPassword = findViewById(R.id.register_password);
        btn_register = findViewById(R.id.btn_register);

        mFirebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();
                if (email.isEmpty()||password.isEmpty()){
                    Toast.makeText(Registration.this,"Fill up the details",Toast.LENGTH_SHORT).show();
                }else {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Registration.this,"Registation Successfull",Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(Registration.this,Login.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(Registration.this,"Registation failled",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
