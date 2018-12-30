package com.fstech.yzedutc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.InformationBean;
import com.fstech.yzedutc.bean.MaterialBean;
import com.fstech.yzedutc.util.ImageUitl;

import java.util.List;

/**
 * Created by shaoxin on 18-3-25.
 * 资讯列表的适配器
 */

public class MaterialListAdapter extends BaseAdapter {

    private Context context;
    private List<MaterialBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public TextView tv_material_type;
        public TextView tv_material_name;
        public TextView tv_material_time;
    }

    public MaterialListAdapter(Context context, List<MaterialBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_material, null);
            //获得控件对象
            vh.tv_material_type = (TextView) convertView.findViewById(R.id.tv_material_type);
            vh.tv_material_name = (TextView) convertView.findViewById(R.id.tv_material_name);
            vh.tv_material_time = (TextView) convertView.findViewById(R.id.tv_material_time);
            //设置空间集到convertView
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        // 获取并设置数据
        MaterialBean mb = listItems.get(position);
        vh.tv_material_name.setText(mb.getMaterial_name());
        vh.tv_material_time.setText(mb.getMaterial_time().substring(0, 10));
        return convertView;
    }

}