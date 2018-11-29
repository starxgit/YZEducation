package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.util.CacheActivityUtil;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.util.TokenUtil;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-11-29.
 * 发布新话题
 */

public class NewDisscussActivity extends AppCompatActivity {
    private EditText etContent;
    private QMUIRoundButton bnSubmit;
    private int lesson_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_disscuss);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        Intent intent = getIntent();
        lesson_id = intent.getIntExtra("lesson_id", -1);
        etContent = (EditText) findViewById(R.id.et_content);
        bnSubmit = (QMUIRoundButton) findViewById(R.id.bn_submit);
        bnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnSubmit.setClickable(false);
                subDiscuss();
            }
        });
    }

    /**
     * 提交发言的方法
     */
    private void subDiscuss() {
        String content = etContent.getText().toString();
        String token = TokenUtil.getToken(NewDisscussActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("communication_content", content);
        map.put("token", token);
        map.put("lesson_id", lesson_id + "");
        String url = Constant.BASE_DB_URL + "communication/addCommunication";
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(NewDisscussActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        bnSubmit.setClickable(true);
                        CacheActivityUtil.finishSingleActivityByClass(CourseDisscussActivity.class);
                        Intent intent = new Intent(NewDisscussActivity.this,CourseDisscussActivity.class);
                        intent.putExtra("lesson_id",lesson_id);
                        startActivity(intent);
                        finish();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(NewDisscussActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
