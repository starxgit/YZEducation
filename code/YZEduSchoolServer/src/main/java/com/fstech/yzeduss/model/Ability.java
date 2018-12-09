package com.fstech.yzeduss.model;

import lombok.Data;

/**
 * Created By shaoxin On 12/9/18
 */
@Data
public class Ability {
    private int ability_id;
    private int student_id;
    private int ability_theory;
    private int ability_practice;
    private int ability_language;
    private int ability_innovate;
    private int ability_think;
    private int ability_teamwork;
}