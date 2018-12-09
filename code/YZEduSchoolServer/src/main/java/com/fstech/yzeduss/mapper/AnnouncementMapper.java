package com.fstech.yzeduss.mapper;

import com.fstech.yzeduss.model.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/9/18
 */
@Repository
@Mapper
public interface AnnouncementMapper {

    // 所有公告列表
    public List<Announcement> findListBySchoolId(@Param("school_id") int school_id);

    // 添加公告
    public int addAnnouncement(@Param("school_id") int school_id, @Param("announcement_title") String announcement_title,
                               @Param("announcement_content") String announcement_content,
                               @Param("announcement_stick") int announcement_stick);

    // 删除公告
    public int delAnnouncement(@Param("announcement_id") int announcement_id);
}