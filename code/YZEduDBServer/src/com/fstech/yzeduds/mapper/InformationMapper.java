package com.fstech.yzeduds.mapper;

import java.util.List;

import com.fstech.yzeduds.model.InformationBean;
import com.fstech.yzeduds.model.InformationContentBean;

public interface InformationMapper {
    // 获取平台资讯列表
    public List<InformationBean> platformInformations(int currIndex,int pageSize);
    // 获取院校资讯列表
    public List<InformationBean> schoolInformations(int schoolId,int currIndex,int pageSize);
    
    // 获取平台资讯详情
    public List<InformationContentBean> platformInformationContent(int infomation_id);
    
}
