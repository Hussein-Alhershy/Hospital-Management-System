package com.hussein.hospitalmanagementsystem;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PatientActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private PatientModel patient;
    private TextView totalCostTextView;
    private int totalCost = 0;
    private int patientId;
    private TextView textViewAdminMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_layout);

        totalCostTextView = findViewById(R.id.textView9);
        updateTotalCost();

        dbHelper = new DatabaseHelper(this);


        Intent intent = getIntent();
        patient = (PatientModel) intent.getSerializableExtra("patientData");

        TextView textViewAlert = findViewById(R.id.textViewAlertMessage);

        if (patient == null) {
            Toast.makeText(this, "Patient data not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TableLayout tableLayout = findViewById(R.id.doctorsTableLayout);

        FrameLayout proFrame = findViewById(R.id.proFrame);

        createTable(tableLayout);

        ImageButton buttonLogout = findViewById(R.id.buttonLogout);
        ImageButton buttonConfirm = findViewById(R.id.buttonConfirm);
        ImageButton buttonDone = findViewById(R.id.button7);
        ImageButton buttonViewDetails = findViewById(R.id.buttonViewDetails);

        Spinner spinner = findViewById(R.id.spinner);

        EditText editPidEditText = findViewById(R.id.editPid);
        EditText editPnameEditText = findViewById(R.id.editPname);
        EditText editPdobEditText = findViewById(R.id.editPdob);
        EditText editPgenderEditText = findViewById(R.id.editPgend);
        EditText editPbloodgroupEdittext = findViewById(R.id.editPbloodgroup);
        EditText editPemailEdittext = findViewById(R.id.editPemail);
        EditText editP_mobile_no = findViewById(R.id.editPmobileno);
        EditText editPaddressEditText = findViewById(R.id.editPadd);
        EditText editPpasswordEditText = findViewById(R.id.editPpassword);

        editPidEditText.setText(String.valueOf(patient.getId()));
        editPnameEditText.setText(patient.getName());
        editPdobEditText.setText(patient.getDob());
        editPgenderEditText.setText(patient.getGender());
        editPbloodgroupEdittext.setText(patient.getBloodGroup());
        editPemailEdittext.setText(patient.getEmail());
        editP_mobile_no.setText(patient.getMobileNo());
        editPaddressEditText.setText(patient.getAddress());
        editPpasswordEditText.setText(patient.getPassword());

        CheckBox checkBoxShowPassword = findViewById(R.id.checkBoxShowPassword2);

        checkBoxShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {

            int inputType = isChecked ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
            editPpasswordEditText.setInputType(inputType);

            editPpasswordEditText.setSelection(editPpasswordEditText.length());
        });

        buttonConfirm.setOnClickListener(v -> {

            String updatedName = editPnameEditText.getText().toString();
            String updatedDob = editPdobEditText.getText().toString();
            String updatedGender = editPgenderEditText.getText().toString();
            String updatedBG = editPbloodgroupEdittext.getText().toString();
            String updatedEmail = editPemailEdittext.getText().toString();
            String updatedMobile = editP_mobile_no.getText().toString();
            String updatedAddress = editPaddressEditText.getText().toString();
            String updatedPassword = editPpasswordEditText.getText().toString();

            textViewAdminMessage = findViewById(R.id.textViewAlertMessage);




            dbHelper.updatePatientData(patient.getId(), updatedName, updatedDob, updatedGender, updatedMobile, updatedBG,
                    updatedEmail, updatedAddress, updatedPassword);

            Toast.makeText(PatientActivity.this, "Data Updated successfully", Toast.LENGTH_SHORT).show();
        });

        List<String> doctorNames = getAllDoctorNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, doctorNames);

        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        buttonDone.setOnClickListener(view -> {
            totalCost =0;
            String selectedDoctorName = spinner.getSelectedItem().toString();

            String schedule = "Pending";

            long appointmentId = dbHelper.insertAppointment(patient.getId(), selectedDoctorName, schedule);

            if (appointmentId != -1) {
                Toast.makeText(PatientActivity.this, "Appointment scheduled successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PatientActivity.this, "Failed to schedule appointment", Toast.LENGTH_SHORT).show();
            }

            displayAppointments();

        });
        editPidEditText.setText(String.valueOf(patient.getId()));
        displayAppointments();


        buttonViewDetails.setOnClickListener(v -> {
            Drawable currentBackground = buttonViewDetails.getBackground();

            if (currentBackground.getConstantState().equals(getResources().getDrawable(R.drawable.view_profile_btn).getConstantState())) {
                proFrame.setVisibility(View.VISIBLE);
                buttonViewDetails.setBackgroundResource(R.drawable.hide_profile_btn);
            } else {
                proFrame.setVisibility(View.GONE);
                buttonViewDetails.setBackgroundResource(R.drawable.view_profile_btn);
            }
        });

        buttonLogout.setOnClickListener(v ->{
            finish();
        });



        displayPrescriptionsForPatient(patient.getId());
    }



    private void updateTotalCost() {
        totalCostTextView.setText(String.valueOf(totalCost) +" $");

        TextView textViewAlert = findViewById(R.id.textViewAlertMessage);

        if(totalCost == 0){
            textViewAlert.setVisibility(View.GONE);
        } else {
            textViewAlert.setVisibility(View.VISIBLE);
        }
    }



    private void createTable(TableLayout tableLayout) {
        List<DoctorListItemModel> doctors = dbHelper.getDoctorsList();

        for (DoctorListItemModel doctor : doctors) {
            TableRow row = new TableRow(this);

            TextView nameTextView = createTextView(doctor.getName());
            TextView specializationTextView = createTextView(doctor.getSpecialization());
            TextView scheduleTextView = createTextView(doctor.getSchedule());
            TextView priceTextView = createTextView(String.valueOf(doctor.getPrice()));

            row.addView(nameTextView);
            row.addView(specializationTextView);
            row.addView(scheduleTextView);
            row.addView(priceTextView);

            tableLayout.addView(row);

            addDivider(tableLayout);
        }
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


    public List<String> getAllDoctorNames() {
        return dbHelper.getAllDoctorNames();
    }

    private void displayAppointments() {

        List<AppointmentModel> appointments = dbHelper.getAppointmentsWithoutPrescriptions(patient.getId());

        TableLayout appointmentsTableLayout = findViewById(R.id.appointmentsTableLayout);

        int childCount = appointmentsTableLayout.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View child = appointmentsTableLayout.getChildAt(i);
            if (child instanceof TableRow) {
                appointmentsTableLayout.removeViewAt(i);
                i--;
                childCount--;
            }
        };

        if (appointments.isEmpty()) {
            TableRow noAppointmentsRow = new TableRow(this);
            TextView noAppointmentsTextView = new TextView(this);
            noAppointmentsTextView.setText("No Appointments");
            noAppointmentsRow.addView(noAppointmentsTextView);
            appointmentsTableLayout.addView(noAppointmentsRow);
        } else {
            for (AppointmentModel appointment : appointments) {
                TableRow row = new TableRow(this);

                TextView appointmentIdTextView = createTextView(String.valueOf(appointment.getId()));
                row.addView(appointmentIdTextView);

                TextView doctorNameTextView = createTextView(appointment.getDoctorName());
                row.addView(doctorNameTextView);

                TextView scheduleTextView = createTextView(appointment.getSchedule());
                row.addView(scheduleTextView);

                TextView paymentTextView = createTextView(appointment.getPayment());
                row.addView(paymentTextView);

                Button payButton = new Button(this);

                if ("Not Paid".equals(appointment.getPayment())) {
                    totalCost += dbHelper.getDoctorPrice(appointment.getDoctorName());
                } else {
                    payButton.setEnabled(false);
                }

                updateTotalCost();

                Button deleteButton = new Button(this);
                deleteButton.setText("Delete");
                deleteButton.setOnClickListener(v -> {

                    int rowIndex = appointmentsTableLayout.indexOfChild(row);
                    appointmentsTableLayout.removeViewAt(rowIndex);

                    dbHelper.deleteAppointment(appointment.getId());

                    if ("Not Paid".equals(appointment.getPayment())) {
                        totalCost -= dbHelper.getDoctorPrice(appointment.getDoctorName());}

                    updateTotalCost();

                });
                row.addView(deleteButton);

                payButton.setText("Pay");
                payButton.setOnClickListener(v -> {

                    totalCost -= dbHelper.getDoctorPrice(appointment.getDoctorName());
                    updateTotalCost();
                    payButton.setEnabled(false);

                    dbHelper.updatePaymentStatus(appointment.getId(), "Paid");

                    paymentTextView.setText("Paid");
                });
                row.addView(payButton);

                if ("Not Paid".equals(appointment.getPayment())) {
                    payButton.setEnabled(true);

                }else{
                    payButton.setEnabled(false);
                }

                appointmentsTableLayout.addView(row);
            }
        }
    }

    private void displayPrescriptionsForPatient(int patientId) {
        List<PrescriptionModel> prescriptions = dbHelper.getPrescriptionsForPatient(patientId);

        TableLayout prescriptionsTableLayout = findViewById(R.id.prescriptionsTableLayout);

        int childCount = prescriptionsTableLayout.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View child = prescriptionsTableLayout.getChildAt(i);
            if (child instanceof TableRow) {
                prescriptionsTableLayout.removeViewAt(i);
                i--;
                childCount--;
            }
        };

        if (prescriptions.isEmpty()) {
            TableRow noPrescriptionsRow = new TableRow(this);
            TextView noPrescriptionsTextView = new TextView(this);
            noPrescriptionsTextView.setText("No Prescriptions");
            noPrescriptionsRow.addView(noPrescriptionsTextView);
            prescriptionsTableLayout.addView(noPrescriptionsRow);
        } else {
            for (PrescriptionModel prescription : prescriptions) {
                TableRow row = new TableRow(this);

                TextView prescriptionIdTextView = createTextView(String.valueOf(prescription.getPrescriptionId()));
                row.addView(prescriptionIdTextView);

                TextView doctorNameTextView = createTextView(prescription.getDrName());
                row.addView(doctorNameTextView);

                TextView prescriptionTextView = createTextView(prescription.getPrescription());
                row.addView(prescriptionTextView);

                TextView descriptionTextView = createTextView(prescription.getDescription());
                row.addView(descriptionTextView);

                TextView adviceTextView = createTextView(prescription.getAdvice());
                row.addView(adviceTextView);

                prescriptionsTableLayout.addView(row);

                addDivider(prescriptionsTableLayout);
            }

        }
    }

    private void showDeleteConfirmationDialog(AppointmentModel appointment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you want to delete this appointment?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            deleteAppointment(appointment);
        });
        builder.setNegativeButton("No", (dialog, which) -> {
        });
        builder.show();
    }

    private void deleteAppointment(AppointmentModel appointment) {
        TableLayout appointmentsTableLayout = findViewById(R.id.appointmentsTableLayout);
        for (int i = 0; i < appointmentsTableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) appointmentsTableLayout.getChildAt(i);
            if (row.getChildAt(0) instanceof TextView) {
                TextView doctorNameTextView = (TextView) row.getChildAt(0);
                if (doctorNameTextView.getText().toString().equals(appointment.getDoctorName())) {
                    appointmentsTableLayout.removeViewAt(i);
                    break;
                }
            }
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.deleteAppointment(appointment.getId());

        Toast.makeText(this, "Appointment deleted successfully", Toast.LENGTH_SHORT).show();
    }
}


