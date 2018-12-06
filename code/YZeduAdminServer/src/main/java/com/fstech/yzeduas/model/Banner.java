package com.fstech.yzeduas.model;

import lombok.Data;

/**
 * Created By shaoxin On 12/6/18
 */
@Data
public class Banner {

    public static final String[] TYPES={"","展示广告","课程广告","其他广告"};

    private int banner_id;
    private String banner_image;
    private int banner_type;
    private String banner_link;
}