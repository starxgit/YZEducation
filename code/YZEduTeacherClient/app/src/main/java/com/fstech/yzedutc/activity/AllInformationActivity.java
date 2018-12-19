package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.InformationListAdapter;
import com.fstech.yzedutc.bean.InformationBean;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.util.TokenUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
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
 * Created by shaoxin on 11/15/18.
 * 更多资讯列表的Activity
 */

public class AllInformationActivity extends AppCompatActivity {
    private final String urlList[] = {"platform/information", "school/information"};
    private SmartRefreshLayout smartRefreshLayout;
    private ListView lvInformation;
    private InformationListAdapter adapter;
    private List<InformationBean> listItemsInformation;
    private int type;
    private int page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_information);
        initView();
        initAction();
        getInformations();
    }

    /*
    * 初始化视图
    * */
    private void initView() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smart_refresh);
        lvInformation = (ListView) findViewById(R.id.lv_information);
        listItemsInformation = new ArrayList<>();
        adapter = new InformationListAdapter(AllInformationActivity.this, listItemsInformation);
        lvInformation.setAdapter(adapter);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        page = 1;

    }


    /*
    * 初始化动作
    * */
    private void initAction() {
        // 刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                listItemsInformation.clear();
                page = 1;
                getInformations();
            }
        });
        // 加载更多
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getInformations();
            }
        });
        // 列表点击动作
        lvInformation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InformationBean ib = listItemsInformation.get(i);
                Intent intent = new Intent(AllInformationActivity.this, InformationDetailActivity.class);
                intent.putExtra("ib", ib);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }


    /*
    * 获取平台资讯的数据
    * 无参数
    * 无返回
    * */
    private void getInformations() {
        String url = Constant.BASE_DB_URL + urlList[type];
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page + "");
        map.put("token", TokenUtil.getToken(AllInformationActivity.this));
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("fail", "okhttp请求失败");
            }

            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        // 返回正确的情况
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        if (jsonArray.length() > 0) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jobj = jsonArray.getJSONObject(i);
                                InformationBean ib = objectMapper.readValue(jobj.toString(), InformationBean.class);
                                listItemsInformation.add(ib);
                            }
                            adapter.notifyDataSetChanged();
                            page++;
                        } else {
                            Toast.makeText(AllInformationActivity.this, R.string.mo_more_content, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(AllInformationActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("Json", e.getMessage());
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("JsonPrase", e.getMessage());
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                    Log.e("JsonMapping", e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("IOerror", e.getMessage());
                } finally {
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.finishLoadmore();
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
