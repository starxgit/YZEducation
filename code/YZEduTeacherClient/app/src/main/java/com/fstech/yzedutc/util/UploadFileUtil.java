package com.fstech.yzedutc.util;

import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by shaoxin on 12/25/18.
 * 上传文件的工具类
 */

public class UploadFileUtil {
    /**
     * 上传文件
     *
     * @param url
     * @param path 文件路径
     * @return 新图片的路径
     * @throws IOException
     * @throws JSONException
     */
    public static String uploadImage(String url, String path) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.e("path", path);
        File file = new File(path);
        RequestBody image = RequestBody.create(MediaType.parse("file"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", path, image)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseStr = response.body().string();
        return responseStr;
    }
//        JSONObject jsonObject = new JSONObject(response.body().string());
//        return jsonObject.optString("image");


    public static void uploadImageP(String url, String path, final ProgressBar progressBar) throws IOException {
        File file = new File(path);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("thumb", file.getName(),
                RequestBody.create(MediaType.parse("file/*"), file));
        Request.Builder request = new Request.Builder().url(url)
                .post(new CmlRequestBody(builder.build()) {
                    @Override
                    public void loading(long current, long total, boolean done) {
//                        Log.e("percent", (double)current / (double)total+"");
                        if (!done) {
                            double percent = (double)current / (double)total;
                            Log.e("percent", percent+"");
                            progressBar.setProgress((int)(percent*100));
                        }
                    }
                });

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("fail","fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                Log.e("res",responseStr);
            }

        });
    }
}
