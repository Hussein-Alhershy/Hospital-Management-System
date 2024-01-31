package com.hussein.hospitalmanagementsystem;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorDetailsActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        dbHelper = new DatabaseHelper(this);

        Button buttonConfirm = findViewById(R.id.buttonUpdateD);

        DoctorModel doctor = (DoctorModel) getIntent().getSerializableExtra("doctor");

        EditText editDidEditText = findViewById(R.id.editDid);
        EditText editDnameEditText = findViewById(R.id.editDname);
        EditText editDdobEditText = findViewById(R.id.editDdob);
        EditText editDgenderEditText = findViewById(R.id.editDgender);
        EditText editDemailEdittext = findViewById(R.id.editDemail);
        EditText editD_mobile_no = findViewById(R.id.editDmob);
        EditText editDexpEditText = findViewById(R.id.editDexp);
        EditText editDspecialization = findViewById(R.id.editDspe);
        EditText editDscedual = findViewById(R.id.editDschedual);
        EditText editDlang = findViewById(R.id.editDlang);
        EditText editDpasswordEditText = findViewById(R.id.editDpassword);
        EditText editDprice = findViewById(R.id.editTextPrice);


        editDidEditText.setText(String.valueOf(doctor.getId()));
        editDnameEditText.setText(doctor.getName());
        editDdobEditText.setText(doctor.getDob());
        editDgenderEditText.setText(doctor.getGender());
        editDemailEdittext.setText(doctor.getEmail());
        editD_mobile_no.setText(doctor.getMobileNo());
        editDpasswordEditText.setText(doctor.getPassword());
        editDexpEditText.setText(doctor.getExperience());
        editDspecialization.setText(doctor.getSpecialization());
        editDlang.setText(doctor.getLanguage());
        editDscedual.setText(doctor.getSchedule());
        editDprice.setText(String.valueOf(doctor.getPrice()));



        CheckBox checkBoxShowPassword = findViewById(R.id.checkBoxShowPassword);

        checkBoxShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int inputType = isChecked ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
            editDpasswordEditText.setInputType(inputType);

            editDpasswordEditText.setSelection(editDpasswordEditText.length());
        });

        buttonConfirm.setOnClickListener(v -> {
            String updatedName = editDnameEditText.getText().toString();
            String updatedDob = editDdobEditText.getText().toString();
            String updatedGender = editDgenderEditText.getText().toString();
            String updatedEmail = editDemailEdittext.getText().toString();
            String updatedMobile = editD_mobile_no.getText().toString();
            String updatedExp = editDexpEditText.getText().toString();
            String updatedSpecialization = editDspecialization.getText().toString();
            String updatedlang = editDlang.getText().toString();
            String updatedScedual = editDscedual.getText().toString();
            String updatedPassword = editDpasswordEditText.getText().toString();
            String updatedPrice = editDprice.getText().toString();

            dbHelper.updateDoctorDataByAdmin(doctor.getId(), updatedName, updatedDob, updatedGender, updatedMobile,
                    updatedEmail, updatedScedual, updatedlang, updatedPassword, updatedSpecialization,  updatedExp, updatedPrice);

            Toast.makeText(DoctorDetailsActivity.this, "Data Updated successfully", Toast.LENGTH_SHORT).show();
        });
    }
}

