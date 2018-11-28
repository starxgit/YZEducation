package com.fstech.yzedusc.adapter;

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

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.activity.MistakeDetailActivity;
import com.fstech.yzedusc.bean.MyExamBean;
import com.fstech.yzedusc.util.CallBackUtil;
import com.fstech.yzedusc.util.Constant;
import com.fstech.yzedusc.util.OkhttpUtil;
import com.fstech.yzedusc.util.TokenUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;


public class ExamAdapter extends BaseAdapter {
    private Context context;
    private List<MyExamBean> listItems;
    private LayoutInflater listContainer;
    private int isDo;
    private List<Map<String, Object>> answer_list;
    private int course_id;

    public final class ListItemView {
        public TextView tv_question;
        public RadioGroup rg_answer;
        public RadioButton rd_a;
        public RadioButton rd_b;
        public RadioButton rd_c;
        public RadioButton rd_d;
        public TextView tv_myans;
        public TextView tv_trueans;
        public LinearLayout ll_ans;
        public EditText et_tiankong;
        public TextView tv_cuo;
    }

    public ExamAdapter(Context context, List<MyExamBean> listItems, int isDo, int course_id,
                       List<Map<String, Object>> answer_list) {
        this.context = context;
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;
        this.isDo = isDo;
        this.answer_list = answer_list;
        this.course_id = course_id;
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
            lv.rg_answer = (RadioGroup) convertView.findViewById(R.id.rg_answer);
            lv.rd_a = (RadioButton) convertView.findViewById(R.id.radioButton1);
            lv.rd_b = (RadioButton) convertView.findViewById(R.id.radioButton2);
            lv.rd_c = (RadioButton) convertView.findViewById(R.id.radioButton3);
            lv.rd_d = (RadioButton) convertView.findViewById(R.id.radioButton4);
            lv.et_tiankong = (EditText) convertView.findViewById(R.id.et_tiankong);
            lv.tv_trueans = (TextView) convertView.findViewById(R.id.tv_trueans);
            lv.tv_myans = (TextView) convertView.findViewById(R.id.tv_myans);
            lv.ll_ans = (LinearLayout) convertView.findViewById(R.id.ll_ans);
            lv.tv_cuo = (TextView) convertView.findViewById(R.id.tv_cuo);
            //设置空间集到convertView

            final MyExamBean meb = listItems.get(position);   // 得到问题对象

            // 必定显示内容
            lv.tv_question.setText(position + 1 + "." + meb.getQuestion());  // 问题题目
            lv.tv_trueans.setText(meb.getAnswer());     // 正确答案
            lv.tv_myans.setText(meb.getStudent_ans());  // 我的答案
            lv.tv_cuo.setText(Constant.QUESTION_STATE[meb.getMy_exam_state()]); // 状态
            int examType = meb.getExam_type();
            switch (examType) {
                case 0:
                    // 选择题
                    lv.rg_answer.setVisibility(View.VISIBLE);
                    lv.et_tiankong.setVisibility(View.GONE);
                    lv.rd_a.setText("A." + meb.getOption1());
                    lv.rd_b.setText("B." + meb.getOption2());
                    lv.rd_c.setText("C." + meb.getOption3());
                    lv.rd_d.setText("D." + meb.getOption4());
                    break;
                case 1:
                    // 填空题
                    lv.rg_answer.setVisibility(View.GONE);
                    if (isDo == 0) {
                        lv.et_tiankong.setVisibility(View.VISIBLE);
                    } else {
                        lv.et_tiankong.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    // 主观题
                    lv.rg_answer.setVisibility(View.GONE);
                    if (isDo == 0) {
                        lv.et_tiankong.setVisibility(View.VISIBLE);
                    } else {
                        lv.et_tiankong.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }

            // 如果没有做过，答案栏不显示,输入框不显示
            if (isDo == 0) {
                lv.ll_ans.setVisibility(View.GONE);
                lv.tv_cuo.setVisibility(View.GONE);

            } else {
                lv.ll_ans.setVisibility(View.VISIBLE);
                lv.tv_cuo.setVisibility(View.VISIBLE);
            }

            // 选择题点击时的业务逻辑
            lv.rd_a.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer_list.get(position).clear();
                    answer_list.get(position).put("my_ans", "A");
                    answer_list.get(position).put("exam_id", meb.getExam_id());
                }
            });
            lv.rd_b.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer_list.get(position).clear();
                    answer_list.get(position).put("my_ans", "B");
                    answer_list.get(position).put("exam_id", meb.getExam_id());
                }
            });
            lv.rd_c.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer_list.get(position).clear();
                    answer_list.get(position).put("my_ans", "C");
                    answer_list.get(position).put("exam_id", meb.getExam_id());
                }
            });
            lv.rd_d.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer_list.get(position).clear();
                    answer_list.get(position).put("my_ans", "D");
                    answer_list.get(position).put("exam_id", meb.getExam_id());
                }
            });
            final ListItemView finalLv = lv;
            // 填空题或主观题输入动作
            lv.et_tiankong.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String str = finalLv.et_tiankong.getText().toString();
                    answer_list.get(position).clear();
                    answer_list.get(position).put("my_ans", str);
                    answer_list.get(position).put("exam_id", meb.getExam_id());
                }
            });
            // 错题可以添加到错题
            lv.tv_cuo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (meb.getMy_exam_state() == 2 || meb.getMy_exam_state() == 3) {
                        addToMistake(meb.getMy_exam_id());
                    }
                }
            });

            convertView.setTag(lv);
        } else {
            lv = (ListItemView) convertView.getTag();
        }
        return convertView;
    }

    private void addToMistake(int my_exam_id) {
        String token = TokenUtil.getToken((Activity) context);
        String url = Constant.BASE_DB_URL + "learn/addToMistake";
        Map<String, String> map = new HashMap<>();
        map.put("course_id", course_id + "");
        map.put("my_exam_id", my_exam_id + "");
        map.put("token", token);
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(context, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        Toast.makeText(context, R.string.add_to_mistake_success, Toast.LENGTH_SHORT).show();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}