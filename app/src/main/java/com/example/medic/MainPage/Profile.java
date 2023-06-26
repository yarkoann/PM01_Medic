package com.example.medic.MainPage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.medic.CreatePasswordActivity;
import com.example.medic.CreateProfileActivity;
import com.example.medic.R;

public class Profile extends Fragment {

    EditText profileName, profileLastName, profileThirdName, profileDate;
    Spinner profileSex;
    Button save;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        setSetting();
        setSpinner();
        return rootView;
    }

    private void setSetting() {
        profileSex = rootView.findViewById(R.id.profileSex);
        profileName = rootView.findViewById(R.id.profileName);
        profileLastName = rootView.findViewById(R.id.profileLastName);
        profileThirdName = rootView.findViewById(R.id.profileThirdName);
        profileDate = rootView.findViewById(R.id.profileDate);
        save = rootView.findViewById(R.id.save);



        save.setOnClickListener(view -> {

        });
    }
    private void setSpinner() {
        String[] items = new String[]{"Не выбрано","Мужской", "Женский"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        profileSex.setAdapter(adapter);
    }
}