package com.fstech.yzedutc.util;

/**
 * Created by shaoxin on 18-3-25.
 * 这是一个存放常量的类
 */

public class Constant {


    // 这个是服务器的IP地址
    public static final String SERVER_IP = "http://118.89.29.44";

    // 设置服务器端口
    public static final String SERVER_PORT = "8080";

    // 基础数据请求地址
    public static final String BASE_DB_URL = SERVER_IP + ":" + SERVER_PORT + "/YZEduDBServer/";

    // 基础图片请求路径
    public static final String BASE_IMG_URL = SERVER_IP + ":" + SERVER_PORT + "/YZEduFileServer/showImg";

    // 基础文件请求路径
    public static final String BASE_FILE_URL = SERVER_IP + ":" + SERVER_PORT + "/YZEduFileServer/download";

    // 基础视频请求路径
    public static final String BASE_VIDEO_URL = SERVER_IP + ":" + SERVER_PORT + "/YZEduFileServer/play?myfile=";

    //几个代表页面的常量，代表第几个Fragment
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;

    // GridView放视频的数量
    public static final int GRID_SIZE = 4;

    public static final String TEMP_BASE_URL = "http://www.fstechnology.cn:8080/XiankeM/";

    // 临时数据
    public static final int[] ARR_COURSE_SUM_HOUR = {12, 5, 17, 19, 28, 16, 39, 18, 13, 21};
    public static final String[] ARR_TEACHER_NAME={"林冲","王白","李伟","李青","张小兰","章笑笑","廖家辉","何晓欢"};

}
