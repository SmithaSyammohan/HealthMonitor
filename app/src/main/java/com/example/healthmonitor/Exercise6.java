package com.example.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Exercise6 extends AppCompatActivity {


    public int counter;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise6);
        getSupportActionBar().hide();

        textView= (TextView) findViewById(R.id.textView);

        ImageView imageView = findViewById(R.id.imageView);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Exercise6.this, Exercise7.class);

                startActivity(intent);


            }
        });

        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){
                textView.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){

                textView.setText("Next!");

                Intent intent = new Intent(Exercise6.this, Exercise7.class);

                startActivity(intent);
            }
        }.start();



        /* from internet*/
        Glide.with(this)
                .load("https://i.gifer.com/Ul9q.gif")
                .into(imageView);



    }
}
