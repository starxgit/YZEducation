package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.bean.StudentBean;
import com.fstech.yzedusc.util.CacheActivityUtil;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-3-25.
 * 开屏页
 */

public class WelcomeActivity extends AppCompatActivity {
    SharedPreferences perPreferences;
    SharedPreferences.Editor editor;
    private StudentBean studentBean;

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
                // 后台登录相关的操作
                perPreferences = getSharedPreferences("loginmsg", MODE_PRIVATE);
                editor = perPreferences.edit();
                if (perPreferences != null) {
                    String mphone = perPreferences.getString("mphone", "");
                    String mpwd = perPreferences.getString("pass", "");
                    String url = Constant.BASE_DB_URL1 + "general/AccountLogin";
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("user_account", mphone);
                    map.put("password", mpwd);
                    map.put("user_type", Constant.TYPE_STUDENT);
                    OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
                        @Override
                        public void onFailure(Call call, Exception e) {
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                ObjectMapper objectMapper = new ObjectMapper();
                                JSONObject jobj = new JSONObject(response);
                                int result_code = jobj.getInt("result_code");
                                if (result_code == 81) {
                                    // 正确情况
                                    JSONObject jsonObject = jobj.getJSONObject("return_data");
                                    try {
                                        studentBean = objectMapper.readValue(jsonObject.toString(), StudentBean.class);
                                        loginScucess();
                                    } catch (IOException e) {
                                        Log.e("io", e.getMessage());
                                        e.printStackTrace();
                                    }
                                } else {
                                    // 错误情况
                                }
                            } catch (JSONException e) {
                                Log.e("json", e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    });

                }

            }
        }).start();
    }

    /*
 * 登录成功的方法
 * */
    private void loginScucess() {
        YZEduApplication application = (YZEduApplication) getApplication();
//        application.setSchool_id(studentBean.getSchool_id());
//        application.setSchool_name(studentBean.getSchool_name());
//        application.setStudent_id(studentBean.getStudent_id());
//        application.setStudent_name(studentBean.getStudent_name());
//        application.setStudent_number(studentBean.getStudent_number());
//        application.setUser_age(studentBean.getUser_age());
//        application.setUser_avatar(studentBean.getUser_avatar());
//        application.setUser_sex(studentBean.getUser_sex());
//        application.setUser_id(studentBean.getUser_id());
//        application.setUser_phone(studentBean.getUser_phone());
    }

}
