package com.example.jarvis.progressbar;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.app.ProgressDialog;
        import android.content.DialogInterface;
        import android.content.res.Resources;
        import android.graphics.Color;
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.GradientDrawable;
        import android.os.Bundle;
        import android.os.*;
        import android.preference.DialogPreference;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import static com.example.jarvis.progressbar.R.id.bar2;


public class progressbar extends Activity {

    private int mProgressStatus = 0;
    private int mProgressStatus2 = 0;
    private int mProgressStatus3 = 0;
    private ProgressBar mProgress;
    private ProgressBar mProgress2;
    private ProgressBar mProgress3;
    private Handler mHandler = new Handler();
    private int levelCount = 0;
    private TextView levelCountTextView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        levelCountTextView = (TextView) findViewById(R.id.levelNumber);
        mProgress = (ProgressBar) findViewById(R.id.bar1);
        mProgress2 = (ProgressBar) findViewById(R.id.bar2);
        mProgress3 = (ProgressBar) findViewById(R.id.bar3);
        //mProgress.setVisibility(View.INVISIBLE);
        //mProgress2.setVisibility(View.INVISIBLE);
        //mProgress3.setVisibility(View.INVISIBLE);
        //mProgress = (ProgressBar) find
        new Thread(new Runnable() {
            public void run() {
                //levelCountTextView.setText(levelCount);
                while (mProgressStatus < 101) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (levelCount % 3 == 0) {
                        mProgressStatus++;
                    } else if (levelCount % 3 == 1) {
                        mProgressStatus2++;
                    } else {
                        mProgressStatus3++;
                    }
                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                            mProgress2.setProgress(mProgressStatus2);
                            mProgress3.setProgress(mProgressStatus3);
                            if (mProgressStatus >= 100) {
                                //mProgressStatus = 0;
                                mProgressStatus = 0;
                                //mProgress.setProgress(mProgressStatus);
                                levelCount++;
                                levelCountTextView.setText("" + levelCount);
                                //mProgress2.setEnabled(false);
                                mProgress2.setVisibility(View.VISIBLE);
                                mProgress2.setProgress(1);
                                mProgress2 = (ProgressBar) findViewById(R.id.bar2);
                                mProgress.setVisibility(View.INVISIBLE);
                                mProgress3.setVisibility(View.INVISIBLE);
                            }
                            else if (mProgressStatus2 >= 100) {
                                //mProgressStatus = 0;
                                mProgressStatus2 = 0;
                                //mProgress2.setProgress(mProgressStatus2);
                                levelCount++;
                                levelCountTextView.setText("" + levelCount);
                                mProgress3.setVisibility(View.VISIBLE);
                                mProgress3 = (ProgressBar) findViewById(R.id.bar3);
                                mProgress2.setVisibility(View.INVISIBLE);
                                mProgress.setVisibility(View.INVISIBLE);
                            }
                            else if (mProgressStatus3 >= 100) {
                                //mProgressStatus = 0;
                                mProgressStatus3 = 0;
                                //mProgress3.setProgress(mProgressStatus3);
                                levelCount++;
                                levelCountTextView.setText("" + levelCount);
                                mProgress.setVisibility(View.VISIBLE);
                                mProgress = (ProgressBar) findViewById(R.id.bar1);
                                mProgress3.setVisibility(View.INVISIBLE);
                                mProgress2.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        }).start();

    }
}