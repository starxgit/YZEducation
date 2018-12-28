package com.fstech.yzedusc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.MistakeBean;
import com.fstech.yzedusc.bean.TaskBean;
import com.fstech.yzedusc.util.Constant;

import java.util.List;

/**
 * Created by shaoxin on 18-4-11.
 * 任务列表的适配器
 */

public class MistakeListAdapter extends BaseAdapter {

    private Context context;
    private List<MistakeBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public TextView tv_type;
        public TextView tv_date;
        public TextView tv_title;
    }

    public MistakeListAdapter(Context context, List<MistakeBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_mistake, null);
            //获得控件对象
            vh.tv_type = (TextView) convertView.findViewById(R.id.item_mistake_tv_type);
            vh.tv_title = (TextView) convertView.findViewById(R.id.item_mistake_tv_question);
            vh.tv_date = (TextView) convertView.findViewById(R.id.item_mistake_tv_date);
            //设置空间集到convertView
            convertView.setTag(vh);

            MistakeBean mb = listItems.get(position);
            vh.tv_title.setText(position + 1 + "." + mb.getQuestion());
            vh.tv_date.setText(mb.getMistake_time().substring(0, 10));

            vh.tv_type.setText("○ " + Constant.EXAM_TYPE[mb.getExam_type()]);
            vh.tv_type.setBackgroundResource(Constant.LABLE_DRABLES[mb.getExam_type()]);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

}