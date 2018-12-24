package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.ClassificationBean;
import com.fstech.yzedutc.bean.MyExamBean;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 12/24/18.
 */

public class CheckExamActivity extends AppCompatActivity {
    // 定义UI对象
    private TextView tv_question;
    private LinearLayout ll_options;
    private TextView tv_option1;
    private TextView tv_option2;
    private TextView tv_option3;
    private TextView tv_option4;
    private TextView tv_trueans;
    private TextView tv_stuans;
    private Button bn_check_true;
    private Button bn_check_false;
    private LinearLayout ll_main;
    private TextView tv_nomore;
    private MyExamBean meb;

    private ProgressBar progressBar;
    private int lesson_id;

    private List<MyExamBean> myExamBeanList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_exam);
        initView();
        initData();
    }

    private void initView() {
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        tv_question = (TextView) findViewById(R.id.tv_question);
        ll_options = (LinearLayout) findViewById(R.id.ll_options);
        tv_option1 = (TextView) findViewById(R.id.tv_option1);
        tv_option2 = (TextView) findViewById(R.id.tv_option2);
        tv_option3 = (TextView) findViewById(R.id.tv_option3);
        tv_option4 = (TextView) findViewById(R.id.tv_option4);
        tv_trueans = (TextView) findViewById(R.id.tv_trueans);
        tv_stuans = (TextView) findViewById(R.id.tv_stuans);
        bn_check_true = (Button) findViewById(R.id.bn_check_true);
        bn_check_false = (Button) findViewById(R.id.bn_check_false);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv_nomore = (TextView) findViewById(R.id.tv_nomore);
        myExamBeanList = new ArrayList<>();

        ll_main.setVisibility(View.GONE);
        tv_nomore.setVisibility(View.GONE);

        bn_check_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExam(1);
            }
        });

        bn_check_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExam(2);
            }
        });
    }

    private void initData() {
        meb = new MyExamBean();
        Intent intent = getIntent();
        lesson_id = intent.getIntExtra("lesson_id", -1);
        getExamList();
    }

    private void getExamList() {
        setLock();
        // 获取批改习题列表的方法
        String url = Constant.BASE_DB_URL + "teach/checkExamList";
        Map<String, String> map = new HashMap<String, String>();
        map.put("lesson_id", lesson_id + "");
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("fail", "okhttp请求失败");
                Toast.makeText(CheckExamActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            MyExamBean meb = objectMapper.readValue(jobj.toString(), MyExamBean.class);
                            myExamBeanList.add(meb);
                        }
                        // 设置页面
                        setContent();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(CheckExamActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    Log.e("Mapping", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("IO", e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 设置习题到页面
     */
    private void setContent() {
        if (myExamBeanList.size() == 0) {
            tv_nomore.setVisibility(View.VISIBLE);
            ll_main.setVisibility(View.GONE);
        } else {
            tv_nomore.setVisibility(View.GONE);
            ll_main.setVisibility(View.VISIBLE);
            // 页面逻辑
            meb = myExamBeanList.get(0);
            tv_question.setText(meb.getQuestion());
            tv_stuans.setText(meb.getStudent_ans());
            tv_trueans.setText(meb.getAnswer());
            if (meb.getExam_type() == 0) {
                ll_options.setVisibility(View.VISIBLE);
                tv_option1.setText("A. " + meb.getOption1());
                tv_option2.setText("B. " + meb.getOption2());
                tv_option3.setText("C. " + meb.getOption3());
                tv_option4.setText("D. " + meb.getOption4());
            } else {
                ll_options.setVisibility(View.GONE);
            }
        }
        releaseLock();
    }

    /**
     * 批改并进入下一题的方法
     *
     * @param state (状态，1对，2错)
     */
    private void checkExam(int state) {
        setLock();
        // TODO 批改逻辑
        String url = Constant.BASE_DB_URL + "teach/teacherCheckExam";
        Map<String, String> map = new HashMap<String, String>();
        map.put("my_exam_id", meb.getMy_exam_id() + "");
        map.put("state", state + "");
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(CheckExamActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
                releaseLock();
            }

            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        myExamBeanList.remove(0);
                        setContent();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(CheckExamActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    releaseLock();
                }

            }
        });
    }

    /**
     * 上锁
     */
    private void setLock() {
        progressBar.setVisibility(View.VISIBLE);
        bn_check_true.setClickable(false);
        bn_check_false.setClickable(false);
    }

    /**
     * 解锁
     */
    private void releaseLock() {
        progressBar.setVisibility(View.GONE);
        bn_check_true.setClickable(true);
        bn_check_false.setClickable(true);
    }

    public void back(View v) {
        finish();
    }
}
