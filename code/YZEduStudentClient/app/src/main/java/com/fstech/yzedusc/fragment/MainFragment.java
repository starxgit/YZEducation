package com.fstech.yzedusc.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.activity.CourseIntroduceActivity;
import com.fstech.yzedusc.activity.InformationDetailActivity;
import com.fstech.yzedusc.adapter.InformationListAdapter;
import com.fstech.yzedusc.bean.BannerBean;
import com.fstech.yzedusc.bean.InformationBean;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.DownloadTools;
import com.fstech.yzedusc.util.ImageUitl;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.util.ThreadUtil;
import com.fstech.yzedusc.view.MyListView;
import com.oragee.banners.BannerView;

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
 * 主页Fragment
 */

public class MainFragment extends Fragment {
    // 定义UI对象
    private BannerView vp_banner;
    private List<BannerBean> listItems_banner;
    private List<View> viewList;
    private MyListView lv_information;
    private InformationListAdapter adapter;
    private List<InformationBean> listItems_information;
    private ScrollView sv_main;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // banner 数据加载完成
                    case 0:
                        setBanner();
                        break;
                    // 资讯数据加载完成
                    case 1:
//                        Log.e("informationsize", listItems_information.size() + "");
                        adapter.notifyDataSetChanged();
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
        vp_banner = (BannerView) getActivity().findViewById(R.id.vp_banner);
        viewList = new ArrayList<View>();
        lv_information = (MyListView) getActivity().findViewById(R.id.lv_information);
        sv_main = (ScrollView) getActivity().findViewById(R.id.sv_main);
        listItems_information = new ArrayList<InformationBean>();
        adapter = new InformationListAdapter(getActivity(), listItems_information);
        lv_information.setAdapter(adapter);
        listItems_banner = new ArrayList<BannerBean>();

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
        getBanners();
        getInformations();
        lv_information.measure(0, 0);
    }

    /*
    * 配置轮播图
    * 无参数
    * 无返回
    * */
    private void setBanner() {

        for (int i = 0; i < listItems_banner.size(); i++) {
            BannerBean b = listItems_banner.get(i);
            final int type = b.getBanner_type();
            final String link = b.getBanner_link();
            final String banner_image = b.getBanner_image();
            final ImageView image = new ImageView(getActivity());
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            // 使用okhttp加载图片
            OkhttpUtil.okHttpGetBitmap(banner_image, new CallBackUtil.CallBackBitmap() {
                @Override
                public void onFailure(Call call, Exception e) {

                }
                @Override
                public void onResponse(Bitmap bitmap) {
                    image.setImageBitmap(bitmap);
                }
            });
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (type == 2) {
                        // 课程广告
                        Intent intent = new Intent(getActivity(), CourseIntroduceActivity.class);
                        intent.putExtra("course_id", link);
                        startActivity(intent);
                    } else if (type == 1) {
                        // 网页广告
                        Intent intent = new Intent(getActivity(), CourseIntroduceActivity.class);
                        intent.putExtra("link", link);
                        startActivity(intent);
                    } else if (type == 3) {
                        // 资讯广告
                        Intent intent = new Intent(getActivity(), CourseIntroduceActivity.class);
                        intent.putExtra("link", link);
                        startActivity(intent);
                    }
                }
            });
            viewList.add(image);
        }
        vp_banner.startLoop(true);
        vp_banner.setViewList(viewList);
    }

    /*
    * 获取平台资讯的数据
    * 无参数
    * 无返回
    * */
    private void getInformations() {
        String url = Constant.BASE_DB_URL + "platform/information";
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", "1");
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
                        // 返回正确的情况
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            InformationBean ib = objectMapper.readValue(jobj.toString(), InformationBean.class);
                            listItems_information.add(ib);
                        }
                        handler.sendMessage(handler.obtainMessage(1));
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("Json", e.getMessage());
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("error", e.getMessage());
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                    Log.e("error", e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("error", e.getMessage());
                }
            }
        });
    }

    /*
    * 获取banner的数据
    * 无参数
    * 无返回
    * */
    private void getBanners() {
        String url = Constant.BASE_DB_URL + "platform/banner";
        OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
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
                        // 返回正确的情况
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            BannerBean bannerBean = objectMapper.readValue(jobj.toString(), BannerBean.class);
                            listItems_banner.add(bannerBean);
                        }
                        handler.sendMessage(handler.obtainMessage(0));
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("Json", "创建Json对象失败");
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("Json", "JSON包装成对象失败");
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
