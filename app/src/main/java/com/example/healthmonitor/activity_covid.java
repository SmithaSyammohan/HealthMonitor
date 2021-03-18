package com.example.healthmonitor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class activity_covid extends AppCompatActivity {
   public TextView mSampleQuestions, mQuestion,mextra;
   public Button myesButton, mnoButton,mprevButton,mnextButton;
    public static String[] score = new String [6];

    private boolean mAnswer;
    private int mQuestionNumber = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidd);
        getSupportActionBar().hide();

        mSampleQuestions = (TextView)findViewById(R.id.sample);
        mQuestion = (TextView)findViewById(R.id.question);
        myesButton = (RadioButton)findViewById(R.id.yesButton);
        mnoButton = (RadioButton)findViewById(R.id.noButton);
        mprevButton = (Button)findViewById(R.id.prevButton);
        mnextButton = (Button)findViewById(R.id.nextButton);

        myesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score[mQuestionNumber] = "yes";
            }
        });
        mnoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score[mQuestionNumber]="no";
            }
        });

        mprevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (mQuestionNumber != -1) {
                        deleteQuestion();
                    }
                }
            });
    mnextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //perform check before you update the question
                if (mQuestionNumber == covid_pred.questions.length) {
                        if((score[0]=="yes" || score[1]=="yes" && score[2]=="yes" && score[3]== "yes"&& score[4]== "yes"&& score[5] == "yes")){
                            Intent intent = new Intent(activity_covid.this, activity_results.class);
                            startActivity(intent);
                       }
                        else{
                            Intent intent = new Intent(activity_covid.this, activity_results2.class);
                            startActivity(intent);
                        }

                }
                else{
                    updateQuestion();
                }

            }
        });



    }

    public void updateQuestion() {


        mQuestion.setText(covid_pred.questions[mQuestionNumber]);
       // mAnswer = covid_pred.answers[mQuestionNumber];
        mQuestionNumber++;
    }
    public void deleteQuestion() {


        mQuestion.setText(covid_pred.questions[mQuestionNumber]);
       // mAnswer = covid_pred.answers[mQuestionNumber];
        mQuestionNumber--;
    }
    public void clickExit(View view) {
        askToClose();
    }


    @Override
    public void onBackPressed() {
        askToClose();
    }

    private void askToClose (){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity_covid.this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}