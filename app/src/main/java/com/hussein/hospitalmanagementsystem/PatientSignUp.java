package com.hussein.hospitalmanagementsystem;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class PatientSignUp extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText editTextPName, editTextPEmail, editTextPMobileno, editTextPBloodGroup, editTextPAddress, editTextPassword;
    private DatePicker datePicker;
    private RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_signup);

        dbHelper = new DatabaseHelper(this);

        editTextPName = findViewById(R.id.editTextPName);
        editTextPBloodGroup = findViewById(R.id.editTextPbg);
        editTextPEmail = findViewById(R.id.editTextPEmail);
        editTextPMobileno = findViewById(R.id.editTextPMobileNo);
        editTextPAddress = findViewById(R.id.editTextPAddress);
        editTextPassword = findViewById(R.id.editTextPPassword);
        TextView textViewId = findViewById(R.id.textViewId);

        datePicker = findViewById(R.id.datePicker);
        radioGroupGender = findViewById(R.id.radioGroupGender);

        ImageButton submitButton = findViewById(R.id.button4);
        ImageButton backButton = findViewById(R.id.button5);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextPName.getText().toString();
                String bloodgroup = editTextPBloodGroup.getText().toString();
                String email = editTextPEmail.getText().toString();
                String mobileno = editTextPMobileno.getText().toString();
                String address = editTextPAddress.getText().toString();
                String password = editTextPassword.getText().toString();


                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();

                if (name.isEmpty() || email.isEmpty() || mobileno.isEmpty() || bloodgroup.isEmpty() || address.isEmpty() || password.isEmpty()) {
                    Toast.makeText(PatientSignUp.this, "Registration failed. Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    int selectedId = radioGroupGender.getCheckedRadioButtonId();
                    String gender = "";
                    if (selectedId == R.id.maleRadioBtn) {
                        gender = "Male";
                    } else if (selectedId == R.id.femaleRadioBtn) {
                        gender = "Female";
                    }

                    long newRowId = dbHelper.insertPatientData(name, getFormattedDate(year, month, day), gender, bloodgroup, email, mobileno, address, password);

                    if (newRowId != -1) {
                        textViewId.setText("Your ID: " + newRowId);
                        textViewId.setVisibility(View.VISIBLE);

                        Toast.makeText(PatientSignUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                        editTextPName.setText("");
                        editTextPEmail.setText("");
                        editTextPMobileno.setText("");
                        editTextPAddress.setText("");
                        editTextPassword.setText("");
                        datePicker.updateDate(2000, 0, 1);
                        radioGroupGender.clearCheck();
                    } else {
                        Toast.makeText(PatientSignUp.this, "Error creating account", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (dbHelper != null) {
            dbHelper.close();
        }
        super.onDestroy();
    }

    private String getFormattedDate(int year, int month, int day) {
        return year + "-" + month + "-" + day;
    }
}
