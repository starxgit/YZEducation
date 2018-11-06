package com.fstech.yzedusc.activity;

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

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.LessonBean;
import com.fstech.yzedusc.util.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by shaoxin on 18-4-11.
 * 一节课的学习页
 */

public class LessonLearnActivity extends AppCompatActivity implements View.OnClickListener {
    private LessonBean lb;
    private TextView tv_title;
    private JCVideoPlayerStandard player;
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

    }

    /*
    * 初始化视图
    * */
    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_exercise = (LinearLayout) findViewById(R.id.ll_exercise);
        ll_disscuss = (LinearLayout) findViewById(R.id.ll_disscuss);
        lv_konwledge = (ListView) findViewById(R.id.lv_knowledge);
        player = (JCVideoPlayerStandard) findViewById(R.id.player_video);
        listItems = new ArrayList<>();
        adapter = new SimpleAdapter(LessonLearnActivity.this, listItems, android.R.layout.activity_list_item,
                new String[]{"knowledge"}, new int[]{android.R.id.text1});
        lv_konwledge.setAdapter(adapter);
        ll_disscuss.setOnClickListener(this);
        ll_exercise.setOnClickListener(this);
    }

    /*
    * 初始化数据
    * */
    private void initData() {
        lb = null;
        Intent intent = getIntent();
        lb = (LessonBean) intent.getSerializableExtra("lb");
        tv_title.setText(lb.getLesson_title());
        String url = Constant.BASE_VIDEO_URL + lb.getLesson_video_url();
//        Log.e("url", url);
//        String url = "rtmp://22280.livepush.myqcloud.com/live/22280_9600f698362d11e892905cb9018cf0d4?bizid=22280";
        player.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");

        Map<String, String> map0 = new HashMap<>();
        map0.put("knowledge", "Java是一种跨平台的语言");
        Map<String, String> map1 = new HashMap<>();
        map1.put("knowledge", "Java的跨平台体现在JVM虚拟机的机制上面");
        Map<String, String> map2 = new HashMap<>();
        map2.put("knowledge", "Java有Java SE，Java EE和Java ME三种主要平台");
        listItems.add(map0);
        listItems.add(map1);
        listItems.add(map2);
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
                startActivity(intent);
                break;
            case R.id.ll_disscuss:
                Log.e("click", "disscuss");
                Intent intent1 = new Intent(LessonLearnActivity.this, CourseDisscussActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
