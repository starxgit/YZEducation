package com.fstech.yzedutc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.LessonBean;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.util.TokenUtil;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;

/**
 * Created by shaoxin on 18-4-11.
 * 一节课的学习页
 */

public class LessonLearnActivity extends AppCompatActivity implements View.OnClickListener {
    private LessonBean lb;
    private TextView tv_title;
    private JCVideoPlayerStandard player;
    private TextView tv_delete;
    private LinearLayout ll_exercise;
    private LinearLayout ll_disscuss;
    private ListView lv_konwledge;
    private SimpleAdapter adapter;
    private List<Map<String, String>> listItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_learn);
        initView();
        initData();
        setKnowledgeList();
    }

    /*
    * 初始化视图
    * */
    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_exercise = (LinearLayout) findViewById(R.id.ll_exercise);
        ll_disscuss = (LinearLayout) findViewById(R.id.ll_disscuss);
        lv_konwledge = (ListView) findViewById(R.id.lv_knowledge);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        player = (JCVideoPlayerStandard) findViewById(R.id.player_video);
        listItems = new ArrayList<>();
        adapter = new SimpleAdapter(LessonLearnActivity.this, listItems, android.R.layout.activity_list_item,
                new String[]{"knowledge"}, new int[]{android.R.id.text1});
        lv_konwledge.setAdapter(adapter);
        ll_disscuss.setOnClickListener(this);
        ll_exercise.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
    }

    /*
    * 初始化数据
    * */
    private void initData() {
        lb = null;
        Intent intent = getIntent();
        lb = (LessonBean) intent.getSerializableExtra("lb");
        tv_title.setText(lb.getLesson_title());
        String url = lb.getLesson_video_url();
        Log.e("url", url);
        player.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
    }

    private void setKnowledgeList() {
        String url = Constant.BASE_DB_URL + "course/knowledgeList";
        Map<String, String> map = new HashMap<String, String>();
        map.put("lesson_id", lb.getLesson_id() + "");
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
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Map<String, String> contentMap = new HashMap<>();
                            contentMap.put("knowledge", jsonArray.get(i).toString());
                            listItems.add(contentMap);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(LessonLearnActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
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
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_exercise:
                Log.e("click", "exercise");
                Intent intent = new Intent(LessonLearnActivity.this, ExamActivity.class);
                intent.putExtra("lesson_id", lb.getLesson_id());
                intent.putExtra("course_id", lb.getCourse_id());
                startActivity(intent);
                break;
            case R.id.ll_disscuss:
                Log.e("click", "disscuss");
                Intent intent1 = new Intent(LessonLearnActivity.this, CourseDisscussActivity.class);
                intent1.putExtra("lesson_id", lb.getLesson_id());
                startActivity(intent1);
                break;
            case R.id.tv_delete:
                tv_delete.setClickable(false);
                new QMUIDialog.MessageDialogBuilder(LessonLearnActivity.this)
                        .setTitle("删除章节")
                        .setMessage("确定要删除" + lb.getLesson_title() + "吗？")
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                delLesson();
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }

    /**
     * 删除章节的方法
     */
    private void delLesson() {
        String lesson_id = lb.getLesson_id() + "";
        String token = TokenUtil.getToken(LessonLearnActivity.this);
        String url = Constant.BASE_DB_URL + "teach/delLesson";
        Map<String, String> map = new HashMap<>();
        map.put("lesson_id", lesson_id);
        map.put("token", token);
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(LessonLearnActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
                tv_delete.setClickable(true);
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        Toast.makeText(LessonLearnActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(LessonLearnActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    tv_delete.setClickable(true);
                }
            }
        });
    }
}
