package com.fstech.yzeduds.util;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * Jedis操作redis缓存数据的工具类
 * 
 * @author shaoxin
 *
 */
public class JedisUtil {

    /**
     * 通过键名获取值的方法
     * 
     * @param key
     * @return value
     */
    public static String getValue(String key) {
        @SuppressWarnings("resource")
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        jedis.select(0);
        String value = "nil";
        if (jedis.exists(key)) {
            value = jedis.get(key);
        }
        jedis.disconnect();
        return value;
    }

    /**
     * 设置键值对的方法
     * 
     * @param key
     * @param value
     */
    public static void setValue(String key, String value) {
        @SuppressWarnings("resource")
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        jedis.select(0);
        jedis.set(key, value);
        jedis.disconnect();
    }

    /**
     * 设置键值对并设置TTL的方法
     * 
     * @param key
     * @param value
     */
    public static void setValueByTTL(String key, String value, int TTL) {
        @SuppressWarnings("resource")
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        jedis.select(0);
        jedis.setex(key, TTL, value);
        jedis.disconnect();
    }
    
    /**
     * 加入到列队的方法
     * @param key
     * @param value
     */
    public static void addToList(String key,String value){
        @SuppressWarnings("resource")
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        jedis.select(0);
        jedis.rpush(key, value);
        jedis.disconnect();
    }
    
    /**
     * 加入到列队并设置TTL的方法
     * @param key
     * @param value
     */
    public static void addToListByTTL(String key,String value,int TTL){
        @SuppressWarnings("resource")
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        jedis.select(0);
        jedis.rpush(key, value);
        jedis.expire(key, TTL);
        jedis.disconnect();
    }
    
    /**
     * 从列队中取出对象的方法
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<String> getList(String key,int start,int end){
        List<String> list = new ArrayList<String>();
        @SuppressWarnings("resource")
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        jedis.select(0);
        if(jedis.exists(key)){
            list = jedis.lrange(key, start, end);
        }
        jedis.disconnect();
        return list;
    }
    
    

    /**
     * 测试主入口
     */
    public static void main(String[] args) {
        addToListByTTL("test", "value2",10);
        List<String> list = getList("test", 0, -1);
        System.out.println(list.toString());
    }
}
