package com.example.lab8;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int progressStatus = 0;
    private ProgressBar progressBar;
    private Handler handler;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);

        progressBar = findViewById(R.id.progressbar);
        handler = new Handler(Looper.getMainLooper());
        findViewById(R.id.validate).setOnClickListener(v -> simulateLoading());
    }

    private void simulateLoading() {
        progressStatus = 0;
        email.setError(null);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                progressStatus += 5;
                progressBar.setProgress(progressStatus);

                if (progressStatus < 100) {
                    new Handler(Looper.getMainLooper()).postDelayed(this, 100);
                } else {
                    if (email.getText().toString().isEmpty()) {
                        email.setError("Need to input email first!");
                    } else {
                        String text = email.getText().toString();
                        if (!text.contains("@")) {
                            email.setError("Not a valid email!");
                        } else {
                            Intent intent = new Intent(getApplicationContext(), ChronoActivity.class);
                            intent.putExtra("email", text);
                            startActivity(intent);
                        }
                    }
                }
            }
        }, 0);
    }

}
