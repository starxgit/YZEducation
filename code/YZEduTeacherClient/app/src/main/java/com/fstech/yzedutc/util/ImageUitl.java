package com.fstech.yzedutc.util;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

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
}
