package com.fstech.yzeduss.model;

import lombok.Data;

/**
 * Created By shaoxin On 12/9/18
 */
@Data
public class Announcement {
    private int announcement_id;
    private String announcement_title;
    private String announcement_content;
    private int announcement_stick;
    private int announcement_del;
}