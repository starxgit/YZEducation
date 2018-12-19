package com.fstech.yzedutc.adapter;
import java.util.List;
import java.util.Map;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.ClassificationBean;

public class TabAdapter extends BaseAdapter{
	private Context context;
	private List<ClassificationBean> listItems;
	private LayoutInflater listContainer;
	public int mSelect = -1;   //选中项

	public TabAdapter(Context context,List<ClassificationBean> listItems) {
		this.context = context;
		listContainer = LayoutInflater.from(context);
		this.listItems = listItems;
	}

	public final class ListItemView{
		public TextView cfa_name;
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
		ListItemView lv = null;
		if(convertView ==null){
			lv = new ListItemView();
			convertView = listContainer.inflate(R.layout.item_tab, null);
			//获得控件对象
			lv.cfa_name=(TextView)convertView.findViewById(R.id.cfa_name);
			//设置空间集到convertView
			convertView.setTag(lv);
		}else{
			lv = (ListItemView) convertView.getTag();
		}
		final String name=listItems.get(position).getClassification_name();
		lv.cfa_name.setText(name);
		if(mSelect==position){
			lv.cfa_name.setTextColor(0xff1B88EE);
		}else{
			lv.cfa_name.setTextColor(0xff333333);
		}
		return convertView;
	}

	public void changeSelected(int positon){ //刷新方法
		if(positon != mSelect){
			mSelect = positon;
			notifyDataSetChanged();
		}
	}


}