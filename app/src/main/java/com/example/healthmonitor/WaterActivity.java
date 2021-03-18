package com.example.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaterActivity extends AppCompatActivity {
    private int progr = 0;
    private int i = 0;
    private String glasses = "";
    private String progress = "";
    private String g;
    private String wp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_water);

        mAuth = FirebaseAuth.getInstance();
        String u_id = mAuth.getCurrentUser().getUid();


        TextView prog = (TextView) findViewById(R.id.tvprog);
        ProgressBar p = (ProgressBar) findViewById(R.id.progress_bar);
        ImageView button1 = (ImageView) findViewById(R.id.button_incr);
        TextView message = (TextView) findViewById(R.id.message);


        String u_email = getIntent().getStringExtra("ID");
        DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference users = myRootRef.child("Users");



        users.child(u_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                g = String.valueOf(dataSnapshot.child("Glasses").getValue());
                wp = String.valueOf(dataSnapshot.child("WaterProgress").getValue());

                prog.setText(g);

                i = Integer.parseInt(g);
                if (i == 8)
                {
                    message.setText("Yay! You have hydrated yourself for the day.");

                }
                else
                {
                    message.setText("Drink 8 glasses of water in a day! Track your progress here.");
                }
                progr = Integer.parseInt(wp);

                p.setProgress(progr);



            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });






        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (progr <= 95) {
                    progr += 13;
                    progress = String.valueOf(progr);

                    p.setProgress(progr);
                    i = i + 1;

                    if (i == 8)
                    {
                        message.setText("Yay! You have hydrated yourself for the day.");

                    }
                    else
                    {
                        message.setText("Drink 8 glasses of water in a day! Track your progress here.");
                    }

                    glasses = String.valueOf(i);
                    prog.setText(glasses);
                    users.child(u_id).child("WaterProgress").setValue(progress);
                    users.child(u_id).child("Glasses").setValue(glasses);


                }


            }
        });

        ImageView button2 = (ImageView) findViewById(R.id.button_decr);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progr >= 5) {
                    progr -= 13;
                    progress = String.valueOf(progr);

                    p.setProgress(progr);

                        i = i - 1;


                    if (i == 8)
                    {
                        message.setText("Yay! You have hydrated yourself for the day.");

                    }
                    else
                    {
                        message.setText("Drink 8 glasses of water in a day! Track your progress here.");
                    }

                    glasses = String.valueOf(i);

                        prog.setText(glasses);


                    users.child(u_id).child("WaterProgress").setValue(progress);
                    users.child(u_id).child("Glasses").setValue(glasses);







                            }


            }
        });
    }

}