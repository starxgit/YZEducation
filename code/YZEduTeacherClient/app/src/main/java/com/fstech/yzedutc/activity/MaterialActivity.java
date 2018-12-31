package com.fstech.yzedutc.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.MaterialListAdapter;
import com.fstech.yzedutc.bean.MaterialBean;
import com.fstech.yzedutc.util.CacheActivityUtil;
import com.fstech.yzedutc.util.CallBackUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.IntentDocumentView;
import com.fstech.yzedutc.util.OkDownloadUtil;
import com.fstech.yzedutc.util.OkhttpUtil;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by shaoxin on 18-4-11.
 * 资料下载页
 */

public class MaterialActivity extends AppCompatActivity {
    private ListView lv_material;
    private List<MaterialBean> listItems;
    private TextView tv_upload;
    private MaterialListAdapter adapter;
    private String course_id;
    private ProgressBar progressBar;
    private Handler handler;
    private LinearLayout ll_jindu;
    private ProgressBar pb_jindu;
    private TextView tv_jindu;
    private int downloadProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        initView();
        initData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // 下载完成
                    case 1:
                        ll_jindu.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
//                        Log.e("download","success");
                        Toast.makeText(MaterialActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        break;
                    // 下载中
                    case 2:
                        int progress = msg.getData().getInt("progress");
                        if (downloadProgress != progress) {
                            pb_jindu.setProgress(progress);
                            tv_jindu.setText("已下载： " + progress + "%");
                            Log.e("download", progress + "");
                            downloadProgress = progress;
                        }
                        break;
                    // 下载失败
                    case 3:
                        ll_jindu.setVisibility(View.GONE);
                        Log.e("download", "fail");
                        Toast.makeText(MaterialActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initView() {
        downloadProgress = 0;
        CacheActivityUtil.addActivity(MaterialActivity.this);
        lv_material = (ListView) findViewById(R.id.lv_material);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv_upload = (TextView) findViewById(R.id.tv_upload);
        ll_jindu = (LinearLayout) findViewById(R.id.ll_jindu);
        ll_jindu.setVisibility(View.GONE);
        pb_jindu = (ProgressBar) findViewById(R.id.pb_jindu);
        tv_jindu = (TextView) findViewById(R.id.tv_jindu);
        tv_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaterialActivity.this, UploadMaterialActivity.class);
                intent.putExtra("course_id", course_id);
                startActivity(intent);
            }
        });
        listItems = new ArrayList<>();
        adapter = new MaterialListAdapter(MaterialActivity.this, listItems);
        lv_material.setAdapter(adapter);
        // 长按删除
        lv_material.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDel(position);
                return false;
            }
        });
        // 点击下载
        lv_material.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                download(position);
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        course_id = intent.getStringExtra("course_id");
        setMaterialList();
    }

    private void setMaterialList() {
        setLock();
        String url = Constant.BASE_DB_URL + "material/list";
        Map<String, String> map = new HashMap<>();
        map.put("course_id", course_id);
        OkhttpUtil.okHttpGet(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(MaterialActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
                releaseLock();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        JSONArray jsonArray = jsonObject.getJSONArray("return_data");
                        ObjectMapper objectMapper = new ObjectMapper();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jobj = jsonArray.getJSONObject(i);
                            MaterialBean mb = objectMapper.readValue(jobj.toString(), MaterialBean.class);
                            listItems.add(mb);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(MaterialActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    Log.e("json", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    Log.e("Mapping", e.getLocalizedMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("IO", e.getLocalizedMessage());
                    e.printStackTrace();
                } finally {
                    releaseLock();
                }
            }
        });
    }

    private void setLock() {
        progressBar.setVisibility(View.VISIBLE);
        lv_material.setVisibility(View.GONE);
    }

    private void releaseLock() {
        progressBar.setVisibility(View.GONE);
        lv_material.setVisibility(View.VISIBLE);
    }

    /**
     * 显示删除一个资料的方法
     */
    private void showDel(final int position) {
        String materialName = listItems.get(position).getMaterial_name();
        new QMUIDialog.MessageDialogBuilder(MaterialActivity.this)
                .setTitle("删除资料")
                .setMessage("确定要删除" + materialName + "吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        delMaterial(position);
                        dialog.dismiss();
                    }
                })
                .show();
    }


    /**
     * 删除资料的方法
     */
    private void delMaterial(final int position) {
        int material_id = listItems.get(position).getMaterial_id();
        progressBar.setVisibility(View.VISIBLE);
        Map<String, String> map = new HashMap<>();
        map.put("material_id", material_id + "");
        String url = Constant.BASE_DB_URL + "material/del";
        OkhttpUtil.okHttpPost(url, map, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(MaterialActivity.this, R.string.server_response_error, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int result_code = jsonObject.getInt("result_code");
                    if (result_code == 0) {
                        Toast.makeText(MaterialActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        listItems.remove(position);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MaterialActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 下载资料
     *
     * @param position
     */
    private void download(int position) {
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "yzedu/others/";
        MaterialBean mb = listItems.get(position);
        // 先判断是否存在，如果存在直接打开
        File file = new File(sdPath + mb.getMaterial_name());
        if (file != null && file.length() > 0) {
            Log.e("存在", "打开" + file.getAbsolutePath());
            openFile(file.getAbsolutePath());
        } else {
            Log.e("下载", mb.getMaterial_name() + "," + mb.getMaterial_url());
            String url = "yzedu/others";
            ll_jindu.setVisibility(View.VISIBLE);
            OkDownloadUtil.get().download(mb.getMaterial_url(), url, mb.getMaterial_name(), new OkDownloadUtil.OnDownloadListener() {
                @Override
                public void onDownloadSuccess() {
                    handler.sendMessage(handler.obtainMessage(1));
                }

                @Override
                public void onDownloading(int progress) {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("progress", progress);
                    message.setData(bundle);
                    message.what = 2;
                    handler.sendMessage(message);
                }

                @Override
                public void onDownloadFailed() {
                    handler.sendMessage(handler.obtainMessage(3));
                }
            });
        }

    }

    /**
     * 打开文件的方法
     */
    private void openFile(String path) {
        String format = path.substring(path.lastIndexOf(".") + 1);
        Intent intent = null;
        if(format.equals("doc")||format.equals("docx")){
            intent= IntentDocumentView.getWordFileIntent(path);
        }else if(format.equals("xls")||format.equals("xlsx")){
            intent = IntentDocumentView.getExcelFileIntent(path);
        }else if(format.equals("pdf")){
            intent = IntentDocumentView.getPdfFileIntent(path);
        }else if(format.equals("jpg")||format.equals("png")||format.equals("gif")){
            intent = IntentDocumentView.getPicturefFileIntent(path);
        }else if(format.equals("mp4")||format.equals("avi")||format.equals("wma")||format.equals("mp3")){
            intent = IntentDocumentView.getVediofFileIntent(path);
        }
        startActivity(intent);
    }

    /*
    * 返回上一级
    * xml布局文件里面调用
    * */
    public void back(View view) {
        finish();
    }

}
