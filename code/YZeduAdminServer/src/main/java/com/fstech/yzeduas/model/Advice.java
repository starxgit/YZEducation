package com.fstech.yzeduas.model;

import lombok.Data;

/**
 * Created By shaoxin On 12/5/18
 */
@Data
public class Advice {
    private int advice_id;
    private int user_id;
    private String advice_content;
    private String advice_time;
    private int is_read;
}