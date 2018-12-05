package com.fstech.yzeduas.mapper;

import com.fstech.yzeduas.model.SensitiveWord;

import java.util.List;

/**
 * Created By shaoxin On 12/5/18
 */
public interface SensitiveWordMapper {
    // 所有敏感词列表
    public List<SensitiveWord> findList();

    // 添加敏感词
    public int addSensitiviWord(String sensitive_word);

    // 删除敏感词
    public int delSensitiviWord(int sensitive_word_id);

}