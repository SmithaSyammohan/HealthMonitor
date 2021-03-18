package com.example.healthmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private EditText emailTV, passwordTV;
    private Button regBtn;
  //  private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        initializeUI();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                registerNewUser();






            }
        });
    }

    private void registerNewUser() {
    //    progressBar.setVisibility(View.VISIBLE);

        String email, password;

        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter your email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter a password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                       //     progressBar.setVisibility(View.GONE);

                            String username, age, height, gender, weight;

                            EditText usernameTV = (EditText) findViewById(R.id.username); //GETTING USERNAME AGE AND HEIGHT HERE
                            EditText ageTV = (EditText) findViewById(R.id.age);
                            EditText heightTV = (EditText) findViewById(R.id.height);
                            EditText weightTV = (EditText) findViewById(R.id.weight);

                            username = usernameTV.getText().toString();
                            age = ageTV.getText().toString();
                            height = heightTV.getText().toString();
                            weight = weightTV.getText().toString();

                            RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

                            int selectedId = radioGroup.getCheckedRadioButtonId();
                            RadioButton genderradioButton = (RadioButton) findViewById(selectedId);
                            if(selectedId==-1){

                                Toast.makeText(getApplicationContext(), "Select your gender please!", Toast.LENGTH_LONG).show();
                                return;
                            }
                            else{
                             //   Toast.makeText(SignupActivity.this,genderradioButton.getText(), Toast.LENGTH_SHORT).show();
                            }

                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            gender = genderradioButton.getText().toString();
                            String userId = email;  //setting userId as email as it is unique



                            DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
                            //  String userId = myRootRef.push().getKey();

                            String u_id = mAuth.getCurrentUser().getUid();


                            DatabaseReference users = myRootRef.child("Users");
                            String u_email = userId.replace(".", "");
                            users.child(u_id).child("Email").setValue(email);
                            users.child(u_id).child("Password").setValue(password);
                            users.child(u_id).child("Username").setValue(username);
                            users.child(u_id).child("Age").setValue(age);
                            users.child(u_id).child("Height").setValue(height);
                            users.child(u_id).child("Gender").setValue(gender);
                            users.child(u_id).child("Weight").setValue(weight);
                            users.child(u_id).child("WaterProgress").setValue("0");
                            users.child(u_id).child("Glasses").setValue("0");
                            users.child(u_id).child("TotalCalories").setValue("0");


                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();

                            startActivity(intent);



                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                 //           progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void initializeUI() {
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);
        regBtn = findViewById(R.id.register);
    //    progressBar = findViewById(R.id.progressBar);
    }
}