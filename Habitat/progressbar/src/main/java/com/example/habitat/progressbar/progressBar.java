package com.example.habitat.progressbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.*;


public class progressBar extends Activity {

    private int mProgressStatus = 0;
    private int mProgressStatus2 = 0;
    private int mProgressStatus3 = 0;
    private int incrementAmount = 1;
    private ProgressBar mProgress;
    private ProgressBar mProgress2;
    private ProgressBar mProgress3;
    private Handler mHandler = new Handler();
    private android.widget.Button btn;
    private Date last_recorded_time = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress = (ProgressBar) findViewById(R.id.bar1);
                mProgress2 = (ProgressBar) findViewById(R.id.bar2);
                mProgress3 = (ProgressBar) findViewById(R.id.bar3);
                new Thread(new Runnable() {
                    public void run() {
                        Date current_time= new Date();
                        mProgressStatus3+=incrementAmount;
                        mHandler.post(new Runnable() {
                            public void run() {
                                mProgress3.setProgress(mProgressStatus3);
                            }
                        });
                        if(Math.abs(last_recorded_time.getSeconds() - current_time.getSeconds()) >= 5){
                        mProgressStatus2+=incrementAmount;

                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress.setProgress(mProgressStatus);
                                    mProgress2.setProgress(mProgressStatus2);
                                }
                            });
                            last_recorded_time =  current_time;

                        }
                         else{//if(Math.abs(last_recorded_time.getMinutes() - current_time.getMinutes()) >= 1 || mProgressStatus==0) {

                           mProgressStatus+=incrementAmount;
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress.setProgress(mProgressStatus);
                                }
                            });
                            last_recorded_time =  current_time;
                        }

                    }
                }).start();
            }

        });

    }

}
