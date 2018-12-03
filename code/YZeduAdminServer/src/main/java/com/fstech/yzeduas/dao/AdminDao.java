package com.fstech.yzeduas.dao;

import com.fstech.yzeduas.mapper.AdminMapper;
import com.fstech.yzeduas.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao implements AdminMapper{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> findList() {
        return adminMapper.findList();
    }
}
