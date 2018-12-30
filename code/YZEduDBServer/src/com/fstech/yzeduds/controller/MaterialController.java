package com.fstech.yzeduds.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstech.yzeduds.mapper.MaterialMapper;
import com.fstech.yzeduds.model.MaterialBean;
import com.fstech.yzeduds.util.ErrorCode;
import com.fstech.yzeduds.util.ResponseUtil;

/**
 * 课程资料相关控制器
 * 
 * @author shaoxin
 */

@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialMapper materialMapper;

    // 所有资料列表
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public void materialList(HttpServletResponse response,
            @RequestParam int course_id) {
        List<MaterialBean> materialBeans = materialMapper
                .findListByCourse(course_id);
        ResponseUtil.normalResponse(response, materialBeans);
    }

    // 添加资料
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void addmaterial(HttpServletResponse response,
            @RequestParam int course_id, @RequestParam String name,
            @RequestParam String url) {
        MaterialBean materialBean = new MaterialBean();
        materialBean.setCourse_id(course_id);
        materialBean.setMaterial_name(name);
        materialBean.setMaterial_url(url);
        materialBean.setMaterial_size("unknown");
        int result = materialMapper.addMaterial(materialBean);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_UPLOAD_FILE_FAIL,
                    ErrorCode.MESSAGE_UPLOAD_FILE_FAIL);
        }

    }

    // 删除资料
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public void delCourseMaterial(HttpServletResponse response,
            @RequestParam int material_id) {
        int result = materialMapper.delMaterial(material_id);
        if (result > 0) {
            ResponseUtil.normalResponse(response, null);
        } else {
            ResponseUtil.errorResponse(response, null,
                    ErrorCode.CODE_DEL_MATERIAL_FAIL,
                    ErrorCode.MESSAGE_DEL_MATERIAL_FAIL);
        }
    }
}
