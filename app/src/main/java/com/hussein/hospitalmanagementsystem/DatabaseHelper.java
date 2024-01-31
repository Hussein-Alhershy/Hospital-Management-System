package com.hussein.hospitalmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.hussein.hospitalmanagementsystem.DoctorListItemModel;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HospitalDatabase";
    private static final int DATABASE_VERSION = 25;


    private static final String TABLE_ADMIN = "admin";
    private static final String TABLE_DOCTOR = "doctor";
    private static final String TABLE_PATIENT = "patient";
    private static final String TABLE_APPOINTMENT = "appointment";
    private static final String TABLE_PRESCRIPTION = "prescription";
    private static final String TABLE_PRESCRIPTION_PATIENT = "prescription_patient";



    protected static final String COLUMN_ID = "id";
    private static final String COLUMN_APPOINTMENT_ID = "id";
    protected static final String COLUMN_PATIENT_ID = "patient_id";


    private static final String COLUMN_PRESCRIPTION_ID = "Prescription_id";
    private static final String COLUMN_PRESCRIPTION = "Prescription";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_ADVICE = "advice";


    private static final String COLUMN_PRESCRIPTION_ID_P = "Prescription_id_p";
    private static final String COLUMN_APPOINTMENT_ID_P = "Appointment_id_p";
    private static final String COLUMN_PATIENT_ID_P = "Patient_id_p";
    private static final String COLUMN_PRESCRIPTION_P = "Prescription_p";
    private static final String COLUMN_DESCRIPTION_P = "Description_p";
    private static final String COLUMN_ADVICE_P = "Advice_p";
    private static final String COLUMN_DOCTOR_NAME_P = "Doctor_name_p";



    protected static final String COLUMN_DOCTOR_NAME = "doctor_name";
    protected static final String COLUMN_SCHEDULE = "schedule";
    protected static final String COLUMN_PAYMENT = "payment";



    private static final String COLUMN_NAME_A = "name";
    private static final String COLUMN_DOB_A = "dob";
    private static final String COLUMN_GENDER_A = "gender";
    private static final String COLUMN_EMAIL_A = "email";
    private static final String COLUMN_MOBILENO_A = "mobileno";
    private static final String COLUMN_ADDRESS_A = "address";
    private static final String COLUMN_PASSWORD_A = "password";


    private static final String COLUMN_NAME_D = "name";
    private static final String COLUMN_AGE_D = "age";
    private static final String COLUMN_GENDER_D = "gender";
    private static final String COLUMN_SPECIALIZATION_D = "specialization";
    private static final String COLUMN_EXPERIENCE_D = "experience";
    private static final String COLUMN_LANGUAGE_SPOKEN_D = "language_spoken";
    private static final String COLUMN_MOBILENO_D = "mobileno";
    private static final String COLUMN_EMAIL_D = "email";
    private static final String COLUMN_SCHEDULE_D = "schedule";
    private static final String COLUMN_PASSWORD_D = "password";
    private static final String COLUMN_PRICE_D = "price";

    private static final String COLUMN_NAME_P = "name";
    private static final String COLUMN_DOB_P = "dob";
    private static final String COLUMN_GENDER_P = "gender";
    private static final String COLUMN_EMAIL_P = "email";
    private static final String COLUMN_BLOODGROUP_P = "bloodgroup";
    private static final String COLUMN_MOBILENO_P = "mobileno";
    private static final String COLUMN_ADDRESS_P = "address";
    private static final String COLUMN_PASSWORD_P = "password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        String createPrescriptionPatientTable = "CREATE TABLE " + TABLE_PRESCRIPTION_PATIENT + "("
                + COLUMN_PRESCRIPTION_ID_P + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_APPOINTMENT_ID_P + " INTEGER," +
                COLUMN_PATIENT_ID_P + " INTEGER," +
                COLUMN_DOCTOR_NAME_P + " TEXT," +
                COLUMN_PRESCRIPTION_P + " TEXT," +
                COLUMN_DESCRIPTION_P + " TEXT," +
                COLUMN_ADVICE_P + " TEXT," +
                "FOREIGN KEY (" + COLUMN_APPOINTMENT_ID_P + ") REFERENCES " +
                TABLE_APPOINTMENT + " (" + COLUMN_APPOINTMENT_ID + "));";
        db.execSQL(createPrescriptionPatientTable);


        String createPrescriptionTable = "CREATE TABLE " + TABLE_PRESCRIPTION + "("
                + COLUMN_PRESCRIPTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_APPOINTMENT_ID + " INTEGER," +
                COLUMN_PATIENT_ID + " INTEGER," +
                COLUMN_PRESCRIPTION + " TEXT," +
                COLUMN_DESCRIPTION  + " TEXT," +
                COLUMN_ADVICE + " TEXT," +
                COLUMN_DOCTOR_NAME + " TEXT" + ")";
        db.execSQL(createPrescriptionTable);


        String createAppointmentTable = "CREATE TABLE " + TABLE_APPOINTMENT + "("
                + COLUMN_APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PATIENT_ID + " INTEGER," +
                COLUMN_DOCTOR_NAME + " TEXT," +
                COLUMN_SCHEDULE + " TEXT," +
                COLUMN_PAYMENT + " TEXT DEFAULT 'Not Paid')";
        db.execSQL(createAppointmentTable);


        String createAdminTable = "CREATE TABLE " + TABLE_ADMIN + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_A + " TEXT,"
                + COLUMN_DOB_A + " TEXT,"
                + COLUMN_GENDER_A + " TEXT,"
                + COLUMN_EMAIL_A + " TEXT,"
                + COLUMN_MOBILENO_A + " TEXT,"
                + COLUMN_ADDRESS_A + " TEXT,"
                + COLUMN_PASSWORD_A + " TEXT" + ")";
        db.execSQL(createAdminTable);


        String createDoctorTable = "CREATE TABLE " + TABLE_DOCTOR + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_D + " TEXT,"
                + COLUMN_AGE_D + " INTEGER,"
                + COLUMN_GENDER_D + " TEXT,"
                + COLUMN_SPECIALIZATION_D + " TEXT,"
                + COLUMN_EXPERIENCE_D + " INTEGER,"
                + COLUMN_LANGUAGE_SPOKEN_D + " TEXT,"
                + COLUMN_MOBILENO_D + " TEXT,"
                + COLUMN_EMAIL_D + " TEXT,"
                + COLUMN_SCHEDULE_D + " TEXT,"
                + COLUMN_PRICE_D + " INTEGER DEFAULT 20,"
                + COLUMN_PASSWORD_D + " TEXT" + ")";
        db.execSQL(createDoctorTable);



        String createPatientTable = "CREATE TABLE " + TABLE_PATIENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_P + " TEXT,"
                + COLUMN_DOB_P + " TEXT,"
                + COLUMN_GENDER_P + " TEXT,"
                + COLUMN_BLOODGROUP_P + " TEXT,"
                + COLUMN_EMAIL_P + " TEXT,"
                + COLUMN_MOBILENO_P + " TEXT,"
                + COLUMN_ADDRESS_P + " TEXT,"
                + COLUMN_PASSWORD_P + " TEXT" + ")";
        db.execSQL(createPatientTable);
    }


    public int getPatientIdFromAppointment(int appointmentId) {
        SQLiteDatabase db = getReadableDatabase();
        int patientId = -1;

        String[] projection = {COLUMN_PATIENT_ID};
        String selection = COLUMN_APPOINTMENT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(appointmentId)};

        Cursor cursor = db.query(TABLE_APPOINTMENT, projection, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            patientId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PATIENT_ID));
            cursor.close();
        }

        db.close();
        return patientId;
    }


    public void addPrescription(int appointmentId, int patientId, String prescription, String description, String advice, String doctorName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues doctorValues = new ContentValues();
        doctorValues.put(COLUMN_APPOINTMENT_ID, appointmentId);
        doctorValues.put(COLUMN_PATIENT_ID, patientId);
        doctorValues.put(COLUMN_PRESCRIPTION, prescription);
        doctorValues.put(COLUMN_DESCRIPTION, description);
        doctorValues.put(COLUMN_ADVICE, advice);
        doctorValues.put(COLUMN_DOCTOR_NAME, doctorName);
        db.insert(TABLE_PRESCRIPTION, null, doctorValues);

        ContentValues patientValues = new ContentValues();
        patientValues.put(COLUMN_APPOINTMENT_ID_P, appointmentId);
        patientValues.put(COLUMN_PATIENT_ID_P, patientId);
        patientValues.put(COLUMN_PRESCRIPTION_P, prescription);
        patientValues.put(COLUMN_DESCRIPTION_P, description);
        patientValues.put(COLUMN_ADVICE_P, advice);
        patientValues.put(COLUMN_DOCTOR_NAME_P, doctorName);
        db.insert(TABLE_PRESCRIPTION_PATIENT, null, patientValues);

        db.close();
    }

    public void deletePrescription(int prescriptionId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = COLUMN_PRESCRIPTION_ID + " = ?";
        String[] whereArgs = {String.valueOf(prescriptionId)};

        db.delete(TABLE_PRESCRIPTION, whereClause, whereArgs);

        db.close();
    }



    public List<PrescriptionModel> getDoctorPrescriptions(String doctorName) {
        List<PrescriptionModel> prescriptionList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                COLUMN_PRESCRIPTION_ID,
                COLUMN_APPOINTMENT_ID,
                COLUMN_PATIENT_ID,
                COLUMN_PRESCRIPTION,
                COLUMN_DESCRIPTION,
                COLUMN_ADVICE,
                COLUMN_DOCTOR_NAME
        };

        String selection = COLUMN_DOCTOR_NAME + " = ?";
        String[] selectionArgs = {doctorName};

        Cursor cursor = db.query(
                TABLE_PRESCRIPTION,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null) {

            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                do {
                    int prescriptionId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRESCRIPTION_ID));
                    int appointmentId = cursor.getInt(cursor.getColumnIndex(COLUMN_APPOINTMENT_ID));
                    int patientId = cursor.getInt(cursor.getColumnIndex(COLUMN_PATIENT_ID));
                    String prescription = cursor.getString(cursor.getColumnIndex(COLUMN_PRESCRIPTION));
                    String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    String advice = cursor.getString(cursor.getColumnIndex(COLUMN_ADVICE));
                    String drName = cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_NAME));

                    if (prescriptionId >= 0 && appointmentId >= 0 && patientId >= 0 &&
                            prescription != null && description != null && advice != null && drName != null) {
                        PrescriptionModel prescriptionModel = new PrescriptionModel(prescriptionId, appointmentId, patientId, prescription, description, advice, drName);
                        prescriptionList.add(prescriptionModel);
                    }
                } while (cursor.moveToNext());

                cursor.close();
            }
        }

        return prescriptionList;
    }

    public void deleteAllDoctorAppointments(String doctorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_APPOINTMENT, COLUMN_DOCTOR_NAME + "=?", new String[]{doctorName});
        db.delete(TABLE_PRESCRIPTION, COLUMN_DOCTOR_NAME + "=?", new String[]{doctorName});
        db.close();
    }

    public void deleteAllDoctorPrescriptions(String doctorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRESCRIPTION, COLUMN_DOCTOR_NAME + "=?", new String[]{doctorName});
        db.close();
    }

    public List<PatientModel> getAllPatients() {
        List<PatientModel> patients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PATIENT, null);

        if (cursor.moveToFirst()) {
            do {
                PatientModel patient = new PatientModel();
                patient.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                patient.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_P)));
                patient.setDob(cursor.getString(cursor.getColumnIndex(COLUMN_DOB_P)));
                patient.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER_P)));
                patient.setBloodGroup(cursor.getString(cursor.getColumnIndex(COLUMN_BLOODGROUP_P)));
                patient.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL_P)));
                patient.setMobileNo(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILENO_P)));
                patient.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_P)));
                patient.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD_P)));
                patients.add(patient);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return patients;
    }



    public List<AppointmentModelForDoctor> getDoctorAppointments(String doctorName) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<AppointmentModelForDoctor> appointments = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_APPOINTMENT +
                " WHERE " + COLUMN_DOCTOR_NAME + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{doctorName});

        if (cursor != null) {

            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                do {
                    AppointmentModelForDoctor appointment = new AppointmentModelForDoctor();
                    appointment.setAppointmentId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_ID)));
                    appointment.setPatientId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PATIENT_ID)));
                    appointment.setSchedule(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SCHEDULE)));

                    appointments.add(appointment);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        db.close();
        return appointments;
    }

    public void updatePaymentStatus(long appointmentId, String newPaymentStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PAYMENT, newPaymentStatus);

        db.update(TABLE_APPOINTMENT, values, COLUMN_APPOINTMENT_ID + " = ?", new String[]{String.valueOf(appointmentId)});
        db.close();
    }

    public AppointmentModelForAdmin getAppointmentByID(int appointmentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_APPOINTMENT +
                " WHERE " + COLUMN_APPOINTMENT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(appointmentId)});

        AppointmentModelForAdmin appointment = null;

        if (cursor != null) {

            if (cursor.moveToFirst()) {

                appointment = new AppointmentModelForAdmin();
                appointment.setAppointmentId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_ID)));
                appointment.setPatientId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PATIENT_ID)));
                appointment.setDoctorName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_NAME)));
                appointment.setSchedule(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SCHEDULE)));
                appointment.setPayment(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAYMENT)));
            }

            cursor.close();
        }

        db.close();
        return appointment;
    }


    public AppointmentModelForDoctor getAppointmentById(int appointmentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_APPOINTMENT +
                " WHERE " + COLUMN_APPOINTMENT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(appointmentId)});

        AppointmentModelForDoctor appointment = null;

        if (cursor != null) {

            if (cursor.moveToFirst()) {

                appointment = new AppointmentModelForDoctor();
                appointment.setAppointmentId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_ID)));
                appointment.setPatientId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PATIENT_ID)));
                appointment.setSchedule(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SCHEDULE)));
            }

            cursor.close();
        }

        db.close();
        return appointment;
    }


    public boolean updateAppointment(int appointmentId, String newSchedule) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SCHEDULE, newSchedule);

        int rowsAffected = db.update(TABLE_APPOINTMENT, values, COLUMN_APPOINTMENT_ID + " = ?",
                new String[]{String.valueOf(appointmentId)});
        db.close();

        return rowsAffected > 0;
    }


    public List<AppointmentModelForAdmin> getAllAppointments() {
        List<AppointmentModelForAdmin> appointments = new ArrayList<>();


        String selectQuery = "SELECT * FROM " + TABLE_APPOINTMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = (int) cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_ID));
                int patientId = (int) cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PATIENT_ID));
                String  doctorName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_NAME));
                String schedule = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SCHEDULE));
                String payment = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAYMENT));

                AppointmentModelForAdmin appointment = new AppointmentModelForAdmin(id, patientId, doctorName, schedule, payment);
                appointments.add(appointment);
            }
        }

        cursor.close();
        db.close();

        return appointments;
    }


    public long insertAppointment(int patientId, String doctorName, String schedule) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_ID, patientId);
        values.put(COLUMN_DOCTOR_NAME, doctorName);
        values.put(COLUMN_SCHEDULE, schedule);
        long newRowId = db.insert(TABLE_APPOINTMENT, null, values);
        db.close();
        return newRowId;
    }

    public List<PrescriptionModel> getPrescriptionsForPatient(int patientId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                COLUMN_PRESCRIPTION_ID_P,
                COLUMN_APPOINTMENT_ID_P,
                COLUMN_DOCTOR_NAME_P,
                COLUMN_PRESCRIPTION_P,
                COLUMN_DESCRIPTION_P,
                COLUMN_ADVICE_P
        };

        String selection = COLUMN_PATIENT_ID_P + " = ?";
        String[] selectionArgs = {String.valueOf(patientId)};

        Cursor cursor = db.query(
                TABLE_PRESCRIPTION_PATIENT,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<PrescriptionModel> prescriptions = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int prescriptionId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRESCRIPTION_ID_P));
                int appointmentId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_ID_P));
                String doctorName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_NAME_P));
                String prescription = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRESCRIPTION_P));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION_P));
                String advice = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADVICE_P));

                prescriptions.add(new PrescriptionModel(prescriptionId, appointmentId, patientId, prescription, description, advice, doctorName));
            }
            cursor.close();
        }

        db.close();

        return prescriptions;
    }





    public List<AppointmentModel> getAppointmentsWithoutPrescriptions(int patientId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_APPOINTMENT_ID, COLUMN_DOCTOR_NAME, COLUMN_SCHEDULE, COLUMN_PAYMENT};

        String tables = TABLE_APPOINTMENT;

        String selection = COLUMN_PATIENT_ID + " = ? AND NOT EXISTS " +
                "(SELECT 1 FROM " + TABLE_PRESCRIPTION_PATIENT + " WHERE " +
                TABLE_PRESCRIPTION_PATIENT + "." + COLUMN_APPOINTMENT_ID_P + " = " + TABLE_APPOINTMENT + "." + COLUMN_APPOINTMENT_ID + ")";

        String[] selectionArgs = {String.valueOf(patientId)};

        Cursor cursor = db.query(
                tables,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<AppointmentModel> appointments = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_ID));
                String doctorName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_NAME));
                String schedule = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SCHEDULE));
                String payment = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAYMENT));
                appointments.add(new AppointmentModel(id, doctorName, schedule, payment));
            }
            cursor.close();
        }

        db.close();
        return appointments;
    }





    public void deleteAppointment(long appointmentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_APPOINTMENT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(appointmentId)};
        db.delete(TABLE_APPOINTMENT, selection, selectionArgs);
        db.close();
    }

    public void deleteAppointment(int appointmentId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = COLUMN_APPOINTMENT_ID + " = ?";
        String[] whereArgs = {String.valueOf(appointmentId)};

        db.delete(TABLE_APPOINTMENT, whereClause, whereArgs);

        db.delete(TABLE_PRESCRIPTION, COLUMN_APPOINTMENT_ID + " = ?", whereArgs);

        db.close();
    }

    public long insertPatientData(String name, String dob, String gender, String blood_group, String email, String mobileno, String address, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_P, name);
        values.put(COLUMN_DOB_P, dob);
        values.put(COLUMN_GENDER_P, gender);
        values.put(COLUMN_BLOODGROUP_P, blood_group);
        values.put(COLUMN_EMAIL_P, email);
        values.put(COLUMN_MOBILENO_P, mobileno);
        values.put(COLUMN_ADDRESS_P, address);
        values.put(COLUMN_PASSWORD_P, password);

        long newRowId = db.insert(TABLE_PATIENT, null, values);

        db.close();
        return newRowId;
    }



    public long insertAdminData(String name, String dob, String gender, String email, String mobileno, String address, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_A, name);
        values.put(COLUMN_DOB_A, dob);
        values.put(COLUMN_GENDER_A, gender);
        values.put(COLUMN_EMAIL_A, email);
        values.put(COLUMN_MOBILENO_A, mobileno);
        values.put(COLUMN_ADDRESS_A, address);
        values.put(COLUMN_PASSWORD_A, password);

        long newRowId = db.insert(TABLE_ADMIN, null, values);

        db.close();

        return newRowId;
    }

    public long insertDoctorData(String name, int age, String gender, String specialization, int experience, String languageSpoken, String mobileNo, String email, String schedule, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_D, name);
        values.put(COLUMN_AGE_D, age);
        values.put(COLUMN_GENDER_D, gender);
        values.put(COLUMN_SPECIALIZATION_D, specialization);
        values.put(COLUMN_EXPERIENCE_D, experience);
        values.put(COLUMN_LANGUAGE_SPOKEN_D, languageSpoken);
        values.put(COLUMN_MOBILENO_D, mobileNo);
        values.put(COLUMN_EMAIL_D, email);
        values.put(COLUMN_SCHEDULE_D, schedule);
        values.put(COLUMN_PASSWORD_D, password);

        long newRowId = db.insert(TABLE_DOCTOR, null, values);

        db.close();

        return newRowId;
    }

    public AdminModel getAdminData(String enteredId, String enteredPassword) {
            SQLiteDatabase db = this.getReadableDatabase();

            String[] projection = {
                    COLUMN_ID,
                    COLUMN_NAME_A,
                    COLUMN_DOB_A,
                    COLUMN_GENDER_A,
                    COLUMN_EMAIL_A,
                    COLUMN_MOBILENO_A,
                    COLUMN_ADDRESS_A,
                    COLUMN_PASSWORD_A
            };

            String selection = COLUMN_ID + " = ? AND " + COLUMN_PASSWORD_A + " = ?";
            String[] selectionArgs = {enteredId, enteredPassword};

            Cursor cursor = db.query(
                    TABLE_ADMIN,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            AdminModel admin = null;

            if (cursor != null && cursor.moveToFirst()) {

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_A));
                String dob = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOB_A));
                String gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER_A));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL_A));
                String mobileNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOBILENO_A));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS_A));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD_A));

                admin = new AdminModel(id, name, dob, gender, email, mobileNo, address, password);
            }

            if (cursor != null) {
            cursor.close();
        }
        db.close();

        return admin;
    }

    public PatientModel getPatientData(String enteredId, String enteredPassword) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME_P,
                COLUMN_DOB_P,
                COLUMN_GENDER_P,
                COLUMN_BLOODGROUP_P,
                COLUMN_EMAIL_P,
                COLUMN_MOBILENO_P,
                COLUMN_ADDRESS_P,
                COLUMN_PASSWORD_P
        };

        String selection = COLUMN_ID + " = ? AND " + COLUMN_PASSWORD_P + " = ?";
        String[] selectionArgs = {enteredId, enteredPassword};

        Cursor cursor = db.query(
                TABLE_PATIENT,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        PatientModel patient = null;

        if (cursor != null && cursor.moveToFirst()) {

            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_P));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOB_P));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER_P));
            String bloodgroup = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BLOODGROUP_P));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL_P));
            String mobileNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOBILENO_P));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS_P));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD_P));

            patient = new PatientModel(id, name, dob, gender, bloodgroup, email, mobileNo, address, password);
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return patient;
    }

    public PatientModel getPatientById(int patientId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PATIENT + " WHERE " + COLUMN_ID + " = ?",
                new String[]{String.valueOf(patientId)});

        PatientModel patient = null;

        if (cursor.moveToFirst()) {
            patient = new PatientModel();
            patient.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            patient.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_P)));
            patient.setDob(cursor.getString(cursor.getColumnIndex(COLUMN_DOB_P)));
            patient.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER_P)));
            patient.setBloodGroup(cursor.getString(cursor.getColumnIndex(COLUMN_BLOODGROUP_P)));
            patient.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL_P)));
            patient.setMobileNo(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILENO_P)));
            patient.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_P)));
            patient.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD_P)));
        }

        cursor.close();
        db.close();

        return patient;
    }

    public DoctorModel getDoctorById(int doctorId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DOCTOR + " WHERE " + COLUMN_ID + " = ?",
                new String[]{String.valueOf(doctorId)});

        DoctorModel doctor = null;

        if (cursor.moveToFirst()) {
            doctor = new DoctorModel();
            doctor.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            doctor.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_D)));
            doctor.setDob(cursor.getString(cursor.getColumnIndex(COLUMN_AGE_D)));
            doctor.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER_D)));
            doctor.setSpecialization(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALIZATION_D)));
            doctor.setExperience(cursor.getString(cursor.getColumnIndex(COLUMN_EXPERIENCE_D)));
            doctor.setLanguage(cursor.getString(cursor.getColumnIndex(COLUMN_LANGUAGE_SPOKEN_D)));
            doctor.setMobileNo(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILENO_D)));
            doctor.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL_D)));
            doctor.setSchedule(cursor.getString(cursor.getColumnIndex(COLUMN_SCHEDULE_D)));
            doctor.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE_D)));
            doctor.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD_D)));
        }

        cursor.close();
        db.close();

        return doctor;
    }




    public DoctorModel getDoctorData(String enteredId, String enteredPassword) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME_D,
                COLUMN_AGE_D,
                COLUMN_GENDER_D,
                COLUMN_SPECIALIZATION_D,
                COLUMN_EXPERIENCE_D,
                COLUMN_LANGUAGE_SPOKEN_D,
                COLUMN_MOBILENO_D,
                COLUMN_EMAIL_D,
                COLUMN_SCHEDULE_D,
                COLUMN_PASSWORD_D
        };

        String selection = COLUMN_ID + " = ? AND " + COLUMN_PASSWORD_D + " = ?";
        String[] selectionArgs = {enteredId, enteredPassword};

        Cursor cursor = db.query(
                TABLE_DOCTOR,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        DoctorModel doctor = null;

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_D));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AGE_D));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER_D));
            String specialization = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPECIALIZATION_D));
            String experience = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPERIENCE_D));
            String languageSpoken = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LANGUAGE_SPOKEN_D));
            String mobileNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOBILENO_D));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL_D));
            String schedule = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SCHEDULE_D));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD_D));

            doctor = new DoctorModel(id, name, dob, gender, schedule, languageSpoken, experience, specialization, email, mobileNo, password);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return doctor;
    }

    public void updatePatientData(int patientId, String updatedName, String updatedDob, String updatedGender, String updatedMobile, String updatedBloodgroup,
                                  String updatedEmail, String updatedAddress, String updatedPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_P, updatedName);
        values.put(COLUMN_DOB_P, updatedDob);
        values.put(COLUMN_GENDER_P, updatedGender);
        values.put(COLUMN_BLOODGROUP_P, updatedBloodgroup);
        values.put(COLUMN_EMAIL_P, updatedEmail);
        values.put(COLUMN_MOBILENO_P, updatedMobile);
        values.put(COLUMN_ADDRESS_P, updatedAddress);
        values.put(COLUMN_PASSWORD_P, updatedPassword);

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(patientId)};

        int count = db.update(
                TABLE_PATIENT,
                values,
                selection,
                selectionArgs);

        db.close();
    }



    public void updateAdminData(int adminId, String updatedName, String updatedDob, String updatedGender, String updatedMobile,
                                  String updatedEmail, String updatedAddress, String updatedPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_A, updatedName);
        values.put(COLUMN_DOB_A, updatedDob);
        values.put(COLUMN_GENDER_A, updatedGender);
        values.put(COLUMN_EMAIL_A, updatedEmail);
        values.put(COLUMN_MOBILENO_A, updatedMobile);
        values.put(COLUMN_ADDRESS_A, updatedAddress);
        values.put(COLUMN_PASSWORD_A, updatedPassword);

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(adminId)};

        int count = db.update(
                TABLE_ADMIN,
                values,
                selection,
                selectionArgs);

        db.close();
    }

    public void updateDoctorData(int doctorId, String updatedName, String updatedDob, String updatedGender, String updatedMobile,
                                String updatedEmail, String updatedScheduale, String updatedLanguage, String updatedPassword,
                                 String updatedSpacialization, String updatedExperience) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_D, updatedName);
        values.put(COLUMN_AGE_D, updatedDob);
        values.put(COLUMN_GENDER_D, updatedGender);
        values.put(COLUMN_EMAIL_D, updatedEmail);
        values.put(COLUMN_MOBILENO_D, updatedMobile);
        values.put(COLUMN_LANGUAGE_SPOKEN_D, updatedLanguage);
        values.put(COLUMN_SPECIALIZATION_D, updatedSpacialization);
        values.put(COLUMN_EXPERIENCE_D, updatedExperience);
        values.put(COLUMN_SCHEDULE_D, updatedScheduale);
        values.put(COLUMN_PASSWORD_D, updatedPassword);

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(doctorId)};

        int count = db.update(
                TABLE_DOCTOR,
                values,
                selection,
                selectionArgs);

        db.close();
    }

    public void updateDoctorDataByAdmin(int doctorId, String updatedName, String updatedDob, String updatedGender, String updatedMobile,
                                 String updatedEmail, String updatedScheduale, String updatedLanguage, String updatedPassword,
                                 String updatedSpacialization, String updatedExperience,  String updatedPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_D, updatedName);
        values.put(COLUMN_AGE_D, updatedDob);
        values.put(COLUMN_GENDER_D, updatedGender);
        values.put(COLUMN_EMAIL_D, updatedEmail);
        values.put(COLUMN_MOBILENO_D, updatedMobile);
        values.put(COLUMN_LANGUAGE_SPOKEN_D, updatedLanguage);
        values.put(COLUMN_SPECIALIZATION_D, updatedSpacialization);
        values.put(COLUMN_EXPERIENCE_D, updatedExperience);
        values.put(COLUMN_SCHEDULE_D, updatedScheduale);
        values.put(COLUMN_PASSWORD_D, updatedPassword);
        values.put(COLUMN_PRICE_D, updatedPrice);

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(doctorId)};

        int count = db.update(
                TABLE_DOCTOR,
                values,
                selection,
                selectionArgs);

        db.close();
    }

    public List<DoctorListItemModel> getDoctorsList() {
        List<DoctorListItemModel> doctorsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_NAME_D,
                COLUMN_SPECIALIZATION_D,
                COLUMN_SCHEDULE_D,
                COLUMN_PRICE_D
        };

        Cursor cursor = db.query(
                TABLE_DOCTOR,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_D));
            String specialization = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPECIALIZATION_D));
            String schedule = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SCHEDULE_D));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRICE_D));

            DoctorListItemModel doctorListItem = new DoctorListItemModel(name, specialization, schedule, price);
            doctorsList.add(doctorListItem);
        }


        cursor.close();
        db.close();

        return doctorsList;
    }

    public List<String> getAllDoctorNames() {
        List<String> doctorNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_NAME_D};

        Cursor cursor = db.query(
                TABLE_DOCTOR,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_D));
                doctorNames.add(name);
            }
            cursor.close();
        }

        db.close();

        return doctorNames;
    }

    public int getDoctorPrice(String doctorName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_PRICE_D};
        String selection = COLUMN_NAME_D + " = ?";
        String[] selectionArgs = {doctorName};
        Cursor cursor = db.query(
                TABLE_DOCTOR,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int price = 0;
        if (cursor != null && cursor.moveToFirst()) {
            price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRICE_D));
            cursor.close();
        }

        db.close();
        return price;
    }

    public List<DoctorModel> getAllDoctors() {
        List<DoctorModel> doctorsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME_D,
                COLUMN_AGE_D,
                COLUMN_GENDER_D,
                COLUMN_SPECIALIZATION_D,
                COLUMN_EXPERIENCE_D,
                COLUMN_LANGUAGE_SPOKEN_D,
                COLUMN_MOBILENO_D,
                COLUMN_EMAIL_D,
                COLUMN_SCHEDULE_D,
                COLUMN_PASSWORD_D
        };

        Cursor cursor = db.query(
                TABLE_DOCTOR,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_D));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE_D));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER_D));
            String specialization = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPECIALIZATION_D));
            int experience = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPERIENCE_D));
            String languageSpoken = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LANGUAGE_SPOKEN_D));
            String mobileNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOBILENO_D));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL_D));
            String schedule = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SCHEDULE_D));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD_D));

            DoctorModel doctor = new DoctorModel(id, name, String.valueOf(age), gender, schedule, languageSpoken, String.valueOf(experience), specialization, email, mobileNo, password);
            doctorsList.add(doctor);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return doctorsList;
    }

    public void deleteDoctor(int doctorId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DOCTOR, COLUMN_ID + " = ?", new String[]{String.valueOf(doctorId)});
        db.close();
    }

    public boolean deletePatient(int patientId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = { String.valueOf(patientId) };

        int rowsDeleted = db.delete(TABLE_PATIENT, whereClause, whereArgs);

        db.close();

        return rowsDeleted > 0;
    }


    public DoctorModel getDoctor(int doctorId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_DOCTOR,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME_D,
                        COLUMN_AGE_D,
                        COLUMN_GENDER_D,
                        COLUMN_SPECIALIZATION_D,
                        COLUMN_EXPERIENCE_D,
                        COLUMN_LANGUAGE_SPOKEN_D,
                        COLUMN_MOBILENO_D,
                        COLUMN_EMAIL_D,
                        COLUMN_SCHEDULE_D,
                        COLUMN_PASSWORD_D
                },
                COLUMN_ID + "=?",
                new String[]{String.valueOf(doctorId)},
                null, null, null, null);

        DoctorModel doctor = null;

        if (cursor != null && cursor.moveToFirst()) {

            int idColumnIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME_D);
            int ageColumnIndex = cursor.getColumnIndex(COLUMN_AGE_D);
            int genderColumnIndex = cursor.getColumnIndex(COLUMN_GENDER_D);
            int speColumnIndex = cursor.getColumnIndex(COLUMN_SPECIALIZATION_D);
            int expColumnIndex = cursor.getColumnIndex(COLUMN_EXPERIENCE_D);
            int langColumnIndex = cursor.getColumnIndex(COLUMN_LANGUAGE_SPOKEN_D);
            int mobColumnIndex = cursor.getColumnIndex(COLUMN_MOBILENO_D);
            int emailColumnIndex = cursor.getColumnIndex(COLUMN_EMAIL_D);
            int scheduleColumnIndex = cursor.getColumnIndex(COLUMN_SCHEDULE_D);
            int passColumnIndex = cursor.getColumnIndex(COLUMN_PASSWORD_D);

            do {
                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                String age = cursor.getString(ageColumnIndex);
                String gender = cursor.getString(genderColumnIndex);
                String spe = cursor.getString(speColumnIndex);
                String exp = cursor.getString(expColumnIndex);
                String lang = cursor.getString(langColumnIndex);
                String mob = cursor.getString(mobColumnIndex);
                String email = cursor.getString(emailColumnIndex);
                String schedule = cursor.getString(scheduleColumnIndex);
                String pass = cursor.getString(passColumnIndex);

                doctor = new DoctorModel(id, name, String.valueOf(age), gender, schedule, lang, String.valueOf(exp), spe, email, mob, pass);

            } while (cursor.moveToNext());

            cursor.close();
        }

        return doctor;
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCRIPTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCRIPTION_PATIENT);
        onCreate(db);
    }
}




