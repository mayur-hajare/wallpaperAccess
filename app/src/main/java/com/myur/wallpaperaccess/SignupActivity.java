package com.myur.wallpaperaccess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    TextView login;
    EditText email, password;
    RelativeLayout signup;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();
        signup = findViewById(R.id.signup);
        email = findViewById(R.id.signupmail);
        password = findViewById(R.id.loginpassword);
        login = findViewById(R.id.login);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email != null && password != null) {
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);

                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userMail = email.getText().toString().trim();
                String userPassword = password.getText().toString().trim();

                if (userMail.isEmpty() || userPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Details...", Toast.LENGTH_SHORT).show();
                } else if (userPassword.length() < 7) {
                    Toast.makeText(getApplicationContext(), "Password must be 8 character", Toast.LENGTH_SHORT).show();

                } else {

                    firebaseAuth.createUserWithEmailAndPassword(userMail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                sentEmailVerification();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to register Try Again...", Toast.LENGTH_LONG).show();

                            }
                            finish();


                        }
                    });

                }


            }
        });
    }

    private void sentEmailVerification() {

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {

            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Toast.makeText(getApplicationContext(), "Verification mail send ,Verify your account...", Toast.LENGTH_LONG).show();
                    firebaseAuth.signOut();
                    finish();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);


                }
            });

        } else {

            Toast.makeText(getApplicationContext(), "Verification mail sending fail", Toast.LENGTH_LONG).show();
        }

    }
}