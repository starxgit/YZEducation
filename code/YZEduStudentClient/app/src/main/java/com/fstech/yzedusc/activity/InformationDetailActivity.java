package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.InformationBean;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.DownloadTools;
import com.fstech.yzedusc.util.ImageUitl;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.util.ThreadUtil;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-5-23.
 * 资讯详情的主界面
 */

public class InformationDetailActivity extends AppCompatActivity {
    // 定义UI对象
    private TextView tv_information_title;
    private TextView tv_information_date;
    private TextView tv_information_content;
    private ImageView iv_information_image;
    private InformationBean informationBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        initView();
        initData();
        setData();
    }

    /*
    * 初始化视图
    * */
    private void initView() {
        tv_information_title = (TextView) findViewById(R.id.tv_information_title);
        tv_information_content = (TextView) findViewById(R.id.tv_information_content);
        tv_information_date = (TextView) findViewById(R.id.tv_information_date);
        iv_information_image = (ImageView) findViewById(R.id.iv_information_image);

    }

    /*
    * 初始化数据
    * */
    private void initData() {
        informationBean = new InformationBean();
        Intent intent = getIntent();
        informationBean = (InformationBean) intent.getSerializableExtra("ib");
    }

    /*
    * 设置数据显示
    * */
    private void setData() {
        tv_information_title.setText(informationBean.getInformation_title());
        tv_information_date.setText(informationBean.getInformation_date().substring(0, 19));
        tv_information_content.setText("");
        if (informationBean.getInformation_cover() != null) {
            OkhttpUtil.okHttpGetBitmap(informationBean.getInformation_cover(), new CallBackUtil.CallBackBitmap() {
                @Override
                public void onFailure(Call call, Exception e) {

                }

                @Override
                public void onResponse(Bitmap response) {
                    iv_information_image.setImageBitmap(response);
                }
            });
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
