package com.fstech.yzeduds.model;

import java.io.Serializable;

/**
 * 资讯详情模型类
 * */
public class InformationContentBean implements Serializable{
    private static final long serialVersionUID = -1;
    
    private String platform_content_img;
    private String platform_content_text;
    private String platform_content_date;
    
    
    public InformationContentBean() {
        super();
    }

    public InformationContentBean(String platform_content_img,
            String platform_content_text, String platform_content_date) {
        super();
        this.platform_content_img = platform_content_img;
        this.platform_content_text = platform_content_text;
        this.platform_content_date = platform_content_date;
    }

    public String getPlatform_content_img() {
        return platform_content_img;
    }

    public void setPlatform_content_img(String platform_content_img) {
        this.platform_content_img = platform_content_img;
    }

    public String getPlatform_content_text() {
        return platform_content_text;
    }

    public void setPlatform_content_text(String platform_content_text) {
        this.platform_content_text = platform_content_text;
    }

    public String getPlatform_content_date() {
        return platform_content_date;
    }

    public void setPlatform_content_date(String platform_content_date) {
        this.platform_content_date = platform_content_date;
    }

    @Override
    public String toString() {
        return "InformationContentBean [platform_content_img="
                + platform_content_img + ", platform_content_text="
                + platform_content_text + ", platform_content_date="
                + platform_content_date + "]";
    }

}
