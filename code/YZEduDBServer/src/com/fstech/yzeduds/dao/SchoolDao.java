package com.fstech.yzeduds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.SchoolMapper;
import com.fstech.yzeduds.model.AnnouncementBean;
import com.fstech.yzeduds.model.SchoolBean;

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
        return schoolMapper.findAnnouncementsBySchoolId(school_id);
    }
    
}
