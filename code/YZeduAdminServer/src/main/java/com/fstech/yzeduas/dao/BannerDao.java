package com.fstech.yzeduas.dao;

import com.fstech.yzeduas.mapper.BannerMapper;
import com.fstech.yzeduas.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/6/18
 */
@Repository
public class BannerDao implements BannerMapper {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> findList() {
        return bannerMapper.findList();
    }

    @Override
    public int addBanner(Banner banner) {
        return bannerMapper.addBanner(banner);
    }

    @Override
    public int delBanner(int banner_id) {
        return bannerMapper.delBanner(banner_id);
    }
}