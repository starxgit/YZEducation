package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.adapter.ExamAdapter;
import com.fstech.yzedusc.adapter.MistakeListAdapter;
import com.fstech.yzedusc.bean.MistakeBean;
import com.fstech.yzedusc.bean.MyExamBean;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.util.TokenUtil;
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
 * Created by shaoxin on 18-5-5.
 * 错题的主界面
 */

public class MistakeActivity extends AppCompatActivity {

    private ListView lv_mistakes;
    private SmartRefreshLayout smartRefreshLayout;
    private List<MistakeBean> listItems;
    private MistakeListAdapter adapter;
    private ProgressBar progressBar;
    private String course_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misstake);
        initView();
        initData();
    }

    /*
    * 初始化视图
    * */
    private void initView() {
        lv_mistakes = (ListView) findViewById(R.id.lv_mistakes);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listItems = new ArrayList<>();
        adapter = new MistakeListAdapter(MistakeActivity.this, listItems);
        lv_mistakes.setAdapter(adapter);
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smart_refresh);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(MistakeActivity.this));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(MistakeActivity.this));
        lv_mistakes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String my_exam_id = listItems.get(i).getMy_exam_id() + "";
                Intent intent = new Intent(MistakeActivity.this, MistakeDetailActivity.class);
                intent.putExtra("my_exam_id", my_exam_id);
                startActivity(intent);
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMistakeList();
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        lv_mistakes.setVisibility(View.GONE);
    }

    /*
    * 初始化数据
    * */
    private void initData() {
        Intent intent = getIntent();
        course_id = intent.getStringExtra("course_id");
        getMistakeList();
    }

    private void getMistakeList() {
        listItems.clear();
        String token = TokenUtil.getToken(MistakeActivity.this);
        String url = Constant.BASE_DB_URL + "learn/myMistakeList";
        Map<String, String> map = new HashMap<>();
        map.put("course_id", course_id);
        map.put("token", token);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(MistakeActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            MistakeBean mb = objectMapper.readValue(jobj.toString(), MistakeBean.class);
                            listItems.add(mb);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(MistakeActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    Log.e("mapping", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("IO", e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    lv_mistakes.setVisibility(View.VISIBLE);
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
