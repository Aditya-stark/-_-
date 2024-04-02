package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView stopwatchTextView;
    private Button startButton, stopButton, holdButton;
    private Handler handler;
    private boolean isRunning = false;
    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatchTextView = findViewById(R.id.stopwatchTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        holdButton = findViewById(R.id.holdButton);
        handler = new Handler();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopwatch();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStopwatch();
            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdStopwatch();
            }
        });
    }

    private void startStopwatch() {
        if (!isRunning) {
            isRunning = true;
            runTimer();
        }
    }

    private void runTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seconds++;
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
                stopwatchTextView.setText(time);

                if (isRunning) {
                    runTimer();
                }
            }
        }, 1000);
    }

    private void stopStopwatch() {
        isRunning = false;
    }

    private void holdStopwatch() {
        isRunning = false;
        seconds = 0;
        stopwatchTextView.setText("00:00:00");
    }
}
