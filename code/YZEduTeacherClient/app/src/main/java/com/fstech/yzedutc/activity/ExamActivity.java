package com.fstech.yzedutc.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.ExamAdapter;
import com.fstech.yzedutc.bean.MyExamBean;
import com.fstech.yzedutc.util.CacheActivityUtil;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.util.TokenUtil;

import okhttp3.Call;

public class ExamActivity extends Activity {
    private ListView lv_exam;
    private TextView tv_submit;
    private ExamAdapter adapter;
    private List<MyExamBean> listItems;
    private int lesson_id;
    private ProgressBar progressBar;
    private Button bn_add_exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        CacheActivityUtil.addActivity(ExamActivity.this);
        initView();
        initData();
        getExamList();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        lv_exam = (ListView) findViewById(R.id.lv_exam);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        bn_add_exam = (Button) findViewById(R.id.bn_add_exam);
        bn_add_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamActivity.this, AddExamActivity.class);
                intent.putExtra("lesson_id", lesson_id);
                startActivity(intent);
            }
        });
        listItems = new ArrayList<>();
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
        adapter = new ExamAdapter(ExamActivity.this, listItems);
        lv_exam.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        lesson_id = intent.getIntExtra("lesson_id", -1);
    }

    /**
     * 获取题目及用户习题完成情况的方法
     */
    private void getExamList() {
        setLock();
        String url = Constant.BASE_DB_URL + "teach/examList";
        String token = TokenUtil.getToken(ExamActivity.this);
        Map<String, String> map = new HashMap<String, String>();
        map.put("lesson_id", lesson_id + "");
        map.put("token", token);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("error", "okHttpRequestError");
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        JSONObject return_data = jsonObject.getJSONObject("return_data");
                        JSONArray jsonArray = return_data.getJSONArray("exam_list");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            MyExamBean eb = objectMapper.readValue(jobj.toString(), MyExamBean.class);
                            listItems.add(eb);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(ExamActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    Log.e("mapping", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("IO", e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    relaseLock();
                }
            }
        });
    }


    public void back(View v) {
        finish();
    }

    private void submit() {
        Intent intent = new Intent(ExamActivity.this,CheckExamActivity.class);
        intent.putExtra("lesson_id",lesson_id);
        startActivity(intent);
    }

    /**
     * 设置锁的方法
     */
    private void setLock() {
        progressBar.setVisibility(View.VISIBLE);
        tv_submit.setClickable(false);
    }

    /**
     * 释放锁的方法
     */
    private void relaseLock() {
        progressBar.setVisibility(View.GONE);
        tv_submit.setClickable(true);
    }

}
