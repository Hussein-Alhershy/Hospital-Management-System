package com.hussein.hospitalmanagementsystem;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.app.AlertDialog;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;


import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private AdminModel admin;
    private TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);


        dbHelper = new DatabaseHelper(this);


        Intent intent = getIntent();
        admin = (AdminModel) intent.getSerializableExtra("adminData");

        FrameLayout proFrame = findViewById(R.id.proFrame);

        ImageButton buttonLogout = findViewById(R.id.buttonLogout1);
        ImageButton buttonConfirm = findViewById(R.id.buttonConfirm);
        ImageButton buttonViewDr = findViewById(R.id.buttonViewDr);
        ImageButton buttonAddDr = findViewById(R.id.buttonAddDr);
        ImageButton buttonViewAppointments = findViewById(R.id.buttonViewApp);
        ImageButton buttonUpdateAppointment = findViewById(R.id.buttonUpdateApp);
        ImageButton buttonViewProfile = findViewById(R.id.buttonViewPro);
        ImageButton buttonDeleteAppointment = findViewById(R.id.buttonDeleteAppointment1);
        ImageButton viewAllPatientsButton = findViewById(R.id.viewAllPatientsButton);
        ImageButton buttonaddPatient = findViewById(R.id.addPatientbtn);
        ImageButton searchButton = findViewById(R.id.buttonSearch);
        ImageButton buttonDeleteP = findViewById(R.id.deleteP);


        EditText editAidEditText = findViewById(R.id.editAid);
        EditText editAnameEditText = findViewById(R.id.editAname);
        EditText editAdobEditText = findViewById(R.id.editAdob);
        EditText editAgenderEditText = findViewById(R.id.editAgend);
        EditText editAemailEdittext = findViewById(R.id.editAemail);
        EditText editA_mobile_no = findViewById(R.id.editAmobileno);
        EditText editAaddressEditText = findViewById(R.id.editAadd);
        EditText editApasswordEditText = findViewById(R.id.editApassword);


        editAidEditText.setText(String.valueOf(admin.getId()));
        editAnameEditText.setText(admin.getName());
        editAdobEditText.setText(admin.getDob());
        editAgenderEditText.setText(admin.getGender());
        editAemailEdittext.setText(admin.getEmail());
        editA_mobile_no.setText(admin.getMobileNo());
        editAaddressEditText.setText(admin.getAddress());
        editApasswordEditText.setText(admin.getPassword());

        TableLayout tableAppointment = findViewById(R.id.tableLayoutAppointments);

        CheckBox checkBoxShowPassword = findViewById(R.id.checkBoxShowPassword1);

        checkBoxShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {

            int inputType = isChecked ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
            editApasswordEditText.setInputType(inputType);

            editApasswordEditText.setSelection(editApasswordEditText.length());
        });


        buttonConfirm.setOnClickListener(v -> {

            String updatedName = editAnameEditText.getText().toString();
            String updatedDob = editAdobEditText.getText().toString();
            String updatedGender = editAgenderEditText.getText().toString();
            String updatedEmail = editAemailEdittext.getText().toString();
            String updatedMobile = editA_mobile_no.getText().toString();
            String updatedAddress = editAaddressEditText.getText().toString();
            String updatedPassword = editApasswordEditText.getText().toString();


            dbHelper.updateAdminData(admin.getId(), updatedName, updatedDob, updatedGender, updatedMobile,
                    updatedEmail, updatedAddress, updatedPassword);

            Toast.makeText(AdminActivity.this, "Data Updated successfully", Toast.LENGTH_SHORT).show();
        });

        tableLayout = findViewById(R.id.tableLayoutDoctors);

        buttonViewDr.setOnClickListener(v -> {

            Drawable currentBackground = buttonViewDr.getBackground();

            if (currentBackground.getConstantState().equals(getResources().getDrawable(R.drawable.view_dr_btn).getConstantState())) {
                tableLayout.setVisibility(View.VISIBLE);
                buttonViewDr.setBackgroundResource(R.drawable.hide_dr_btn);
            } else {
                tableLayout.setVisibility(View.GONE);
                buttonViewDr.setBackgroundResource(R.drawable.view_dr_btn);
            }

            displayDoctorNames();
        });

        buttonAddDr.setOnClickListener(v -> {
            Intent addDrIntent = new Intent(AdminActivity.this, DoctorSignUp.class);
            startActivity(addDrIntent);
        });

        buttonLogout.setOnClickListener(v -> {
            finish();
        });


        buttonViewAppointments.setOnClickListener(v -> {
            Drawable currentBackground = buttonViewAppointments.getBackground();

            if (currentBackground.getConstantState().equals(getResources().getDrawable(R.drawable.view_app_btn).getConstantState())) {
                tableAppointment.setVisibility(View.VISIBLE);
                buttonDeleteAppointment.setVisibility(View.VISIBLE);
                buttonUpdateAppointment.setVisibility(View.VISIBLE);
                buttonViewAppointments.setBackgroundResource(R.drawable.hide_app_btn);
            } else {
                tableAppointment.setVisibility(View.GONE);
                buttonDeleteAppointment.setVisibility(View.GONE);
                buttonUpdateAppointment.setVisibility(View.GONE);
                buttonViewAppointments.setBackgroundResource(R.drawable.view_app_btn);
            }

            displayAppointments();

        });

        buttonUpdateAppointment.setOnClickListener(v -> {

            showUpdateAppointmentDialog();
            displayAppointments();
        });

        viewAllPatientsButton.setOnClickListener(v -> {

            Drawable currentBackground = viewAllPatientsButton.getBackground();

            TableLayout patientsTable = findViewById(R.id.patientTableLayout);

            if (currentBackground.getConstantState().equals(getResources().getDrawable(R.drawable.view_patients_btn).getConstantState())) {
                patientsTable.setVisibility(View.VISIBLE);
                displayAllPatients();
                viewAllPatientsButton.setBackgroundResource(R.drawable.hide_patient_btn);
            } else {
                patientsTable.setVisibility(View.GONE);
                viewAllPatientsButton.setBackgroundResource(R.drawable.view_patients_btn);
            }

        });

        buttonViewProfile.setOnClickListener(v -> {


            Drawable currentBackground = buttonViewProfile.getBackground();

            if (currentBackground.getConstantState().equals(getResources().getDrawable(R.drawable.view_profile_btn).getConstantState())) {
                proFrame.setVisibility(View.VISIBLE);
                buttonViewProfile.setBackgroundResource(R.drawable.hide_profile_btn);
            } else {
                proFrame.setVisibility(View.GONE);
                buttonViewProfile.setBackgroundResource(R.drawable.view_profile_btn);
            }
        });

        buttonDeleteAppointment.setOnClickListener(v -> showDeleteAppointmentDialog());

        buttonaddPatient.setOnClickListener(v -> {

            Intent signUpIntent = new Intent(AdminActivity.this, PatientSignUp.class);
            startActivity(signUpIntent);

        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSearchDialog();
            }
        });

        buttonDeleteP.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete Patient");
            builder.setMessage("Enter Patient ID:");


            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);


            builder.setPositiveButton("OK", (dialog, which) -> {
                String patientIdInput = input.getText().toString().trim();
                if (!patientIdInput.isEmpty()) {

                    int patientId = Integer.parseInt(patientIdInput);


                    deletePatient(patientId);


                    displayAllPatients();
                } else {

                    Toast.makeText(this, "Please enter a valid Patient ID", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());


            builder.show();
        });

    }

    private void deletePatient(int patientId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);


        boolean isDeleted = dbHelper.deletePatient(patientId);

        if (isDeleted) {

            Toast.makeText(this, "Patient deleted successfully", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Failed to delete patient", Toast.LENGTH_SHORT).show();
        }


        displayAllPatients();
    }


    private void showSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search");


        View dialogView = getLayoutInflater().inflate(R.layout.dialog_search, null);
        builder.setView(dialogView);

        final EditText idEditText = dialogView.findViewById(R.id.editTextId);
        final String[] searchTypes = {"Patient", "Doctor", "Appointment"};

        builder.setSingleChoiceItems(searchTypes, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedSearchType = searchTypes[which];


                String idString = idEditText.getText().toString();
                if (!TextUtils.isEmpty(idString)) {
                    int id = Integer.parseInt(idString);

                    switch (selectedSearchType) {
                        case "Patient":
                            PatientModel patient = dbHelper.getPatientById(id);
                            if (patient != null) {
                                displayPatientData(patient);
                            } else {
                                showToast("Patient not found");
                            }
                            break;
                        case "Doctor":
                            DoctorModel doctor = dbHelper.getDoctor(id);
                            if (doctor != null) {
                                displayDoctor(doctor);
                            } else {
                                showToast("Doctor not found");
                            }
                            break;
                        case "Appointment":
                            AppointmentModelForAdmin appointment = dbHelper.getAppointmentByID(id);
                            if (appointment != null) {
                                displayAppointmentData(appointment);
                            } else {
                                showToast("Appointment not found");
                            }
                            break;
                        default:

                    }
                } else {
                    showToast("Please enter an ID");
                }

                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void displayPatientData(PatientModel patient) {

        TableLayout patientTableLayout = findViewById(R.id.patientTableLayout);

        patientTableLayout.removeAllViews();


        TableRow row = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);


        TextView idTextView = createTextView(String.valueOf(patient.getId()));
        TextView nameTextView = createTextView(patient.getName());
        TextView dobTextView = createTextView(patient.getDob());
        TextView genderTextView = createTextView(patient.getGender());
        TextView bloodGroupTextView = createTextView(patient.getBloodGroup());
        TextView emailTextView = createTextView(patient.getEmail());
        TextView mobileNoTextView = createTextView(patient.getMobileNo());
        TextView addressTextView = createTextView(patient.getAddress());
        TextView passwordTextView = createTextView(patient.getPassword());


        row.addView(idTextView);
        row.addView(nameTextView);
        row.addView(dobTextView);
        row.addView(genderTextView);
        row.addView(bloodGroupTextView);
        row.addView(emailTextView);
        row.addView(mobileNoTextView);
        row.addView(addressTextView);
        row.addView(passwordTextView);


        patientTableLayout.addView(row);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    private void displayAllPatients() {
        List<PatientModel> patients = dbHelper.getAllPatients();
        TableLayout patientTableLayout = findViewById(R.id.patientTableLayout);


        int childCount = patientTableLayout.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View child = patientTableLayout.getChildAt(i);
            if (child instanceof TableRow) {

                patientTableLayout.removeViewAt(i);
                i--;
                childCount--;
            }
        }

        if (patients.isEmpty()) {

            TableRow noPatientsRow = new TableRow(this);
            TextView noPatientsTextView = new TextView(this);
            noPatientsTextView.setText("No Patients");
            noPatientsRow.addView(noPatientsTextView);
            patientTableLayout.addView(noPatientsRow);
        } else {

            for (PatientModel patient : patients) {
                TableRow row = new TableRow(this);


                TextView idTextView = createTextView(String.valueOf(patient.getId()));
                TextView nameTextView = createTextView(patient.getName());
                TextView dobTextView = createTextView(patient.getDob());
                TextView genderTextView = createTextView(patient.getGender());
                TextView bloodGroupTextView = createTextView(patient.getBloodGroup());
                TextView emailTextView = createTextView(patient.getEmail());
                TextView mobileNoTextView = createTextView(patient.getMobileNo());
                TextView addressTextView = createTextView(patient.getAddress());


                row.addView(idTextView);
                row.addView(nameTextView);
                row.addView(dobTextView);
                row.addView(genderTextView);
                row.addView(bloodGroupTextView);
                row.addView(emailTextView);
                row.addView(mobileNoTextView);
                row.addView(addressTextView);


                patientTableLayout.addView(row);


                addDivider(patientTableLayout);
            }
        }
    }


    private void showDeleteAppointmentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Appointment");


        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);


        builder.setPositiveButton("OK", (dialog, which) -> {
            String appointmentIdText = input.getText().toString();

            if (!TextUtils.isEmpty(appointmentIdText)) {
                int appointmentId = Integer.parseInt(appointmentIdText);


                dbHelper.deleteAppointment(appointmentId);
                Toast.makeText(this, "Appointment deleted successfully", Toast.LENGTH_SHORT).show();


                displayAppointments();
            } else {
                Toast.makeText(this, "Please enter a valid appointment ID", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showUpdateAppointmentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Appointment");


        final EditText inputAppointmentId = new EditText(this);
        final EditText inputSchedule = new EditText(this);
        final EditText inputStatus = new EditText(this);

        inputAppointmentId.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputSchedule.setInputType(InputType.TYPE_CLASS_TEXT);


        inputAppointmentId.setHint("Appointment ID");
        inputSchedule.setHint("New Schedule");


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(inputAppointmentId);
        layout.addView(inputSchedule);


        builder.setView(layout);


        builder.setPositiveButton("Update", (dialog, which) -> {
            String appointmentIdText = inputAppointmentId.getText().toString();
            String newSchedule = inputSchedule.getText().toString();


            if (!appointmentIdText.isEmpty() && !newSchedule.isEmpty()) {
                int appointmentId = Integer.parseInt(appointmentIdText);


                if (dbHelper.updateAppointment(appointmentId, newSchedule)) {

                    displayAppointments();
                    Toast.makeText(this, "Appointment updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to update appointment. Appointment not found.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }


    private void displayAppointments() {

        List<AppointmentModelForAdmin> appointments = dbHelper.getAllAppointments();

        TableLayout tableLayoutAppointments = findViewById(R.id.tableLayoutAppointments);


        int childCount = tableLayoutAppointments.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View child = tableLayoutAppointments.getChildAt(i);
            if (child instanceof TableRow) {

                tableLayoutAppointments.removeViewAt(i);
                i--;
                childCount--;
            }
        }

        if (appointments.isEmpty()) {

            TableRow noAppointmentsRow = new TableRow(this);
            TextView noAppointmentsTextView = new TextView(this);
            noAppointmentsTextView.setText("No Appointments");
            noAppointmentsRow.addView(noAppointmentsTextView);
            tableLayoutAppointments.addView(noAppointmentsRow);
        } else {
            for (AppointmentModelForAdmin appointment : appointments) {
                TableRow row = new TableRow(this);

                TextView appointmentIdTextView = createTextView(String.valueOf(appointment.getAppointmentId()));
                row.addView(appointmentIdTextView);

                TextView patientIdTextView = createTextView(String.valueOf(appointment.getPatientId()));
                row.addView(patientIdTextView);

                TextView doctorNameTextView = createTextView(appointment.getDoctorName());
                row.addView(doctorNameTextView);

                TextView scheduleTextView = createTextView(appointment.getSchedule());
                row.addView(scheduleTextView);

                TextView paymentTextView = createTextView(appointment.getPayment());
                row.addView(paymentTextView);

                tableLayoutAppointments.addView(row);
            }
        }
    }


    private void displayAppointmentData(AppointmentModelForAdmin appointment) {

        TableRow row = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);


        TextView appointmentIdTextView = createTextView(String.valueOf(appointment.getAppointmentId()));
        TextView patientIdTextView = createTextView(String.valueOf(appointment.getPatientId()));
        TextView doctorNameTextView = createTextView(appointment.getDoctorName());
        TextView scheduleTextView = createTextView(appointment.getSchedule());
        TextView paymentTextView = createTextView(appointment.getPayment());


        row.addView(appointmentIdTextView);
        row.addView(patientIdTextView);
        row.addView(doctorNameTextView);
        row.addView(scheduleTextView);
        row.addView(paymentTextView);

        TableLayout tableAppointment = findViewById(R.id.tableLayoutAppointments);


        tableAppointment.addView(row);
        tableAppointment.setVisibility(View.VISIBLE);
    }


    private void displayDoctor(DoctorModel doctor) {
        TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.doctor_table_row, null);

        TextView textViewDoctorId = row.findViewById(R.id.textViewDoctorid);
        TextView textViewDoctorName = row.findViewById(R.id.textViewDoctorName);
        Button buttonDetails = row.findViewById(R.id.buttonDetails);


        textViewDoctorId.setText(String.valueOf(doctor.getId()));
        textViewDoctorName.setText(doctor.getName());

        buttonDetails.setOnClickListener(v -> {

            showDoctorDetails(doctor);
        });

        tableLayout.addView(row);
    }

    private void displayDoctorNames() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<DoctorModel> doctors = dbHelper.getAllDoctors();


        tableLayout.removeAllViews();


        for (DoctorModel doctor : doctors) {
            TableRow row = createDoctorRow(doctor);
            tableLayout.addView(row);


            addDivider(tableLayout);
        }
    }


    private void showDoctorDetails(DoctorModel doctor) {

        Intent intent = new Intent(this, DoctorDetailsActivity.class);


        intent.putExtra("doctor", doctor);


        startActivity(intent);
    }

    private void deleteDoctor(DoctorModel doctor) {

        dbHelper.deleteDoctor(doctor.getId());
        Toast.makeText(this, "Doctor deleted successfully", Toast.LENGTH_SHORT).show();
    }

    private TableRow createDoctorRow(DoctorModel doctor) {
        TableRow row = new TableRow(this);

        TextView doctorIdTextView = createTextView(String.valueOf(doctor.getId()));
        TableRow.LayoutParams paramsId = new TableRow.LayoutParams(50, TableRow.LayoutParams.WRAP_CONTENT, 1);
        doctorIdTextView.setLayoutParams(paramsId);
        row.addView(doctorIdTextView);

        TextView doctorNameTextView = createTextView(String.valueOf(doctor.getName()));
        TableRow.LayoutParams paramsName = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT, 2);
        doctorNameTextView.setLayoutParams(paramsName);
        row.addView(doctorNameTextView);

        Button buttonDetails = new Button(this);
        buttonDetails.setText("Details");
        buttonDetails.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        buttonDetails.setTextColor(Color.parseColor("#07a5e4"));
        row.addView(buttonDetails);

        Button buttonDelete = new Button(this);
        buttonDelete.setText("Delete");
        buttonDelete.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        buttonDelete.setTextColor(Color.parseColor("#07a5e4"));
        row.addView(buttonDelete);


        buttonDetails.setOnClickListener(v -> showDoctorDetails(doctor));
        buttonDelete.setOnClickListener(v -> {
            deleteDoctor(doctor);
            displayDoctorNames();
        });

        return row;
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(20);
        textView.setTextColor(Color.parseColor("#07a5e4"));
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(20, 20, 20, 20);
        textView.setBackgroundResource(R.drawable.cell_border);
        return textView;
    }

    private void addDivider(TableLayout tableLayout) {
        View divider = new View(this);
        divider.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
        divider.setBackgroundColor(Color.parseColor("#07a5e4"));
        tableLayout.addView(divider);
    }
}







