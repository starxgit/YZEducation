package com.fstech.yzeduas.dao;

import com.fstech.yzeduas.mapper.SchoolMapper;
import com.fstech.yzeduas.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/5/18
 */
@Repository
public class SchoolDao implements SchoolMapper{
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public List<School> findList() {
        return schoolMapper.findList();
    }

    @Override
    public School findSchoolById(int school_id) {
        return schoolMapper.findSchoolById(school_id);
    }

    @Override
    public int addSchool(School school) {
        return schoolMapper.addSchool(school);
    }

    @Override
    public int updateSchool(School school) {
        return schoolMapper.updateSchool(school);
    }

    @Override
    public int deleteSchool(int school_id) {
        return schoolMapper.deleteSchool(school_id);
    }
}