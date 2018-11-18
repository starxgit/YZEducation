package com.fstech.yzeduds.model;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-8.
 * 课程分类的模型类
 */

public class ClassificationBean implements Serializable {
    private int classification_id;          // 分类id
    private String classification_name;     // 分类名
    private int classification_own;         // 所属分类

    public ClassificationBean() {
    }

    public ClassificationBean(int classification_id, String classification_name, int classification_own) {
        this.classification_id = classification_id;
        this.classification_name = classification_name;
        this.classification_own = classification_own;
    }

    public int getClassification_id() {
        return classification_id;
    }

    public void setClassification_id(int classification_id) {
        this.classification_id = classification_id;
    }

    public String getClassification_name() {
        return classification_name;
    }

    public void setClassification_name(String classification_name) {
        this.classification_name = classification_name;
    }

    public int getClassification_own() {
        return classification_own;
    }

    public void setClassification_own(int classification_own) {
        this.classification_own = classification_own;
    }

    @Override
    public String toString() {
        return "ClassificationBean{" +
                "classification_id=" + classification_id +
                ", classification_name='" + classification_name + '\'' +
                ", classification_own=" + classification_own +
                '}';
    }
}
