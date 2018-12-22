package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.util.CacheActivityUtil;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 12/22/18.
 */

public class AddExamActivity extends AppCompatActivity {
    private int lesson_id;
    private EditText et_question;
    private EditText et_option1;
    private EditText et_option2;
    private EditText et_option3;
    private EditText et_option4;
    private EditText et_answer;
    private Spinner sp_type;
    private Button bn_submit;
    private int type;   // （0表示选择题，1表示填空题，2表示主观题）
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);
        initView();
        initData();

    }

    private void initView() {
        et_question = (EditText) findViewById(R.id.et_question);
        et_option1 = (EditText) findViewById(R.id.et_option1);
        et_option2 = (EditText) findViewById(R.id.et_option2);
        et_option3 = (EditText) findViewById(R.id.et_option3);
        et_option4 = (EditText) findViewById(R.id.et_option4);
        et_answer = (EditText) findViewById(R.id.et_answer);
        sp_type = (Spinner) findViewById(R.id.sp_type);
        bn_submit = (Button) findViewById(R.id.bn_submit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
                if (position == 0) {
                    et_option1.setVisibility(View.VISIBLE);
                    et_option2.setVisibility(View.VISIBLE);
                    et_option3.setVisibility(View.VISIBLE);
                    et_option4.setVisibility(View.VISIBLE);
                } else {
                    et_option1.setVisibility(View.GONE);
                    et_option2.setVisibility(View.GONE);
                    et_option3.setVisibility(View.GONE);
                    et_option4.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExam();
            }
        });

    }

    private void initData() {
        Intent intent = getIntent();
        lesson_id = intent.getIntExtra("lesson_id", -1);
    }

    public void back(View v) {
        finish();
    }

    /**
     * 添加习题的方法
     */
    public void addExam() {
        String question = et_question.getText().toString();
        String answer = et_answer.getText().toString();
        String option1, option2, option3, option4;
        if (!question.equals("") && !answer.equals("")) {
            switch (type) {
                case 0:
                    // 选择题
                    option1 = et_option1.getText().toString();
                    option2 = et_option2.getText().toString();
                    option3 = et_option3.getText().toString();
                    option4 = et_option4.getText().toString();
                    if (option1.equals("") || option2.equals("") || option3.equals("") || option4.equals("")) {
                        Toast.makeText(AddExamActivity.this, "选择题选项不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        if(option1.equals(answer)){
                            answer = "A";
                            submitExam(question, option1, option2, option3, option4, answer);
                        }else if(option2.equals(answer)){
                            answer = "B";
                            submitExam(question, option1, option2, option3, option4, answer);
                        }else if(option3.equals(answer)){
                            answer = "C";
                            submitExam(question, option1, option2, option3, option4, answer);
                        }else if(option4.equals(answer)){
                            answer = "D";
                            submitExam(question, option1, option2, option3, option4, answer);
                        }else{
                            Toast.makeText(AddExamActivity.this,"答案和选项不一致",Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case 1:
                    // 填空题
                    submitExam(question, "", "", "", "", answer);
                    break;
                case 2:
                    // 主观题
                    submitExam(question, "", "", "", "", answer);
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(AddExamActivity.this, "请输入问题题目和答案", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 提交习题的方法
     */
    public void submitExam(String question, String option1, String option2, String option3, String option4, String answer) {
        setLock();
        Map<String, String> map = new HashMap<>();
        map.put("lesson_id", lesson_id + "");
        map.put("question", question);
        map.put("exam_type", type + "");
        map.put("option1", option1);
        map.put("option2", option2);
        map.put("option3", option3);
        map.put("option4", option4);
        map.put("answer", answer);
        String url = Constant.BASE_DB_URL + "teach/addExam";
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            public void onFailure(Call call, Exception e) {
                Log.e("error", "okHttpRequestError");
                relaseLock();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        CacheActivityUtil.finishSingleActivityByClass(ExamActivity.class);
                        Toast.makeText(AddExamActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddExamActivity.this,ExamActivity.class);
                        intent.putExtra("lesson_id",lesson_id);
                        startActivity(intent);
                        finish();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(AddExamActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    relaseLock();
                }
                ;
            }
        });
    }

    /**
     * 设置锁的方法
     */
    private void setLock() {
        bn_submit.setClickable(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 释放锁的方法
     */
    private void relaseLock() {
        bn_submit.setClickable(true);
        progressBar.setVisibility(View.GONE);
    }
}
