package com.fstech.yzedusc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.LessonBean;
import com.fstech.yzedusc.bean.LiveRoomBean;

import java.util.List;

/**
 * Created by shaoxin on 18-3-25.
 * 资讯列表的适配器
 */

public class LessonListAdapter extends BaseAdapter {

    private Context context;
    private List<LessonBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public TextView tv_lesson_title;
    }

    public LessonListAdapter(Context context, List<LessonBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_lesson, null);
            //获得控件对象
            vh.tv_lesson_title = (TextView) convertView.findViewById(R.id.item_lesson_tv_title);
            //设置空间集到convertView
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        LessonBean lb = listItems.get(position);
        vh.tv_lesson_title.setText(lb.getLesson_title());

        return convertView;
    }

}