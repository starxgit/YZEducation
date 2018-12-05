package com.fstech.yzeduas.mapper;

import com.fstech.yzeduas.model.School;

import java.util.List;

/**
 * Created By shaoxin On 12/5/18
 */
public interface SchoolMapper {
    // 所有院校列表
    public List<School> findList();

    // 通过Id找到一个院校
    public School findSchoolById(int school_id);

    // 添加院校
    public int addSchool(School school);

    // 修改院校
    public int updateSchool(School school);

    // 删除院校
    public int deleteSchool(int school_id);
}