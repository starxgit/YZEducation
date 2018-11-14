package com.fstech.yzedusc.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.activity.InformationDetailActivity;
import com.fstech.yzedusc.activity.LoginActivity;
import com.fstech.yzedusc.activity.MyCourseActivity;
import com.fstech.yzedusc.adapter.AnnouncementListAdapter;
import com.fstech.yzedusc.adapter.InformationListAdapter;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.bean.AnnouncementBean;
import com.fstech.yzedusc.bean.InformationBean;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.view.MyListView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

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
 * 院校界面的Fragment
 */

public class SchoolFragment extends Fragment {
    // 定义UI对象
    private TabHost tabhost;
    private QMUIRadiusImageView iv_school_image;
    private TextView tv_school_name;
    private RelativeLayout re_school_background;
    private MyListView lv_information;
    private MyListView lv_announcement;
    private MyListView lv_circle;
    private InformationListAdapter information_adapter;
    private List<InformationBean> listItems_information;
    private AnnouncementListAdapter announcement_adapter;
    private List<AnnouncementBean> listItems_announcement;
    private YZEduApplication application;
    private int school_id;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_school, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkLogin();
        initView();
        initData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // 资讯列表加载完成
                    case 1:
                        information_adapter.notifyDataSetChanged();
                        break;
                    // 公告列表
                    case 2:
                        announcement_adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    /*
    * 判断用户是否已登录
    * 登录后才可见
    * */
    private void checkLogin() {
        application = (YZEduApplication) getActivity().getApplication();
        if (application.getUser_id() == 0) {
            Toast.makeText(getActivity(), R.string.please_login_first, Toast.LENGTH_SHORT).show();
            // 转到登录页
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        iv_school_image = (QMUIRadiusImageView) getActivity().findViewById(R.id.iv_school_image);
        tv_school_name = (TextView) getActivity().findViewById(R.id.tv_school_name);
        tabhost = (TabHost) getActivity().findViewById(R.id.main_school_tab);
        lv_information = (MyListView) getActivity().findViewById(R.id.lv_school_information);
        lv_announcement = (MyListView) getActivity().findViewById(R.id.lv_school_announcement);
        lv_circle = (MyListView) getActivity().findViewById(R.id.lv_school_circle);
        re_school_background = (RelativeLayout) getActivity().findViewById(R.id.re_school_background);
        //调用 TabHost.setup()
        tabhost.setup();
        //创建Tab标签
        tabhost.addTab(tabhost.newTabSpec("one").setIndicator("资讯").setContent(R.id.ll_school_information));
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("公告").setContent(R.id.ll_school_announcement));
        tabhost.addTab(tabhost.newTabSpec("three").setIndicator("学友圈").setContent(R.id.ll_school_circle));

        listItems_information = new ArrayList<InformationBean>();
        information_adapter = new InformationListAdapter(getActivity(), listItems_information);
        lv_information.setAdapter(information_adapter);

        listItems_announcement = new ArrayList<AnnouncementBean>();
        announcement_adapter = new AnnouncementListAdapter(getActivity(), listItems_announcement);
        lv_announcement.setAdapter(announcement_adapter);

        lv_information.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InformationBean ib = listItems_information.get(i);
                Intent intent = new Intent(getActivity(), InformationDetailActivity.class);
                intent.putExtra("ib", ib);
                startActivity(intent);
            }
        });
    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        school_id = -1;
        if (application.getSchool_id() != 0) {
            tv_school_name.setText(application.getSchool_name());
            school_id = application.getSchool_id();
        }

        setInformations();
        setAnnouncements();
    }

    /*
    * 设置资讯列表
    * */
    public void setInformations() {
        String url = Constant.BASE_DB_URL1 + "school/Information";
        Map<String, String> map = new HashMap<>();
        map.put("school_id", school_id + "");
        map.put("page_id", 1 + "");
//        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
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
////                            Log.e("informationsize", listItems_information.size() + "," + i + "," + jsonArray.length());
//                            JSONObject jobj = jsonArray.getJSONObject(i);
//                            InformationBean ib = objectMapper.readValue(jobj.toString(), InformationBean.class);
//                            listItems_information.add(ib);
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

    /*
    * 设置公告列表
    * */
    public void setAnnouncements() {
        String url = Constant.BASE_DB_URL1 + "school/Announcement";
        Map<String, String> map = new HashMap<>();
        map.put("school_id", school_id + "");
        map.put("page_id", 1 + "");
//        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
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
////                            Log.e("informationsize", listItems_information.size() + "," + i + "," + jsonArray.length());
//                            JSONObject jobj = jsonArray.getJSONObject(i);
//                            AnnouncementBean ab= objectMapper.readValue(jobj.toString(), AnnouncementBean.class);
//                            listItems_announcement.add(ab);
//                        }
//                        handler.sendMessage(handler.obtainMessage(2));
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

    /*
    * 设置学友圈列表
    * */
    public void setCircleList() {

    }

}
