package com.fstech.yzedusc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.fstech.yzedusc.util.TokenUtil;
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
    private ProgressBar progressBar;

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
        CacheActivityUtil.addActivity(CourseIntroduceActivity.this);
        iv_course_image = (QMUIRadiusImageView) findViewById(R.id.iv_course_image);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
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
                String token = application.getToken();
                if (token == null) {
                    Toast.makeText(CourseIntroduceActivity.this, R.string.please_login_first, Toast.LENGTH_SHORT).show();
                } else {
                    String strOption = bn_option.getText().toString();
                    if (strOption.equals("报名选课")) {
                        joinCourse(token);
                    } else {
                        quitCourse(token);
                    }

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
        String url = Constant.BASE_DB_URL + "course/catalog";
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
        String url = Constant.BASE_DB_URL + "course/detail";
        String token = TokenUtil.getToken(CourseIntroduceActivity.this);
        Map<String, String> map = new HashMap<String, String>();
        map.put("course_id", course_id);
        map.put("token", token);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("fail", "okhttp请求失败");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        JSONObject jobj = jsonObject.getJSONObject("return_data");
                        JSONObject courseInfo = jobj.getJSONObject("courseBean");
                        int isLearn = jobj.getInt("isLearn");
                        if (isLearn == 1) {
                            bn_option.setText("退选课程");
                        } else {
                            bn_option.setText("报名选课");
                        }
                        ObjectMapper objectMapper = new ObjectMapper();
                        CourseBean courseBean = objectMapper.readValue(courseInfo.toString(), CourseBean.class);
                        tv_course_name.setText(courseBean.getCourse_name());
                        tv_course_introduce.setText(courseBean.getCourse_introduce());
                        tv_course_teacher.setText("授课教师: " + courseBean.getCourse_teacher());
                        tv_course_code.setText("课程代码: " + courseBean.getCourse_code());
                        String sum_student = " / " + courseBean.getCourse_sum_student();
                        if (courseBean.getCourse_sum_student() == -1) sum_student = "";
                        tv_learn_num.setText(courseBean.getCourse_learn_student() + sum_student + " 人学习");
                        tv_sumhour.setText("共 " + courseBean.getCourse_sum() + " 课时");
                        if (courseBean.getCourse_price() > 0) {
                            tv_course_price.setText("¥ " + courseBean.getCourse_price());
                        } else {
                            tv_course_price.setText("免费");
                        }
                        final String str_course_cover = courseBean.getCourse_cover();
                        ImageUitl.showNetImage(iv_course_image, str_course_cover);
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
                } finally {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 报名选课
     */
    private void joinCourse(String token) {
        progressBar.setVisibility(View.VISIBLE);
        bn_option.setClickable(false);
        String url = Constant.BASE_DB_URL + "course/joinCourse";
        Map<String, String> map = new HashMap<String, String>();
        map.put("course_id", course_id);
        map.put("token", token);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(CourseIntroduceActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                bn_option.setClickable(true);
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        bn_option.setText("退选课程");
                        Toast.makeText(CourseIntroduceActivity.this, "选课成功", Toast.LENGTH_SHORT).show();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(CourseIntroduceActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json创建出错", e.getLocalizedMessage());
                } finally {
                    progressBar.setVisibility(View.GONE);
                    bn_option.setClickable(true);
                }
            }
        });

    }

    /**
     * 退选课程
     */
    private void quitCourse(String token) {
        progressBar.setVisibility(View.VISIBLE);
        bn_option.setClickable(false);
        String url = Constant.BASE_DB_URL + "course/quitCourse";
        Map<String, String> map = new HashMap<String, String>();
        map.put("course_id", course_id);
        map.put("token", token);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(CourseIntroduceActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                bn_option.setClickable(true);
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        bn_option.setText("报名选课");
                        Toast.makeText(CourseIntroduceActivity.this, "退课成功", Toast.LENGTH_SHORT).show();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(CourseIntroduceActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json创建出错", e.getLocalizedMessage());
                } finally {
                    progressBar.setVisibility(View.GONE);
                    bn_option.setClickable(true);
                }
            }
        });
    }
}
