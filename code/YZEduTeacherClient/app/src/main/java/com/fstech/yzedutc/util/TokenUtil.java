package com.fstech.yzedutc.util;

import android.app.Activity;

import com.fstech.yzedutc.application.YZEduApplication;

/**
 * Created by shaoxin on 11/17/18.
 * 便捷获取token的工具类
 */

public class TokenUtil {

    public static String getToken(Activity activity){
        String token = "-1";
        YZEduApplication application = (YZEduApplication)activity.getApplication();
        if(application.getToken()!=null){
            token = application.getToken();
        }
        return token;
    }
}
