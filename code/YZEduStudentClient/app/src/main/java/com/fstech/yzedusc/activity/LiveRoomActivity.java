package com.fstech.yzedusc.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.adapter.ChatListAdapter;
import com.fstech.yzedusc.bean.ChatBean;
import com.fstech.yzedusc.bean.LiveRoomBean;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by shaoxin on 18-4-12.
 * 直播间的主界面
 */

public class LiveRoomActivity extends AppCompatActivity {
    private JCVideoPlayerStandard player;
    private LiveRoomBean lb;
    private TabHost tabhost;
    private ListView lv_chat;
    private List<ChatBean> chatList;
    private ChatListAdapter adapter;
    private EditText et_content;
    private Handler handler;
    private int a1, a2;
    private final String[] students = {"奉先", "张翼德", "悟空", "子龙", "陆逊", "小乔", "孟德"};
    private final String[] contents = {"666", "2333", "路过～", "有趣", "原来如此", "???"};
    private boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_room);
        hideKeyBoard();
        initView();
        initData();
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        player = (JCVideoPlayerStandard) findViewById(R.id.player_video);
        tabhost = (TabHost) findViewById(R.id.task_tab);
        lv_chat = (ListView) findViewById(R.id.lv_chat);
        et_content = (EditText) findViewById(R.id.et_content);
        chatList = new ArrayList<>();
        adapter = new ChatListAdapter(LiveRoomActivity.this, chatList);
        tabhost.setup();
        //创建Tab标签
        tabhost.addTab(tabhost.newTabSpec("one").setIndicator("讨论").setContent(R.id.re_disscuss));
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("总览").setContent(R.id.ll_detail));
        lv_chat.setAdapter(adapter);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // 每次列表加载数据完成
                    case 0:
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initData() {
        a1 = 0;
        a2 = 0;
        flag = true;
        Intent intent = getIntent();
        lb = (LiveRoomBean) intent.getSerializableExtra("lb");
        String url = "rtmp://cdn.fstechnology.cn/live/22280_9600f698362d11e892905cb9018cf0d4";
        Log.e("lburl", url);
        player.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
//        ChatBean cb = new ChatBean(1, "直播机器人", "欢迎进入直播间", null, null, null);
//        chatList.add(cb);
        hideKeyBoard();
        autoDisscuss();
    }

    /*
    * 自动发言
    * */
    private void autoDisscuss() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(30000);
                randomValue();
                if (flag == true)
                    autoDisscuss();
            }
        }).start();
    }

    /*
    * 随机数
    * */
    private void randomValue() {
        a1 = (int) (Math.random() * 100);
        a2 = (int) (Math.random() * 100);
        Log.e("a1,a2", "a1=" + a1 + ",a2=" + a2);
        String inUser = students[a1 % students.length];
        String inContent = contents[a2 % contents.length];
        ChatBean cb = new ChatBean(0, inUser, inContent, null, null, null);
        chatList.add(cb);
        handler.sendMessage(handler.obtainMessage(0));
    }

    /*
    * 我的发言
    * */
    public void myDisscuss(View v) {
        String str = et_content.getText().toString();
//        Log.e("str", str);
        if (str.equals("")) {
            Toast.makeText(LiveRoomActivity.this, R.string.please_input_content, Toast.LENGTH_SHORT).show();
        } else {
            ChatBean c = new ChatBean(1, null, null, "荀况", str, null);
            chatList.add(c);
            adapter.notifyDataSetChanged();
            et_content.setText("");
            hideKeyBoard();
        }
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
