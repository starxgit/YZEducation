package com.fstech.yzedutc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.activity.GradeQueryActivity;
import com.fstech.yzedutc.activity.MyCourseActivity;
import com.fstech.yzedutc.activity.MyPracticalActivity;
import com.fstech.yzedutc.activity.MyTaskActivity;

/**
 * Created by shaoxin on 18-3-25.
 * 学习界面的Fragment
 */

public class TeachFragment extends Fragment implements View.OnClickListener {
    // 定义UI对象
    private RelativeLayout re_mycourse;
    private RelativeLayout re_practical;
    private RelativeLayout re_task;
    private RelativeLayout re_grade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teach, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        re_mycourse = (RelativeLayout) getActivity().findViewById(R.id.main_re_mycourse);
        re_practical = (RelativeLayout) getActivity().findViewById(R.id.main_re_practical);
        re_task = (RelativeLayout) getActivity().findViewById(R.id.main_re_task);
        re_grade = (RelativeLayout) getActivity().findViewById(R.id.main_re_grade);

        re_mycourse.setOnClickListener(this);
        re_practical.setOnClickListener(this);
        re_task.setOnClickListener(this);
        re_grade.setOnClickListener(this);
    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_re_mycourse:
                // 转到我的课程
                Intent intent0 = new Intent(getActivity(), MyCourseActivity.class);
                startActivity(intent0);
                break;
            case R.id.main_re_practical:
                // 转到我的实训
                Intent intent1 = new Intent(getActivity(), MyPracticalActivity.class);
                startActivity(intent1);
                break;
            case R.id.main_re_task:
                // 转到我的任务
                Intent intent2 = new Intent(getActivity(), MyTaskActivity.class);
                startActivity(intent2);
                break;
            case R.id.main_re_grade:
                // 转到成绩查询
                Intent intent3 = new Intent(getActivity(), GradeQueryActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

}
