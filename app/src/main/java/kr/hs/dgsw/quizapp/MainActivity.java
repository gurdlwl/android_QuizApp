package kr.hs.dgsw.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    //private RadioGroup rg = findViewById(R.id.group);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rg.setOnCheckedChangeListener();
    }



    public void onQuizActivity(View view) {
        startActivity(new Intent(this, QuizActivity.class));
    }

    public void onQuestionListActivity(View view) {
        startActivity(new Intent( this, QuestionListActivity.class));
    }


}
