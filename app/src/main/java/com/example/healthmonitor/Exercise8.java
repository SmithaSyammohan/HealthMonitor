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


public class Exercise8 extends AppCompatActivity {

    public int counter;
    Button button;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise8);
        getSupportActionBar().hide();
        button= (Button) findViewById(R.id.button);

        textView= (TextView) findViewById(R.id.textView);

        ImageView imageView = findViewById(R.id.imageView);





        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercise8.this, MenuActivity.class);

                startActivity(intent);

            }
        });



        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){
                textView.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){

                textView.setText("Good job! You have finished the set.");










            }
        }.start();



        /* from internet*/
        Glide.with(this)
                .load("https://i.gifer.com/6zUm.gif")
                .into(imageView);



    }
}
