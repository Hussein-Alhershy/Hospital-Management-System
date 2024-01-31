package com.hussein.hospitalmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class DoctorAdapter extends ArrayAdapter<DoctorListItemModel> {

    private LayoutInflater inflater;
    private int resource;

    public DoctorAdapter(Context context, int resource, List<DoctorListItemModel> doctors) {
        super(context, resource, doctors);
        this.inflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(resource, parent, false);
        }

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView specializationTextView = view.findViewById(R.id.specializationTextView);
        TextView scheduleTextView = view.findViewById(R.id.scheduleTextView);

        DoctorListItemModel doctor = getItem(position);

        if (doctor != null) {
            nameTextView.setText(doctor.getName());
            specializationTextView.setText(doctor.getSpecialization());
            scheduleTextView.setText(doctor.getSchedule());
        }

        return view;
    }
}


