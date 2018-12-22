package com.fstech.yzedutc.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.fstech.yzedutc.R;
import com.fstech.yzedutc.bean.MyExamBean;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.util.TokenUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;


public class ExamAdapter extends BaseAdapter {
    private Context context;
    private List<MyExamBean> listItems;
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
        private LinearLayout rg_answer;
    }

    public ExamAdapter(Context context, List<MyExamBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.item_exam, null);
            //获得控件对象
            lv.tv_question = (TextView) convertView.findViewById(R.id.tv_question);
            lv.rd_a = (TextView) convertView.findViewById(R.id.radioButton1);
            lv.rd_b = (TextView) convertView.findViewById(R.id.radioButton2);
            lv.rd_c = (TextView) convertView.findViewById(R.id.radioButton3);
            lv.rd_d = (TextView) convertView.findViewById(R.id.radioButton4);
            lv.tv_trueans = (TextView) convertView.findViewById(R.id.tv_trueans);
            lv.tv_myans = (TextView) convertView.findViewById(R.id.tv_myans);
            lv.ll_ans = (LinearLayout) convertView.findViewById(R.id.ll_ans);
            lv.rg_answer = (LinearLayout) convertView.findViewById(R.id.rg_answer);
            //设置空间集到convertView
            convertView.setTag(lv);
        } else {
            lv = (ListItemView) convertView.getTag();
        }
        final MyExamBean meb = listItems.get(position);   // 得到问题对象
        if (meb.getExam_type() == 0) {
            lv.rg_answer.setVisibility(View.VISIBLE);
            lv.rd_a.setText("A. " + meb.getOption1());
            lv.rd_b.setText("B. " + meb.getOption2());
            lv.rd_c.setText("C. " + meb.getOption3());
            lv.rd_d.setText("D. " + meb.getOption4());
        } else {
            lv.rg_answer.setVisibility(View.GONE);
        }
        // 必定显示内容
        lv.tv_question.setText(position + 1 + "." + meb.getQuestion());  // 问题题目
        lv.tv_trueans.setText(meb.getAnswer());     // 正确答案
        lv.tv_myans.setText(meb.getStudent_ans() + meb.getMy_exam_state());  // 完成情况
        return convertView;
    }

}