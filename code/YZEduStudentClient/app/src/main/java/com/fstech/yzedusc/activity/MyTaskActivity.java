package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.adapter.TaskListAdapter;
import com.fstech.yzedusc.bean.TaskBean;
import com.fstech.yzedusc.util.CacheActivityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaoxin on 18-4-2.
 * 我的任务主界面
 */

public class MyTaskActivity extends AppCompatActivity {
    private TabHost tabhost;
    private ListView lv_doing;
    private ListView lv_all;
    private ListView lv_finish;
    private List<TaskBean> listItems_doing;
    private List<TaskBean> listItems_all;
    private List<TaskBean> listItems_finish;
    private TaskListAdapter adapter_doing;
    private TaskListAdapter adapter_all;
    private TaskListAdapter adapter_finish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        initView();
        initData();
    }

    /*
   * 返回上一级
   * xml布局文件里面调用
   * */
    public void back(View view) {
        finish();
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        CacheActivityUtil.addActivity(MyTaskActivity.this);
        tabhost = (TabHost) findViewById(R.id.task_tab);
        tabhost.setup();
        //创建Tab标签
        tabhost.addTab(tabhost.newTabSpec("one").setIndicator("进行中").setContent(R.id.ll_doing_task));
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("全部").setContent(R.id.ll_all_task));
        tabhost.addTab(tabhost.newTabSpec("three").setIndicator("已结束").setContent(R.id.ll_finish_task));
        lv_doing = (ListView) findViewById(R.id.lv_doing_task);
        lv_all = (ListView) findViewById(R.id.lv_all_task);
        lv_finish = (ListView) findViewById(R.id.lv_finish_task);
        listItems_doing = new ArrayList<>();
        listItems_all = new ArrayList<>();
        listItems_finish = new ArrayList<>();
        adapter_doing = new TaskListAdapter(MyTaskActivity.this, listItems_doing);
        adapter_all = new TaskListAdapter(MyTaskActivity.this, listItems_all);
        adapter_finish = new TaskListAdapter(MyTaskActivity.this, listItems_finish);
        lv_doing.setAdapter(adapter_doing);
        lv_all.setAdapter(adapter_all);
        lv_finish.setAdapter(adapter_finish);
        lv_doing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaskBean tb = listItems_doing.get(i);
                if (tb.getTask_type() == 1) {
                    Intent intent = new Intent(MyTaskActivity.this, ExamActivity.class);
                    startActivity(intent);
                } else if (tb.getTask_type() == 2) {
                    Intent intent = new Intent(MyTaskActivity.this, DoPracticalActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        TaskBean tb0 = new TaskBean(1, "完成第1节的课后习题", 1, 3298, "2018-04-09", "2018-04-18", 2, "王白老师", "2018-04-09");
        TaskBean tb1 = new TaskBean(1, "完成第2节的课后习题", 1, 3298, "2018-04-09", "2018-04-18", 4, "王白老师", "2018-04-09");
        TaskBean tb2 = new TaskBean(1, "完成关于HTML5编程的实践", 2, 4298, "2018-04-11", "2018-04-15", 2, "文民老师", "2018-04-09");
        listItems_doing.add(tb0);
        listItems_doing.add(tb2);
        listItems_all.add(tb0);
        listItems_all.add(tb1);
        listItems_all.add(tb2);
        listItems_finish.add(tb1);
        adapter_doing.notifyDataSetChanged();
        adapter_all.notifyDataSetChanged();
        adapter_finish.notifyDataSetChanged();
    }

}
