package com.fstech.yzeduds.mapper;

import java.util.List;

import com.fstech.yzeduds.model.AnnouncementBean;
import com.fstech.yzeduds.model.SchoolBean;

public interface SchoolMapper {
    // 获取院校基本信息
    public SchoolBean findSchoolById(int school_id);
    // 获取院校公告内容
    public List<AnnouncementBean> findAnnouncementsBySchoolId(int school_id);
    
}
