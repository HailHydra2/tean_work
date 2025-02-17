package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.dao.*;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.service.*;
import com.fzu.teamwork.util.ErrorStatus;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class QuestionController {

    @Resource
    private QuestionService questionService;

    //获取问题列表接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/questions")
    public @ResponseBody AjaxResponse getQuestionPage(@RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getQuestionPage(questionPage);
        return AjaxResponse.success(page);
    }

    //新增问题接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/question")
    public  @ResponseBody AjaxResponse insertQuestion(@RequestBody QuestionVO questionVO){
        return AjaxResponse.success(questionService.insertQuestion(questionVO));
    }

    //获取个人中心用户问题列表接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/userQuestions/{uid}")
    public @ResponseBody AjaxResponse getUserQuestionPage
    (@PathVariable Integer uid,  @RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getQuestionPage(uid,questionPage,
                QuestionService.QUESTION_TYPE);
        return AjaxResponse.success(page);
    }

    //个人中心关注问题列表接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/userAttentions/{uid}")
    public @ResponseBody AjaxResponse getAttentionQuestionPage
    (@PathVariable Integer uid, @RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getQuestionPage(uid, questionPage,
                QuestionService.ATTENTION_TYPE);
        return AjaxResponse.success(page);
    }

    //用户回答过的问题列表接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/userResponseQuestions/{uid}")
    public @ResponseBody AjaxResponse getUserResponseQuestion
    (@PathVariable Integer uid, @RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getQuestionPage(uid, questionPage,
                QuestionService.RESPONSE_TYPE);
        return AjaxResponse.success(page);
    }

    //问题详细信息接口（除了基本信息还包含与用户有关的信息——是否已关注等）
    @SneakyThrows
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @GetMapping("/question/{id}/{uid}")
    public @ResponseBody AjaxResponse getDetailQuestion
    (@PathVariable Integer id, @PathVariable Integer uid){
        Question question = questionService.getQuestionById(id);
        if(question == null){//查询问题不存在
            return AjaxResponse.error(ErrorStatus.QUESTION_NOT_EXIT,"查询问题已被删除");
        }
        QuestionVO questionVO = questionService.convertToVO(question);
        //添加与用户uid之间的关系
        questionService.addRelationToUId(questionVO, uid);
        return AjaxResponse.success(questionVO);
    }

    //问题详细信息接口（只包含问题的基本信息——标题和内容）
    @SneakyThrows
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @GetMapping("/question/{id}")
    public @ResponseBody AjaxResponse getDetailQuestion(@PathVariable Integer id){
        Question question = questionService.getQuestionById(id);
        if(question == null){//查询问题不存在
            return AjaxResponse.error(ErrorStatus.QUESTION_NOT_EXIT,"查询问题已被删除");
        }
        QuestionVO questionVO = questionService.convertToVO(question);
        return AjaxResponse.success(questionVO);
    }

    //删除问题接口
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @DeleteMapping("/question/{id}")
    public @ResponseBody AjaxResponse deleteQuestion(@PathVariable int id){
        if(questionService.deleteQuestionById(id) == true){//删除成功
            return AjaxResponse.success();
        }else{//删除失败——要删除的问题不存在
            return AjaxResponse.error(ErrorStatus.QUESTION_NOT_EXIT,
                    "抱歉，您要删除的问题不存在");
        }
    }

    //批量删除问题
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @DeleteMapping("/questions")
    public @ResponseBody AjaxResponse deleteQuestions(@RequestBody List<Integer> questionIdList){
        List<Integer> failedList;//删除失败的问题id列表
        failedList = questionService.deleteQuestionsById(questionIdList);
        String message = "成功删除" + (questionIdList.size() - failedList.size()) + "条问题";
        if(failedList.size() == 0){//全部成功
            return AjaxResponse.success(message);
        }else{
            message += ",id为[";
            for(Integer id : failedList){
                message += (id + ",");
            }
            message += "]的问题不存在";
            return AjaxResponse.error(ErrorStatus.QUESTION_NOT_EXIT, message);
        }
    }
}
