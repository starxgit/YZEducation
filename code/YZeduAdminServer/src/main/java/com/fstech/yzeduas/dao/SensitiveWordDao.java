package com.fstech.yzeduas.dao;

import com.fstech.yzeduas.mapper.SensitiveWordMapper;
import com.fstech.yzeduas.model.SensitiveWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shaoxin On 12/5/18
 */
@Repository
public class SensitiveWordDao implements SensitiveWordMapper {

    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    @Override
    public List<SensitiveWord> findList() {
        return sensitiveWordMapper.findList();
    }

    @Override
    public int addSensitiviWord(String sensitive_word) {
        return sensitiveWordMapper.addSensitiviWord(sensitive_word);
    }

    @Override
    public int delSensitiviWord(int sensitive_word_id) {
        return sensitiveWordMapper.delSensitiviWord(sensitive_word_id);
    }
}