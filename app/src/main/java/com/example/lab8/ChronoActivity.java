package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;

public class ChronoActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long pause;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        TextView email = findViewById(R.id.emailView);
        email.setText(getIntent().getStringExtra("email"));

        chronometer = findViewById(R.id.chronometer2);

        findViewById(R.id.start).setOnClickListener(view -> start());

        findViewById(R.id.stop).setOnClickListener(view -> stop());

        findViewById(R.id.reset).setOnClickListener(view -> reset());
    }

    public void start(){
        if(!isRunning) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pause);
            chronometer.start();
            isRunning = true;
        }
    }

    public void stop() {
        if(isRunning) {
            chronometer.stop();
            pause = (SystemClock.elapsedRealtime() - chronometer.getBase());
            isRunning = false;
        }
    }

    public void reset() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pause = 0;
    }
}