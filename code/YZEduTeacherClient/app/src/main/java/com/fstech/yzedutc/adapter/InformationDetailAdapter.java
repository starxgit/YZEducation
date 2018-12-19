package com.fstech.yzedutc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.InformationContentBean;
import com.fstech.yzedutc.util.ImageUitl;

import java.util.List;

/**
 * Created by shaoxin on 18-3-25.
 * 资讯列表的适配器
 */

public class InformationDetailAdapter extends BaseAdapter {

    private Context context;
    private List<InformationContentBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public TextView tv_text;
        public ImageView iv_img;
    }

    public InformationDetailAdapter(Context context, List<InformationContentBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_information_content, null);
            //获得控件对象
            vh.iv_img = (ImageView) convertView.findViewById(R.id.item_information_content_img);
            vh.tv_text = (TextView) convertView.findViewById(R.id.item_information_content_text);
            //设置空间集到convertView
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        InformationContentBean icb = listItems.get(position);

        String text = icb.getPlatform_content_text();
        final String imaUrl = icb.getPlatform_content_img();

        if (text == null || text.equals("")) {
            vh.tv_text.setVisibility(View.GONE);
        } else {
            vh.tv_text.setVisibility(View.VISIBLE);
            vh.tv_text.setText(text);
        }

        if (imaUrl == null || imaUrl.equals("")) {
            vh.iv_img.setVisibility(View.GONE);
        } else {
            vh.iv_img.setVisibility(View.VISIBLE);
            ImageUitl.showNetImage(vh.iv_img, imaUrl);
        }

        return convertView;
    }

}