package com.fstech.yzedusc.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.adapter.CourseListAdapter;
import com.fstech.yzedusc.bean.CourseBean;
import com.fstech.yzedusc.util.CacheActivityUtil;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.view.MyListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

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
 * Created by shaoxin on 18-3-29.
 * 搜索结果的主界面
 */

public class CourseSearchResultActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_search;
    private EditText et_search;
    private String keyword;
    private String user_id;
    private ListView lv_search_course;
    private CourseListAdapter adapter_course;
    private List<CourseBean> listItems_course;
    private SmartRefreshLayout smartRefreshLayout;
    private ProgressBar pb;
    private boolean isLoading;
    private int page;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search_result);
        initView();
        initData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // 课程列表数据加载完成
                    case 0:
                        if(listItems_course.size()==0){
                            Toast.makeText(CourseSearchResultActivity.this,R.string.search_another_course,Toast.LENGTH_SHORT).show();
                        }
                        adapter_course.notifyDataSetChanged();
                        isLoading = false;
                        setLoading();
                        break;

                    default:
                        break;
                }
            }
        };
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        CacheActivityUtil.addActivity(CourseSearchResultActivity.this);
        Intent intent = getIntent();
        keyword = intent.getStringExtra("keyword");
        user_id = "1";
        page = 1;
        isLoading = true;
        tv_search = (TextView) findViewById(R.id.tv_search);
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setText(keyword);
        tv_search.setOnClickListener(this);
        lv_search_course = (ListView) findViewById(R.id.lv_search_course);
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smart_refresh);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        pb = (ProgressBar) findViewById(R.id.progressBar);
        setLoading();
        listItems_course = new ArrayList<CourseBean>();
        adapter_course = new CourseListAdapter(CourseSearchResultActivity.this, listItems_course);
        lv_search_course.setAdapter(adapter_course);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reSearch();
            }
        });
        lv_search_course.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String course_id = listItems_course.get(i).getCourse_id() + "";
                Intent intent1 = new Intent(CourseSearchResultActivity.this, CourseIntroduceActivity.class);
                intent1.putExtra("course_id", course_id);
                startActivity(intent1);
            }
        });
    }

    /*
    * 设置加载显示
    * 无参数
    * 无返回
    * */
    private void setLoading() {
        if (isLoading == true) {
            pb.setVisibility(View.VISIBLE);
        } else {
            pb.setVisibility(View.GONE);
        }
    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        listItems_course.clear();
        String url = Constant.BASE_DB_URL + "SearchCourse";
        Map<String, String> map = new HashMap<String, String>();
        map.put("keyword", keyword);
        map.put("user_id", user_id);
        map.put("page", page + "");
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("fail", "okhttp请求失败");
            }

            @Override
            public void onResponse(String response) {
                isLoading = true;
                setLoading();
                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        // 返回正确的情况
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < Constant.GRID_SIZE; i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            CourseBean courseBean = objectMapper.readValue(jobj.toString(), CourseBean.class);
                            // TODO 取消预定设置
                            courseBean.setCourse_sum(Constant.ARR_COURSE_SUM_HOUR[courseBean.getCourse_id() % Constant.ARR_COURSE_SUM_HOUR.length]);
                            listItems_course.add(courseBean);
                        }
                        handler.sendMessage(handler.obtainMessage(0));
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(CourseSearchResultActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("Json", e.getLocalizedMessage());
                    e.printStackTrace();
                    handler.sendMessage(handler.obtainMessage(0));
                } catch (JsonParseException e) {
                    Log.e("Json", "JSON包装成对象失败");
                    e.printStackTrace();
                    handler.sendMessage(handler.obtainMessage(0));
                } catch (JsonMappingException e) {
                    Log.e("error", "NewCourseMapping异常");
                    e.printStackTrace();
                    handler.sendMessage(handler.obtainMessage(0));
                } catch (IOException e) {
                    Log.e("error", "IO异常" + e.getLocalizedMessage());
                    e.printStackTrace();
                    handler.sendMessage(handler.obtainMessage(0));
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                reSearch();
                hideKeyBoard();
                break;
            default:
                break;
        }
    }

    /*
    * 重新搜索的方法
    * */
    private void reSearch() {
        hideKeyBoard();
        keyword = et_search.getText().toString();
        Log.e("key", keyword);
        initData();
    }

    /*
    * 隐藏键盘的方法
    * */
    private void hideKeyBoard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
