package com.fstech.yzeduas.mapper;

import com.fstech.yzeduas.model.Information;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 1/1/19
 */
@Mapper
@Repository
public interface InformationMapper {

    // 资讯列表
    public List<Information> findList();
}