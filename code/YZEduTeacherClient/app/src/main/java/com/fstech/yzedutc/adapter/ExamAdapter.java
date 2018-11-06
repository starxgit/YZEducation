package com.fstech.yzedutc.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fstech.yzedutc.R;


public class ExamAdapter extends BaseAdapter{
	private Context context;
	private List<Map<String,Object>> listItems;
	private List<Map<String,Object>> answers;
	private LayoutInflater listContainer;

	public final class ListItemView{
		public TextView tv_question;
		public RadioGroup rg_answer;
		public RadioButton rd_a;
		public RadioButton rd_b;
		public RadioButton rd_c;
		public RadioButton rd_d;
		public TextView tv_myans;
		public TextView tv_trueans;
		public LinearLayout ll_ans;
	}

	public ExamAdapter(Context context,List<Map<String,Object>> listItems,List<Map<String,Object>> answers) {
		this.context = context;
		listContainer = LayoutInflater.from(context);
		this.listItems = listItems;
		this.answers=answers;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ListItemView lv = null;
		if(convertView ==null){
			lv = new ListItemView();
			convertView = listContainer.inflate(R.layout.item_exam, null);
			//获得控件对象
			lv.tv_question=(TextView) convertView.findViewById(R.id.tv_question);
			lv.rg_answer=(RadioGroup) convertView.findViewById(R.id.rg_answer);
			lv.rd_a=(RadioButton) convertView.findViewById(R.id.radioButton1);
			lv.rd_b=(RadioButton) convertView.findViewById(R.id.radioButton2);
			lv.rd_c=(RadioButton) convertView.findViewById(R.id.radioButton3);
			lv.rd_d=(RadioButton) convertView.findViewById(R.id.radioButton4);
			lv.tv_trueans=(TextView) convertView.findViewById(R.id.tv_trueans);
			lv.tv_myans=(TextView) convertView.findViewById(R.id.tv_myans);
			lv.ll_ans=(LinearLayout) convertView.findViewById(R.id.ll_ans);
			//设置空间集到convertView
			String trueans=listItems.get(position).get("trueans").toString();
			if(trueans.equals("no")){
				lv.ll_ans.setVisibility(View.GONE);
			}else{
				lv.ll_ans.setVisibility(View.VISIBLE);
				lv.tv_trueans.setText(listItems.get(position).get("trueans").toString());
				lv.tv_myans.setText(listItems.get(position).get("myans").toString());
			}
			String question=listItems.get(position).get("question").toString();
			String A="A. "+listItems.get(position).get("A").toString();
			String B="B. "+listItems.get(position).get("B").toString();
			String C="C. "+listItems.get(position).get("C").toString();
			String D="D. "+listItems.get(position).get("D").toString();
			final String exam_id=listItems.get(position).get("exam_id").toString();
			lv.tv_question.setText(question);
			lv.rd_a.setText(A);
			lv.rd_b.setText(B);
			lv.rd_c.setText(C);
			lv.rd_d.setText(D);

			lv.rd_a.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					Log.e("A", A);
					answers.get(position).clear();
					answers.get(position).put("ms", "A");
					answers.get(position).put("exam_id", exam_id);
				}
			});
			lv.rd_b.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					Log.e("B", B);
					answers.get(position).clear();
					answers.get(position).put("ms", "B");
					answers.get(position).put("exam_id", exam_id);
				}
			});
			lv.rd_c.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					Log.e("C", C);
					answers.get(position).clear();
					answers.get(position).put("ms", "C");
					answers.get(position).put("exam_id", exam_id);
				}
			});
			lv.rd_d.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					Log.e("D", D);
					answers.get(position).clear();
					answers.get(position).put("ms", "D");
					answers.get(position).put("exam_id", exam_id);
				}
			});

			convertView.setTag(lv);
		}else{
			lv = (ListItemView) convertView.getTag();
		}
		return convertView;
	}

}