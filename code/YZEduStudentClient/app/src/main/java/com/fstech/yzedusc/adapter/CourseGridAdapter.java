package com.fstech.yzedusc.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.CourseBean;
import com.fstech.yzedusc.util.DownloadTools;
import com.fstech.yzedusc.util.ImageUitl;
import com.fstech.yzedusc.util.ThreadUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

public class CourseGridAdapter extends BaseAdapter {
    private Context context;
    private List<CourseBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public QMUIRadiusImageView course_image;
        public TextView course_name;
        public TextView course_learn;
        public TextView course_sum;
    }

    public CourseGridAdapter(Context context, List<CourseBean> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = listContainer.inflate(R.layout.item_course_grid, null);
            // 获得控件对象
            vh.course_image = (QMUIRadiusImageView) convertView.findViewById(R.id.item_course_grid_iv_image);
            vh.course_name = (TextView) convertView.findViewById(R.id.item_course_grid_tv_name);
            vh.course_learn = (TextView) convertView.findViewById(R.id.item_course_grid_tv_learn);
            vh.course_sum = (TextView) convertView.findViewById(R.id.item_course_grid_tv_sum);
            // 设置空间集到convertView
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        CourseBean courseBean = listItems.get(position);
        final String str_course_iamge = courseBean.getCourse_cover();
        final String str_course_name = courseBean.getCourse_name();
        final String str_course_learn_student = courseBean.getCourse_learn_student() + "";
        final String str_course_sum = courseBean.getCourse_sum() + "";
        String str_course_sum_student = " / " + courseBean.getCourse_sum_student() + "";
        if (courseBean.getCourse_sum_student() == -1) str_course_sum_student = "";
        vh.course_name.setText(str_course_name);
        vh.course_learn.setText(str_course_learn_student + str_course_sum_student + " 人学习");
        vh.course_sum.setText("共 " + str_course_sum + " 课时");
        ImageUitl.showNetImage(vh.course_image,str_course_iamge);
        return convertView;
    }

}
