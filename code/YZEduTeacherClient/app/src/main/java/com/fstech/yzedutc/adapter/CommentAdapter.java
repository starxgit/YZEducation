package com.fstech.yzedutc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.CommunicationCommentBean;
import com.fstech.yzedutc.util.ImageUitl;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

/**
 * Created by shaoxin on 11/29/18.
 * 课程交流评论适配器
 */

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommunicationCommentBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public QMUIRadiusImageView iv_avatar;
        public TextView tv_name;
        public TextView tv_delete;
        public TextView tv_time;
        public TextView tv_text;
    }

    public CommentAdapter(Context context, List<CommunicationCommentBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_desscuss, null);
            //获得控件对象
            vh.iv_avatar = (QMUIRadiusImageView) convertView.findViewById(R.id.iv_avatar);
            vh.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            vh.tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
            vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            vh.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            //设置空间集到convertView
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        CommunicationCommentBean ccb = listItems.get(position);
        vh.tv_name.setText(ccb.getAuthor_name());
        vh.tv_time.setText(ccb.getCommunication_comment_time());
        vh.tv_text.setText(ccb.getCommunication_comment_content());
        ImageUitl.showNetImage(vh.iv_avatar, ccb.getAuthor_avatar());
        int isMy = ccb.getIsMy();
        if (isMy == 1) {
            vh.tv_delete.setVisibility(View.VISIBLE);
        } else {
            vh.tv_delete.setVisibility(View.GONE);
        }

        return convertView;
    }

}
