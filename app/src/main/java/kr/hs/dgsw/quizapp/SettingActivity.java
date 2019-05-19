package kr.hs.dgsw.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import kr.hs.dgsw.quizapp.Model.QuestionBean;
import kr.hs.dgsw.quizapp.Model.QuestionDBHelper;

public class SettingActivity extends AppCompatActivity {

    public final int REQUEST_CODE1 =  1;
    public final int REQUEST_CODE2 =  2;
    public final int REQUEST_CODE3 =  3;
    public final int REQUEST_CODE4 =  4;

    private LinearLayout textLayout;
    private LinearLayout imageLayout;

    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private ToggleButton tb;
    private EditText editText;
    private EditText tv1;
    private EditText tv2;
    private EditText tv3;
    private EditText tv4;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;

    private int id;
    private int answer = 0;
    //private String newQuestion;
    private String[] choice = new String[5];
    private QuestionBean bean;
    private QuestionDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setting();
        setLayout("text"); //텍스트로 초기화

        helper = new QuestionDBHelper(this, "question", null, 1);
        id = getIntent().getIntExtra("id", -1);
        //newQuestion = getIntent().getStringExtra("newQuestion");

        if(id > -1){
            bean = helper.select(id);
            if(bean.getType() == 1)
                tb.setChecked(false);
            else if(bean.getType() == 2)
                tb.setChecked(true);
            editText.setText(bean.getQuestion());
            choice = bean.getChoices();
            tv1.setText(choice[0]);
            tv2.setText(choice[1]);
            tv3.setText(choice[2]);
            tv4.setText(choice[3]);
            answer = bean.getAnswer();
            if(answer == 1)
                rb1.setChecked(true);
            else if(answer == 2)
                rb2.setChecked(true);
            else if(answer == 3)
                rb3.setChecked(true);
            else if(answer == 4)
                rb4.setChecked(true);
        } else
            bean = new QuestionBean();

        //if(newQuestion.equals("new"))
        //    this.findViewById(R.id.btnDelete).setVisibility(View.GONE);

        tb.setOnClickListener(v -> {
            if(tb.isChecked()) //사진
                setLayout("image");
            else if(!tb.isChecked()) //텍스트
                setLayout("text");
        });

        rg.setOnCheckedChangeListener((group, checkedId) -> {
            Toast.makeText(this, "" + checkedId, Toast.LENGTH_SHORT).show();
            if(checkedId == R.id.radioButton1)
                answer = 1;
            else if(checkedId == R.id.radioButton2)
                answer = 2;
            else if(checkedId == R.id.radioButton3)
                answer = 3;
            else if(checkedId == R.id.radioButton4)
                answer = 4;
        });
    }

    private void setting(){
        textLayout = findViewById(R.id.layoutText);
        imageLayout = findViewById(R.id.layoutImage);

        rg = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        tb = findViewById(R.id.toggleButton);

        editText = findViewById(R.id.editTextQuestion);
        tv1 = findViewById(R.id.editText1);
        tv2 = findViewById(R.id.editText2);
        tv3 = findViewById(R.id.editText3);
        tv4 = findViewById(R.id.editText4);

        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
    }

    private void setLayout(String type){
        if(type == "text"){
            textLayout.setVisibility(View.VISIBLE);
            imageLayout.setVisibility(View.GONE);
        } else if(type == "image") {
            textLayout.setVisibility(View.GONE);
            imageLayout.setVisibility(View.VISIBLE);
        }
    }

    public void onImageClick(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        //초기화된 이미지의 ID와 받아온 ID를 확인해서 데이터 삽입
        switch(v.getId()){
            case R.id.imageView1:
                startActivityForResult(intent, REQUEST_CODE1);
                break;
            case R.id.imageView2:
                startActivityForResult(intent, REQUEST_CODE2);
                break;
            case R.id.imageView3:
                startActivityForResult(intent, REQUEST_CODE3);
                break;
            case R.id.imageView4:
                startActivityForResult(intent, REQUEST_CODE4);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE1)
                iv1.setImageURI(data.getData());
            else if(requestCode == REQUEST_CODE2)
                iv2.setImageURI(data.getData());
            else if(requestCode == REQUEST_CODE3)
                iv3.setImageURI(data.getData());
            else if(requestCode == REQUEST_CODE4)
                iv4.setImageURI(data.getData());
        }
    }

    public void onSave(View view){
        String question = editText.getText().toString();
        choice[0] = tv1.getText().toString();
        choice[1] = tv2.getText().toString();
        choice[2] = tv3.getText().toString();
        choice[3] = tv4.getText().toString();

        if(question.length() == 0){
            Toast.makeText(this, "문제를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if(answer == 0) {
            Toast.makeText(this, "정답을 체크해주세요", Toast.LENGTH_SHORT).show();
        } else if(choice == null){
            Toast.makeText(this, "선택지를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else {

            bean.setQuestion(question);
            bean.setChoices(choice);
            bean.setAnswer(answer);
            if(tb.isChecked())
                bean.setType(QuestionBean.TYPE_IMAGE);
            else
                bean.setType(QuestionBean.TYPE_TEXT);;

            if(id > -1)
                helper.update(bean);
            else
                helper.insert(bean);

            setResult(RESULT_OK);
            finish();
        }
    }

    public void onDelete(View view){
        if(id > -1)
            helper.delete(id);

        setResult(RESULT_OK);
        finish();
    }
}