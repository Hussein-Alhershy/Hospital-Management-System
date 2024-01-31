package com.hussein.hospitalmanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorSignUp extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText editTextDName, editTextDEmail, editTextDMobileno, editTextDPassword, editTextLang,
            editTextDAge, editTextDExp, editTextDSpe, editTextDay, editTextStart, editTextEnd, editTextDSchedule;
    private RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_signup);

        dbHelper = new DatabaseHelper(this);

        editTextDName = findViewById(R.id.editTextDName);
        editTextDEmail = findViewById(R.id.editTextDEmail);
        editTextDMobileno = findViewById(R.id.editTextDMobileNo);
        editTextDAge = findViewById(R.id.editTextDAge);
        editTextDPassword = findViewById(R.id.editTextDPassword);
        editTextDExp = findViewById(R.id.editTextDExp);
        editTextDSpe = findViewById(R.id.editTextDSpe);
        editTextDay = findViewById(R.id.editTextDay);
        editTextStart = findViewById(R.id.editTextStart);
        editTextEnd = findViewById(R.id.editTextEnd);
        editTextDSchedule = findViewById(R.id.editTextDSchedule);
        editTextLang = findViewById(R.id.editTextDLanguage);
        TextView textViewDId = findViewById(R.id.textViewId);
        radioGroupGender = findViewById(R.id.radioGroupGender);

        ImageButton submitButton = findViewById(R.id.button4);
        ImageButton backButton = findViewById(R.id.button5);
        ImageButton addButton = findViewById(R.id.button6);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = editTextDay.getText().toString();
                String startTime = editTextStart.getText().toString();
                String endTime = editTextEnd.getText().toString();

                if (!day.isEmpty() && !startTime.isEmpty() && !endTime.isEmpty()) {
                    String currentSchedule = editTextDSchedule.getText().toString();
                    String newSchedule = "-" + day + " from " + startTime + " to " + endTime + "-";

                    if (!currentSchedule.isEmpty()) {
                        newSchedule = "\n" + newSchedule;
                    }

                    String updatedSchedule = currentSchedule + newSchedule;

                    editTextDSchedule.setText(updatedSchedule);

                    editTextDay.getText().clear();
                    editTextStart.getText().clear();
                    editTextEnd.getText().clear();

                    Toast.makeText(DoctorSignUp.this, "Schedule added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DoctorSignUp.this, "Please fill in all schedule fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = editTextDName.getText().toString();
                    String email = editTextDEmail.getText().toString();
                    String mobileno = editTextDMobileno.getText().toString();
                    String age = editTextDAge.getText().toString();
                    String password = editTextDPassword.getText().toString();
                    String exp = editTextDExp.getText().toString();
                    String spe = editTextDSpe.getText().toString();
                    String schedule =  editTextDSchedule.getText().toString();
                    String lang = editTextLang.getText().toString();

                    if (name.isEmpty() || email.isEmpty() || mobileno.isEmpty() || age.isEmpty() || password.isEmpty() || exp.isEmpty() || spe.isEmpty()) {
                        Toast.makeText(DoctorSignUp.this, "Registration failed. Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    } else {

                        int selectedId = radioGroupGender.getCheckedRadioButtonId();
                        String gender = "";
                        if (selectedId == R.id.maleRadio) {
                            gender = "Male";
                        } else if (selectedId == R.id.femaleRadio) {
                            gender = "Female";
                        }

                        try {
                            long newRowId = dbHelper.insertDoctorData(
                                    name,
                                    Integer.parseInt(age),
                                    gender,
                                    spe,
                                    Integer.parseInt(exp),
                                    lang,
                                    mobileno,
                                    email,
                                    schedule,
                                    password
                            );

                            if (newRowId != -1) {
                                textViewDId.setText("Your ID: " + newRowId);
                                textViewDId.setVisibility(View.VISIBLE);

                                Toast.makeText(DoctorSignUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                                editTextDName.setText("");
                                editTextDEmail.setText("");
                                editTextDMobileno.setText("");
                                editTextDAge.setText("");
                                editTextDPassword.setText("");
                                editTextDExp.setText("");
                                editTextDSpe.setText("");
                                radioGroupGender.clearCheck();
                                editTextDSchedule.setText("");
                            } else {
                                Toast.makeText(DoctorSignUp.this, "Error creating account", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(DoctorSignUp.this, "Invalid age or experience value", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
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
}
