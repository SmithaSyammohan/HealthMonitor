package com.example.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class Exercise1 extends AppCompatActivity {

    public int counter;

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);
        getSupportActionBar().hide();

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent = new Intent(Exercise1.this, Exercise2.class);

                startActivity(intent);


            }
        });


        textView= (TextView) findViewById(R.id.textView);

        ImageView imageView = findViewById(R.id.imageView);

        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){
                textView.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){

                textView.setText("Next!");

                Intent intent = new Intent(Exercise1.this, Exercise2.class);

                startActivity(intent);
            }
        }.start();



        /* from internet*/
        Glide.with(this)
                .load("https://media.giphy.com/media/ckMk3RKUK29lziaspI/giphy.gif")
                .into(imageView);



    }
}