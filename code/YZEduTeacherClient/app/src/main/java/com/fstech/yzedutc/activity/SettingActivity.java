package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.util.DataCleanManager;

/**
 * Created by shaoxin on 18-4-10.
 * 设置主界面
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout re_about_us;
    private RelativeLayout re_use_help;
    private RelativeLayout re_advice;
    private RelativeLayout re_clear_cache;
    private RelativeLayout re_update;
    private ProgressBar pb_update;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        re_about_us = (RelativeLayout) findViewById(R.id.re_about_us);
        re_use_help = (RelativeLayout) findViewById(R.id.re_use_help);
        re_advice = (RelativeLayout) findViewById(R.id.re_advice);
        re_clear_cache = (RelativeLayout) findViewById(R.id.re_clear_cache);
        re_update = (RelativeLayout) findViewById(R.id.re_update);
        pb_update = (ProgressBar) findViewById(R.id.pb_update);
        pb_update.setVisibility(View.GONE);

        re_about_us.setOnClickListener(this);
        re_use_help.setOnClickListener(this);
        re_advice.setOnClickListener(this);
        re_clear_cache.setOnClickListener(this);
        re_update.setOnClickListener(this);
    }

    private void initData() {

    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_about_us:
                Intent intent0 = new Intent(SettingActivity.this, WebAboutActivity.class);
                intent0.putExtra("title", "关于我们");
                intent0.putExtra("url", "http://mp.weixin.qq.com/s/Ns-yIWatEEm8Mk9kxlsNKw");
                startActivity(intent0);
                break;
            case R.id.re_use_help:
                Intent intent1 = new Intent(SettingActivity.this, WebAboutActivity.class);
                intent1.putExtra("title", "使用帮助");
                intent1.putExtra("url", "http://mp.weixin.qq.com/s/G42hL_BRJ7Cz8-Wu_HMM0g");
                startActivity(intent1);
                break;
            case R.id.re_advice:
                Intent intent2 = new Intent(SettingActivity.this, WebAboutActivity.class);
                intent2.putExtra("title", "问题反馈");
                intent2.putExtra("url", "http://www.fstechnology.cn/yzedu/feedback.html");
                startActivity(intent2);
                break;
            case R.id.re_clear_cache:
                String re = "0";
                try {
                    re = DataCleanManager.getCacheSize(SettingActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(SettingActivity.this, "您已清除缓存" + re, Toast.LENGTH_SHORT).show();
                DataCleanManager.cleanInternalCache(SettingActivity.this);
                break;
            case R.id.re_update:
                pb_update.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pb_update.setVisibility(View.GONE);
                        Toast.makeText(SettingActivity.this, "当前版本已经是最新版本！", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
                break;
            default:
                break;
        }
    }
}
