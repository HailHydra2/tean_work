package com.fzu.teamwork.view;

import com.fzu.teamwork.model.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class ResponseVO {
    private Response response;
    private int quality;//回复质量 -1：差   0：普通   1：优质
    private String content;//回复内容
    private int like;//用户点赞情况-1：点灭  0：都没有  1：点赞
    private boolean doesReported;//用户是否投诉过
    private String authorName;//回复者姓名


    static public int getQuality(int likeNum, int disLikeNum, int reportNum){
        if(reportNum > 10 && reportNum > 2*likeNum && disLikeNum > likeNum){
            return -1;
        }else if(likeNum > 10 && likeNum > 5*reportNum && likeNum > 3*disLikeNum){
            return 1;
        }
        return 0;
    }
}
