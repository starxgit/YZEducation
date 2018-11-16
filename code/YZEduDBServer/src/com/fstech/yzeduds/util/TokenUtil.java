package com.fstech.yzeduds.util;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * Created By shaoxin On 2018-11-15 
 * 用户凭证工具类
 * */
public class TokenUtil {

    /**
     * 创建凭证的方法
     * */
    public static String enCodeToken(int user_id, int student_id,
            int teacher_id, int class_id, int faculty_id, int school_id) {
        String token = "";
        List<Integer> arr = new ArrayList<>();
        arr.add(user_id);
        arr.add(student_id);
        arr.add(teacher_id);
        arr.add(class_id);
        arr.add(faculty_id);
        arr.add(school_id);
        String arrString = arr.toString();
        System.out.println(arrString);
        token = Base64.encode(arrString.getBytes());
        return token;
    }

    /**
     * 解析用户id的方法
     * */
    public static int decodeUserId(String token) {
        int userId = -1;
        ArrayList<Integer> arrayList = (ArrayList<Integer>) decode(token);
        if (arrayList.size() == 6) {
            userId = arrayList.get(0);
        }
        return userId;
    }

    /**
     * 解析学生id的方法
     * */
    public static int decodeStudentId(String token) {
        int studentId = -1;
        ArrayList<Integer> arrayList = (ArrayList<Integer>) decode(token);
        if (arrayList.size() == 6) {
            studentId = arrayList.get(1);
        }
        return studentId;
    }

    /**
     * 解析教师id的方法
     * */
    public static int decodeTeacherId(String token) {
        int teacherId = -1;
        ArrayList<Integer> arrayList = (ArrayList<Integer>) decode(token);
        if (arrayList.size() == 6) {
            teacherId = arrayList.get(2);
        }
        return teacherId;
    }

    /**
     * 解析班级id的方法
     * */
    public static int decodeClassId(String token) {
        int classId = -1;
        ArrayList<Integer> arrayList = (ArrayList<Integer>) decode(token);
        if (arrayList.size() == 6) {
            classId = arrayList.get(3);
        }
        return classId;
    }

    /**
     * 解析院系id的方法
     * */
    public static int decodeFacultyId(String token) {
        int facultyId = -1;
        ArrayList<Integer> arrayList = (ArrayList<Integer>) decode(token);
        if (arrayList.size() == 6) {
            facultyId = arrayList.get(4);
        }
        return facultyId;
    }

    /**
     * 解析院校id的方法
     * */
    public static int decodeSchoolId(String token) {
        int schoolId = -1;
        ArrayList<Integer> arrayList = (ArrayList<Integer>) decode(token);
        if (arrayList.size() == 6) {
            schoolId = arrayList.get(5);
        }
        return schoolId;
    }
    
    /**
     * 解码的核心类
     * @param token
     * @return arrayList
     *  */
    @SuppressWarnings("finally")
    private static List<Integer> decode(String token) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            byte[] arrByte = Base64.decode(token);
            String arrString = new String(arrByte);
            arrString = arrString.substring(1, arrString.length() - 1);
            String[] arr = arrString.split(", ");
            for (int i = 0; i < arr.length; i++) {
                arrayList.add(Integer.parseInt(arr[i]));
            }
        } catch (Base64DecodingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            return arrayList;
        }

    }

}
