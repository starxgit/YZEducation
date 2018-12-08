package com.fstech.yzeduss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fstech.yzeduss.mapper")
public class YzEduSchoolServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(YzEduSchoolServerApplication.class, args);
	}
}
