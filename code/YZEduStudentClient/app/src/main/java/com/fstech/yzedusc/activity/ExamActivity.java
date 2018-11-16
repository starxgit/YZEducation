package com.fstech.yzedusc.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.adapter.ExamAdapter;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;

import okhttp3.Call;

public class ExamActivity extends Activity {
    private ListView lv_exam;
    private List<Map<String, Object>> listItems;
    private List<Map<String, Object>> answers;
    private TextView tv_submit;
    private ExamAdapter adapter;
    private String acosid, userid;
    private int hasDone = 0;
    private YZEduApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initView();
        initData();
        try {
            questionList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        lv_exam = (ListView) findViewById(R.id.lv_exam);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        listItems = new ArrayList<Map<String, Object>>();
        answers = new ArrayList<Map<String, Object>>();
    }

    private void initData() {
        application = (YZEduApplication) getApplication();
        acosid = "1";
        userid = "120110040225";
    }


    private void hiddenSubmit() {
        if (hasDone == 0) {
            tv_submit.setVisibility(View.VISIBLE);
        } else {
            tv_submit.setVisibility(View.GONE);
        }
    }

    public void back(View v) {
        finish();
    }

    public void submit(View v) {
        for (int i = 0; i < answers.size(); i++) {
            String ans = answers.get(i).get("ms").toString();
            String eid = answers.get(i).get("exam_id").toString();
            try {
                subans(eid, ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(ExamActivity.this, "已提交！", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void questionList() throws Exception {
        String url = Constant.TEMP_BASE_URL + "exam/qlist";
        Map<String, String> map = new HashMap<String, String>();
        map.put("acosid", acosid);
        map.put("userid", userid);
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(ExamActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    JSONArray ja = jobj.getJSONArray("qlist");
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject exam = ja.getJSONObject(i);
                        Map<String, Object> listItem = new HashMap<String, Object>();
                        listItem.put("exam_id", exam.getString("exam_id"));
                        listItem.put("question", exam.getString("question"));
                        listItem.put("A", exam.get("ans_a"));
                        listItem.put("B", exam.get("ans_b"));
                        listItem.put("C", exam.get("ans_c"));
                        listItem.put("D", exam.get("ans_d"));
                        listItem.put("trueans", exam.getString("trueans"));
                        if (!exam.getString("trueans").equals("no")) hasDone = 1;
                        listItem.put("myans", exam.getString("myans"));
                        listItems.add(listItem);
                        answers.add(listItem);
                    }
                    hiddenSubmit();
                    adapter = new ExamAdapter(ExamActivity.this, listItems, answers);
                    lv_exam.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("err", e.getMessage());
                }
            }
        });
    }

    private void subans(String examid, String myans) throws Exception {
        String url = Constant.TEMP_BASE_URL + "exam/subans";
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        map.put("examid", examid);
        map.put("myans", myans);
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(ExamActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
            }
        });
    }

}
