package kr.hs.dgsw.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        String type = intent.getExtras().getString("type");
        Toast.makeText(this, type, Toast.LENGTH_SHORT).show();

        if(type == "easy"){

        } else if(type == "hard"){

        }
    }
}
