package com.fstech.yzedusc.adapter;


import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.activity.CourseIntroduceActivity;
import com.fstech.yzedusc.util.DownloadTools;
import com.fstech.yzedusc.util.ImageUitl;
import com.fstech.yzedusc.util.OutlineContainer;
import com.fstech.yzedusc.util.ThreadUtil;
import com.fstech.yzedusc.view.JazzyViewPager;


public class DiscoverAdapter extends PagerAdapter{
	private Context context;
	private List<Map<String,Object>> listItems;
    private LayoutInflater listContainer;
    private JazzyViewPager vp;
    
    public final class HoderView{
		public ImageView course_image;
		public TextView course_name;
		public Button btn_take;
		public TextView course_pnum;
	}
    
    public DiscoverAdapter(Context context,List<Map<String,Object>> listItems,JazzyViewPager vp) {
    	this.context = context;
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;
        this.vp=vp;
    }
    
    @Override
	public Object instantiateItem(ViewGroup container, final int position)
	{
		RelativeLayout re=(RelativeLayout) container.inflate(context, R.layout.item_course_discover, null);
		final HoderView hv=new HoderView();
		hv.course_image=(ImageView)re.findViewById(R.id.iv_course_img);
		hv.btn_take=(Button)re.findViewById(R.id.btn_take);
		hv.course_name=(TextView)re.findViewById(R.id.tv_course_name);
		hv.course_pnum=(TextView)re.findViewById(R.id.tv_learn_pnum);
		
		hv.course_name.setText(listItems.get(position).get("course_name").toString());
		hv.course_pnum.setText(listItems.get(position).get("course_pnum").toString());
		final String img=listItems.get(position).get("course_img").toString();
		// 显示图片
        ImageUitl.showNetImage(hv.course_image,img);
		hv.btn_take.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(context,CourseIntroduceActivity.class);
				i.putExtra("course_id", listItems.get(position).get("course_id")+"");
				context.startActivity(i);
			}
		});
		
		container.addView(re, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		vp.setObjectForPosition(re, position);
		return re;
	}

    @Override
	public void destroyItem(ViewGroup container, int position, Object obj)
	{
		container.removeView(vp.findViewFromObject(position));
	}

	@Override
	public int getCount()
	{
		return listItems.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj)
	{
		if (view instanceof OutlineContainer)
		{
			return ((OutlineContainer) view).getChildAt(0) == obj;
		} else
		{
			return view == obj;
		}
	}
	
}
