package com.fstech.yzeduss.mapper;

import com.fstech.yzeduss.model.SchoolAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/8/18
 */
@Repository
@Mapper
public interface AdminMapper {
    // 所有管理员列表
    public List<SchoolAdmin> findListBySchool(@Param("school_id")int school_id);

    // 通过账号查找管理员
    public SchoolAdmin findByAccount(@Param("account")String account);
}