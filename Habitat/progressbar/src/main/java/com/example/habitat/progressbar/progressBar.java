package com.example.habitat.progressbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.AvoidXfermode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.*;
import android.preference.DialogPreference;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;


public class progressBar extends Activity {

    private int mProgressStatus = 0;
    private int mProgressStatus2 = 0;
    private int mProgressStatus3 = 0;
    private int incrementAmount = 1;
    private android.widget.TextView days_total;
    private int no_days_total = 0;
    private android.widget.TextView days_progress;
    private int no_days_progress = 0;
    private android.widget.TextView days_skipped;
    private int no_days_skipped = 0;
    private ProgressBar mProgress;
    private ProgressBar mProgress2;
    private ProgressBar mProgress3;
    private Handler mHandler = new Handler();
    private ImageButton btn;
    private Date last_recorded_time = new Date();
    private ViewGroup mContainerView;
    private String habit;
    private int days;
    private int every_something_day;
    private TextView habit_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        habit = getIntent().getStringExtra("SH");
        days = Integer.valueOf(getIntent().getStringExtra("SD").trim());
        every_something_day = Integer.valueOf(getIntent().getStringExtra("SESD").trim());
        habit_view = (TextView) findViewById(R.id.textView);
        mProgress = (ProgressBar) findViewById(R.id.bar1);
        mProgress2 = (ProgressBar) findViewById(R.id.bar2);
        mProgress3 = (ProgressBar) findViewById(R.id.bar3);

                mHandler.post(new Runnable() {
                    public void run() {
                        habit_view.setText(habit);
                        mProgress.setMax(days);
                        mProgress2.setMax(days);
                        mProgress3.setMax(days);
                    }
                });

        incrementAmount = every_something_day;

        days_total = (TextView) findViewById(R.id.days_total);
        days_progress = (TextView) findViewById(R.id.days_progress);
        days_skipped = (TextView) findViewById(R.id.days_skipped);
        btn = (ImageButton) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    public void run() {
                        Date current_time = new Date();
                        mProgressStatus3 += incrementAmount;
                        no_days_total++;
                        mHandler.post(new Runnable() {
                            public void run() {
                                mProgress3.setProgress(mProgressStatus3);
                                days_total.setText("" + no_days_total);
                            }
                        });
                        if (Math.abs(last_recorded_time.getSeconds() - current_time.getSeconds()) >= 5) {
                            mProgressStatus2 += incrementAmount;
                            no_days_skipped++;
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress2.setProgress(mProgressStatus2);
                                    days_skipped.setText("" + no_days_skipped);
                                }
                            });
                            last_recorded_time = current_time;

                        } else {//if(Math.abs(last_recorded_time.getMinutes() - current_time.getMinutes()) >= 1 || mProgressStatus==0) {

                            mProgressStatus += incrementAmount;
                            no_days_progress++;
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress.setProgress(mProgressStatus);
                                    days_progress.setText("" + no_days_progress);
                                }
                            });
                            last_recorded_time = current_time;
                        }

                        if (no_days_total >= (int)Math.round(days/every_something_day)) {
                            changeActivity();
                        }
                    }
                }).start();
            }

        });

    }
    public void changeActivity(){
        Intent intent= new Intent(this, activity_complete.class);
        startActivity(intent);
    }
}

/*
    private void addItem() {
        View newView;

        mContainerView.addView(newView, 0);
    }
}
*/