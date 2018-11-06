package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fstech.yzedutc.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shaoxin on 18-3-25.
 * 开屏页
 */

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        autoLogin();
        timer();
    }

    /*
    * 计时器，1.5秒后转到主界面
    * */
    private void timer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }

    /*
    * 后台自动登录
    * */
    private void autoLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO 后台登录相关的操作
            }
        }).start();
    }

}
