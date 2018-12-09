package com.fstech.yzeduss.mapper;

import com.fstech.yzeduss.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/9/18
 */
@Repository
@Mapper
public interface StudentMapper {

    // 所有学生列表
    public List<Student> findListBySchoolId(@Param("school_id")int school_id);
}