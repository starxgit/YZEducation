package com.fstech.yzeduds.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.fstech.yzeduds.mapper.BannerMapper;
import com.fstech.yzeduds.model.BannerBean;
import com.fstech.yzeduds.util.Constant;
import com.fstech.yzeduds.util.JedisUtil;
import com.fstech.yzeduds.util.SerializeUtil;

@Repository
public class BannerDao implements BannerMapper {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerBean> bannerList() {
        List<BannerBean> bannerList = new ArrayList<BannerBean>();
        String key = "bannerList";
        List<String> list = JedisUtil.getList(key, 0, -1);
        if (list.size() > 0) {
            // 直接从redis读取
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                BannerBean bannerBean = (BannerBean) SerializeUtil
                        .unSerialize(str);
                bannerList.add(bannerBean);
            }
        } else {
            // 读数据库
            bannerList = bannerMapper.bannerList();
            for (int i = 0; i < bannerList.size(); i++) {
                JedisUtil.addToListByTTL(key,
                        SerializeUtil.serialize(bannerList.get(i)),
                        Constant.REDIS_CACHE_TTL);
            }
        }
        return bannerList;
    }

}
