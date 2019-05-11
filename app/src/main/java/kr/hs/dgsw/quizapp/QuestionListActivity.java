package kr.hs.dgsw.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class QuestionListActivity extends AppCompatActivity implements ListClickListener{

    RecyclerView list;
    LinearLayoutManager layoutManager;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        list = findViewById(R.id.questionList);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ListAdapter(this);
    }

    public void onSetting(View view){
        startActivity(new Intent(this, SettingActivity.class));
    }

    @Override
    public void onClick(View v, int position) {
        Toast.makeText(this, "click!", Toast.LENGTH_SHORT).show();
    }
}
