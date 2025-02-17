package com.fzu.teamwork.model;

import java.util.List;

//QuestionService用于获取问题列表的抽象策略基类
public abstract class QuestionStrategy {

    public abstract List<Question> getQuestionList();

    //获取分页下面的按钮列表
    public abstract void getButtonList();
}
