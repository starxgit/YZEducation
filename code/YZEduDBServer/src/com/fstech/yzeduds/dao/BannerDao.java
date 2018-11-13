package com.fstech.yzeduds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.BannerMapper;
import com.fstech.yzeduds.model.BannerBean;

@Repository
public class BannerDao implements BannerMapper{
    @Autowired
    private BannerMapper bannerMapper;
    
    @Override
    public List<BannerBean> bannerList() {
        return bannerMapper.bannerList();
    }
    

}
