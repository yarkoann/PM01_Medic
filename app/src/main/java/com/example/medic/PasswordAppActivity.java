package com.example.medic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.MainPage.MainActivity;

public class PasswordAppActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDel;
    private EditText passwordApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_app);
        setSetting();

        btn0.setOnClickListener(v -> onClick(btn0));
        btn1.setOnClickListener(v -> onClick(btn1));
        btn2.setOnClickListener(v -> onClick(btn2));
        btn3.setOnClickListener(v -> onClick(btn3));
        btn4.setOnClickListener(v -> onClick(btn4));
        btn5.setOnClickListener(v -> onClick(btn5));
        btn6.setOnClickListener(v -> onClick(btn6));
        btn7.setOnClickListener(v -> onClick(btn7));
        btn8.setOnClickListener(v -> onClick(btn8));
        btn9.setOnClickListener(v -> onClick(btn9));

        btnDel.setOnClickListener(v -> {
            int cursorPosition = passwordApp.getSelectionEnd();
            if (cursorPosition > 0) {
                passwordApp.getText().delete(cursorPosition - 1, cursorPosition);
            }
        });
        passwordApp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                if (text.length() == 4) {
                    Log.d("Errorrrr", ""+text);
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(PasswordAppActivity.this);
                    String pincode = sharedPref.getString("pin", "");
                    if(pincode.equals(text)) {
                        Intent intent = new Intent(PasswordAppActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    } else {
                        Toast.makeText(PasswordAppActivity.this, "Неправльный пароль", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void onClick(Button btn) {
        String number = btn.getText().toString();
        passwordApp.getText().insert(passwordApp.getSelectionStart(), number);
    }

    private void setSetting() {
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btnDel = (Button) findViewById(R.id.btnDelete);
        passwordApp =  (EditText) findViewById(R.id.passwordApp);

    }
}