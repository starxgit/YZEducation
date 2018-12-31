package com.fstech.yzeduds.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.SchoolMapper;
import com.fstech.yzeduds.model.AnnouncementBean;
import com.fstech.yzeduds.model.SchoolBean;
import com.fstech.yzeduds.util.Constant;
import com.fstech.yzeduds.util.JedisUtil;
import com.fstech.yzeduds.util.SerializeUtil;

@Repository
public class SchoolDao implements SchoolMapper{
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public SchoolBean findSchoolById(int school_id) {
        return schoolMapper.findSchoolById(school_id);
    }

    @Override
    public List<AnnouncementBean> findAnnouncementsBySchoolId(int school_id) {
        List<AnnouncementBean> announcementBeans = new ArrayList<AnnouncementBean>();
        String key = "schoolAnnouncements"+school_id;
        List<String> list = JedisUtil.getList(key, 0, -1);
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                AnnouncementBean ab= (AnnouncementBean) SerializeUtil.unSerialize(list.get(i));
                announcementBeans.add(ab);
            }
        }else{
            announcementBeans = schoolMapper.findAnnouncementsBySchoolId(school_id);
            for(int i=0;i<announcementBeans.size();i++){
                String str = SerializeUtil.serialize(announcementBeans.get(i));
                JedisUtil.addToListByTTL(key, str, Constant.REDIS_CACHE_TTL);
            }
        }
        return announcementBeans;
    }
    
}
