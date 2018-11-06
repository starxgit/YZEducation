package com.fstech.yzedusc.util;

import android.app.Activity;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shaoxin on 18-3-10.
 * 活动池的类
 */

public class CacheActivityUtil {
    public static List<Activity> activityList = new LinkedList<Activity>();

    public CacheActivityUtil() {}

    /**
     * 添加到Activity容器中
     */
    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    /**
     * 便利所有Activigty并finish
     */
    public static void finishActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    /**
     * 结束指定的Activity
     */
    public static void finishSingleActivity(Activity activity) {
        if (activity != null) {
            if (activityList.contains(activity)) {
                activityList.remove(activity);
            }
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity 在遍历一个列表的时候不能执行删除操作，所有我们先记住要删除的对象，遍历之后才去删除。
     */
    public static void finishSingleActivityByClass(Class<?> cls) {
        Activity tempActivity = null;
        for (Activity activity : activityList) {
            if (activity.getClass().equals(cls)) {
                tempActivity = activity;
            }
        }

        finishSingleActivity(tempActivity);
    }

    /*
    * 把当前活动加入活动栈
    * 参数context
    * 无返回
    * */
    private static void addActivity(Context context){
        if (!CacheActivityUtil.activityList.contains(context)) {
            CacheActivityUtil.addActivity(context);
        }
    }

}
