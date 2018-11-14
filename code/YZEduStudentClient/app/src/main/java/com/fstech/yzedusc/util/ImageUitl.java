package com.fstech.yzedusc.util;

import java.io.File;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import okhttp3.Call;
import okhttp3.Response;

public class ImageUitl {
    private static String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    public static void SimpleShowImage(String url, ImageView iv) {
        String path = SDPATH + "yzedu/images" + File.separator + url;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bm = BitmapFactory.decodeFile(path, options);
        iv.setImageBitmap(bm);
    }

    public static void SimpleShowImage(String url, QMUIRadiusImageView iv) {
        String path = SDPATH + "yzedu/images" + File.separator + url;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bm = BitmapFactory.decodeFile(path, options);
        iv.setImageBitmap(bm);
    }

    /**
     * 显示图片缓存的工具类
     * 先查看本地缓存，如果没有再请求网络
     *
     * @param iv  要显示那个组件
     * @param url 图片地址
     **/
    public static void showNetImage(final ImageView iv, final String url) {
        Bitmap bm = LruCacheUtils.getInstance().get(url);
        if (bm == null) {
            OkhttpUtil.okHttpGetBitmap(url, new CallBackUtil.CallBackBitmap() {
                @Override
                public void onFailure(Call call, Exception e) {

                }

                @Override
                public void onResponse(Bitmap bm) {
                    LruCacheUtils.getInstance().addBitmapToMemoryCache(url, bm);
                    iv.setImageBitmap(bm);
                }
            });
        } else {
            iv.setImageBitmap(bm);
        }

    }

}
