package com.fstech.yzedutc.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

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
}
