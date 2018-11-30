package com.fstech.yzedusc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.adapter.LearnLikeGridAdapter;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.util.TokenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 18-4-10.
 * 用户学习偏好的主界面
 */

public class LearnLikeActivity extends AppCompatActivity {
    private GridView gv_like;
    private List<Map<String, Object>> listItems;
    private LearnLikeGridAdapter adapter;
    private String token;
    private TextView tv_finish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_like);
        initView();
        initData();
        setListContent();
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        gv_like = (GridView) findViewById(R.id.gv_like);
        listItems = new ArrayList<Map<String, Object>>();
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChange();
            }
        });
        gv_like.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String chose = listItems.get(position).get("chose").toString();
                String name = listItems.get(position).get("name").toString();
                String cfa_id = listItems.get(position).get("cfa_id").toString();
                if (chose.equals("1")) {
                    chose = "0";
                    listItems.get(position).clear();
                    listItems.get(position).put("name", name);
                    listItems.get(position).put("chose", chose);
                    listItems.get(position).put("cfa_id", cfa_id);
                    adapter.notifyDataSetChanged();
                } else {
                    chose = "1";
                    listItems.get(position).clear();
                    listItems.get(position).put("name", name);
                    listItems.get(position).put("chose", chose);
                    listItems.get(position).put("cfa_id", cfa_id);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initData() {
        token = TokenUtil.getToken(LearnLikeActivity.this);
    }

    private void setListContent() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        String url = Constant.BASE_DB_URL + "userlike/likelist";
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(LearnLikeActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Log.e("res",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if(result_code==0){
                        JSONArray ja = jsonObject.getJSONArray("return_data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jobj = (JSONObject) ja.get(i);
                            Map<String, Object> listItem = new HashMap<String, Object>();
                            listItem.put("chose", jobj.getString("chose"));
                            listItem.put("name", jobj.getString("name"));
                            listItem.put("cfa_id", jobj.getString("cfa_id"));
                            listItems.add(listItem);
                        }
                        adapter = new LearnLikeGridAdapter(LearnLikeActivity.this, listItems);
                        gv_like.setAdapter(adapter);
                    }else{
                        String message = jsonObject.getString("message");
                        Toast.makeText(LearnLikeActivity.this,message,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void saveChange() {
        String userlike = "";
        for (int i = 0; i < listItems.size(); i++) {
            Map<String, Object> listItem = listItems.get(i);
            if (listItem.get("chose").toString().equals("1")) {
                String mid = listItem.get("cfa_id") + ",";
                userlike = userlike + mid;
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("userlike", userlike);
        String url = Constant.BASE_DB_URL + "userlike/savelike";
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(LearnLikeActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(LearnLikeActivity.this, R.string.save_success, Toast.LENGTH_SHORT).show();
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
