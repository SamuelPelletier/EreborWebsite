package com.gmsam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.gmsam.R;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by PC on 08/12/2017.
 */

public class CreateSam extends DialogFragment {

    private EditText setPseudo;
    private Button setDate;
    private Button setHeures;
    private EditText setDepart;
    private EditText setArrivee;
    private SeekBar setNbPlace;
    private Button valider;

    public static String date;

    static CreateSam newInstance() {
        return new CreateSam();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_sam_layout, container, false);

        setPseudo = v.findViewById(R.id.setPseudo);
        setDate = v.findViewById(R.id.setDate);
        setHeures = v.findViewById(R.id.setTime);
        setDepart = v.findViewById(R.id.setLieuDepart);
        setArrivee = v.findViewById(R.id.setLieuDepart);
        setNbPlace = v.findViewById(R.id.setNbPlace);
        valider = v.findViewById(R.id.valider);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        setHeures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        return v;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
}
