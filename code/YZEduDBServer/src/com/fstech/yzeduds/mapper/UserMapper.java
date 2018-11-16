package com.fstech.yzeduds.mapper;

import com.fstech.yzeduds.model.UserBean;

public interface UserMapper {
    // 账号查找这个用户
    public UserBean findUserByAccount(String user_account);

    // id查找这个用户
    public UserBean findUserById(int user_id);

    // 修改用户信息
    public void updateUser(int user_id, String password, String phone,
            int user_age, String user_sex,String user_avatar);    
}
