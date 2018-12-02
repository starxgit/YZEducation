package com.fstech.yzeduas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class YzEduAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YzEduAdminApplication.class, args);
    }

    @RequestMapping("/")
    public String selectUser() {
        return "hello Spring boot! <br> Welcome to YZEducation manager platform!";
    }
}
