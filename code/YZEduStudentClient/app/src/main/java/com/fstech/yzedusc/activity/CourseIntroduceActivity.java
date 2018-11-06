package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.adapter.LessonListAdapter;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.bean.CourseBean;
import com.fstech.yzedusc.bean.InformationBean;
import com.fstech.yzedusc.bean.LessonBean;
import com.fstech.yzedusc.util.CacheActivityUtil;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.DownloadTools;
import com.fstech.yzedusc.util.ImageUitl;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.util.ThreadUtil;
import com.fstech.yzedusc.view.MyListView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

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
 * Created by shaoxin on 18-3-28.
 * 课程介绍页的主界面
 */

public class CourseIntroduceActivity extends AppCompatActivity {
    private QMUIRadiusImageView iv_course_image;
    private TextView tv_course_name;
    private TextView tv_learn_num;
    private TextView tv_sumhour;
    private TextView tv_course_code;
    private TextView tv_course_price;
    private QMUIRoundButton bn_option;
    private TabHost tabhost;
    private LinearLayout ll_introduce;
    private LinearLayout ll_catalog;
    private MyListView lv_catalog;
    private TextView tv_course_teacher;
    private TextView tv_course_introduce;
    private List<LessonBean> listItems;
    private LessonListAdapter adapter_lesson;
    private String course_id;
    private String user_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_introduce);
        initView();
        initData();
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        Intent intent = getIntent();
        course_id = intent.getStringExtra("course_id");
        Log.e("course_id", course_id);
        user_id = "1";
        CacheActivityUtil.addActivity(CourseIntroduceActivity.this);
        iv_course_image = (QMUIRadiusImageView) findViewById(R.id.iv_course_image);
        tv_course_name = (TextView) findViewById(R.id.tv_course_name);
        tv_learn_num = (TextView) findViewById(R.id.tv_learn_num);
        tv_sumhour = (TextView) findViewById(R.id.tv_sum_hour);
        tv_course_price = (TextView) findViewById(R.id.tv_course_price);
        tv_course_code = (TextView) findViewById(R.id.tv_course_code);
        bn_option = (QMUIRoundButton) findViewById(R.id.bn_option);
        tabhost = (TabHost) findViewById(R.id.tab_course_introduce);
        ll_introduce = (LinearLayout) findViewById(R.id.ll_course_introduce);
        ll_catalog = (LinearLayout) findViewById(R.id.ll_course_catalog);
        lv_catalog = (MyListView) findViewById(R.id.lv_course_catalog);
        tv_course_teacher = (TextView) findViewById(R.id.tv_course_teacher);
        tv_course_introduce = (TextView) findViewById(R.id.tv_course_introduce);
        //调用 TabHost.setup()
        tabhost.setup();
        //创建Tab标签
        tabhost.addTab(tabhost.newTabSpec("one").setIndicator("课程介绍").setContent(R.id.ll_course_introduce));
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("课程目录").setContent(R.id.ll_course_catalog));
        listItems = new ArrayList<LessonBean>();
        adapter_lesson = new LessonListAdapter(CourseIntroduceActivity.this, listItems);
        lv_catalog.setAdapter(adapter_lesson);

        bn_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YZEduApplication application = (YZEduApplication) getApplication();
                if (application.getUser_id() == 0) {
                    Toast.makeText(CourseIntroduceActivity.this, R.string.please_login_first, Toast.LENGTH_SHORT).show();
                } else {
                    // TODO 选课
                    Toast.makeText(CourseIntroduceActivity.this, "现在不是选课时间！", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        // 初始化设置字符为空
        tv_course_name.setText("");
        tv_course_introduce.setText("");
        tv_course_teacher.setText("");
        tv_course_code.setText("");
        tv_learn_num.setText("");
        tv_sumhour.setText("");
        tv_course_price.setText("");

        getCourseData();
        getCatalogs();

    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

    /*
    * 获取课程目录
    * 无参数
    * 无返回
    * */
    private void getCatalogs() {
        String url = Constant.BASE_DB_URL + "CourseCatalog";
        Map<String, String> map = new HashMap<String, String>();
        map.put("course_id", course_id);
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
                        Toast.makeText(CourseIntroduceActivity.this, message, Toast.LENGTH_SHORT).show();
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
                }
            }
        });
    }

    /*
    * 获取并设置课程基本信息
    * 无参数
    * 无返回
    * */
    private void getCourseData() {
        String url = Constant.BASE_DB_URL + "CourseDetail";
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", user_id);
        map.put("course_id", course_id);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("fail", "okhttp请求失败");
            }

            @Override
            public void onResponse(String response) {
//                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        JSONObject jobj = jsonObject.getJSONObject("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        CourseBean courseBean = objectMapper.readValue(jobj.toString(), CourseBean.class);
                        tv_course_name.setText(courseBean.getCourse_name());
                        tv_course_introduce.setText(courseBean.getCourse_introduce());
                        // TODO 设置教师
                        String teacher = Constant.ARR_TEACHER_NAME[Integer.parseInt(courseBean.getCourse_teacher()) % Constant.ARR_TEACHER_NAME.length];
                        tv_course_teacher.setText("授课教师: " + teacher);
                        tv_course_code.setText("课程代码: " + courseBean.getCourse_code());
                        String sum_student = " / " + courseBean.getCourse_sum_student();
                        if (courseBean.getCourse_sum_student() == -1) sum_student = "";
                        tv_learn_num.setText(courseBean.getCourse_learn_student() + sum_student + " 人学习");
                        // TODO 设置课时
                        int sHour = Constant.ARR_COURSE_SUM_HOUR[Integer.parseInt(course_id) % Constant.ARR_COURSE_SUM_HOUR.length];
                        tv_sumhour.setText("共 " + sHour + " 课时");
                        if (courseBean.getCourse_price() > 0) {
                            tv_course_price.setText("¥ " + courseBean.getCourse_price());
                        } else {
                            tv_course_price.setText("免费");
                        }
                        final String str_course_cover = courseBean.getCourse_cover();
                        ThreadUtil.runInThread(new Runnable() {
                            @Override
                            public void run() {
                                int state = DownloadTools.downloadImg(str_course_cover);
                                ThreadUtil.runInUIThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ImageUitl.SimpleShowImage(str_course_cover, iv_course_image);
                                    }
                                });
                            }
                        });

                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(CourseIntroduceActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json创建出错", e.getLocalizedMessage());
                } catch (JsonParseException e) {
                    Log.e("json转换出错", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    Log.e("Mapping出错", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("IO出错", e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}
