package com.fstech.yzedusc.util;

/**
 * Created by shaoxin on 18-3-25.
 * 这是一个存放常量的类
 */

public class Constant {

    // 这个是服务器的IP地址
//    public static final String SERVER_IP = "http://118.89.29.44";

    // 本地IP地址
    public static final String SERVER_IP = "http://192.168.199.175";

    // 数据服务器1的IP地址
    public static final String DB_SERVER_IP1 = "http://47.101.36.60";

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

    // 基础数据请求路径2
//    public static final String BASE_DB_URL1 = SERVER_IP + ":" + SERVER_PORT + "/YZEdu-1.0/";
    public static final String BASE_DB_URL1 = DB_SERVER_IP1 + "/YZEdu-1.0/";

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
    public static final String[] ARR_TEACHER_NAME = {"林冲", "王白", "李伟", "李青", "张小兰", "章笑笑", "廖家辉", "何晓欢"};

    // 学生类型的基本类型
    public static final String TYPE_STUDENT = "1";
}
