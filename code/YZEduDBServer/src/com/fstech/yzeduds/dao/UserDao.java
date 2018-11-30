package com.fstech.yzeduds.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.UserMapper;
import com.fstech.yzeduds.model.UserBean;

@Repository
public class UserDao implements UserMapper{
    @Autowired UserMapper userMapper;
    
    @Override
    public UserBean findUserByAccount(String user_account) {
        return userMapper.findUserByAccount(user_account);
    }

    @Override
    public UserBean findUserById(int user_id) {
        return userMapper.findUserById(user_id);
    }

    @Override
    public int updateUser(int user_id, String phone, int user_age,
            String user_sex, String user_avatar) {
        return userMapper.updateUser(user_id, phone, user_age, user_sex, user_avatar);
    }

    @Override
    public int updatePassword(int user_id, int password) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
}
