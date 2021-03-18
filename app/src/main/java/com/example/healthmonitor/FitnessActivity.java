package com.example.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FitnessActivity extends AppCompatActivity {
    public int counter;
    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        getSupportActionBar().hide();
        button= (Button) findViewById(R.id.button);
        textView= (TextView) findViewById(R.id.textView);

        ImageView imageView = findViewById(R.id.imageView);



        /* from internet*/
        Glide.with(this)
                .load("https://media.giphy.com/media/5t9IcXiBCyw60XPpGu/giphy.gif")
                .into(imageView);


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                new CountDownTimer(3000, 1000){
                    public void onTick(long millisUntilFinished){
                        textView.setText(String.valueOf(counter));
                        counter++;
                    }
                    public  void onFinish(){

                        textView.setText("Next!");

                        Intent intent = new Intent(FitnessActivity.this, Exercise1.class);

                        startActivity(intent);
                    }
                }.start();
            }
        });
    }
}