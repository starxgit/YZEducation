package com.fstech.yzeduds.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

public class SimilarUtil {
    private final static String SH = "/home/static/python/Similar.py";
    private final static String PYTHON = "python3";
    
    public final static String[] EXAM_STATES={"待批改","对","错"};
    
    /**
     * 综合答案包含回答，回答包含答案的情况来比对相似度
     * @param answer
     * @param str
     * @return similar
     */
    public static double similarDegree(String answer, String str) {
        double similar = 0;
        // 如果输入为空，相似度为0
        if (str != null && !str.equals("")) {
            if (answer.contains(str)) {
                // 用户输入是参考答案的一部分，给定0.25的基础得分
                double baseSimilar = 0.25;
                int len1 = answer.length();
                int len2 = str.length();
                double delta = 1 - (double)(len1-len2) / (double)len1;
                String strSimilar = pySimilar(answer, str);
                similar = baseSimilar+(Double.parseDouble(strSimilar)+delta)/2;
            } else if (str.contains(answer)) {
                // 用户输入包含参考答案，判断字数相差程度
                double baseSimilar = 0.25;
                int len1 = str.length();
                int len2 = answer.length();
                double delta = 1 - (double)(len1-len2) / (double)len2;
                String strSimilar = pySimilar(answer, str);
                similar = baseSimilar+(Double.parseDouble(strSimilar)+delta)/2;
            } else {
                // 两个词没有明确包含关系,调用Python求相似度
                String strSimilar = pySimilar(answer, str);
                similar = Double.parseDouble(strSimilar) - 0.1;
            }

        }
        return similar;
    }
    
    /**
     * 自动批改填空题的方法，返回做题对应的状态
     * @param answer
     * @param str
     * @return
     */
    public static int autocheck(String answer,String str){
        double similar = similarDegree(answer, str);
        int state= 0;
        if(similar>0.8){
            // 判对
            state = 1;
        }else if(similar <0.3){
            // 判错误
            state = 2;
        }else{
            // 留给教师批改
            state = 0;
        }
        return state;
    }
    
    /**
     * 使用Python来判断两个词语相似度的方法
     * @param str1
     * @param str2
     * @return similar
     */
    private static String pySimilar(String str1, String str2) {
        String similar = "";
        String[] cmdArr = new String[] { PYTHON, SH, str1, str2 };
        Process process;
        String line = "0";
        try {
            process = Runtime.getRuntime().exec(cmdArr);
            // 用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    process.getInputStream(), "utf-8")); // gbk 避免汉字乱码
            while ((line = in.readLine()) != null) {
                similar = line;
            }
            in.close();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        return similar;
    }

    public static void main(String[] args) {
//        人工审核判对
        String[] answers={"JVM虚拟机","计算机软件","高并发","Linux","十六进制","A","C","B","D"};
        String[] str1s={"JVM","软件","高并发","Linux","十六进制","A","C","B","D"};
//        人工审核判错
        String[] str2s={"JAVA","硬件","安全性","Unix","十进制","D","A","C","B"};
        
        for(int i=0;i<answers.length;i++){
            String str1 = answers[i];
            String str2 = str1s[i];
            String str3 = str2s[i];
            System.out.println("人工对："+EXAM_STATES[autocheck(str1, str2)]);
            System.out.println("人工错："+EXAM_STATES[autocheck(str1, str3)]);
        }

    }

}
