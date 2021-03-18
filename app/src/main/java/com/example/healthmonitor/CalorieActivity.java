package com.example.healthmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalorieActivity extends AppCompatActivity {
    int sum = 0;
    int i = 0;



    String flag = "False";
  //  List<String> list = new ArrayList<>();

 //   static CalorieActivity INSTANCE;
 //    String data = "star123@gmailcom";
  private int totalcal = 0;
 private int firstcal = 0;

    private FirebaseAuth mAuth;
    String s = "0";
    String j = "4";
    Map<String, Object> updates = new HashMap<String,Object>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        String u_id = mAuth.getCurrentUser().getUid();





        j = getIntent().getStringExtra("cal");
        String name = getIntent().getStringExtra("name");


        TextView measure = (TextView) findViewById(R.id.measure);
        TextView cal = (TextView) findViewById(R.id.cal);
        TextView calo = (TextView) findViewById(R.id.message3);

        DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference users = myRootRef.child("Users");











        ListView lv = (ListView) findViewById(R.id.lv);
        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        lv.setAdapter(adapter);



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Foodstuff");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String str=snapshot.getValue().toString();
                    String numberOnly= str.replaceAll("[^0-9]", "");


                    try {
                        i=Integer.parseInt(numberOnly);
                    } catch (NumberFormatException e) {
                        //Log it if needed
                        i = 0;//default fallback value;
                    }




                    sum = sum + i;

                    String s=String.valueOf(sum);
                    calo.setText(s);
                    users.child(u_id).child("TotalCalories").setValue(s);

                    list.add(snapshot.getValue().toString());
                }



                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Initializing a new String Array
        String[] fruits = new String[] {
                "Cape Gooseberry",
                "Capuli cherry"
        };






        // Create a List from String Array elements


        // Create an ArrayAdapter from List






















        Button button7 = (Button) findViewById(R.id.search);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalorieActivity.this, ScActivity.class);


                view.getContext().startActivity(intent);}
        });







    }

    /*

    public static CalorieActivity getActivityInstance()
    {
       return INSTANCE;
    }

    public String getData()
    {

        return this.data;
    }

     */
}