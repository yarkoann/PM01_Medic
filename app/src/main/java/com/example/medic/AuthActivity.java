package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AuthActivity extends AppCompatActivity {

    Button sendEmail;
    EditText email;
    TextView authWithYandex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        sendEmail = findViewById(R.id.sendEmail);
        email = findViewById(R.id.loginEmail);
        authWithYandex = findViewById(R.id.signInYandex);

        sendEmail.setEnabled(false);


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                sendEmail.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sendEmail.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(email.getText())) {
                    sendEmail.setEnabled(false);
                }

            }
        });
        sendEmail.setOnClickListener(v -> {

        });
        authWithYandex.setOnClickListener(v -> {

        });
    }
}