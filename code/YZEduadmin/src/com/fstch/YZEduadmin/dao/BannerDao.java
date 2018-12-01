package com.fstch.YZEduadmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstch.YZEduadmin.mapper.BannerMapper;
import com.fstch.YZEduadmin.models.Banner;
@Repository
public class BannerDao implements BannerMapper {
	@Autowired
	BannerMapper bannerMapper;
	@Override
	public List<Banner> findAll() {
		// TODO Auto-generated method stub
		return bannerMapper.findAll();
	}
	@Override
	public Banner findById(int banner_id) {
		// TODO Auto-generated method stub
		return bannerMapper.findById(banner_id);
	}
	@Override
	public void addBanner(Banner banner) {
		// TODO Auto-generated method stub
		bannerMapper.addBanner(banner);
	}
	@Override
	public void deleteBanner(int banner_id) {
		// TODO Auto-generated method stub
		bannerMapper.deleteBanner(banner_id);
	}
	@Override
	public void modify(Banner banner) {
		// TODO Auto-generated method stub
		bannerMapper.modify(banner);
	}

}
