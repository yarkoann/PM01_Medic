 package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

 public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(LauncherActivity.this);
                if (sharedPref.contains("token")){
                    Intent main = new Intent(LauncherActivity.this, PasswordAppActivity.class);
                    startActivity(main);
                    finish();
                } else {
                    Intent onBoard = new Intent(LauncherActivity.this, OnBoardActivity.class);
                    startActivity(onBoard);
                    finish();
                }
            }
        }, 3000);
    }
}