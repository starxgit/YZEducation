package com.fstech.yzedutc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.AnnouncementListAdapter;
import com.fstech.yzedutc.adapter.InformationListAdapter;
import com.fstech.yzedutc.bean.AnnouncementBean;
import com.fstech.yzedutc.bean.InformationBean;
import com.fstech.yzedutc.view.MyListView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_school, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
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
    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        tv_school_name.setText("高等院校01");

        InformationBean ib = new InformationBean(1, "学院开设新的大数据课程",
                "响应大数据的发展,我们院校将开设大数据的课程。", "2018-04-13", "570360ca00010eeb06000338-240-135.jpg", null);
        InformationBean ib2 = new InformationBean(2, "阿里云助力弹性伸缩服务",
                "阿里云助力弹性伸缩服务，同学们可以借此机会学习这类课程，深入了解一下弹性伸缩服务。", "2018-04-10", "57466be500018b8006000338-240-135.jpg", null);
        InformationBean ib3 = new InformationBean(3, "关于评选先进团支部的通知",
                "我们学院将组织评选2017年度的优秀团支部，希望各个团支部都踊跃报名。", "2018-04-09", null, null);
        listItems_information.add(ib);
        listItems_information.add(ib2);
        listItems_information.add(ib3);


        AnnouncementBean ab = new AnnouncementBean(1, "大三学生体测通知", "教育部将与4月17号对我校的大三学生进行体质考察抽测，希望大家做好充分准备。", 1,
                "2018-04-05");
        AnnouncementBean ab1 = new AnnouncementBean(2, "停水通知", "由于学校所在路线的水管维修修。明天8点到20点停水，请同学们做好水源储备。", 0,
                "2018-04-01");
        AnnouncementBean ab2 = new AnnouncementBean(3, "开发选课平台通知", "第二轮选课将与3月29到4月1号开启，希望大家根据自己的学分情况进行选课。", 0,
                "2018-03-29");
        AnnouncementBean ab3 = new AnnouncementBean(4, "大学生CET4/CET6考试报名通知", "大学生英语CET4/CET6考试报名已经开设了，请要参加的同学尽快完成考试报名。", 0,
                "2018-03-25");
        listItems_announcement.add(ab);
        listItems_announcement.add(ab1);
        listItems_announcement.add(ab2);
        listItems_announcement.add(ab3);
    }

}
