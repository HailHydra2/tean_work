package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.ReportQuestionDao;
import com.fzu.teamwork.dao.ReportResponseDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ReportController {

    @Resource
    private ReportService reportService;


    //实现获取所有被举报的问题接口
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @GetMapping("/questionReports")
    public List<Question> testGetReportQuestion(){
        return reportService.getReportQuestion();
    }

    //实现获取所有被举报的回复接口
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @GetMapping("/responseReports")
    public List<Response> testGetReportResponse(){
        return reportService.getReportResponse();
    }

    //实现举报问题接口
    @LoginToken//登录权限
    @UserLimit//普通用户权限
    @PostMapping("questionReport")
    public @ResponseBody
    AjaxResponse addQuestionReport(@RequestBody ReportQuestion reportQuestion){
        reportService.addQuestionReport(reportQuestion);
        return AjaxResponse.success();
    }

    //实现举报回复接口
    @LoginToken//登录权限
    @UserLimit//普通用户权限
    @PostMapping("/responseReport")
    public @ResponseBody AjaxResponse addResponseReport(@RequestBody ReportResponse reportResponse){
        reportService.addResponseReport(reportResponse);
        return AjaxResponse.success();
    }
}
