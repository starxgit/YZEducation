package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.LessonListAdapter;
import com.fstech.yzedutc.bean.CourseBean;
import com.fstech.yzedutc.bean.LessonBean;
import com.fstech.yzedutc.util.CacheActivityUtil;
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
 * Created by shaoxin on 18-4-11.
 * 课程学习主界面
 */

public class CourseLearnActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_title;
    private ImageView iv_course_cover;
    private LinearLayout ll_material;
    private LinearLayout ll_detail;
    private ListView lv_catalog;
    private CourseBean cb;
    private List<LessonBean> listItems;
    private LessonListAdapter adapter_lesson;
    private ProgressBar progressBar;
    private TextView tv_edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_learn);
        initView();
        initData();
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

    /*
    * 初始化视图
    * */
    private void initView() {
        CacheActivityUtil.addActivity(CourseLearnActivity.this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_course_cover = (ImageView) findViewById(R.id.iv_course_cover);
        ll_material = (LinearLayout) findViewById(R.id.ll_material);
        ll_detail = (LinearLayout) findViewById(R.id.ll_detail);
        lv_catalog = (ListView) findViewById(R.id.lv_catalog);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv_edit =(TextView)findViewById(R.id.tv_edit);
        listItems = new ArrayList<>();
        adapter_lesson = new LessonListAdapter(CourseLearnActivity.this, listItems);
        lv_catalog.setAdapter(adapter_lesson);

        ll_material.setOnClickListener(this);
        ll_detail.setOnClickListener(this);
        tv_edit.setOnClickListener(this);
        lv_catalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LessonBean lb = listItems.get(i);
                Log.e("lb", lb.toString());
                Intent intent = new Intent(CourseLearnActivity.this, LessonLearnActivity.class);
                intent.putExtra("lb", lb);
                startActivity(intent);
            }
        });
    }

    /*
    * 初始化数据
    * */
    private void initData() {
        cb = null;
        Intent intent = getIntent();
        cb = (CourseBean) intent.getSerializableExtra("cb");
        tv_title.setText(cb.getCourse_name());
        ImageUitl.showNetImage(iv_course_cover, cb.getCourse_cover());
        getCatalogs(cb.getCourse_id() + "");
    }

    /*
   * 获取课程目录
   * 无参数
   * 无返回
   * */
    private void getCatalogs(String course_id) {
        progressBar.setVisibility(View.VISIBLE);
        String url = Constant.BASE_DB_URL + "course/catalog";
        Map<String, String> map = new HashMap<String, String>();
        map.put("course_id", course_id);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("fail", "okhttp请求失败");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response) {
//                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            LessonBean lb = objectMapper.readValue(jobj.toString(), LessonBean.class);
                            listItems.add(lb);
                        }
                        adapter_lesson.notifyDataSetChanged();
                        lv_catalog.measure(0, 0);
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(CourseLearnActivity.this, message, Toast.LENGTH_SHORT).show();
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
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_material:
                Log.e("click", "material");
                Intent intent = new Intent(CourseLearnActivity.this, MaterialActivity.class);
                intent.putExtra("course_id", cb.getCourse_id() + "");
                startActivity(intent);
                break;
            case R.id.ll_detail:
                Log.e("click", "add");
                Intent intent3 = new Intent(CourseLearnActivity.this, AddLessonActivity.class);
                intent3.putExtra("cb", cb);
                startActivity(intent3);
                break;
            case R.id.tv_edit:
                Log.e("click", "edit");
                Intent intent4 = new Intent(CourseLearnActivity.this, CourseEditActivity.class);
                intent4.putExtra("course_id", cb.getCourse_id() + "");
                startActivity(intent4);
                break;
            default:
                break;
        }
    }
}
