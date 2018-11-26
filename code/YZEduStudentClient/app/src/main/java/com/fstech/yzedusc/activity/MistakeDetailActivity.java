package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.MistakeBean;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.util.TokenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-5-31.
 * 错题详情主界面
 */

public class MistakeDetailActivity extends AppCompatActivity {
    // 定义UI组件
    private ProgressBar progressBar;
    private TextView tv_remove;
    private TextView tv_question;

    private LinearLayout ll_options;
    private TextView tv_option1;
    private TextView tv_option2;
    private TextView tv_option3;
    private TextView tv_option4;

    private TextView tv_trueans;
    private TextView tv_myans;

    private String mistake_id;
    private String my_exam_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mistake_detail);
        initView();
        initData();
    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        tv_remove = (TextView) findViewById(R.id.tv_remove);
        tv_question = (TextView) findViewById(R.id.tv_question);
        ll_options = (LinearLayout) findViewById(R.id.ll_options);
        tv_option1 = (TextView) findViewById(R.id.tv_option1);
        tv_option2 = (TextView) findViewById(R.id.tv_option2);
        tv_option3 = (TextView) findViewById(R.id.tv_option3);
        tv_option4 = (TextView) findViewById(R.id.tv_option4);
        tv_trueans = (TextView) findViewById(R.id.tv_trueans);
        tv_myans = (TextView) findViewById(R.id.tv_myans);
        tv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeMistake();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        my_exam_id = intent.getStringExtra("my_exam_id");
        setDetail();
    }

    /**
     * 设置错题详情
     */
    private void setDetail() {
        String token = TokenUtil.getToken(MistakeDetailActivity.this);
        String url = Constant.BASE_DB_URL + "learn/mistakeDetail";
        Map<String, String> map = new HashMap<>();
        map.put("my_exam_id", my_exam_id);
        map.put("token", token);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(MistakeDetailActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        JSONObject mistake = jsonObject.getJSONObject("return_data");
                        int exam_type = mistake.getInt("exam_type");
                        if (exam_type == 0) {
                            ll_options.setVisibility(View.VISIBLE);
                            tv_option1.setText("A." + mistake.getString("option1"));
                            tv_option2.setText("B." + mistake.getString("option2"));
                            tv_option3.setText("C." + mistake.getString("option3"));
                            tv_option4.setText("D." + mistake.getString("option4"));
                        } else {
                            ll_options.setVisibility(View.GONE);
                        }
                        tv_question.setText(mistake.getString("question"));
                        tv_myans.setText(mistake.getString("student_ans"));
                        tv_trueans.setText(mistake.getString("answer"));
                        mistake_id = mistake.getInt("lesson_id") + "";    // 借一下属性
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(MistakeDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }

    /**
     * 移出错题的方法
     */
    private void removeMistake() {
        progressBar.setVisibility(View.VISIBLE);
        String token = TokenUtil.getToken(MistakeDetailActivity.this);
        String url = Constant.BASE_DB_URL + "learn/removeFromMistake";
        Map<String, String> map = new HashMap<>();
        map.put("mistake_id", mistake_id);
        map.put("my_exam_id", my_exam_id);
        map.put("token", token);
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(MistakeDetailActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        Toast.makeText(MistakeDetailActivity.this, R.string.remove_success, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(MistakeDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 返回上一级的方法
     */
    public void back(View v) {
        finish();
    }


}
