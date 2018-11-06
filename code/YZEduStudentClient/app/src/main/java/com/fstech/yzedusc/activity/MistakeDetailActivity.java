package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.adapter.ExamAdapter;
import com.fstech.yzedusc.adapter.ExamAdapter2;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.bean.MistakeBean;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-5-31.
 * 错题详情主界面
 */

public class MistakeDetailActivity extends AppCompatActivity {
    private ListView lv_exam;
    private List<Map<String, Object>> listItems;
    private ExamAdapter2 adapter;
    private YZEduApplication application;
    private List<MistakeBean> mblist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);
        initView();
        initData();
        questionList();
    }

    private void initView() {
        lv_exam = (ListView) findViewById(R.id.lv_exam);
        listItems = new ArrayList<Map<String, Object>>();
        mblist = new ArrayList<>();
    }

    private void initData() {
        application = (YZEduApplication) getApplication();
    }


    public void back(View v) {
        finish();
    }

    private void questionList(){
        Intent intent = getIntent();
        MistakeBean mb = (MistakeBean) intent.getSerializableExtra("mb");
        Map<String, Object> listItem = new HashMap<String, Object>();
        listItem.put("exam_id", mb.getMisktake_id());
        listItem.put("question", mb.getExam_item_question());
        listItem.put("A", mb.getExam_item_option1());
        listItem.put("B", mb.getExam_item_option2());
        listItem.put("C", mb.getExam_item_option3());
        listItem.put("D", mb.getExam_item_option4());
        listItem.put("trueans", mb.getExam_item_answer());
        listItem.put("myans", mb.getStudent_answer());
        listItems.add(listItem);
        adapter = new ExamAdapter2(MistakeDetailActivity.this, listItems);
        lv_exam.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}
