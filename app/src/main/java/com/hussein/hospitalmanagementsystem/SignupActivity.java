package com.hussein.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        ImageButton backButton = findViewById(R.id.backButton1);
        ImageButton adminButton = findViewById(R.id.button3);
        ImageButton doctorButton = findViewById(R.id.button2);
        ImageButton patientButton = findViewById(R.id.button);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(SignupActivity.this, AdminSignUp.class);
                startActivity(signUpIntent);
            }
        });

        doctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(SignupActivity.this, DoctorSignUp.class);
                startActivity(signUpIntent);
            }
        });

        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(SignupActivity.this, PatientSignUp.class);
                startActivity(signUpIntent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

