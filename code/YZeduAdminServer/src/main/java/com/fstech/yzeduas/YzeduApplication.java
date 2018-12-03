package com.fstech.yzeduas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fstech.yzeduas.mapper")
public class YzeduApplication {

    public static void main(String[] args) {
        SpringApplication.run(YzeduApplication.class, args);
    }
}
