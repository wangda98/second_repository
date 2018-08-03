package com.cyberwise.workanalysis.mapper;

import com.cyberwise.workanalysis.bean.GroupAndUser;
import com.cyberwise.workanalysis.bean.GroupAndUserExample;
import com.cyberwise.workanalysis.bean.GroupAndUserValue;
import com.cyberwise.workanalysis.bean.ZtUser;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupAndUserMapper {
    long countByExample(GroupAndUserExample example);

    int deleteByExample(GroupAndUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupAndUser record);

    int insertSelective(GroupAndUser record);

    List<GroupAndUser> selectByExample(GroupAndUserExample example);

    GroupAndUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupAndUser record, @Param("example") GroupAndUserExample example);

    int updateByExample(@Param("record") GroupAndUser record, @Param("example") GroupAndUserExample example);

    int updateByPrimaryKeySelective(GroupAndUser record);

    int updateByPrimaryKey(GroupAndUser record);
    /**
     * 查询在组成员的名字以及组名的详细信息(三表联查)
     * @return
     */
    List<GroupAndUserValue> queryAllMember();
    List<GroupAndUserValue> queryByGroupId(Integer id);
    List<ZtUser> likequery(String string);
}