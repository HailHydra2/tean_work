package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.LikesDao;
import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.MessageWay;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    @Resource
    private LikesDao likesDao;

    @Resource
    private ResponseDao responseDao;

    @Override
    //添加（更新）点赞记录
    public boolean insertLikeInfo(Likes like){
        Response response = responseDao.selectByPrimaryKey(like.getResponseId());
        if(response == null){//点赞回复不存在
            return false;
        }
        //查看数据库中是否已经存在该用户对该回复的点赞记录
        LikesExample example = new LikesExample();
        LikesExample.Criteria criteria = example.createCriteria();
        //用户id一样的点赞记录
        criteria.andUserIdEqualTo(like.getUserId());
        //被点赞回复id一样的记录
        criteria.andResponseIdEqualTo(like.getResponseId());
        //查找记录
        List<Likes> likeList = likesDao.selectByExample(example);
        //原来的点赞记录标志位
        int oldFlag = 0;

        if(likeList.size() > 0){//已经存在记录
            like.setId(likeList.get(0).getId());
            //旧记录的标志位
            oldFlag = likeList.get(0).getFlag();
            //更新数据库
            likesDao.updateByPrimaryKey(like);
        }else{//不存在记录
            //插入数据库
            likesDao.insert(like);
        }

        //创建内部发送消息
        /**
         * 创建内部发送的消息
         * （1）保存操作者为点赞用户、被操作对象是被点赞回复
         * （2）通过like.flag保存消息产生方式
         * （3）分别保存新旧标志位到消息的flag变量和flag2变量
         */
        InternalMessage message = new InternalMessage();
        message.setOperator_id(like.getUserId());
        message.setObject_id(like.getResponseId());
        if(like.getFlag() == 1){//点赞
            message.setWay(MessageWay.LIKE_RESPONSE);
            message.setFlag(1);
            message.setFlag2(oldFlag);
        }else if(like.getFlag() == -1){//点灭
            message.setFlag(-1);
            message.setWay(MessageWay.DISLIKE_RESPONSE);
            message.setFlag2(oldFlag);
        }else if(like.getFlag() == 0){//取消点赞或者点灭
            if(oldFlag == 1){//取消点赞
                message.setWay(MessageWay.LIKE_RESPONSE);
                message.setFlag(0);
            }else{//取消点灭
                message.setWay(MessageWay.DISLIKE_RESPONSE);
                message.setFlag(0);
            }
        }
        messageService.updateInfoByMessage(message);
        return true;
    }
}
