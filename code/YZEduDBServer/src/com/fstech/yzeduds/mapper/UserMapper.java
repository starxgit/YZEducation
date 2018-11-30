package com.fstech.yzeduds.mapper;

import org.apache.ibatis.annotations.Param;

import com.fstech.yzeduds.model.UserBean;

public interface UserMapper {
    // 账号查找这个用户
    public UserBean findUserByAccount(String user_account);

    // id查找这个用户
    public UserBean findUserById(int user_id);

    // 修改用户基本信息
    public int updateUser(@Param("user_id") int user_id,
            @Param("phone") String phone, @Param("user_age") int user_age,
            @Param("user_sex") String user_sex,
            @Param("user_avatar") String user_avatar);

    // 修改密码
    public int updatePassword(int user_id, int password);
}
