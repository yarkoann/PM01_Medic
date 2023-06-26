package com.example.medic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medic.Interface.MedicApi;
import com.example.medic.Models.EmailCode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    Button sendEmail;
    EditText emailUser;
    TextView authWithYandex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        sendEmail = findViewById(R.id.sendEmail);
        emailUser = findViewById(R.id.createName);
        authWithYandex = findViewById(R.id.signInYandex);

        sendEmail.setEnabled(false);


        emailUser.addTextChangedListener(new TextWatcher() {
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
                if (TextUtils.isEmpty(emailUser.getText())) {
                    sendEmail.setEnabled(false);
                }

            }
        });
        sendEmail.setOnClickListener(v -> {
            Intent code = new Intent(AuthActivity.this, GetCodeEmailActivity.class);
            startActivity(code);
        });
        authWithYandex.setOnClickListener(v -> {

        });
    }

    private void sendApiEmail() {
        MedicApi api = MedicApi.retrofit.create(MedicApi.class);
        Call<EmailCode> call = api.sendCode(emailUser.getText().toString());

        call.enqueue(new Callback<EmailCode>() {
            @Override
            public void onResponse(@NonNull Call<EmailCode> call, @NonNull Response<EmailCode> response) {
                if(response.isSuccessful()) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AuthActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", emailUser.getText().toString());
                    editor.putInt("code", response.body().getCode());
                    editor.apply();
                    Intent code = new Intent(AuthActivity.this, GetCodeEmailActivity.class);
                    startActivity(code);
                } else {
                    Log.d("JJJJJ", ""+response.body());
                    Toast.makeText(AuthActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<EmailCode> call, Throwable t) {
                Log.d("Error_API", "Ошибка"+t.getMessage());
                Toast.makeText(AuthActivity.this, "Ошибка"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}