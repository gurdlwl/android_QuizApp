package kr.hs.dgsw.quizapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingActivity extends AppCompatActivity {

    public final int REQUEST_CODE1 =  1;
    public final int REQUEST_CODE2 =  2;
    public final int REQUEST_CODE3 =  3;
    public final int REQUEST_CODE4 =  4;

    LinearLayout textLayout;
    LinearLayout imageLayout;

    RadioGroup rg;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    ToggleButton tb;
    EditText tv1;
    EditText tv2;
    EditText tv3;
    EditText tv4;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setting();
        setLayout("text"); //텍스트로 초기화

        tb.setOnClickListener(v -> {
            if(tb.isChecked()) //사진
                setLayout("image");
            else if(!tb.isChecked()) //텍스트
                setLayout("text");
        });
    }

    private void setting(){
        textLayout = (LinearLayout) findViewById(R.id.layoutText);
        imageLayout = (LinearLayout) findViewById(R.id.layoutImage);

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        tb = (ToggleButton) findViewById(R.id.toggleButton);

        tv1 = (EditText) findViewById(R.id.editText1);
        tv2 = (EditText) findViewById(R.id.editText2);
        tv3 = (EditText) findViewById(R.id.editText3);
        tv4 = (EditText) findViewById(R.id.editText4);

        iv1 = (ImageView) findViewById(R.id.imageView1);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        iv4 = (ImageView) findViewById(R.id.imageView4);
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

    // radiobox 체크되어있는 문항, 문제 내용 DB에 저장
    public void onDelete(View view){

    }

    public void onSave(View view){

    }
}
