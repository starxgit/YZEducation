package com.fstech.yzedutc.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.activity.CourseLearnActivity;
import com.fstech.yzedutc.activity.PracticalLearnActivity;
import com.fstech.yzedutc.adapter.CourseListAdapter;
import com.fstech.yzedutc.bean.CourseBean;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.util.TokenUtil;
import com.fstech.yzedutc.view.ClearEditText;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
 * Created by shaoxin on 18-11-20.
 * 学习界面的Fragment
 */

public class LearnFragment extends Fragment {
    // 定义UI对象
    private ClearEditText etSearch;
    private SmartRefreshLayout smartRefresh;
    private ListView lvMyCourse;
    private CourseListAdapter courseListAdapter;
    private List<CourseBean> myCourseList;
    private List<CourseBean> myCourseList_re;

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
    }

    /**
     * 初始化视图
     */
    private void initView() {
        // 找到UI对象
        etSearch = (ClearEditText) getActivity().findViewById(R.id.cet_search);
        smartRefresh = (SmartRefreshLayout) getActivity().findViewById(R.id.smart_refresh);
        lvMyCourse = (ListView) getActivity().findViewById(R.id.lv_my_course);
        // 初始化
        myCourseList = new ArrayList<>();
        myCourseList_re = new ArrayList<>();
        courseListAdapter = new CourseListAdapter(getActivity(), myCourseList);
        lvMyCourse.setAdapter(courseListAdapter);
        // 设置样式
        smartRefresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefresh.setRefreshFooter(new ClassicsFooter(getActivity()));
        // 设置动作
        lvMyCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CourseBean cb = myCourseList.get(i);
                int type = cb.getCourse_type();
                if (type == 3) {
                    // 综合实训
                    Intent intent = new Intent(getActivity(), PracticalLearnActivity.class);
                    intent.putExtra("cb",cb);
                    startActivity(intent);
                } else {
                    // 课程学习
                    Intent intent = new Intent(getActivity(), CourseLearnActivity.class);
                    intent.putExtra("cb",cb);
                    startActivity(intent);
                }

            }
        });
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();
            }
        });
        // 监听输入
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String key = etSearch.getText().toString();
                selectCourse(key);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initData() {
        String url = Constant.BASE_DB_URL + "course/myList";
        String token = TokenUtil.getToken(getActivity());
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("fail", "okhttp请求失败");
                Toast.makeText(getActivity(), R.string.server_response_error, Toast.LENGTH_SHORT).show();
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
                        myCourseList.clear();
                        myCourseList_re.clear();
                        etSearch.setText("");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            CourseBean cb = objectMapper.readValue(jobj.toString(), CourseBean.class);
                            myCourseList.add(cb);
                            myCourseList_re.add(cb);
                        }
                        courseListAdapter.notifyDataSetChanged();
                        smartRefresh.finishRefresh();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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
    }

    /**
     * 课程列表筛选的方法，对大小写还有问题
     */
    private void selectCourse(String key) {
        myCourseList.clear();
        for (CourseBean cb : myCourseList_re) {
            if (cb.getCourse_name().contains(key)) {
                myCourseList.add(cb);
            }
        }
        courseListAdapter.notifyDataSetChanged();
    }


}
