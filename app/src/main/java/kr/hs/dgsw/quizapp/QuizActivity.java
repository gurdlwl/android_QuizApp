package kr.hs.dgsw.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.hs.dgsw.quizapp.Model.QuestionBean;
import kr.hs.dgsw.quizapp.Model.QuestionDBHelper;

public class QuizActivity extends AppCompatActivity {

    private ArrayList<QuestionBean> bean;
    private QuestionBean quiz;
    private QuestionDBHelper helper;
    private int index = 0;
    private String[] choices = new String[5];
    private int answer;

    private TextView tv;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton chkRb;

    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setting();

        Intent intent = getIntent();
        String type = intent.getExtras().getString("type");
        // Toast.makeText(this, type, Toast.LENGTH_SHORT).show();

        helper = new QuestionDBHelper(this, "question", null, 1);
        bean = helper.select();

        if(bean.isEmpty()){
            Toast.makeText(this, "문제가 없습니다", Toast.LENGTH_SHORT).show();
            finish();
        } else if(!bean.isEmpty()){
            nextQuiz();
        }
    }

    private void setting(){
        tv = findViewById(R.id.TextViewQTitle);
        rg = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);


    }

    public void onNextClick(View v){
        index++;
        if(index < bean.size())
            nextQuiz();
        else{
            Toast.makeText(this, "다음 문제가 없습니다", Toast.LENGTH_SHORT).show();
            findViewById(R.id.mainLayout).setVisibility(View.GONE);
            findViewById(R.id.scroeLayout).setVisibility(View.VISIBLE);
        }
    }

    private void nextQuiz(){
        //chkAnswer();

        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);

        quiz = bean.get(index);
        tv.setText(quiz.getQuestion());
        choices = quiz.getChoices();
        rb1.setText(choices[0]);
        rb2.setText(choices[1]);
        rb3.setText(choices[2]);
        rb4.setText(choices[3]);
    }

    private void chkAnswer(){
        if(index == 0)
            return;

        answer = quiz.getAnswer();
        //if(chkRb == answer)
    }
}
