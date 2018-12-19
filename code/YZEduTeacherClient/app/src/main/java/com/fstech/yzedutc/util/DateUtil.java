package com.fstech.yzedutc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shaoxin on 18-5-30.
 * 日期操作相关的工具类
 */

public class DateUtil {

    public static String NowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

}
