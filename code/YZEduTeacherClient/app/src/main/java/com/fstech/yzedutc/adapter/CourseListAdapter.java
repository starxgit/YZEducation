package com.fstech.yzedutc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.CourseBean;
import com.fstech.yzedutc.util.DownloadTools;
import com.fstech.yzedutc.util.ImageUitl;
import com.fstech.yzedutc.util.ThreadUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

/**
 * Created by shaoxin on 18-3-29.
 * 课程列表的适配器
 */

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<CourseBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public QMUIRadiusImageView iv_course_image;
        public TextView tv_course_name;
        public TextView tv_learn_student;
        public TextView tv_sum_student;
        public TextView tv_course_sum;
        public TextView tv_course_price;
    }

    public CourseListAdapter(Context context, List<CourseBean> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = listContainer.inflate(R.layout.item_course_list, null);
            //获得控件对象
            vh.iv_course_image = convertView.findViewById(R.id.item_course_list_iv_image);
            vh.tv_course_name = convertView.findViewById(R.id.item_course_list_tv_name);
            vh.tv_learn_student = convertView.findViewById(R.id.item_course_list_tv_learn_student);
            vh.tv_sum_student = convertView.findViewById(R.id.item_course_list_tv_sum_student);
            vh.tv_course_sum = convertView.findViewById(R.id.item_course_list_tv_sum);
            vh.tv_course_price = convertView.findViewById(R.id.item_course_list_tv_price);
            //设置空间集到convertView
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        CourseBean cb = listItems.get(position);
        String course_name = cb.getCourse_name();
        String course_learn_student = cb.getCourse_learn_student() + " 人学习";
        String course_sum_student = "容量: " + cb.getCourse_sum_student() + " 人";
        if (cb.getCourse_sum_student() == -1) course_sum_student = "";
        String course_sum = "共 " + cb.getCourse_sum() + " 课时";
        String course_price = "免费";
        if (cb.getCourse_price() > 0) course_price = "¥ " + cb.getCourse_price();
        final String course_cover = cb.getCourse_cover();
        vh.tv_course_name.setText(course_name);
        vh.tv_learn_student.setText(course_learn_student);
        vh.tv_sum_student.setText(course_sum_student);
        vh.tv_course_sum.setText(course_sum);
        vh.tv_course_price.setText(course_price);

        final ViewHolder finalVh = vh;
        ThreadUtil.runInThread(new Runnable() {
            @Override
            public void run() {
                int state = DownloadTools.downloadImg(course_cover);
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageUitl.SimpleShowImage(course_cover, finalVh.iv_course_image);
                    }
                });
            }
        });

        return convertView;
    }

}