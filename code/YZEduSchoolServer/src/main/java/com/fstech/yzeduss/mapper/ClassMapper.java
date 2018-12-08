package com.fstech.yzeduss.mapper;

import com.fstech.yzeduss.model.GradeClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/8/18
 */
@Repository
@Mapper
public interface ClassMapper {
    // 所有班级列表
    public List<GradeClass> findListBySchoolId(@Param("school_id") int school_id);

    // 添加一个班级
    public int addClass(@Param("school_id") int school_id, @Param("faculty_id") int faculty_id,
                        @Param("class_name") String class_name);

    // 删除一个班级
    public int delClass(@Param("class_id") int class_id);

}