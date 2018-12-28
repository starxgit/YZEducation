package com.fstech.yzedusc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.AnnouncementBean;
import com.fstech.yzedusc.bean.InformationBean;

import java.util.List;

/**
 * Created by shaoxin on 18-3-25.
 * 资讯列表的适配器
 */

public class AnnouncementListAdapter extends BaseAdapter {

    private Context context;
    private List<AnnouncementBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public TextView tv_isstick;
        public TextView tv_announcement_title;
        public TextView tv_announcement_date;
        public TextView tv_announcement_content;
    }

    public AnnouncementListAdapter(Context context, List<AnnouncementBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_announcement, null);
            //获得控件对象
            vh.tv_isstick = (TextView) convertView.findViewById(R.id.item_announcement_tv_istick);
            vh.tv_announcement_title = (TextView) convertView.findViewById(R.id.item_announcement_tv_title);
            vh.tv_announcement_content = (TextView) convertView.findViewById(R.id.item_announcement_tv_content);
            vh.tv_announcement_date = (TextView) convertView.findViewById(R.id.item_announcement_tv_date);
            //设置空间集到convertView
            convertView.setTag(vh);

            AnnouncementBean ab = listItems.get(position);

            int is_stick = ab.getAnnouncement_stick();
            if (is_stick == 1) {
                vh.tv_isstick.setVisibility(View.VISIBLE);
            } else {
                vh.tv_isstick.setVisibility(View.GONE);
            }

            vh.tv_announcement_date.setText(ab.getAnnouncement_date().substring(0, 10));
            vh.tv_announcement_title.setText(ab.getAnnouncement_title());
            vh.tv_announcement_content.setText(ab.getAnnouncement_content());
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

}