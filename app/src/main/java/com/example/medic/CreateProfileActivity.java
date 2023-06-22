package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateProfileActivity extends AppCompatActivity {
    EditText createName, createLastName, createThirdName, createDate;
    Spinner createSex;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        setSetting();
        setSpinner();
        setWatcher();
    }

    private void setSetting() {
        createSex = findViewById(R.id.createSex);
        createName = findViewById(R.id.createName);
        createLastName = findViewById(R.id.createLastName);
        createThirdName = findViewById(R.id.createThirdName);
        createDate = findViewById(R.id.createDate);
        next = findViewById(R.id.next);

        next.setOnClickListener(view -> {
            Intent create_password = new Intent(CreateProfileActivity.this, CreatePasswordActivity.class);
            startActivity(create_password);
        });
    }

    private void setSpinner() {
        String[] items = new String[]{"Не выбрано","Мужской", "Женский"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        createSex.setAdapter(adapter);
    }

    private void setWatcher() {
        createName.addTextChangedListener(watcher);
        createLastName.addTextChangedListener(watcher);
        createThirdName.addTextChangedListener(watcher);
        createDate.addTextChangedListener(watcher);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            next.setEnabled(false);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            next.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean isEmpty = TextUtils.isEmpty(createName.getText()) || TextUtils.isEmpty(createLastName.getText()) || TextUtils.isEmpty(createThirdName.getText()) || TextUtils.isEmpty(createDate.getText());
            if (isEmpty) {
                next.setEnabled(false);
            }
        }
    };
}