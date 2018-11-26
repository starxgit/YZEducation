package com.fstech.yzedusc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fstech.yzedusc.R;

import java.util.List;
import java.util.Map;


public class ExamAdapter2 extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> listItems;
    private LayoutInflater listContainer;

    public final class ListItemView {
        public TextView tv_question;
        public TextView rd_a;
        public TextView rd_b;
        public TextView rd_c;
        public TextView rd_d;
        public TextView tv_myans;
        public TextView tv_trueans;
        public LinearLayout ll_ans;
    }

    public ExamAdapter2(Context context, List<Map<String, Object>> listItems) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListItemView lv = null;
        if (convertView == null) {
            lv = new ListItemView();
            convertView = listContainer.inflate(R.layout.item_exam2, null);
            //获得控件对象
            lv.tv_question = (TextView) convertView.findViewById(R.id.tv_question);
            lv.tv_trueans = (TextView) convertView.findViewById(R.id.tv_trueans);
            lv.tv_myans = (TextView) convertView.findViewById(R.id.tv_myans);
            lv.rd_a = (TextView) convertView.findViewById(R.id.radioButton1);
            lv.rd_b = (TextView) convertView.findViewById(R.id.radioButton2);
            lv.rd_c = (TextView) convertView.findViewById(R.id.radioButton3);
            lv.rd_d = (TextView) convertView.findViewById(R.id.radioButton4);
            lv.ll_ans = (LinearLayout) convertView.findViewById(R.id.ll_ans);
            //设置空间集到convertView
            final String trueans = listItems.get(position).get("trueans").toString();
            final String myans = listItems.get(position).get("myans").toString();
            if (trueans.equals("no")) {
                lv.ll_ans.setVisibility(View.GONE);
            } else {
                lv.ll_ans.setVisibility(View.VISIBLE);
                lv.tv_trueans.setText(listItems.get(position).get("trueans").toString());
                lv.tv_myans.setText(listItems.get(position).get("myans").toString());
            }

            final String question = listItems.get(position).get("question").toString();
            final String A = listItems.get(position).get("A").toString();
            final String B = listItems.get(position).get("B").toString();
            final String C = listItems.get(position).get("C").toString();
            final String D = listItems.get(position).get("D").toString();
            final String exam_id = listItems.get(position).get("exam_id").toString();
            lv.tv_question.setText(question);
            lv.rd_a.setText(A);
            lv.rd_b.setText(B);
            lv.rd_c.setText(C);
            lv.rd_d.setText(D);

            convertView.setTag(lv);
        } else {
            lv = (ListItemView) convertView.getTag();
        }
        return convertView;
    }

}