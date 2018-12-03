package com.fstech.yzeduas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan("com.fstech.yzeduas.mapper")
@Controller
public class YzeduApplication {

    public static void main(String[] args) {
        SpringApplication.run(YzeduApplication.class, args);
    }

    // 默认进入登录页
    @RequestMapping("/")
    public String index(){
        return "redirect:admin/index";
    }
}
