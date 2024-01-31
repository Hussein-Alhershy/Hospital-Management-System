package com.hussein.hospitalmanagementsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private DoctorModel doctor;
    private TableLayout tableLayoutAppointments;
    private ImageButton buttonSearchAppointment;
    private ImageButton  buttonViewAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_layout);

        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        doctor = (DoctorModel) intent.getSerializableExtra("doctorData");

        tableLayoutAppointments = findViewById(R.id.tableLayoutAppointments1);
        TableLayout tableLayoutPrescriptionsAll = findViewById(R.id.tableLayoutPrescriptionsAll);

        FrameLayout ProFrame = findViewById(R.id.ProFrame);

        ImageButton buttonLogout = findViewById(R.id.buttonBack);
        ImageButton buttonConfirm = findViewById(R.id.buttonUpdateD);
        buttonViewAppointments = findViewById(R.id.buttonViewAppointments);
        ImageButton buttonViewPrescriptions = findViewById(R.id.buttonViewPrescriptions2);
        ImageButton buttonDeleteAppointment = findViewById(R.id.buttonDeleteAppointment);
        ImageButton buttonDeletePrescription = findViewById(R.id.buttonDeletePrescription);
        buttonSearchAppointment = findViewById(R.id.buttonSearchAppointment);
        ImageButton buttonDeleteAllAppointments = findViewById(R.id.btnDeleteAppointments);
        ImageButton buttonViewProfile = findViewById(R.id.buttonViewPro);

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

        CheckBox checkBoxShowPassword = findViewById(R.id.checkBoxShowPassword);

        checkBoxShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {

            int inputType = isChecked ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
            editDpasswordEditText.setInputType(inputType);

            editDpasswordEditText.setSelection(editDpasswordEditText.length());
        });

        buttonLogout.setOnClickListener(v ->{
            finish();
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

            dbHelper.updateDoctorData(doctor.getId(), updatedName, updatedDob, updatedGender, updatedMobile,
                    updatedEmail, updatedScedual, updatedlang, updatedPassword, updatedSpecialization,  updatedExp);

            Toast.makeText(DoctorActivity.this, "Data Updated successfully", Toast.LENGTH_SHORT).show();
        });

        buttonViewAppointments.setOnClickListener(v -> {

            Drawable currentBackground =buttonViewAppointments.getBackground();

            if (currentBackground.getConstantState().equals(getResources().getDrawable(R.drawable.view_app_btn).getConstantState())) {
                tableLayoutAppointments.setVisibility(View.VISIBLE);
                viewAppointments();
                buttonViewAppointments.setBackgroundResource(R.drawable.hide_app_btn);
                buttonDeleteAppointment.setVisibility(View.VISIBLE);
            } else {
                tableLayoutAppointments.setVisibility(View.GONE);
                buttonViewAppointments.setBackgroundResource(R.drawable.view_app_btn);
                buttonDeleteAppointment.setVisibility(View.GONE);
            }



        });

        buttonDeleteAppointment.setOnClickListener(v -> showDeleteAppointmentDialog());

        buttonDeletePrescription.setOnClickListener(v -> showDeletePrescriptionDialog());

        buttonViewPrescriptions.setOnClickListener(v -> {


            if (tableLayoutPrescriptionsAll.getVisibility() == View.VISIBLE) {
                tableLayoutPrescriptionsAll.setVisibility(View.GONE);
                buttonViewPrescriptions.setBackgroundResource(R.drawable.view_prescription_btn);
                buttonDeletePrescription.setVisibility(View.GONE);

            } else {
                tableLayoutPrescriptionsAll.setVisibility(View.VISIBLE);
                buttonDeletePrescription.setVisibility(View.VISIBLE);
                buttonViewPrescriptions.setBackgroundResource(R.drawable.hide_presc_btn);
            }

            viewAllPrescriptions();

        });

        buttonSearchAppointment.setOnClickListener(v -> {

            showSearchAppointmentDialog();

        });

        buttonDeleteAllAppointments.setOnClickListener(v -> showDeletionDialog());

        buttonViewProfile.setOnClickListener(v -> {


            Drawable currentBackground = buttonViewProfile.getBackground();

            if (currentBackground.getConstantState().equals(getResources().getDrawable(R.drawable.view_profile_btn).getConstantState())) {
                ProFrame.setVisibility(View.VISIBLE);
                buttonViewProfile.setBackgroundResource(R.drawable.hide_profile_btn);
            } else {
                ProFrame.setVisibility(View.GONE);
                buttonViewProfile.setBackgroundResource(R.drawable.view_profile_btn);
            }
        });

    }

    private void showDeletionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Data");
        builder.setMessage("Do you want to delete all appointments and prescriptions, or only prescriptions?");
        builder.setPositiveButton("All Appointments", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteAllAppointmentsAndPrescriptions();
            }
        });
        builder.setNegativeButton("Prescriptions Only", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletePrescriptionsOnly();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteAllAppointmentsAndPrescriptions() {

        dbHelper.deleteAllDoctorAppointments(doctor.getName());
        dbHelper.deleteAllDoctorPrescriptions(doctor.getName());

        Toast.makeText(this, "All appointments and prescriptions deleted", Toast.LENGTH_SHORT).show();

    }

    private void deletePrescriptionsOnly() {

        dbHelper.deleteAllDoctorPrescriptions(doctor.getName());

        Toast.makeText(this, "All prescriptions deleted", Toast.LENGTH_SHORT).show();

    }



    private void showSearchAppointmentDialog() {
        int childCount = tableLayoutAppointments.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View child = tableLayoutAppointments.getChildAt(i);
            if (child instanceof TableRow) {
                tableLayoutAppointments.removeViewAt(i);
                i--;
                childCount--;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search Appointment");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", null);

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String appointmentIdText = input.getText().toString();

            if (!TextUtils.isEmpty(appointmentIdText)) {
                int appointmentId = Integer.parseInt(appointmentIdText);

                AppointmentModelForDoctor appointment = dbHelper.getAppointmentById(appointmentId);

                if (appointment != null) {
                    if (!"pending".equalsIgnoreCase(appointment.getSchedule())) {
                        showAppointmentDetailsDialog(appointment);
                        tableLayoutAppointments.setVisibility(View.VISIBLE);
                        buttonViewAppointments.setBackgroundResource(R.drawable.hide_app_btn);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(this, "Appointment schedule is pending.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Appointment not found", Toast.LENGTH_SHORT).show();
                    tableLayoutAppointments.setVisibility(View.GONE);
                }
            } else {
                Toast.makeText(this, "Please enter a valid appointment ID", Toast.LENGTH_SHORT).show();
                tableLayoutAppointments.setVisibility(View.GONE);
            }
        });
    }


    private void showAppointmentDetailsDialog(AppointmentModelForDoctor appointment) {

        TableLayout tableLayoutAppointments = findViewById(R.id.tableLayoutAppointments1);

        View rowView = getLayoutInflater().inflate(R.layout.appointment_table_row_doctor, null);

        TextView textViewAppointmentId = rowView.findViewById(R.id.textViewAppointmentIdDoctor);
        TextView textViewPatientId = rowView.findViewById(R.id.textViewPatientIdDoctor);
        TextView textViewSchedule = rowView.findViewById(R.id.textViewScheduleDoctor);
        Button buttonAddPrescription = rowView.findViewById(R.id.buttonAddPrescription);

        textViewAppointmentId.setText(String.valueOf(appointment.getAppointmentId()));
        textViewPatientId.setText(String.valueOf(appointment.getPatientId()));
        textViewSchedule.setText(appointment.getSchedule());

        setCellBackgroundWithBorders(textViewAppointmentId);
        setCellBackgroundWithBorders(textViewPatientId);
        setCellBackgroundWithBorders(textViewSchedule);

        buttonAddPrescription.setOnClickListener(v -> {
            onAddPrescriptionClick(appointment);
        });

        tableLayoutAppointments.addView(rowView, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }


    private void viewAllPrescriptions() {
        List<PrescriptionModel> prescriptions = dbHelper.getDoctorPrescriptions(doctor.getName());

        TableLayout tableLayoutPrescriptionsAll = findViewById(R.id.tableLayoutPrescriptionsAll);

        int childCount = tableLayoutPrescriptionsAll.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View child = tableLayoutPrescriptionsAll.getChildAt(i);
            if (child instanceof TableRow) {
                tableLayoutPrescriptionsAll.removeViewAt(i);
                i--;
                childCount--;
            }
        }

        if (prescriptions != null && !prescriptions.isEmpty()) {
            for (PrescriptionModel prescription : prescriptions) {
                TableRow row = new TableRow(this);

                TextView textViewPrescriptionId = createTextView(String.valueOf(prescription.getPrescriptionId()));
                row.addView(textViewPrescriptionId);

                TextView textViewAppointmentId = createTextView(String.valueOf(prescription.getAppointmentId()));
                row.addView(textViewAppointmentId);

                TextView textViewPatientId = createTextView(String.valueOf(prescription.getPatientId()));
                row.addView(textViewPatientId);

                TextView textViewPrescriptionText = createTextView(prescription.getPrescription());
                row.addView(textViewPrescriptionText);

                TextView textViewDescription = createTextView(prescription.getDescription());
                row.addView(textViewDescription);

                TextView textViewAdvice = createTextView(prescription.getAdvice());
                row.addView(textViewAdvice);

                tableLayoutPrescriptionsAll.addView(row, new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
            }

        } else {
            Log.d("DoctorPrescriptions", "No prescriptions found for the doctor");
        }
    }


    private void viewAppointments() {
        List<AppointmentModelForDoctor> appointments = dbHelper.getDoctorAppointments(doctor.getName());

        tableLayoutAppointments = findViewById(R.id.tableLayoutAppointments1);

        int childCount = tableLayoutAppointments.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View child = tableLayoutAppointments.getChildAt(i);
            if (child instanceof TableRow) {
                tableLayoutAppointments.removeViewAt(i);
                i--;
                childCount--;
            }
        }

        if (appointments != null && !appointments.isEmpty()) {
            for (AppointmentModelForDoctor appointment : appointments) {
                if (!"pending".equalsIgnoreCase(appointment.getSchedule())) {
                    TableRow row = new TableRow(this);

                    TextView appointmentIdTextView = createTextView(String.valueOf(appointment.getAppointmentId()));
                    row.addView(appointmentIdTextView);

                    TextView textViewPatientId= createTextView(String.valueOf(appointment.getPatientId()));
                    row.addView(textViewPatientId);

                    TextView textViewSchedule= createTextView(appointment.getSchedule());
                    row.addView(textViewSchedule);

                    Button  buttonAddPrescription = new Button(this);
                    buttonAddPrescription.setText("Add Prescription");
                    buttonAddPrescription.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
                    buttonAddPrescription.setTextColor(Color.parseColor("#07a5e4"));

                    buttonAddPrescription.setOnClickListener(v -> {
                        onAddPrescriptionClick(appointment);
                    });
                    row.addView(buttonAddPrescription);

                    tableLayoutAppointments.addView(row, new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        } else {
            Log.d("DoctorAppointments", "No appointments found for the doctor");
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

                viewAppointments();
                viewAllPrescriptions();
            } else {
                Toast.makeText(this, "Please enter a valid appointment ID", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showDeletePrescriptionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Prescription ID");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String prescriptionIdText = input.getText().toString();

            if (!TextUtils.isEmpty(prescriptionIdText)) {
                int prescriptionId = Integer.parseInt(prescriptionIdText);

                dbHelper.deletePrescription(prescriptionId);
                Toast.makeText(this, "Prescription deleted successfully", Toast.LENGTH_SHORT).show();

                viewAllPrescriptions();
            } else {
                Toast.makeText(this, "Please enter a valid prescription ID", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    public void onAddPrescriptionClick(AppointmentModelForDoctor appointment) {

        showPrescriptionDialog(appointment);
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(20);
        textView.setTextColor(Color.parseColor("#07a5e4"));
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(20, 25, 20, 25);
        textView.setBackgroundResource(R.drawable.cell_border);
        return textView;
    }

    private void showPrescriptionDialog(AppointmentModelForDoctor appointment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Prescription");

        View dialogView = getLayoutInflater().inflate(R.layout.custom_prescription_dialog, null);
        builder.setView(dialogView);

        EditText inputPrescription = dialogView.findViewById(R.id.editTextPrescription);
        EditText inputDescription = dialogView.findViewById(R.id.editTextDescription);
        EditText inputAdvice = dialogView.findViewById(R.id.editTextAdvice);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String prescription = inputPrescription.getText().toString();
            String description = inputDescription.getText().toString();
            String advice = inputAdvice.getText().toString();

            dbHelper.addPrescription(appointment.getAppointmentId(), appointment.getPatientId(), prescription, description, advice, doctor.getName());
            Toast.makeText(this, "Prescription added successfully", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }



    private void setCellBackgroundWithBorders(TextView textView) {
        Drawable drawable = getResources().getDrawable(R.drawable.cell_border);

        textView.setBackground(drawable);
    }

}


