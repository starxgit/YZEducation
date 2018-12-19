package com.fstech.yzedutc.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.CommentAdapter;
import com.fstech.yzedutc.bean.CommunicationBean;
import com.fstech.yzedutc.bean.CommunicationCommentBean;
import com.fstech.yzedutc.util.CacheActivityUtil;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.ImageUitl;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.fstech.yzedutc.util.TokenUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by shaoxin on 11/29/18.
 */

public class DisscussDetail extends AppCompatActivity {
    private int lesson_id;
    private CommunicationBean communicationBean;
    // 定义UI对象
    private QMUIRadiusImageView iv_avatar;
    private TextView tv_name;
    private TextView tv_text;
    private TextView tv_time;
    private TextView tv_comment_num;
    private ListView lv_comment;
    private EditText editText;
    private QMUIRoundButton bnSubmit;
    private ProgressBar progressBar;
    private TextView tv_delete;
    private List<CommunicationCommentBean> listItems;
    private CommentAdapter adapter;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disscuss_detail);
        initView();
        initData();
        initAction();
        getCommentList();
    }

    private void initView() {
        iv_avatar = (QMUIRadiusImageView) findViewById(R.id.iv_avatar);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_comment_num = (TextView) findViewById(R.id.tv_comment_num);
        lv_comment = (ListView) findViewById(R.id.lv_comment);
        editText = (EditText) findViewById(R.id.editText);
        bnSubmit = (QMUIRoundButton) findViewById(R.id.bnSubmit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        listItems = new ArrayList<>();
        adapter = new CommentAdapter(DisscussDetail.this, listItems);
        lv_comment.setAdapter(adapter);
    }

    private void initData() {
        token = TokenUtil.getToken(DisscussDetail.this);
        Intent intent = getIntent();
        lesson_id = intent.getIntExtra("lesson_id", -1);
        communicationBean = (CommunicationBean) intent.getSerializableExtra("communication");
        ImageUitl.showNetImage(iv_avatar, communicationBean.getAvatar());
        tv_name.setText(communicationBean.getNick_name());
        tv_text.setText(communicationBean.getCommunication_content());
        tv_time.setText(communicationBean.getCommunication_time());
        tv_comment_num.setText(communicationBean.getComment_num() + "");
        int isMy = communicationBean.getIsMy();
        if (isMy == 1) {
            tv_delete.setVisibility(View.VISIBLE);
        } else {
            tv_delete.setVisibility(View.GONE);
        }
    }

    private void initAction() {
        // 提交评论
        bnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitComment();
            }
        });
        // 删除讨论
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new QMUIDialog.MessageDialogBuilder(DisscussDetail.this)
                        .setTitle("删除讨论")
                        .setMessage("确定要删除吗？")
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                delCommunication();
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });
        lv_comment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int isMy = listItems.get(position).getIsMy();
                if (isMy == 1) {
                    final int positionFn = position;
                    new QMUIDialog.MessageDialogBuilder(DisscussDetail.this)
                            .setTitle("删除评论")
                            .setMessage("确定要删除吗？")
                            .addAction("取消", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            })
                            .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    delMyComment(positionFn);
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });
    }

    /**
     * 提交我的评论的方法
     */
    private void submitComment() {
        String text = editText.getText().toString();
        String url = Constant.BASE_DB_URL + "communication/addComment";
        int communication_id = communicationBean.getCommunication_id();
        int reply_id = 0;
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("comment_content", text);
        map.put("communication_id", communication_id + "");
        map.put("reply_id", reply_id + "");
        progressBar.setVisibility(View.VISIBLE);
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(DisscussDetail.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        getCommentList();
                        editText.setText("");
                        Toast.makeText(DisscussDetail.this, "提交成功", Toast.LENGTH_SHORT).show();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(DisscussDetail.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    progressBar.setVisibility(View.GONE);
                    hideKeyBoard();
                }
            }
        });

    }

    /**
     * 删除这条讨论的方法
     */
    private void delCommunication() {
        String url = Constant.BASE_DB_URL + "communication/delCommunication";
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("communication_id", communicationBean.getCommunication_id() + "");
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(DisscussDetail.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        CacheActivityUtil.finishSingleActivityByClass(CourseDisscussActivity.class);
                        Intent intent = new Intent(DisscussDetail.this, CourseDisscussActivity.class);
                        intent.putExtra("lesson_id", lesson_id);
                        startActivity(intent);
                        finish();
                        Toast.makeText(DisscussDetail.this, "删除成功", Toast.LENGTH_SHORT).show();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(DisscussDetail.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 获取讨论列表的方法
     */
    private void getCommentList() {
        progressBar.setVisibility(View.VISIBLE);
        String url = Constant.BASE_DB_URL + "communication/commentList";
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("communication_id", communicationBean.getCommunication_id() + "");
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(DisscussDetail.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        listItems.clear();
                        // 返回正确的情况
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            CommunicationCommentBean ccb = objectMapper.readValue(jobj.toString(), CommunicationCommentBean.class);
                            listItems.add(ccb);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(DisscussDetail.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("Json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("Json", "JSON包装成对象失败");
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    Log.e("error", "NewCourseMapping异常");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("error", "IO异常" + e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 删除我的评论的方法
     */
    private void delMyComment(final int positionFn) {
        progressBar.setVisibility(View.VISIBLE);
        int comment_id = listItems.get(positionFn).getCommunication_comment_id();
        String url = Constant.BASE_DB_URL + "communication/delComment";
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("comment_id", comment_id + "");
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(DisscussDetail.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        listItems.remove(positionFn);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(DisscussDetail.this, "删除成功", Toast.LENGTH_SHORT).show();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(DisscussDetail.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    /**
     * 隐藏软键盘的方法
     */
    private void hideKeyBoard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }
}
