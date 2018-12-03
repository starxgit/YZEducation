package com.fstech.yzeduas.mapper;

import com.fstech.yzeduas.model.Admin;

import java.util.List;


public interface AdminMapper {
    // 所有管理员列表
    public List<Admin> findList();

    // 根据账号查找管理员
    public Admin findByAccount(String account);

    // 根据Id查找管理员
    public Admin findById(int id);

    // 修改管理员
    public int updateAdmin(Admin admin);

    // 删除管理员
    public int deleteAdmin(String account);

    // 添加管理员
    public int addAdmin(Admin admin);
}
