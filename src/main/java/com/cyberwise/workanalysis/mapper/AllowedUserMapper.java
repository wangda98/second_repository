package com.cyberwise.workanalysis.mapper;

import com.cyberwise.workanalysis.bean.AllowedUser;
import com.cyberwise.workanalysis.bean.AllowedUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AllowedUserMapper {
    long countByExample(AllowedUserExample example);

    int deleteByExample(AllowedUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AllowedUser record);

    int insertSelective(AllowedUser record);

    List<AllowedUser> selectByExample(AllowedUserExample example);

    AllowedUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AllowedUser record, @Param("example") AllowedUserExample example);

    int updateByExample(@Param("record") AllowedUser record, @Param("example") AllowedUserExample example);

    int updateByPrimaryKeySelective(AllowedUser record);

    int updateByPrimaryKey(AllowedUser record);
}