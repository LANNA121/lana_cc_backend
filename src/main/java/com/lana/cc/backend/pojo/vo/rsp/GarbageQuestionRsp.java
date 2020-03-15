package com.lana.cc.backend.pojo.vo.rsp;

import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/15 10:43
 */
public class GarbageQuestionRsp {
    List<Question> questionList;

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public static class Question{
        private String name;
        private Integer sortId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getSortId() {
            return sortId;
        }

        public void setSortId(Integer sortId) {
            this.sortId = sortId;
        }
    }

}
