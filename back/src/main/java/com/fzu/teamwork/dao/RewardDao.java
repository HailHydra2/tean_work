package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.Reward;
import com.fzu.teamwork.model.RewardExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RewardDao {
    long countByExample(RewardExample example);

    int deleteByExample(RewardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reward record);

    int insertSelective(Reward record);

    List<Reward> selectByExample(RewardExample example);

    Reward selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reward record, @Param("example") RewardExample example);

    int updateByExample(@Param("record") Reward record, @Param("example") RewardExample example);

    int updateByPrimaryKeySelective(Reward record);

    int updateByPrimaryKey(Reward record);

}