package com.fstech.yzeduss.controller;

import com.fstech.yzeduss.mapper.AdminMapper;
import com.fstech.yzeduss.model.SchoolAdmin;
import com.fstech.yzeduss.util.CreateMD5;
import com.fstech.yzeduss.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created By shaoxin On 12/8/18
 */
@RestController
@CrossOrigin("*")
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    // 所有管理员列表
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public void test(HttpServletResponse response, @RequestParam int school_id) {
        List<SchoolAdmin> adminList = adminMapper.findListBySchool(school_id);
        ResponseUtil.normalResponse(response, adminList);
    }

    // 管理员登录
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public void login(HttpServletResponse response,@RequestParam String account,@RequestParam String password){
        SchoolAdmin admin = adminMapper.findByAccount(account);
        if(admin!=null){
            if(CreateMD5.getMd5(password).equals(admin.getSchool_admin_password())){
                ResponseUtil.normalResponse(response,admin);
            }else{
                ResponseUtil.errorResponse(response,null,-1,"账号或密码错误");
            }
        }else{
            ResponseUtil.errorResponse(response,null,-1,"管理员不存在");
        }
    }

    // TODO 添加一个管理员

    // TODO 删除一个管理员

    // TODO 修改管理员密码

}