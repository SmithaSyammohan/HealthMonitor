package com.example.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {
    private String prot, car, fa, fi, name, cal;
    private FirebaseAuth mAuth;


    private TextView foodTV,measurementTV,caloriesTV,gramsTV,catTV;
    private Scientist receivedScientist;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private void initializeWidgets(){
        foodTV= findViewById(R.id.foodTV);
        measurementTV= findViewById(R.id.measurementTV);
        caloriesTV= findViewById(R.id.caloriesTV);
        gramsTV= findViewById(R.id.gramsTV);

        catTV= findViewById(R.id.catTV);
        FloatingActionButton editFAB = findViewById(R.id.editFAB);
        mCollapsingToolbarLayout=findViewById(R.id.mCollapsingToolbarLayout);
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().
                getColor(R.color.white));
    }
    private void receiveAndShowData(){
         receivedScientist= Utils.receiveScientist(getIntent(),DetailActivity.this);

         if(receivedScientist != null){
             foodTV.setText(receivedScientist.getFood());
             measurementTV.setText(receivedScientist.getMeasure());
             caloriesTV.setText(receivedScientist.getCalories());
             gramsTV.setText(receivedScientist.getGrams());
             catTV.setText(receivedScientist.getCategory());

             prot = receivedScientist.getProtein();
             car = receivedScientist.getCarbs();
             fa = receivedScientist.getFat();
             fi = receivedScientist.getFib();
             name = receivedScientist.getFood();
             cal = receivedScientist.getCalories();


             mCollapsingToolbarLayout.setTitle(receivedScientist.getFood());
         }

    }

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mAuth = FirebaseAuth.getInstance();
        String u_id = mAuth.getCurrentUser().getUid();





//        data=CalorieActivity.getActivityInstance().getData();
//        Toast.makeText(DetailActivity.this,"Data from first activity is"+data, Toast.LENGTH_LONG).show();






        Button nutri = (Button) findViewById(R.id.nutri);
        nutri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, PieActivity.class);
                intent.putExtra("prot", prot);
                intent.putExtra("car", car);
                intent.putExtra("fa", fa);
                intent.putExtra("fi", fi);
                intent.putExtra("name", name);

                view.getContext().startActivity(intent);
            }
        });

        Button addfood = (Button) findViewById(R.id.addfood);
        addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference users = myRootRef.child("Users");
                users.child(u_id).child("Foodstuff").child(name).setValue(name);
                users.child(u_id).child("Foodstuff").child(name).child("Name").setValue(name);

                users.child(u_id).child("Foodstuff").child(name).child("Calories").setValue(cal);




/*

                DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
                //  String userId = myRootRef.push().getKey();


                DatabaseReference users = myRootRef.child("Users");

                users.child(data).child("Foods").child(name).setValue(name);  */





                Intent intent1 = new Intent(DetailActivity.this, CalorieActivity.class);
                intent1.putExtra("name", name);
               intent1.putExtra("cal", cal);

                view.getContext().startActivity(intent1);
            }
        });


        initializeWidgets();
        receiveAndShowData();


    }
}
//end

