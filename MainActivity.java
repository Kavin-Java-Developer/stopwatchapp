package com.example.stopwatch;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView timer;
    Button start, pause, reset;
    long millisecondtime, stime, timebuff, update=0l;
    Handler handler;
    int sec, min, millisec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);

        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        reset = (Button) findViewById(R.id.reset);

        handler = new Handler();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                reset.setEnabled(false);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timebuff += millisecondtime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                millisecondtime = 0l;
                stime = 0l;
                timebuff = 0l;
                update = 0l;
                sec = 0;
                min = 0;
                millisec = 0;
                timer.setText("00.00.00");

            }
        });
    }


    public Runnable runnable = new Runnable() {
        @Override
        public void run() {

            millisecondtime = SystemClock.uptimeMillis() - stime;
            update = timebuff + millisecondtime;
            sec = (int) (update / 1000);
            min = sec / 60;
            sec = sec % 60;
            millisec = (int) (update % 1000);
            timer.setText("" + min + ":" + String.format("%02d", sec) + ":" + String.format("%03d", millisec));

            handler.postDelayed(this, 0);
        }
    };

}





