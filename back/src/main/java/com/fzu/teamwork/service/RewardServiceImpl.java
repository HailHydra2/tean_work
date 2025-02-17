package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.RewardDao;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.Reward;
import com.fzu.teamwork.model.RewardExample;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.util.RewardType;
import com.fzu.teamwork.view.RewardVO;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RewardServiceImpl implements RewardService{

    @Resource(name = "userServiceImpl")
    private UserService userServiceImpl;

    @Resource
    private RewardDao rewardDao;

    @Resource
    private UserDao userDao;

    //用户账号信息
    private UserVO userVO;


    /**
     *
     * @param reward  要保存的奖励申请信息
     * @return  是否保存成功（true 成功  false 积分不足）
     * 保存奖励函数
     */
    @Override
    public Boolean insertReward(Reward reward){
        //查找申请用户
        User user = userServiceImpl.getUserById(reward.getUserId());
        //获取用户的账户信息（将user对象转换为userVO对象）
        userVO = userServiceImpl.convertToUserVo(user);
        //计算用户所需积分（若积分充足返回所需积分，不足返回-1）
        int needScore = calculateScore(reward);
        //判断用户积分是否充足
        if(needScore > 0) {
            //积分充足
            //用户当前积分
            int score = userVO.getAccountData().getScore();
            //扣除兑换积分
            userVO.getAccountData().setScore(score - needScore);
            //更新用户数据
            userServiceImpl.updateUser(userVO);
            rewardDao.insert(reward);
            return true;
        }
        return false;
    }


    //判断用户的积分是否足够申请, 返回值为所需积分（积分不足返回-1）
    public int calculateScore(Reward reward){
        //计算所需积分数
        int needScore = calculate(reward.getType(), reward.getRewardNum());
        if(userVO.getAccountData().getScore() >= needScore){
            //积分充足
            return needScore;
        }else{
            //积分不足
            return -1;
        }
    }

    //计算申请的积分数（类别兑换规则*数量），若类别非法返回-1
    public int calculate(String rewardType, double num){
        if(rewardType.equals(RewardType.SERVICE)){
            //申请类别为党员服务时长
            return (int)(num*RewardType.SERVICE_SCORE);//一个时长对应100积分
        }else if(rewardType.equals(RewardType.SYNTHETIC)){
            //兑换类别为综测
            return (int)(num*RewardType.SYNTHETIC_SCORE);//一分综测对应100积分
        }else{
            log.info("申请类别输入错误：{}",rewardType);
            return -1;
        }
    }

    //获取奖励申请列表
    @Override
    public List<RewardVO> getRewardList(){
        RewardExample example = new RewardExample();
        example.setOrderByClause("`APPLY_TIME` DESC");
        List<Reward> rewardsList = rewardDao.selectByExample(example);
        return convertListToVO(rewardsList);
    }

    public RewardVO convertToVO(Reward reward){
        RewardVO rewardVO = new RewardVO();
        rewardVO.setReward(reward);
        User user = userDao.selectByPrimaryKey(reward.getUserId());
        rewardVO.setName(user.getName());
        rewardVO.setAccount(user.getAccount());
        return rewardVO;
    }

    public List<RewardVO> convertListToVO(List<Reward> list){
        List<RewardVO> rewardVOList = new ArrayList<>();
        for (Reward reward : list){
            rewardVOList.add(convertToVO(reward));
        }
        return rewardVOList;
    }

}
