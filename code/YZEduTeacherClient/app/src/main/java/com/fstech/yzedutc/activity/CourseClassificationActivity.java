package com.fstech.yzedutc.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.ClassificationBean;
import com.fstech.yzedutc.util.CacheActivityUtil;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.view.ClearEditText;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;

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
 * 课程分类的主界面
 */

public class CourseClassificationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_search;
    private ClearEditText et_search;
    private List<ClassificationBean> listItems_cb;
    private QMUIFloatLayout fl_content;
    private final int draws[] = {R.drawable.round_btn_blue_bg, R.drawable.round_btn_pink_bg, R.drawable.round_btn_qing_bg,
            R.drawable.round_btn_yellow_bg, R.drawable.round_btn_green_bg};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_classification);
        initView();
        listItems_cb = getClassfivations(-1);
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        CacheActivityUtil.addActivity(CourseClassificationActivity.this);
        tv_search = (TextView) findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);
        et_search = (ClearEditText) findViewById(R.id.et_search);
        fl_content = (QMUIFloatLayout) findViewById(R.id.fl_content);
        listItems_cb = new ArrayList<ClassificationBean>();
    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        for (int i = 0; i < listItems_cb.size(); i++) {
            TextView textView = new TextView(CourseClassificationActivity.this);
            int textViewPadding = QMUIDisplayHelper.dp2px(CourseClassificationActivity.this, 15);
            textView.setPadding(textViewPadding, textViewPadding, textViewPadding, textViewPadding);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            textView.setTextColor(ContextCompat.getColor(CourseClassificationActivity.this, R.color.Write));
            textView.setGravity(Gravity.CENTER);
            textView.setText(listItems_cb.get(i).getClassification_name());
            Drawable drawable = getResources().getDrawable(draws[i % draws.length]);
            textView.setBackground(drawable);
            int textViewSize = QMUIDisplayHelper.dpToPx(60);//将dp转化为px
            //设置textview在屏幕中所在位置
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(textViewSize, textViewSize);
            fl_content.addView(textView, layoutParams);//将textview添加到floatLayout布局中
            fl_content.setGravity(Gravity.LEFT);//floatLayout中子节点左对齐
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String cfa_id = listItems_cb.get(finalI).getClassification_id() + "";
                    Intent intent = new Intent(CourseClassificationActivity.this, CourseCfaResultActivity.class);
                    intent.putExtra("cfa_id", cfa_id);
                    startActivity(intent);
                }
            });
        }
    }

    /*
    * 获取分类列表的方法
    *  @param cfa_own , 返回消息
     *  返回分类对象的列表
    * */
    private List<ClassificationBean> getClassfivations(int cfa_own) {
        final List<ClassificationBean> list = new ArrayList<ClassificationBean>();
        String url = Constant.BASE_DB_URL + "course/classification";
        Map<String, String> map = new HashMap<String, String>();
        map.put("classification_own", cfa_own + "");
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("fail", "okhttp请求失败");
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
                            ClassificationBean cb = objectMapper.readValue(jobj.toString(), ClassificationBean.class);
                            list.add(cb);
                        }
                        initData();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(CourseClassificationActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    Log.e("Mapping", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("IO", e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });
        return list;
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
                doSearch();
                hideKeyBoard();
                break;
            default:
                break;
        }
    }

    /*
    * 开始搜索课程的方法
    * */
    private void doSearch() {
        String keyword = et_search.getText().toString();
        if (!keyword.equals("")) {
            Intent intent = new Intent(CourseClassificationActivity.this, CourseSearchResultActivity.class);
            intent.putExtra("keyword", keyword);
            startActivity(intent);
        }
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
