package com.fstech.yzedusc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.activity.CourseClassificationActivity;
import com.fstech.yzedusc.activity.CourseIntroduceActivity;
import com.fstech.yzedusc.activity.LiveRoomActivity;
import com.fstech.yzedusc.adapter.CourseGridAdapter;
import com.fstech.yzedusc.adapter.LiveRoomListAdapter;
import com.fstech.yzedusc.bean.BannerBean;
import com.fstech.yzedusc.bean.CourseBean;
import com.fstech.yzedusc.bean.LiveRoomBean;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.view.MyGridView;
import com.fstech.yzedusc.view.MyListView;

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
 * 课程界面的Fragment
 */

public class CourseFragment extends Fragment implements View.OnClickListener {
    // 定义UI对象
    private MyListView lv_live;
    private MyGridView gv_new;
    private MyGridView gv_top;
    private List<LiveRoomBean> list_live;
    private List<CourseBean> list_new;
    private List<CourseBean> list_top;
    private LiveRoomListAdapter adapter_live;
    private CourseGridAdapter adapter_new;
    private CourseGridAdapter adapter_top;
    private EditText et_search;
    private String userid;
    private int page;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course, container, false);
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
                    // newCourse 数据加载完成
                    case 7:
//                        Log.e("new", "NewCourse loading......size=" + list_new.size());
                        adapter_new.notifyDataSetChanged();
                        break;
                    // topCourse 数据加载完成
                    case 8:
//                        Log.e("new", "TopCourse loading......size=" + list_new.size());
                        adapter_top.notifyDataSetChanged();
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
        lv_live = (MyListView) getActivity().findViewById(R.id.lv_live);
        gv_new = (MyGridView) getActivity().findViewById(R.id.gv_new);
        gv_top = (MyGridView) getActivity().findViewById(R.id.gv_top);
        et_search = (EditText) getActivity().findViewById(R.id.et_search);
        et_search.setOnClickListener(this);
        list_live = new ArrayList<LiveRoomBean>();
        list_new = new ArrayList<CourseBean>();
        list_top = new ArrayList<CourseBean>();
        adapter_live = new LiveRoomListAdapter(getActivity(), list_live);
        adapter_new = new CourseGridAdapter(getActivity(), list_new);
        adapter_top = new CourseGridAdapter(getActivity(), list_top);
        lv_live.setAdapter(adapter_live);
        lv_live.measure(0, 0);
        gv_new.setAdapter(adapter_new);
        gv_top.setAdapter(adapter_top);
        userid = "1";
        page = 1;
        gv_new.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String course_id = list_new.get(i).getCourse_id() + "";
                Intent intent = new Intent(getActivity(), CourseIntroduceActivity.class);
                intent.putExtra("course_id", course_id);
                startActivity(intent);
            }
        });
        gv_top.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String course_id = list_top.get(i).getCourse_id() + "";
                Intent intent = new Intent(getActivity(), CourseIntroduceActivity.class);
                intent.putExtra("course_id", course_id);
                startActivity(intent);
            }
        });

        lv_live.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), LiveRoomActivity.class);
                intent.putExtra("lb", list_live.get(i));
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
        getNewCourses();
        getTopCourses();
        LiveRoomBean l = new LiveRoomBean(1, "张老师", "趣味计算机", "9600f698", "shujujiegou.png", 2);
        LiveRoomBean l2 = new LiveRoomBean(1, "廖老师", "廖老师的直播间", "c4a3c846", "ppt2010.png", 2);
        list_live.add(l);
        list_live.add(l2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_search:
                Intent intent0 = new Intent(getActivity(), CourseClassificationActivity.class);
                startActivity(intent0);
                break;
            default:
                break;
        }
    }

    /*
    * 获取最新课程的列表
    * */
    private void getNewCourses() {
        String url = Constant.BASE_DB_URL + "NewCourse";
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", userid);
        map.put("page", page + "");
//        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
//            @Override
//            public void onFailure(Call call, Exception e) {
//                Log.e("fail", "okhttp请求失败");
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Log.e("newCourseResponse", response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    int result_code = jsonObject.getInt("result_code");
//                    if (result_code == 0) {
//                        // 返回正确的情况
//                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        for (int i = 0; i < Constant.GRID_SIZE; i++) {
//                            JSONObject jobj = jsonArray.getJSONObject(i);
//                            CourseBean courseBean = objectMapper.readValue(jobj.toString(), CourseBean.class);
//                            // TODO 取消预定设置
//                            courseBean.setCourse_sum(Constant.ARR_COURSE_SUM_HOUR[courseBean.getCourse_id() % Constant.ARR_COURSE_SUM_HOUR.length]);
//                            list_new.add(courseBean);
//                        }
//                        handler.sendMessage(handler.obtainMessage(7));
//                    } else {
//                        String message = jsonObject.getString("message");
//                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    Log.e("Json", "创建Json对象失败");
//                    e.printStackTrace();
//                } catch (JsonParseException e) {
//                    Log.e("Json", "JSON包装成对象失败");
//                    e.printStackTrace();
//                } catch (JsonMappingException e) {
//                    Log.e("error", "NewCourseMapping异常");
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    Log.e("error", "IO异常" + e.getLocalizedMessage());
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    /*
    * 获取热门课程的列表
    * */
    private void getTopCourses() {
        String url = Constant.BASE_DB_URL + "TopCourse";
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", userid);
        map.put("page", page + "");
//        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
//            @Override
//            public void onFailure(Call call, Exception e) {
//                Log.e("fail", "okhttp请求失败");
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Log.e("TopCourseResponse", response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    int result_code = jsonObject.getInt("result_code");
//                    if (result_code == 0) {
//                        // 返回正确的情况
//                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        for (int i = 0; i < Constant.GRID_SIZE; i++) {
//                            JSONObject jobj = jsonArray.getJSONObject(i);
//                            CourseBean courseBean = objectMapper.readValue(jobj.toString(), CourseBean.class);
//                            // TODO 取消预定设置
//                            courseBean.setCourse_sum(Constant.ARR_COURSE_SUM_HOUR[courseBean.getCourse_id() % Constant.ARR_COURSE_SUM_HOUR.length]);
//                            list_top.add(courseBean);
//                        }
//                        handler.sendMessage(handler.obtainMessage(8));
//                    } else {
//                        String message = jsonObject.getString("message");
//                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    Log.e("Json", "创建Json对象失败");
//                    e.printStackTrace();
//                } catch (JsonParseException e) {
//                    Log.e("Json", "JSON包装成对象失败");
//                    e.printStackTrace();
//                } catch (JsonMappingException e) {
//                    Log.e("error", "TopCourseMapping异常");
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    Log.e("error", "IO异常" + e.getLocalizedMessage());
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
