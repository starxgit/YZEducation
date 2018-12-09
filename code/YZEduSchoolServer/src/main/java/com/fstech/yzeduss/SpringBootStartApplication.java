package com.fstech.yzeduss;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created By shaoxin On 12/7/18
 */
public class SpringBootStartApplication  extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YzEduSchoolServerApplication.class);
    }
}