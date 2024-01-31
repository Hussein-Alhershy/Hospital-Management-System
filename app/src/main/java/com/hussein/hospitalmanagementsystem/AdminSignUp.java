package com.hussein.hospitalmanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class AdminSignUp extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText editTextAName, editTextAEmail, editTextAMobileno, editTextAAddress, editTextPassword;
    private DatePicker datePicker;
    private RadioGroup radioGroupGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminsignup);

        dbHelper = new DatabaseHelper(this);


        editTextAName = findViewById(R.id.editTextDName);
        editTextAEmail = findViewById(R.id.editTextDEmail);
        editTextAMobileno = findViewById(R.id.editTextDMobileNo);
        editTextAAddress = findViewById(R.id.editTextAAddress);
        editTextPassword = findViewById(R.id.editTextDPassword);
        TextView textViewId = findViewById(R.id.textViewId);


        datePicker = findViewById(R.id.datePicker);
        radioGroupGender = findViewById(R.id.radioGroupGender);

        ImageButton submitButton = findViewById(R.id.button4);
        ImageButton backButton = findViewById(R.id.button5);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextAName.getText().toString();
                String email = editTextAEmail.getText().toString();
                String mobileno = editTextAMobileno.getText().toString();
                String address = editTextAAddress.getText().toString();
                String password = editTextPassword.getText().toString();


                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();


                if (name.isEmpty() || email.isEmpty() || mobileno.isEmpty() || address.isEmpty() || password.isEmpty()) {
                    Toast.makeText(AdminSignUp.this, "Registration failed. Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else if (!isAgeValid(year, month, day)) {
                    Toast.makeText(AdminSignUp.this, "Registration failed. Age must be 18 or older.", Toast.LENGTH_SHORT).show();
                } else {

                    int selectedId = radioGroupGender.getCheckedRadioButtonId();
                    String gender = "";
                    if (selectedId == R.id.maleRadioBtn) {
                        gender = "Male";
                    } else if (selectedId == R.id.femaleRadioBtn) {
                        gender = "Female";
                    }


                    long newRowId = dbHelper.insertAdminData(name, getFormattedDate(year, month, day), gender, email, mobileno, address, password);

                    if (newRowId != -1) {

                        textViewId.setText("Your ID: " + newRowId);
                        textViewId.setVisibility(View.VISIBLE);

                        Toast.makeText(AdminSignUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();


                        editTextAName.setText("");
                        editTextAEmail.setText("");
                        editTextAMobileno.setText("");
                        editTextAAddress.setText("");
                        editTextPassword.setText("");
                        datePicker.updateDate(2000, 0, 1);
                        radioGroupGender.clearCheck();
                    } else {

                        Toast.makeText(AdminSignUp.this, "Error creating account", Toast.LENGTH_SHORT).show();
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


    private boolean isAgeValid(int year, int month, int day) {

        int currentYear = 2023;
        int age = currentYear - year;


        if (month > 11 || (month == 11 && day > 23)) {
            age--;
        }

        return age >= 18;
    }


    private String getFormattedDate(int year, int month, int day) {
        return year + "-" + month + "-" + day;
    }
}



