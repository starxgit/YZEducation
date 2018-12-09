package com.fstech.yzeduss.mapper;

import com.fstech.yzeduss.model.Information;
import com.fstech.yzeduss.model.InformationContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/9/18
 */
@Repository
@Mapper
public interface InformationMapper {

    // 所有资讯列表
    public List<Information> findListBySchoolId(@Param("school_id") int school_id);

    // 添加资讯
    public int addInformation(Information information);

    // 添加资讯内容
    public int addInformationContent(InformationContent informationContent);

    // 删除资讯
    public int delInformation(@Param("information_id") int information_id);

}