package com.example.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BmiActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bmi);

        mAuth = FirebaseAuth.getInstance();
        String u_id = mAuth.getCurrentUser().getUid();


        DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();

     //   String id = getIntent().getStringExtra("ID");
     //   String u_email = id.replace(".", "");
        double d;



        DatabaseReference users = myRootRef.child("Users");
        users.child(u_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String height = String.valueOf(dataSnapshot.child("Height").getValue());
                double h = Double.parseDouble(height);
                h = h/100;

                String weight = String.valueOf(dataSnapshot.child("Weight").getValue());
                double w = Double.parseDouble(weight);

                TextView category = (TextView)findViewById(R.id.message);

                String cat = getCategory(metricFormula(h, w));

                category.setText(cat);





            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }
    public static double metricFormula(double m, double kg) {
        double result=0;

        result = kg / (Math.pow(m, 2));

        return result;
    }

    public static String getCategory (double result) {
        String category;
        if (result < 15) {
            category = "You are extremely underweight.";
        } else if (result >=15 && result <= 16) {
            category = "You are severely underweight.";
        } else if (result >16 && result <= 18.5) {
            category = "You are underweight.";
        } else if (result >18.5 && result <= 25) {
            category = "You are at a normal weight.";
        } else if (result >25 && result <= 30) {
            category = "You are overweight.";
        } else if (result >30 && result <= 35) {
            category = "You are moderately obese.";
        } else if (result >35 && result <= 40) {
            category = "You are severely obese.";
        } else {
            category ="You are extremely obese.";
        }
        return category;
    }


}