package com.fstech.yzedutc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.TaskBean;

import java.util.List;

/**
 * Created by shaoxin on 18-4-11.
 * 任务列表的适配器
 */

public class TaskListAdapter extends BaseAdapter {

    private Context context;
    private List<TaskBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public TextView tv_type;
        public TextView tv_state;
        public TextView tv_name;
        public TextView tv_start_time;
        public TextView tv_end_time;
        public TextView tv_publish;
    }

    public TaskListAdapter(Context context, List<TaskBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_task, null);
            //获得控件对象
            vh.tv_type = (TextView) convertView.findViewById(R.id.item_task_tv_type);
            vh.tv_state = (TextView) convertView.findViewById(R.id.item_task_tv_state);
            vh.tv_name = (TextView) convertView.findViewById(R.id.item_task_tv_name);
            vh.tv_start_time = (TextView) convertView.findViewById(R.id.item_task_tv_start_time);
            vh.tv_end_time = (TextView) convertView.findViewById(R.id.item_task_tv_end_time);
            vh.tv_publish = (TextView) convertView.findViewById(R.id.item_task_tv_publish);
            //设置空间集到convertView
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        TaskBean tb = listItems.get(position);
        if (tb.getTask_type() == 1) {
            vh.tv_type.setText("○ 练习任务");
            vh.tv_type.setBackgroundResource(R.drawable.lable_btn_qing_bg);
        } else {
            vh.tv_type.setText("○ 实训任务");
            vh.tv_type.setBackgroundResource(R.drawable.lable_btn_pink_bg);
        }
        switch (tb.getTask_state()) {
            case 1:
                vh.tv_state.setText("未开始");
                break;
            case 2:
                vh.tv_state.setText("进行中");
                break;
            case 3:
                vh.tv_state.setText("批改中");
                break;
            case 4:
                vh.tv_state.setText("已结束");
                break;
            default:
                break;
        }
        vh.tv_start_time.setText(tb.getTask_start_time());
        vh.tv_end_time.setText(tb.getTask_end_time());
        vh.tv_name.setText(tb.getTask_name());
        vh.tv_publish.setText("publish by " + tb.getTask_publish() + " On " + tb.getTask_create_time());
        return convertView;
    }

}