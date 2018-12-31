package com.fstech.yzeduds.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化和反序列化的方法
 * @author shaoxin
 *
 */
public class SerializeUtil {
    
    /**
     * 对象序列成字符串的方法
     * @param object
     * @return
     */
    public static String serialize(Object object){
        String str = "";
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
        ObjectOutputStream objOut;
        try {
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(object);  
            str = byteOut.toString("ISO-8859-1");//此处只能是ISO-8859-1,但是不会影响中文使用
        } catch (IOException e) {
            e.printStackTrace();
        }  
        return str;
    }
    
    public static Object unSerialize(String str){
        Object object = new Object();
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));  
            ObjectInputStream objIn;
            objIn = new ObjectInputStream(byteIn);
            object =objIn.readObject();  
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  
        return object;
    }

}
