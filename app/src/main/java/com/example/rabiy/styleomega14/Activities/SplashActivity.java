package com.example.rabiy.styleomega14.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rabiy.styleomega14.SharedPrefUtility;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SharedPrefUtility.init(getApplicationContext());
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        finish();
    }
}
