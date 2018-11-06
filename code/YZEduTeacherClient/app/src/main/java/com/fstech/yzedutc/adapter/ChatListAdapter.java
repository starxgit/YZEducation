package com.fstech.yzedutc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.ChatBean;

import java.util.List;

/**
 * Created by shaoxin on 18-3-29.
 * 聊天列表的适配器
 */

public class ChatListAdapter extends BaseAdapter {

    private Context context;
    private List<ChatBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public TextView tv_user_in;
        public TextView tv_content_in;
        public TextView tv_user_out;
        public TextView tv_content_out;
    }

    public ChatListAdapter(Context context, List<ChatBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_chat, null);
            //获得控件对象
            vh.tv_user_in = (TextView) convertView.findViewById(R.id.item_chart_tv_user_in);
            vh.tv_content_in = (TextView) convertView.findViewById(R.id.item_chart_tv_content_in);
            vh.tv_content_out = (TextView) convertView.findViewById(R.id.item_chart_tv_content_out);
            vh.tv_user_out = (TextView) convertView.findViewById(R.id.item_chart_tv_user_out);
            //设置空间集到convertView
            convertView.setTag(vh);

            vh.tv_user_in.setText("");
            vh.tv_content_in.setText("");
            vh.tv_user_out.setText("");
            vh.tv_content_out.setText("");
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        ChatBean cb = listItems.get(position);

        if (cb.getUser_in() != null) {
            vh.tv_user_in.setVisibility(View.VISIBLE);
            vh.tv_content_in.setVisibility(View.VISIBLE);
            vh.tv_user_out.setVisibility(View.GONE);
            vh.tv_content_out.setVisibility(View.GONE);
            vh.tv_user_in.setText(cb.getUser_in() + " ： ");
        }else{
            vh.tv_user_in.setVisibility(View.GONE);
            vh.tv_content_in.setVisibility(View.GONE);
            vh.tv_user_out.setVisibility(View.VISIBLE);
            vh.tv_content_out.setVisibility(View.VISIBLE);
        }
        if (cb.getUser_out() != null)
            vh.tv_user_out.setText(" ： " + cb.getUser_out());
        if (cb.getContent_in() != null)
            vh.tv_content_in.setText(cb.getContent_in());
        if (cb.getContent_out() != null)
            vh.tv_content_out.setText(cb.getContent_out());

        return convertView;
    }

}