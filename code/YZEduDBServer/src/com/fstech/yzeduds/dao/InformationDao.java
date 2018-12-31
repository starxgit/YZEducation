package com.fstech.yzeduds.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.InformationMapper;
import com.fstech.yzeduds.model.InformationBean;
import com.fstech.yzeduds.model.InformationContentBean;
import com.fstech.yzeduds.util.Constant;
import com.fstech.yzeduds.util.JedisUtil;
import com.fstech.yzeduds.util.SerializeUtil;

@Repository
public class InformationDao implements InformationMapper {
    @Autowired
    private InformationMapper informationMapper;

    @Override
    public List<InformationBean> platformInformations(int currIndex,
            int pageSize) {
        List<InformationBean> informationList = new ArrayList<InformationBean>();
        String key = "platformInformationList";
        List<String> list = JedisUtil.getList(key, currIndex, currIndex
                + pageSize);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                InformationBean informationBean = (InformationBean) SerializeUtil
                        .unSerialize(list.get(i));
                informationList.add(informationBean);
            }
        } else {
            informationList = informationMapper.platformInformations(currIndex,
                    pageSize);
            for (int i = 0; i < informationList.size(); i++) {
                String str = SerializeUtil.serialize(informationList.get(i));
                JedisUtil.addToListByTTL(key, str, Constant.REDIS_CACHE_TTL);
            }
        }
        return informationList;
    }

    @Override
    public List<InformationBean> schoolInformations(int schoolId,
            int currIndex, int pageSize) {
        List<InformationBean> informationList = new ArrayList<InformationBean>();
        String key = "platformInformationList" + schoolId;
        List<String> list = JedisUtil.getList(key, currIndex, currIndex
                + pageSize);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                InformationBean informationBean = (InformationBean) SerializeUtil
                        .unSerialize(list.get(i));
                informationList.add(informationBean);
            }
        } else {
            informationList = informationMapper.schoolInformations(schoolId,
                    currIndex, pageSize);
            for (int i = 0; i < informationList.size(); i++) {
                String str = SerializeUtil.serialize(informationList.get(i));
                JedisUtil.addToListByTTL(key, str, Constant.REDIS_CACHE_TTL);
            }
        }
        return informationList;
    }

    @Override
    public List<InformationContentBean> platformInformationContent(
            int infomation_id) {
        String key = "platformInformationContent" + infomation_id;
        List<InformationContentBean> informationContentBeans = new ArrayList<>();
        List<String> list = JedisUtil.getList(key, 0, -1);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                InformationContentBean ib = (InformationContentBean) SerializeUtil
                        .unSerialize(list.get(i));
                informationContentBeans.add(ib);
            }
        } else {
            informationContentBeans = informationMapper
                    .platformInformationContent(infomation_id);
            for (int i = 0; i < informationContentBeans.size(); i++) {
                String str = SerializeUtil.serialize(informationContentBeans
                        .get(i));
                JedisUtil.addToListByTTL(key, str, Constant.REDIS_CACHE_TTL);
            }
        }
        return informationContentBeans;
    }

    @Override
    public List<InformationContentBean> schoolInformationContent(
            int infomation_id) {
        String key = "schoolInformationContent" + infomation_id;
        List<InformationContentBean> informationContentBeans = new ArrayList<>();
        List<String> list = JedisUtil.getList(key, 0, -1);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                InformationContentBean ib = (InformationContentBean) SerializeUtil
                        .unSerialize(list.get(i));
                informationContentBeans.add(ib);
            }
        } else {
            informationContentBeans = informationMapper
                    .schoolInformationContent(infomation_id);
            for (int i = 0; i < informationContentBeans.size(); i++) {
                String str = SerializeUtil.serialize(informationContentBeans
                        .get(i));
                JedisUtil.addToListByTTL(key, str, Constant.REDIS_CACHE_TTL);
            }
        }
        return informationContentBeans;
    }

}
