package com.hussein.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SigninActivity extends AppCompatActivity {

    private String x = "";
    private EditText AidText;
    private EditText ApasswordText;
    private ImageButton adminButton;
   private ImageButton doctorButton;
   private ImageButton patientButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        ImageButton backButton = findViewById(R.id.backButton);
        adminButton = findViewById(R.id.adminButton);
        doctorButton = findViewById(R.id.doctorButton);
        patientButton = findViewById(R.id.patientButton);
        ImageButton loginButton = findViewById(R.id.loginButton);

        AidText = findViewById(R.id.AidText);
        ApasswordText = findViewById(R.id.ApasswordText);

        TextView ApasswordTextView = findViewById(R.id.ApasswordTextView);
        TextView AidTextView = findViewById(R.id.AidTextView);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = "a";

                AidTextView.setVisibility(View.VISIBLE);
                AidText.setVisibility(View.VISIBLE);
                ApasswordTextView.setVisibility(View.VISIBLE);
                ApasswordText.setVisibility(View.VISIBLE);

                doctorButton.setVisibility(View.GONE);
                patientButton.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);

            }
        });

        doctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = "d";

                AidTextView.setVisibility(View.VISIBLE);
                AidText.setVisibility(View.VISIBLE);
                ApasswordTextView.setVisibility(View.VISIBLE);
                ApasswordText.setVisibility(View.VISIBLE);

                adminButton.setVisibility(View.GONE);
                patientButton.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);

            }
        });

        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = "p";

                AidTextView.setVisibility(View.VISIBLE);
                AidText.setVisibility(View.VISIBLE);
                ApasswordTextView.setVisibility(View.VISIBLE);
                ApasswordText.setVisibility(View.VISIBLE);

                doctorButton.setVisibility(View.GONE);
                adminButton.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredId = AidText.getText().toString();
                String enteredPassword = ApasswordText.getText().toString();

                AidText.setText(null);
                ApasswordText.setText(null);

                if (x.equals("a")) {
                    DatabaseHelper dbHelper = new DatabaseHelper(SigninActivity.this);
                    AdminModel admin = dbHelper.getAdminData(enteredId, enteredPassword);

                    if (admin != null) {
                        Intent adminIntent = new Intent(SigninActivity.this, AdminActivity.class);
                        adminIntent.putExtra("adminData", admin);
                        startActivity(adminIntent);
                    } else {
                        Toast.makeText(SigninActivity.this, "Incorrect ID or password", Toast.LENGTH_SHORT).show();
                    }
                } else if (x.equals("d")) {

                    DatabaseHelper dbHelper = new DatabaseHelper(SigninActivity.this);
                    DoctorModel doctor = dbHelper.getDoctorData(enteredId, enteredPassword);

                    if (doctor != null) {
                        Intent doctorIntent = new Intent(SigninActivity.this, DoctorActivity.class);
                        doctorIntent.putExtra("doctorData", doctor);
                        startActivity(doctorIntent);
                    } else {
                        Toast.makeText(SigninActivity.this, "Incorrect ID or password", Toast.LENGTH_SHORT).show();
                    }
                } else if (x.equals("p")) {
                    DatabaseHelper dbHelper = new DatabaseHelper(SigninActivity.this);
                    PatientModel patient = dbHelper.getPatientData(enteredId, enteredPassword);

                    if (patient != null) {
                        Intent patientIntent = new Intent(SigninActivity.this, PatientActivity.class);
                        patientIntent.putExtra("patientData", patient);
                        startActivity(patientIntent);
                    } else {
                        Toast.makeText(SigninActivity.this, "Incorrect ID or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areAllButtonsVisible()) {
                    finish();
                }else{

                    patientButton.setVisibility(View.VISIBLE);
                    adminButton.setVisibility(View.VISIBLE);
                    doctorButton.setVisibility(View.VISIBLE);
                    loginButton.setVisibility(View.GONE);

                    AidTextView.setVisibility(View.GONE);
                    AidText.setVisibility(View.GONE);
                    AidText.setText(null);
                    ApasswordTextView.setVisibility(View.GONE);
                    ApasswordText.setVisibility(View.GONE);
                    ApasswordText.setText(null);

                }
            }
        });
    }

    private boolean areAllButtonsVisible() {
        return patientButton.getVisibility() == View.VISIBLE && doctorButton.getVisibility() == View.VISIBLE && adminButton.getVisibility() == View.VISIBLE;
    }
}
