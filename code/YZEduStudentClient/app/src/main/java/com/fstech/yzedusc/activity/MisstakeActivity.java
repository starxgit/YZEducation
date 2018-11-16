package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.bean.MistakeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoxin on 18-5-5.
 * 错题的主界面
 */

public class MisstakeActivity extends AppCompatActivity {

    private ListView lv_mistakes;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> listItems;
    private List<MistakeBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misstake);
        initView();
        initData();
    }

    /*
    * 初始化视图
    * */
    private void initView() {
        lv_mistakes = (ListView) findViewById(R.id.lv_mistakes);
        listItems = new ArrayList<>();
        list = new ArrayList<>();
        adapter = new SimpleAdapter(MisstakeActivity.this, listItems,
                android.R.layout.simple_list_item_1,
                new String[]{"question"},
                new int[]{android.R.id.text1});
        lv_mistakes.setAdapter(adapter);
        lv_mistakes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MisstakeActivity.this,MistakeDetailActivity.class);
                intent.putExtra("mb",list.get(i));
                startActivity(intent);
            }
        });
    }

    /*
    * 初始化数据
    * */
    private void initData() {
        YZEduApplication application = (YZEduApplication) getApplication();
//        list = application.getMistakes();
//        for (int i = 0; i < list.size(); i++) {
//            Map<String, Object> li = new HashMap<String, Object>();
//            li.put("question", list.get(i).getExam_item_question());
//            listItems.add(li);
//        }
        adapter.notifyDataSetChanged();
    }

    /*
   * 返回上一级
   * xml布局文件里面调用
   * */
    public void back(View view) {
        finish();
    }

}
