package com.fstech.yzedutc.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Trace;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.CourseBean;
import com.fstech.yzedutc.util.CacheActivityUtil;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.util.UploadFileUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 12/19/18.
 */

public class AddLessonActivity extends AppCompatActivity {
    // 定义UI对象
    private TextView tv_file_name;
    private Button bn_upload;
    private EditText et_knowledge;
    private EditText et_title;
    private Button bn_submit;
    private String path = null;//文件路径
    private ProgressBar progressBar;
    private ProgressBar jindu;
    private TextView post_text;
    private CourseBean cb;
    private Handler handler;
    private String videoUrl;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        initView();
        initData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // 数据加载完成
                    case 1:
                        releaseLock();
                        doUpload();
                        break;
                    // 错误
                    case 2:
                        releaseLock();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initView() {
        videoUrl = null;
        tv_file_name = (TextView) findViewById(R.id.tv_file_name);
        et_knowledge = (EditText) findViewById(R.id.et_knowledge);
        et_title = (EditText) findViewById(R.id.et_title);
        bn_upload = (Button) findViewById(R.id.bn_upload);
        bn_submit = (Button) findViewById(R.id.bn_submit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        jindu = (ProgressBar) findViewById(R.id.jindu);
        bn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });

        bn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        cb = (CourseBean) intent.getSerializableExtra("cb");
        Log.e("cb",cb.toString());
    }

    /**
     * 选择文件的方法
     */
    public void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//无类型限制
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        Uri uri = null;
        switch (requestCode) {
            case 1:// 相册
                if (data == null) {
                    return;
                }
                uri = data.getData();
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(uri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                path = cursor.getString(column_index);// 图片在的路径
                tv_file_name.setText(path);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

    /**
     * 上传文件的方法
     */
    private void uploadFile() {
        if (et_title.getText().toString().equals("") || et_knowledge.getText().toString().equals("")) {
            Toast.makeText(AddLessonActivity.this, "课程标题和知识点不能为空内容", Toast.LENGTH_SHORT).show();
        } else {
            if (path != null) {
                setLock();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            videoUrl = UploadFileUtil.uploadImage(Constant.UPLOAD_URL, path);
                            handler.sendMessage(handler.obtainMessage(1));
                        } catch (IOException e) {
                            e.printStackTrace();
                            handler.sendMessage(handler.obtainMessage(2));
                        }
                    }
                }).start();
            } else {
                Toast.makeText(AddLessonActivity.this, "请先选择文件", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 上锁
     */
    private void setLock() {
        progressBar.setVisibility(View.VISIBLE);
        bn_submit.setClickable(false);
        bn_upload.setClickable(false);
    }

    /**
     * 解锁
     */
    private void releaseLock() {
        progressBar.setVisibility(View.GONE);
        bn_submit.setClickable(true);
        bn_upload.setClickable(true);
    }

    /**
     * 上传课程的方法
     */
    private void doUpload() {
        setLock();
        String title = et_title.getText().toString();
        String knowledge = et_knowledge.getText().toString();
        String url = Constant.BASE_DB_URL + "teach/addLesson";
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("knowledge", knowledge);
        map.put("videoUrl", videoUrl);
        map.put("course_id", cb.getCourse_id() + "");
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(AddLessonActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
                releaseLock();
            }
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        Toast.makeText(AddLessonActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        CacheActivityUtil.finishSingleActivityByClass(CourseLearnActivity.class);
                        Intent intent = new Intent(AddLessonActivity.this,CourseLearnActivity.class);
                        intent.putExtra("cb",cb);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(AddLessonActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    releaseLock();
                }
            }
        });
    }
}
