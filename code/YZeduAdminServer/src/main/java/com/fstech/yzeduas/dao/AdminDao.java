package com.fstech.yzeduas.dao;

import com.fstech.yzeduas.mapper.AdminMapper;
import com.fstech.yzeduas.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao implements AdminMapper {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> findList() {
        return adminMapper.findList();
    }

    @Override
    public Admin findByAccount(String account) {
        return adminMapper.findByAccount(account);
    }

    @Override
    public Admin findById(int id) {
        return adminMapper.findById(id);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int deleteAdmin(String account) {
        return adminMapper.deleteAdmin(account);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }
}
