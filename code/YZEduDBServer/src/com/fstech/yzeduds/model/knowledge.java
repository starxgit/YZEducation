package com.fstech.yzeduds.model;

import java.io.Serializable;

/**
 * Created by shaoxin on 18-4-9.
 * 知识点的模型类
 */

public class knowledge{
    private int knowledge_id;               // 知识点Id
    private String knowledge_content;       // 知识点内容
    private int knowledge_rank;             // 知识点重要级别
    private int lesson_id;                  // 所属一节课Id

    public knowledge() {
    }

    public knowledge(int knowledge_id, String knowledge_content, int knowledge_rank, int lesson_id) {
        this.knowledge_id = knowledge_id;
        this.knowledge_content = knowledge_content;
        this.knowledge_rank = knowledge_rank;
        this.lesson_id = lesson_id;
    }

    public int getKnowledge_id() {
        return knowledge_id;
    }

    public void setKnowledge_id(int knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public String getKnowledge_content() {
        return knowledge_content;
    }

    public void setKnowledge_content(String knowledge_content) {
        this.knowledge_content = knowledge_content;
    }

    public int getKnowledge_rank() {
        return knowledge_rank;
    }

    public void setKnowledge_rank(int knowledge_rank) {
        this.knowledge_rank = knowledge_rank;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    @Override
    public String toString() {
        return "knowledge{" +
                "knowledge_id=" + knowledge_id +
                ", knowledge_content='" + knowledge_content + '\'' +
                ", knowledge_rank=" + knowledge_rank +
                ", lesson_id=" + lesson_id +
                '}';
    }
}
