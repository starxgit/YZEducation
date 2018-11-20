package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.LessonBean;

/**
 * Created by shaoxin on 18-4-16.
 * 完成实训页面
 */

public class DoPracticalActivity extends AppCompatActivity {
    private Button bn_chose;
    private TextView tv_file_path;
    private LessonBean lessonBean;
    private TextView tv_title;
    private TextView tv_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_practical);
        initView();
        initData();
    }

    private void initView() {
        bn_chose = (Button) findViewById(R.id.bn_chose);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView)findViewById(R.id.tv_content);
        tv_file_path = (TextView) findViewById(R.id.tv_file_path);
        bn_chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//无类型限制
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        lessonBean = (LessonBean) intent.getSerializableExtra("lb");
        tv_title.setText(lessonBean.getLesson_title());
        tv_content.setText(lessonBean.getLesson_video_url());
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
                final String path = cursor.getString(column_index);// 图片在的路径
                tv_file_path.setText(path);
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

    public void submit(View view) {
        // TODO 提交文件
        Toast.makeText(DoPracticalActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
        finish();
    }
}
