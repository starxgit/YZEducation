package com.fstech.yzedusc.util;

import com.fstech.yzedusc.R;

/**
 * Created by shaoxin on 18-3-25.
 * 这是一个存放常量的类
 */

public class Constant {

    // 这个是服务器的IP地址
//    public static final String SERVER_IP = "https://www.fstechnology.cn";

    // 本地IP地址
    public static final String SERVER_IP = "http://192.168.199.175";

    // 设置服务器端口
    public static final String SERVER_PORT = "8080";

    // 基础数据请求地址
    public static final String BASE_DB_URL = SERVER_IP + ":" + SERVER_PORT + "/YZEduDBServer/";

    // 基础图片请求路径
    public static final String BASE_IMG_URL = "http://cdn.fstechnology.cn/YZEduResources/images/";

    // 基础文件请求路径
    public static final String BASE_FILE_URL = "http://cdn.fstechnology.cn/YZEduResources/other/";

    // 基础视频请求路径
    public static final String BASE_VIDEO_URL = "http://cdn.fstechnology.cn/YZEduResources/videos/";


    //几个代表页面的常量，代表第几个Fragment
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;

    // GridView放图片的数量
    public static final int GRID_SIZE = 4;

    // 问题状态数组
    public static String QUESTION_STATE[] = {"待批改", "正确", "[添加到错题]", "不全对"};

    // 题目类型数组
    public static String EXAM_TYPE[] = {"选择题", "填空题", "主观题"};

    // 标签颜色列表
    public static int LABLE_DRABLES[] = {R.drawable.lable_btn_qing_bg, R.drawable.lable_btn_pink_bg,
            R.drawable.lable_btn_yellow_bg, R.drawable.lable_btn_blue_bg, R.drawable.lable_btn_red_bg};

}
