package com.fstch.YZEduadmin.mapper;

import java.util.List;

import com.fstch.YZEduadmin.models.Banner;

public interface BannerMapper {
	List<Banner> findAll();
	Banner findById(int banner_id);
	void addBanner(Banner banner);
	void deleteBanner(int banner_id);
	void modify(Banner banner);
}
