package com.fstech.yzeduss.mapper;

import com.fstech.yzeduss.model.Faculty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/8/18
 */
@Repository
@Mapper
public interface FacultyMapper {
    // 所有院系列表
    public List<Faculty> findListBySchoolId(@Param("school_id") int school_id);

    // 添加一个院系
    public int addFaculty(@Param("faculty_name")String faculty,@Param("school_id")int school_id);

    // 删除一个院系
    public int delFaculty(@Param("faculty_id")int faculty_id);
}