package kr.hs.dgsw.quizapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import kr.hs.dgsw.quizapp.Model.QuestionBean;
import kr.hs.dgsw.quizapp.Model.QuestionDBHelper;

public class QuestionListActivity extends AppCompatActivity implements ListClickListener{

    private final int REQUEST_CODE = 1;
    private RecyclerView list;
    private QuestionDBHelper helper;
    private ListAdapter adapter;
    private ArrayList<QuestionBean> data;
    private final int password = 0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        findViewById(R.id.mainLayout).setVisibility(View.GONE);
        findViewById(R.id.pwLayout).setVisibility(View.VISIBLE);

        list = findViewById(R.id.questionList);
        helper = new QuestionDBHelper(this, "question", null, 1);
        data = helper.select();
        adapter = new ListAdapter(data, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }

    public void onSetting(View view){
        Intent intent = new Intent(this, SettingActivity.class);
        //intent.putExtra("newQuestion", "new");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode != REQUEST_CODE)
            return;
        else if(resultCode != RESULT_OK){
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            return;
        }

        this.data = helper.select();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    public void onClick(View v, int position) {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("id", data.get(position).getId());
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void onBtnPwClick(View v) {
        EditText pw = findViewById(R.id.editTextPw);

        if (pw.getText().toString().length() == 0){
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password == Integer.parseInt(pw.getText().toString())){
            findViewById(R.id.pwLayout).setVisibility(View.GONE);
            findViewById(R.id.mainLayout).setVisibility(View.VISIBLE);
        } else
            Toast.makeText(this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
    }
}
