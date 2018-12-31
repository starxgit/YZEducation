package com.fstech.yzedutc.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.CourseMaterial;
import com.fstech.yzedutc.util.CacheActivityUtil;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.CmlRequestBody;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by shaoxin on 12/19/18.
 */

public class UploadMaterialActivity extends AppCompatActivity {
    // 定义UI对象
    private TextView tv_file_name;
    private Button bn_upload;
    private Button bn_submit;
    private String path = null;//文件路径
    private ProgressBar progressBar;
    private ProgressBar jindu;
    private Handler handler;
    private String videoUrl;
    private String course_id;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
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
        bn_upload = (Button) findViewById(R.id.bn_upload);
        bn_submit = (Button) findViewById(R.id.bn_submit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        jindu = (ProgressBar) findViewById(R.id.jindu);
        jindu.setProgress(1);
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
        course_id = intent.getStringExtra("course_id");
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
        if (path != null) {
            setLock();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 上传文件
                    File file = new File(path);
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("thumb", file.getName(),
                            RequestBody.create(MediaType.parse("file/*"), file));
                    Request.Builder request = new Request.Builder().url(Constant.OTHER_UPLOAD_URL)
                            .post(new CmlRequestBody(builder.build()) {
                                @Override
                                public void loading(long current, long total, boolean done) {
                                    if (!done) {
                                        double percent = (double) current / (double) total;
                                        Log.e("percent", percent + "");
                                        jindu.setProgress((int) (percent * 100));
                                    }
                                }
                            });

                    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                            .connectTimeout(1000, TimeUnit.SECONDS)
                            .build();
                    okHttpClient.newCall(request.build()).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("fail", "fail");
                            handler.sendMessage(handler.obtainMessage(2));
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            videoUrl = response.body().string();
                            Log.e("res", videoUrl);
                            handler.sendMessage(handler.obtainMessage(1));
                        }
                    });
                }
            }).start();
        } else {
            Toast.makeText(UploadMaterialActivity.this, "请先选择文件", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 上锁
     */
    private void setLock() {
        bn_submit.setClickable(false);
        bn_upload.setClickable(false);
    }

    /**
     * 解锁
     */
    private void releaseLock() {
        bn_submit.setClickable(true);
        bn_upload.setClickable(true);
    }

    /**
     * 上传课程的方法
     */
    private void doUpload() {
        setLock();
        String name = path.substring(path.lastIndexOf("/")+1);
        progressBar.setVisibility(View.VISIBLE);
        String url = Constant.BASE_DB_URL + "material/add";
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("url", videoUrl);
        map.put("course_id", course_id);
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(UploadMaterialActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
                releaseLock();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        Toast.makeText(UploadMaterialActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        CacheActivityUtil.finishSingleActivityByClass(MaterialActivity.class);
                        Intent intent = new Intent(UploadMaterialActivity.this, MaterialActivity.class);
                        intent.putExtra("course_id", course_id);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(UploadMaterialActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    releaseLock();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

}
