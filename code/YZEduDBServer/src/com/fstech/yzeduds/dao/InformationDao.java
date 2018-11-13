package com.fstech.yzeduds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.InformationMapper;
import com.fstech.yzeduds.model.InformationBean;
import com.fstech.yzeduds.model.InformationContentBean;

@Repository
public class InformationDao implements InformationMapper{
    @Autowired
    private InformationMapper informationMapper;
    
    @Override
    public List<InformationBean> platformInformations(int currIndex,
            int pageSize) {
        return informationMapper.platformInformations(currIndex, pageSize);
    }

    @Override
    public List<InformationBean> schoolInformations(int schoolId,
            int currIndex, int pageSize) {
        return informationMapper.schoolInformations(schoolId, currIndex, pageSize);
    }

    @Override
    public List<InformationContentBean> platformInformationContent(
            int infomation_id) {
        return informationMapper.platformInformationContent(infomation_id);
    }
    
}
