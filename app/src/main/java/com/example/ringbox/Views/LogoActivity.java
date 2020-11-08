package com.example.ringbox.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ringbox.R;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
        setContentView(R.layout.activity_logo);
    }
}