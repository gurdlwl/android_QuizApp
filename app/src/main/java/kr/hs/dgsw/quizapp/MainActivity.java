package kr.hs.dgsw.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton easy;
    RadioButton hard;
    RadioButton rb;
    String diff;
    TextView diffInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg  = findViewById(R.id.group);
        easy = findViewById(R.id.EasyBtn);
        hard = findViewById(R.id.HardBtn);
        diffInfo = findViewById(R.id.textViewDiffInfo);

        easy.setOnClickListener(radioBtnClickListener);
        hard.setOnClickListener(radioBtnClickListener);
    }

    RadioButton.OnClickListener radioBtnClickListener = new RadioButton.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(easy.isChecked()){
                diffInfo.setText("글과 사진으로된 객관식 문제로만\n이루어져 있습니다.");
            } else if(hard.isChecked()){
                diffInfo.setText("글로된 주관식 문제로만\n이루어져 있습니다.");
            }
        }
    };

    public void onQuizActivity(View view) {
        rb = findViewById(rg.getCheckedRadioButtonId());
        diff = rb.getText().toString();

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("type", diff);
        startActivity(intent);
    }

    public void onQuestionListActivity(View view) {
        startActivity(new Intent( this, QuestionListActivity.class));
    }

}
