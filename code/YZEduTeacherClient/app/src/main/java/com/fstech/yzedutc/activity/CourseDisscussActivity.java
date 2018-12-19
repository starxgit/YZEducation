package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.CommunicationAdapter;
import com.fstech.yzedutc.bean.CommunicationBean;
import com.fstech.yzedutc.util.CacheActivityUtil;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.util.TokenUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
 * Created by shaoxin on 18-11-28.
 * 课程交流主页
 */

public class CourseDisscussActivity extends AppCompatActivity {
    // 定义UI对象
    private SmartRefreshLayout smartRefreshLayout;
    private ListView lv_communication;
    private CommunicationAdapter adapter;
    private TextView tv_new;
    private List<CommunicationBean> listItems;
    private int lesson_id;
    private String token;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_disscuss);
        CacheActivityUtil.addActivity(CourseDisscussActivity.this);
        initView();
        initData();
    }

    private void initView() {
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smart_refresh);
        lv_communication = (ListView) findViewById(R.id.lv_communication);
        tv_new = (TextView) findViewById(R.id.tv_new);
        listItems = new ArrayList<>();
        adapter = new CommunicationAdapter(CourseDisscussActivity.this, listItems);
        lv_communication.setAdapter(adapter);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(CourseDisscussActivity.this));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(CourseDisscussActivity.this));
        tv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDisscussActivity.this, NewDisscussActivity.class);
                intent.putExtra("lesson_id",lesson_id);
                startActivity(intent);
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getCommunicationList();
            }
        });
        lv_communication.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CourseDisscussActivity.this,DisscussDetail.class);
                intent.putExtra("lesson_id",lesson_id);
                intent.putExtra("communication",listItems.get(position));
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        lesson_id = intent.getIntExtra("lesson_id", -1);
        token = TokenUtil.getToken(CourseDisscussActivity.this);
        getCommunicationList();
    }

    /**
     * 获取课程交流列表
     */
    private void getCommunicationList() {
        progressBar.setVisibility(View.VISIBLE);
        String url = Constant.BASE_DB_URL + "communication/communicationList";
        Map<String, String> map = new HashMap<String, String>();
        map.put("lesson_id", lesson_id + "");
        map.put("token", token);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("error", "okHttpRequestError");
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        listItems.clear();
                        // 返回正确的情况
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            CommunicationBean courseBean = objectMapper.readValue(jobj.toString(), CommunicationBean.class);
                            listItems.add(courseBean);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(CourseDisscussActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("Json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("Json", "JSON包装成对象失败");
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    Log.e("error", "NewCourseMapping异常");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("error", "IO异常" + e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    progressBar.setVisibility(View.GONE);
                    smartRefreshLayout.finishRefresh();
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
