package com.fstech.yzedusc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fstech.yzedusc.R;

/**
 * Created by shaoxin on 18-4-10.
 * 用户信息主界面
 */

public class UserInfoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView(){

    }

    private void initData(){

    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

}
