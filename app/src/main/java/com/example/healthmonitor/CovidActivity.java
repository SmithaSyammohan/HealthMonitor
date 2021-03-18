package com.example.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.healthmonitor.ui.activity.MainActivitya;


public class CovidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_covid);

        Button button6 = (Button) findViewById(R.id.button2);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(CovidActivity.this, MainActivitya.class);
                view.getContext().startActivity(intent2);}
        });


        Button button7 = (Button) findViewById(R.id.button3);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(CovidActivity.this, activity_covid.class);
                view.getContext().startActivity(intent2);}
        });


    }
}