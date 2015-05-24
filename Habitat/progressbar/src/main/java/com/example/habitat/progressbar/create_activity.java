package com.example.habitat.progressbar;

import com.example.habitat.progressbar.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class create_activity extends Activity {

    private EditText habit;
    private EditText days;
    private EditText every_something_day;
    private Button btn;
    private String S_habitat;
    private String S_days;
    private String S_every_something_day;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);
        habit = (EditText) findViewById(R.id.editText);
        days = (EditText) findViewById(R.id.editText2);
        every_something_day = (EditText) findViewById(R.id.editText3);
        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habit.setVisibility(View.INVISIBLE);
                days.setVisibility(View.INVISIBLE);
                every_something_day.setVisibility(View.INVISIBLE);
                S_habitat = habit.getText().toString();
                S_days = days.getText().toString();
                S_every_something_day = every_something_day.getText().toString();
                startActivity(S_habitat, S_days, S_every_something_day);
            }
        });
    }

    private void startActivity(String S_habitat, String S_days, String S_every_something_day){
        Intent intent = new Intent(this, progressBar.class);
        intent.putExtra("SH",S_habitat);
        intent.putExtra("SD", S_days);
        intent.putExtra("SESD", S_every_something_day);
        startActivity(intent);
    }
}
