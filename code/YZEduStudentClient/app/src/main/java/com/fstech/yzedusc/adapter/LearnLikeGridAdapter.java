package com.fstech.yzedusc.adapter;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fstech.yzedusc.R;


public class LearnLikeGridAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> listItems;
    private LayoutInflater listContainer;
    private final int draws[] = {R.drawable.lable_btn_blue_bg, R.drawable.lable_btn_pink_bg, R.drawable.lable_btn_qing_bg,
            R.drawable.lable_btn_yellow_bg, R.drawable.lable_btn_green_bg};

    public final class ListItemView {
        public TextView name;
    }

    public LearnLikeGridAdapter(Context context, List<Map<String, Object>> listItems) {
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

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView lv = null;
        if (convertView == null) {
            lv = new ListItemView();
            convertView = listContainer.inflate(R.layout.item_simple_line, null);
            lv.name = (TextView) convertView.findViewById(R.id.textline1);
            convertView.setTag(lv);
        } else {
            lv = (ListItemView) convertView.getTag();
        }

        lv.name.setPadding(30, 30, 30, 30);
        String chose = listItems.get(position).get("chose").toString();
        lv.name.setText(listItems.get(position).get("name").toString());
        if (chose.equals("1")) {
            lv.name.setBackgroundResource(draws[position % draws.length]);
            lv.name.setTextColor(R.color.text_black3);
        } else {
            lv.name.setBackgroundResource(R.drawable.lable_gray);
            lv.name.setTextColor(R.color.text_black3);
        }

        return convertView;
    }

}
