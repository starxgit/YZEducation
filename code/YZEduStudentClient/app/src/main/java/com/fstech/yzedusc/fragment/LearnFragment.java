package com.fstech.yzedusc.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.activity.CourseIntroduceActivity;
import com.fstech.yzedusc.activity.GradeQueryActivity;
import com.fstech.yzedusc.activity.MyCourseActivity;
import com.fstech.yzedusc.activity.MyPracticalActivity;
import com.fstech.yzedusc.activity.MyTaskActivity;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.bean.HappyReadBean;
import com.fstech.yzedusc.bean.InformationBean;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.DateUtil;
import com.fstech.yzedusc.util.DownloadTools;
import com.fstech.yzedusc.util.ImageUitl;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.util.ThreadUtil;

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
 * Created by shaoxin on 18-3-25.
 * 学习界面的Fragment
 */

public class LearnFragment extends Fragment implements View.OnClickListener {
    // 定义UI对象
    private RelativeLayout re_mycourse;
    private RelativeLayout re_practical;
    private RelativeLayout re_task;
    private RelativeLayout re_grade;

    private ImageView iv_read_image;
    private TextView tv_read_title;
    private TextView tv_read_date;

    private List<HappyReadBean> listItems;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // 数据加载完成
                    case 1:
                        Log.e("listites", listItems.size() + "");
                        if (listItems.size() > 0) {
                            HappyReadBean hb = listItems.get(0);
                            tv_read_title.setText(hb.getHappy_read_title());
                            final String img = hb.getHappy_read_img();
                            Log.e("image", img);
                            // 使用线程工具类设置banner图片
                            ThreadUtil.runInThread(new Runnable() {
                                @Override
                                public void run() {
                                    int state = DownloadTools.downloadImg(img);
                                    ThreadUtil.runInUIThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ImageUitl.SimpleShowImage(img, iv_read_image);
                                        }
                                    });
                                }
                            });
                        }
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
        re_mycourse = (RelativeLayout) getActivity().findViewById(R.id.main_re_mycourse);
        re_practical = (RelativeLayout) getActivity().findViewById(R.id.main_re_practical);
        re_task = (RelativeLayout) getActivity().findViewById(R.id.main_re_task);
        re_grade = (RelativeLayout) getActivity().findViewById(R.id.main_re_grade);

        iv_read_image = (ImageView) getActivity().findViewById(R.id.iv_read_image);
        tv_read_date = (TextView) getActivity().findViewById(R.id.tv_read_date);
        tv_read_date.setText(DateUtil.NowDate());
        tv_read_title = (TextView) getActivity().findViewById(R.id.tv_read_title);

        re_mycourse.setOnClickListener(this);
        re_practical.setOnClickListener(this);
        re_task.setOnClickListener(this);
        re_grade.setOnClickListener(this);


    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        listItems = new ArrayList<>();
        String url = Constant.BASE_DB_URL1 + "platform/HappyRead";
//        OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
//            @Override
//            public void onFailure(Call call, Exception e) {
//                Log.e("fail", "okhttp请求失败");
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Log.e("response", response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    int result_code = jsonObject.getInt("result_code");
//                    if (result_code == 0) {
//                        // 返回正确的情况
//                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jobj = jsonArray.getJSONObject(i);
//                            HappyReadBean hb = objectMapper.readValue(jobj.toString(), HappyReadBean.class);
//                            listItems.add(hb);
//                        }
//                        handler.sendMessage(handler.obtainMessage(1));
//                    } else {
//                        String message = jsonObject.getString("message");
//                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    Log.e("Json", e.getMessage());
//                    e.printStackTrace();
//                } catch (JsonParseException e) {
//                    Log.e("error", e.getMessage());
//                    e.printStackTrace();
//                } catch (JsonMappingException e) {
//                    e.printStackTrace();
//                    Log.e("error", e.getMessage());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.e("error", e.getMessage());
//                }
//            }
//        });
    }

    private boolean checkLogin() {
        YZEduApplication application = (YZEduApplication) getActivity().getApplication();
        if (application.getToken() == null) {
            Toast.makeText(getActivity(), R.string.please_login_first, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_re_mycourse:
                if (checkLogin() == true) {
                    // 转到我的课程
                    Intent intent0 = new Intent(getActivity(), MyCourseActivity.class);
                    startActivity(intent0);
                }
                break;
            case R.id.main_re_practical:
                if (checkLogin() == true) {
                    // 转到我的实训
                    Intent intent1 = new Intent(getActivity(), MyPracticalActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.main_re_task:
                if (checkLogin() == true) {
                    // 转到我的任务
                    Intent intent2 = new Intent(getActivity(), MyTaskActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.main_re_grade:
                if (checkLogin() == true) {
                    // 转到成绩查询
                    Intent intent3 = new Intent(getActivity(), GradeQueryActivity.class);
                    startActivity(intent3);
                }
                break;
            default:
                break;
        }
    }

}
