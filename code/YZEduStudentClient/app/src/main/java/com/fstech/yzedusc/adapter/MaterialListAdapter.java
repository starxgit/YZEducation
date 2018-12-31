package com.fstech.yzedusc.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.bean.MaterialBean;

import java.io.File;
import java.util.List;

/**
 * Created by shaoxin on 18-3-25.
 * 资讯列表的适配器
 */

public class MaterialListAdapter extends BaseAdapter {

    public static final String IMAGETYPES ="jpg,jpeg,jpge,png";
    public static final String VEDIOTYPES = "mp4";
    public static final String AUDIOTYPES = "mp3,wma";

    private Context context;
    private List<MaterialBean> listItems;
    private LayoutInflater listContainer;

    public final class ViewHolder {
        public TextView tv_material_type;
        public TextView tv_material_name;
        public TextView tv_material_time;
        public TextView tv_download;
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
            vh.tv_download = (TextView)convertView.findViewById(R.id.tv_download);
            //设置空间集到convertView
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        // 获取并设置数据
        MaterialBean mb = listItems.get(position);
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"yzedu/others/";
        File file=new File(sdPath+mb.getMaterial_name());
        if(file!=null&&file.length()>0){
            vh.tv_download.setText("查看");
        }else{
            vh.tv_download.setText("下载");
        }
        String type = mb.getMaterial_name().substring(mb.getMaterial_name().lastIndexOf(".")+1);
        if(IMAGETYPES.contains(type)){
            vh.tv_material_type.setText("○ 图像文件");
            vh.tv_material_type.setBackgroundResource(R.drawable.lable_btn_blue_bg);
        }else if(VEDIOTYPES.contains(type)){
            vh.tv_material_type.setText("○ 视频文件");
            vh.tv_material_type.setBackgroundResource(R.drawable.lable_btn_qing_bg);
        }else if(AUDIOTYPES.contains(type)){
            vh.tv_material_type.setText("○ 音频文件");
            vh.tv_material_type.setBackgroundResource(R.drawable.lable_btn_pink_bg);
        }else{
            vh.tv_material_type.setText("○ 其他文件");
            vh.tv_material_type.setBackgroundResource(R.drawable.lable_btn_yellow_bg);
        }

        vh.tv_material_name.setText(mb.getMaterial_name());
        vh.tv_material_time.setText(mb.getMaterial_time().substring(0, 10));
        // 释放变量
        file = null;
        sdPath = null;
        return convertView;
    }

}