package com.fstech.yzeduss.mapper;

import com.fstech.yzeduss.model.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/9/18
 */
@Repository
@Mapper
public interface TeacherMapper {

    // 所有教师列表
    public List<Teacher> findListBySchoolId(@Param("school_id") int school_id);

    // 添加一个教师
    public int addTeacher(@Param("school_id") int school_id, @Param("teacher_name") String teacher_name,
                          @Param("teacher_number") String teacher_number, @Param("teacher_password") String teacher_password);

    // 删除一个教师
    public int delTeacher(@Param("teacher_number") String teacher_number);
}