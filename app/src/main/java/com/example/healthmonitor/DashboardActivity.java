package com.example.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;




public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();

        TextView food1 = (TextView)findViewById(R.id.textView2);
        TextView email = (TextView)findViewById(R.id.textView3);

        DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();

        String id = getIntent().getStringExtra("ID");
        String u_email = id.replace(".", "");

        DatabaseReference users = myRootRef.child("Users");
        users.child(u_email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ema = String.valueOf(dataSnapshot.child("Email").getValue());
                email.setText(ema);
                String f1 = String.valueOf(dataSnapshot.child("Username").getValue());
                food1.setText(f1);


            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        Button button7 = (Button) findViewById(R.id.save);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = getIntent().getStringExtra("ID");
                String u_email = id.replace(".", "");

                EditText fd1 = (EditText) findViewById(R.id.food1);
                EditText fd2 = (EditText) findViewById(R.id.food2);
                String f1, f2;
                f1 = fd1.getText().toString();
                f2 = fd2.getText().toString();
                DatabaseReference users = myRootRef.child("Users");

                users.child(u_email).child("Food1").setValue(f1);
                users.child(u_email).child("Food2").setValue(f2);


            }


            });


        }




}