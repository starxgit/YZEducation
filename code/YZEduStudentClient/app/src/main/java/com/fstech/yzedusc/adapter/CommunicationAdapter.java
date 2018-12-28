package com.fstech.yzedusc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.CommunicationBean;
import com.fstech.yzedusc.bean.TaskBean;
import com.fstech.yzedusc.util.ImageUitl;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

/**
 * Created by shaoxin on 18-11-28.
 * 课程交流列表的适配器
 */

public class CommunicationAdapter extends BaseAdapter {

    private Context context;
    private List<CommunicationBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public QMUIRadiusImageView iv_avatar;
        public TextView tv_name;
        public TextView tv_text;
        public TextView tv_time;
        public TextView tv_comment_num;
    }

    public CommunicationAdapter(Context context, List<CommunicationBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_communication, null);
            //获得控件对象
            vh.iv_avatar = (QMUIRadiusImageView) convertView.findViewById(R.id.iv_avatar);
            vh.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            vh.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            vh.tv_comment_num = (TextView) convertView.findViewById(R.id.tv_comment_num);
            //设置空间集到convertView
            convertView.setTag(vh);
            CommunicationBean cb = listItems.get(position);
            vh.tv_name.setText(cb.getNick_name());
            vh.tv_text.setText(cb.getCommunication_content());
            vh.tv_time.setText(cb.getCommunication_time());
            vh.tv_comment_num.setText(cb.getComment_num() + "");
            ImageUitl.showNetImage(vh.iv_avatar, cb.getAvatar());
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

}