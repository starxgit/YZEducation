package com.fstech.yzedusc.util;

import android.os.Handler;

/**
 * Created by shaoxin on 18-4-6.
 * 线程工具类
 */

public class ThreadUtil {
    // 子线程执行task
    public static void runInThread(Runnable task) {
        new Thread(task).start();
    }

    //　创建一个主线程中的Handler
    public static Handler mHandler = new Handler();

    // UI线程执行task
    public static void runInUIThread(Runnable task) {
        mHandler.post(task);
    }
}
