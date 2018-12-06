package com.fstech.yzeduas.mapper;

import com.fstech.yzeduas.model.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created By shaoxin On 12/6/18
 */

@Mapper
public interface BannerMapper {

    // 所有Banner列表
    public List<Banner> findList();

    // 添加Banner
    public int addBanner(Banner banner);

    // 删除Banner
    public int delBanner(int banner_id);

}