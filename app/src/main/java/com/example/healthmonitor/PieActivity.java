package com.example.healthmonitor;



// Import the required libraries
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DecimalFormat;

public class PieActivity
        extends AppCompatActivity {


    // Create the object of TextView
    // and PieChart class
    TextView tvR, tvPython, tvCPP, tvJava, fooditem;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        getSupportActionBar().hide();

        String name = getIntent().getStringExtra("name");

        String prot = getIntent().getStringExtra("prot");
        String car = getIntent().getStringExtra("car");
        String fa = getIntent().getStringExtra("fa");
        String fi = getIntent().getStringExtra("fi");

/*
   //     String u_email = getIntent().getStringExtra("ID");
        DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference food = myRootRef.child("Fooditems");

        food.child("Cows milk").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String carbs = String.valueOf(dataSnapshot.child("Carbs").getValue());

                String fat = String.valueOf(dataSnapshot.child("Fat").getValue());

                String fiber = String.valueOf(dataSnapshot.child("Fiber").getValue());

                String protein = String.valueOf(dataSnapshot.child("Protein").getValue());


                // Link those objects with their
                // respective id's that
                // we have given in .XML file
                tvR = findViewById(R.id.tvR);
                tvPython = findViewById(R.id.tvPython);
                tvCPP = findViewById(R.id.tvCPP);
                tvJava = findViewById(R.id.tvJava);
                pieChart = findViewById(R.id.piechart);
                fooditem = findViewById(R.id.fooditem);
                fooditem.setText("Cows milk");

                // Creating a method setData()
                // to set the text in text view and pie chart
                setData(carbs, fat, fiber, protein);






            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        */

        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        tvR = findViewById(R.id.tvR);
        tvPython = findViewById(R.id.tvPython);
        tvCPP = findViewById(R.id.tvCPP);
        tvJava = findViewById(R.id.tvJava);
        pieChart = findViewById(R.id.piechart);
        fooditem = findViewById(R.id.fooditem);
        fooditem.setText(name);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData(car, fa, fi, prot);




















    }

    private void setData(String carbs, String fat, String fiber, String protein)
    {


        // Set the percentage of language used


        double c=Double.parseDouble(carbs);
        double f=Double.parseDouble(fat);
        double fib=Double.parseDouble(fiber);
        double p=Double.parseDouble(protein);


        double total = c + f + fib + p;

        c = ((c/total) * 100);
        f = ((f/total) * 100);
        fib =((fib/total) * 100);
        p = ((p/total) * 100);

        DecimalFormat df = new DecimalFormat("0.00");


        String cc = df.format(c);
        String ff = df.format(f);
        String ffib = df.format(fib);
        String pp = df.format(p);




        tvR.setText(carbs);
        tvPython.setText(fat);
        tvCPP.setText(protein);
        tvJava.setText(fiber);


        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Carbohydrates",
                        (float) Double.parseDouble(cc),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Fat",
                        (float) Double.parseDouble(ff),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Protein",
                        (float) Double.parseDouble(pp),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Fiber",
                        (float) Double.parseDouble(ffib),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}
