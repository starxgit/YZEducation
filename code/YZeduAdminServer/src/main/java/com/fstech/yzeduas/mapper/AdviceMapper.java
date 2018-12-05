package com.fstech.yzeduas.mapper;

import com.fstech.yzeduas.model.Advice;

import java.util.List;

/**
 * Created By shaoxin On 12/5/18
 */
public interface AdviceMapper
{
    // 所以未读建议
    public List<Advice> findList();

    // 标记已读
    public int updateRead(int advice_id);
}