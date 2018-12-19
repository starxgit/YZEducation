package com.fstech.yzedutc.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.application.YZEduApplication;
import com.fstech.yzedutc.bean.UserBean;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.ImageUitl;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.util.TokenUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-4-10.
 * 用户信息主界面
 */

public class UserInfoActivity extends AppCompatActivity {
    private QMUIRadiusImageView ivAvatar;
    private TextView tvFinish;
    private TextView tvName;
    private TextView tvAccount;
    private RadioGroup rgSex;
    private RadioButton rbBoy;
    private RadioButton rbGirl;
    private EditText etAge;
    private EditText etPhone;
    private String token;
    private UserBean userBean;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        initData();
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        ivAvatar = (QMUIRadiusImageView) findViewById(R.id.iv_avatar);
        tvFinish = (TextView) findViewById(R.id.tv_finish);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvAccount = (TextView) findViewById(R.id.tv_account);
        rgSex = (RadioGroup) findViewById(R.id.rg_sex);
        etAge = (EditText) findViewById(R.id.et_age);
        etPhone = (EditText) findViewById(R.id.et_phone);
        rbBoy = (RadioButton) findViewById(R.id.rb_boy);
        rbGirl = (RadioButton) findViewById(R.id.rb_girl);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });
    }

    private void initData() {
        userBean = null;
        token = TokenUtil.getToken(UserInfoActivity.this);
        getUserInfo();
    }

    /**
     * 获取用户基本信息的方法
     */
    private void getUserInfo() {
        progressBar.setVisibility(View.VISIBLE);
        String url = Constant.BASE_DB_URL + "user/info";
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(UserInfoActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        JSONObject user = jsonObject.getJSONObject("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        userBean = objectMapper.readValue(user.toString(), UserBean.class);
                        setInfo();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(UserInfoActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    
    /**
     * 设置基本信息的方法
     */
    private void setInfo() {
        YZEduApplication application = (YZEduApplication) getApplication();
        tvName.setText(application.getUserName());
        ImageUitl.showNetImage(ivAvatar, application.getAvatar());
        tvAccount.setText(userBean.getUser_account());
        etAge.setText(userBean.getUser_age() + "");
        etPhone.setText(userBean.getUser_phone());
        String sex = userBean.getUser_sex();
        if (sex.equals("男")) {
            rgSex.check(rbBoy.getId());
        } else {
            rgSex.check(rbGirl.getId());
        }
    }

    /**
     * 保存基本信息的方法
     */
    private void saveUserInfo() {
        tvFinish.setClickable(false);
        progressBar.setVisibility(View.VISIBLE);
        String avatar = userBean.getUser_avatar();
        String phone = etPhone.getText().toString();
        String age = etAge.getText().toString();
        String sex = "";
        if (rbBoy.isChecked()) {
            sex = "男";
        } else {
            sex = "女";
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("phone", phone);
        map.put("age", age);
        map.put("sex", sex);
        map.put("avatar", avatar);
        Log.e("map", map.toString());
        String url = Constant.BASE_DB_URL + "user/modify";
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(UserInfoActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        Toast.makeText(UserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(UserInfoActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    progressBar.setVisibility(View.GONE);
                    tvFinish.setClickable(true);
                }
            }
        });
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

}
