package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.application.YZEduApplication;
import com.fstech.yzedutc.bean.UserInfoBean;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;

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
                    String url = Constant.BASE_DB_URL + "user/login";
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("account", mphone);
                    map.put("password", mpwd);
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
                                if (result_code == 0) {
                                    // 正确情况
                                    JSONObject jsonObject = jobj.getJSONObject("return_data");
                                    JSONObject userInfo = jsonObject.getJSONObject("userInfo");
                                    String token = jsonObject.getString("token");
                                    try {
                                        UserInfoBean userInfoBean = objectMapper.readValue(userInfo.toString(), UserInfoBean.class);
                                        loginScucess(userInfoBean, token);
                                    } catch (IOException e) {
                                        Log.e("io", e.getMessage());
                                        e.printStackTrace();
                                    }
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
    private void loginScucess(UserInfoBean userInfo, String token) {
        YZEduApplication application = (YZEduApplication) getApplication();
        // TODO
        int userType = userInfo.getUser_type();
        application.setAvatar(userInfo.getUser_avatar());
        application.setUser_type(userType);
        application.setToken(token);
        if (userType == 1) {
            // 学生用户
            application.setUserName(userInfo.getStudent_name());
        } else if (userType == 3) {
            // 自由人用户
            application.setUserName("用户" + userInfo.getUser_account());
        }
    }

}
