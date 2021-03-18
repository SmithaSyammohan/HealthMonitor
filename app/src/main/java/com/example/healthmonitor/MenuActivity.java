package com.example.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {
    String f = "ds";
    private FirebaseAuth mAuth;

    static MenuActivity INSTANCE;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        String u_id = mAuth.getCurrentUser().getUid();


        TextView name = (TextView)findViewById(R.id.user_name);
        TextView email = (TextView)findViewById(R.id.user_email);

        DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();

     //   String id = getIntent().getStringExtra("ID");
  //      String u_email = id.replace(".", "");

  //      f = u_email;

        INSTANCE=this;

        DatabaseReference users = myRootRef.child("Users");
        users.child(u_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ema = String.valueOf(dataSnapshot.child("Email").getValue());
                email.setText(ema);
                String f1 = String.valueOf(dataSnapshot.child("Username").getValue());
                name.setText(f1);


            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        ImageView logout = (ImageView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(), "Logged out!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);

                startActivity(intent);


            }
        });

        ImageView imgFavorite = (ImageView) findViewById(R.id.bmi);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, BmiActivity.class);
        //        intent.putExtra("ID", u_email); //putExtra allows us to take variables to one activity to the next
                startActivity(intent);

            }
        });

        ImageView imgFavorite1 = (ImageView) findViewById(R.id.water);
        imgFavorite1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, WaterActivity.class);
          //      intent.putExtra("ID", u_email); //putExtra allows us to take variables to one activity to the next
                startActivity(intent);

            }
        });

        ImageView imgFavorite2 = (ImageView) findViewById(R.id.calorie);
        imgFavorite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, CalorieActivity.class);
        //        intent.putExtra("ID", u_email); //putExtra allows us to take variables to one activity to the next
                startActivity(intent);

            }
        });

        ImageView imgFavorite3 = (ImageView) findViewById(R.id.fitness);
        imgFavorite3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, FitnessActivity.class);
        //        intent.putExtra("ID", u_email); //putExtra allows us to take variables to one activity to the next
                startActivity(intent);

            }
        });

        ImageView imgFavorite4 = (ImageView) findViewById(R.id.covid);
        imgFavorite4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, CovidActivity.class);
                //        intent.putExtra("ID", u_email); //putExtra allows us to take variables to one activity to the next
                startActivity(intent);

            }
        });


    }

    public static MenuActivity getActivityInstance()
    {
        return INSTANCE;
    }

    public String getData()
    {

        return this.f;
    }
}