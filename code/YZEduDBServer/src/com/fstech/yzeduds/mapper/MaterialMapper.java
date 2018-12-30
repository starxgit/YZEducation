package com.fstech.yzeduds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.model.MaterialBean;

@Repository
public interface MaterialMapper {

    // 查看这节课所有资料列表
    public List<MaterialBean> findListByCourse(@Param("courseId") int courseId);

    // 新增一个资料
    public int addMaterial(MaterialBean materialBean);

    // 删除一个资料
    public int delMaterial(@Param("materialId") int materialId);
}
