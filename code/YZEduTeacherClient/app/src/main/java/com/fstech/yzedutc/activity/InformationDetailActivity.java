package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.InformationDetailAdapter;
import com.fstech.yzedutc.bean.InformationBean;
import com.fstech.yzedutc.bean.InformationContentBean;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.ImageUitl;
import com.fstech.yzedutc.util.OkhttpUtil;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-5-23.
 * 资讯详情的主界面
 */

public class InformationDetailActivity extends AppCompatActivity {
    private final String urlList[] = {"platform/informationDetail", "school/informationDetail"};
    // 定义UI对象
    private TextView tv_information_title;
    private TextView tv_information_date;
    private ImageView iv_information_image;
    private InformationBean informationBean;
    private ListView lv_content;
    private List<InformationContentBean> listContent;
    private InformationDetailAdapter informartonDetailAdapter;
    private int informationId;
    private int type;

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
        tv_information_date = (TextView) findViewById(R.id.tv_information_date);
        iv_information_image = (ImageView) findViewById(R.id.iv_information_image);
        lv_content = (ListView) findViewById(R.id.lv_content);
        listContent = new ArrayList<>();
        informartonDetailAdapter = new InformationDetailAdapter(InformationDetailActivity.this, listContent);
        lv_content.setAdapter(informartonDetailAdapter);
    }

    /*
    * 初始化数据
    * */
    private void initData() {
        informationId = 0;
        informationBean = new InformationBean();
        Intent intent = getIntent();
        informationBean = (InformationBean) intent.getSerializableExtra("ib");
        type = intent.getIntExtra("type", 0);
        if (informationBean != null) {
            informationId = informationBean.getInformation_id();
        }
    }

    /*
    * 设置数据显示
    * */
    private void setData() {
        tv_information_title.setText(informationBean.getInformation_title());
        tv_information_date.setText(informationBean.getInformation_date().substring(0, 19));
        if (informationBean.getInformation_cover() != null) {
            ImageUitl.showNetImage(iv_information_image, informationBean.getInformation_cover());
        }
        setListContent();
    }

    /*
    * 设置内容数据
    * */
    private void setListContent() {
        Map<String, String> map = new HashMap<>();
        map.put("infomation_id", informationId + "");
        final String url = Constant.BASE_DB_URL + urlList[type];
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(InformationDetailActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        // 返回正确的情况
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            InformationContentBean icb = objectMapper.readValue(jobj.toString(), InformationContentBean.class);
                            listContent.add(icb);
                        }
                        informartonDetailAdapter.notifyDataSetChanged();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(InformationDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JsonError", e.getMessage());
                } catch (JsonParseException e) {
                    e.printStackTrace();
                    Log.e("JsonPraseError", e.getMessage());
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                    Log.e("JsonMappingError", e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("IOError", e.getMessage());
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
