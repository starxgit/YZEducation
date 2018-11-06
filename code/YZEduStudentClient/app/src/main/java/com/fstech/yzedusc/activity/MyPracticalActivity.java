package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.adapter.CourseListAdapter;
import com.fstech.yzedusc.bean.CourseBean;
import com.fstech.yzedusc.util.CacheActivityUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaoxin on 18-4-2.
 * 综合实训主界面
 */

public class MyPracticalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    // 定义UI对象
    private SmartRefreshLayout smart_refresh;
    private ListView lv_my_course;
    private CourseListAdapter adapter_course;
    private List<CourseBean> listItems_course;
    private Spinner sp_course_state;
    private ArrayAdapter<String> adapter_state;
    private String[] array_course_state;
    private TextView tv_title;
    private CourseBean cb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_practical);
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
        CacheActivityUtil.addActivity(MyPracticalActivity.this);
        smart_refresh = (SmartRefreshLayout) findViewById(R.id.smart_refresh);
        lv_my_course = (ListView) findViewById(R.id.lv_practial);
        sp_course_state = (Spinner) findViewById(R.id.sp_course_state);
        tv_title = (TextView) findViewById(R.id.tv_title);
        listItems_course = new ArrayList<CourseBean>();
        adapter_course = new CourseListAdapter(MyPracticalActivity.this, listItems_course);
        lv_my_course.setAdapter(adapter_course);
        lv_my_course.setOnItemClickListener(this);

        array_course_state = new String[]{"全部", "进行中", "已结束"};
        adapter_state = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array_course_state);
        sp_course_state.setAdapter(adapter_state);
        sp_course_state.setOnItemSelectedListener(this);

        smart_refresh.setRefreshHeader(new ClassicsHeader(this));
        smart_refresh.setRefreshFooter(new ClassicsFooter(this));
    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        cb = new CourseBean(3301, "12321345", "Html5课程实践", null, null, 40, 28, "html5shijian.png", 6, 3, 0, 4);
        listItems_course.add(cb);
    }

    /*
    * 判断选择的课程状态
    * */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                tv_title.setText("我选择的实训");
                // TODO 设置成全部我学习的课程列表
                listItems_course.clear();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listItems_course.add(cb);
                        adapter_course.notifyDataSetChanged();
                    }
                }, 500);
                break;
            case 1:
                tv_title.setText("进行中的实训");
                // TODO 设置成全部进行中的课程列表
                listItems_course.clear();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listItems_course.add(cb);
                        adapter_course.notifyDataSetChanged();
                    }
                }, 500);
                break;
            case 2:
                tv_title.setText("已结束的实训");
                // TODO 设置成全部已结束的课程列表
                listItems_course.clear();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter_course.notifyDataSetChanged();
                    }
                }, 500);
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*
    * 列表点击事件的方法
    * */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e("item", i + "");
        CourseBean cb = listItems_course.get(i);
        Intent intent = new Intent(MyPracticalActivity.this, PracticalLearnActivity.class);
        intent.putExtra("cb", cb);
        startActivity(intent);
    }
}
