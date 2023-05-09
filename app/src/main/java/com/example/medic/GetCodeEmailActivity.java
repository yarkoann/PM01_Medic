package com.example.medic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCodeEmailActivity extends AppCompatActivity {

    EditText code1, code2, code3, code4;
    TextView timeCode;
    ImageView back;

    // Для обратного отсчета
    private int resendTime = 60;

    // позиция ввода текста
    private int selectedPosition = 0;

    //если правильно введен результат
    private boolean isCurrentCode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_code_email);

        code1 = findViewById(R.id.codeEmail1);
        code2 = findViewById(R.id.codeEmail2);
        code3 = findViewById(R.id.codeEmail3);
        code4 = findViewById(R.id.codeEmail4);
        timeCode = findViewById(R.id.timeCode);
        back = findViewById(R.id.emailBack);



        code1.addTextChangedListener(watcher);
        code2.addTextChangedListener(watcher);
        code3.addTextChangedListener(watcher);
        code4.addTextChangedListener(watcher);

        // установка по умолчанию
        showKeyboard(code1);

        // обратный отсчет
        startCountDownTime();

        back.setOnClickListener(v -> {
            goEmail();
        });

    }

    private void goEmail() {
        Intent email = new Intent(this, AuthActivity.class);
        startActivity(email);
        finish();
    }
    private void showKeyboard(EditText code) {
        code.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(code, InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCountDownTime() {
        new CountDownTimer(resendTime * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeCode.setText("Отправить код повторно можно \n будет через "+(millisUntilFinished/1000) +" секунд");
            }

            @Override
            public void onFinish() {
                timeCode.setText("Отправить код повторно");
            }
        }.start();
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() > 0) {
                switch(selectedPosition) {
                    case 0:
                        selectedPosition = 1;
                        showKeyboard(code2);
                        break;
                    case 1:
                        selectedPosition = 2;
                        showKeyboard(code3);
                        break;
                    case 2:
                        selectedPosition = 3;
                        showKeyboard(code4);
                        break;
                    case 3:
                        isCurrentCode = checkCode();
                        code1.setEnabled(false);
                        code2.setEnabled(false);
                        code3.setEnabled(false);
                        code4.setEnabled(false);

                        if(isCurrentCode) {
                            Toast.makeText(GetCodeEmailActivity.this, "Все верно", Toast.LENGTH_SHORT).show();
                            Intent create_password = new Intent(GetCodeEmailActivity.this, CreatePasswordActivity.class);
                            startActivity(create_password);
                        } else {
                            Toast.makeText(GetCodeEmailActivity.this, "Неверно введен код", Toast.LENGTH_SHORT).show();
                            goEmail();

                        }
                }
            }
        }
    };

    private boolean checkCode() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String email = sharedPref.getString("email", "");
        String userCode = code1.getText().toString() + code2.getText().toString() + code3.getText().toString() + code4.getText().toString();

        MedicApi api = MedicApi.retrofit.create(MedicApi.class);
        Call<EmailCode> call = api.signIn(email, Integer.parseInt(userCode));
        call.enqueue(new Callback<EmailCode>() {
            @Override
            public void onResponse(@NonNull Call<EmailCode> call, @NonNull Response<EmailCode> response) {
                if (response.isSuccessful()) {
                    EmailCode token = response.body();
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token", token.getToken());
                    editor.apply();
                }
            }

            @Override
            public void onFailure(@NonNull Call<EmailCode> call, @NonNull Throwable t) {
                Toast.makeText(GetCodeEmailActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ErrorCode", ""+t.getMessage());
            }
        });
        return sharedPref.contains("token");
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_DEL) {
            switch(selectedPosition) {
                case 1:
                    selectedPosition = 0;
                    showKeyboard(code1);
                    break;
                case 2:
                    selectedPosition = 1;
                    showKeyboard(code2);
                    break;
                case 3:
                    selectedPosition = 2;
                    showKeyboard(code3);
                    break;
            }
            return true;
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }
}