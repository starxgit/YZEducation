package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.bean.StudentBean;
import com.fstech.yzedusc.bean.UserBean;
import com.fstech.yzedusc.util.CacheActivityUtil;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.view.ClearEditText;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundLinearLayout;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-5-23.
 * 登录主界面
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    // 定义UI对象
    private ClearEditText etAccount;
    private ClearEditText etPassword;
    private TextView tvForget;
    private TextView tvRegister;
    private QMUIRoundLinearLayout qrlLogin;
    private TextView tvSchoolLogin;
    private ProgressBar progressBar;
    private Handler handler;
    SharedPreferences perPreferences;
    SharedPreferences.Editor editor;
    private StudentBean studentBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // 数据加载完成
                    case 1:
                        if (studentBean != null) {
                            loginScucess();
                        } else {
                            Toast.makeText(LoginActivity.this, R.string.system_error, Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                        qrlLogin.setClickable(true);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initView() {
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        CacheActivityUtil.addActivity(LoginActivity.this);
        etAccount = (ClearEditText) findViewById(R.id.et_account);
        etPassword = (ClearEditText) findViewById(R.id.et_password);
        tvForget = (TextView) findViewById(R.id.tv_forget);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        qrlLogin = (QMUIRoundLinearLayout) findViewById(R.id.qrl_login);
        tvSchoolLogin = (TextView) findViewById(R.id.tv_school_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        tvForget.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        qrlLogin.setOnClickListener(this);
        tvSchoolLogin.setOnClickListener(this);
    }

    private void initData() {
        perPreferences = getSharedPreferences("loginmsg", MODE_PRIVATE);
        editor = perPreferences.edit();
        if (perPreferences != null) {
            String mphone = perPreferences.getString("mphone", "");
            String mpwd = perPreferences.getString("pass", "");
            etAccount.setText(mphone);
            etPassword.setText(mpwd);
        }
    }

    /*
    * 登录成功的方法
    * */
    private void loginScucess() {
        Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
        editor.putString("mphone", etAccount.getText().toString());
        editor.putString("pass", etPassword.getText().toString());
        editor.commit();
        YZEduApplication application = (YZEduApplication) getApplication();
        application.setSchool_id(studentBean.getSchool_id());
        application.setSchool_name(studentBean.getSchool_name());
        application.setStudent_id(studentBean.getStudent_id());
        application.setStudent_name(studentBean.getStudent_name());
        application.setStudent_number(studentBean.getStudent_number());
        application.setUser_age(studentBean.getUser_age());
        application.setUser_avatar(studentBean.getUser_avatar());
        application.setUser_sex(studentBean.getUser_sex());
        application.setUser_id(studentBean.getUser_id());
        application.setUser_phone(studentBean.getUser_phone());
        CacheActivityUtil.finishSingleActivityByClass(MainActivity.class);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // 监听点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_forget:
                // TODO 忘记密码的业务
                Toast.makeText(LoginActivity.this, R.string.forget_password, Toast.LENGTH_SHORT).show();
                break;
            case R.id.qrl_login:
                // 登录
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                if (checkFormit(account, password) == true) {
                    // 登录验证
                    doLogin(account, password);
                }
                break;
            case R.id.tv_register:
                // 进入注册页
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_school_login:
                // 进入院校登录页
                Intent intent3 = new Intent(LoginActivity.this, SchoolSelectActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    /*
    * 登录的方法
    * */
    private void doLogin(String account, String password) {
        progressBar.setVisibility(View.VISIBLE);
        qrlLogin.setClickable(false);
        // 登录
        String url = Constant.BASE_DB_URL1 + "general/AccountLogin";
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_account", account);
        map.put("password", password);
        map.put("user_type", Constant.TYPE_STUDENT);
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(LoginActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
                handler.sendMessage(handler.obtainMessage(1));
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
                        } catch (IOException e) {
                            Log.e("io", e.getMessage());
                            e.printStackTrace();
                        }
                        handler.sendMessage(handler.obtainMessage(1));
                    } else {
                        // 错误情况
                        Toast.makeText(LoginActivity.this, jobj.getString("message"), Toast.LENGTH_SHORT).show();
                        handler.sendMessage(handler.obtainMessage(1));
                    }
                } catch (JSONException e) {
                    Log.e("json", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    /*
    * 验证输入格式合法的方法
    * */
    private boolean checkFormit(String account, String password) {
        if (account.equals("") || password.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.account_password_not_null, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            // TODO 其他输入格式判断
            return true;
        }
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }
}

