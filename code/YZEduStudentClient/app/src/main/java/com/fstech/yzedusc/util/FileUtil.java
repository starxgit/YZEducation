package com.fstech.yzedusc.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by shaoxin on 18-3-10.
 * 文件操作相关类
 */

public class FileUtil {
    private static String SDPATH;
    public FileUtil(){
        //得到SD卡的目录，如：“sdcard/”
        SDPATH= Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;
    }
    /**
     * 在SD卡的指定目录上创建文件
     * @param fileName
     */
    public File createFile(String fileName){
        File file=new File(SDPATH+fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    /**
     * 在SD卡上创建指定名称的目录
     * @param dirName
     */
    public File createDir(String dirName){
        File file=new File(SDPATH+dirName+File.separator);
        file.mkdir();
        return file;
    }
    /**
     * 判断指定名称的文件在SD卡上是否存在
     * @param fileName
     * @return
     */
    public static boolean isExist(String dirName,String fileName){
        File file=new File(SDPATH+dirName+fileName);
        return file.exists();
    }

    /**
     * 通过URL得到HttpURLConnection，通过HttpURLConnection得到InputStream
     * @param urlStr
     * @return
     */
    public InputStream getIS(String urlStr){
        URL url=null;
        HttpURLConnection urlConn=null;
        InputStream is=null;
        try {
            url=new URL(urlStr);
            urlConn=(HttpURLConnection)url.openConnection();
            is=urlConn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    public static long getSize(String urlStr){
        URL url=null;
        HttpURLConnection urlConn=null;
        long size=0;
        try {
            url=new URL(urlStr);
            urlConn=(HttpURLConnection)url.openConnection();
            urlConn.setRequestProperty("Accept-Encoding", "identity");
            size=urlConn.getContentLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
    /**
     * 由得到的输入流把下载的文件写入到SD卡的指定位置
     * @param is
     * @param dirName
     * @param fileName
     * @return
     */
    public File IS2SD(InputStream is,String dirName,String fileName){
        OutputStream os=null;
        File file=null;
        try {
            createDir(dirName);
            file=createFile(dirName+fileName);
            os=new FileOutputStream(file);
            byte buffer[]=new byte[1024*4];
            int temp=0;
            while((temp=is.read(buffer))!=-1){
                os.write(buffer, 0, temp);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 获取指定文件大小
     * @paramf
     * @return
     * @throws Exception
     */
    public static long getAutoFileOrFilesSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            blockSize = getFileSize(file);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        return blockSize;
    }

    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }
}
