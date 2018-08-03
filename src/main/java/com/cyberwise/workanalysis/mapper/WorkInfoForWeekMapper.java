package com.cyberwise.workanalysis.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cyberwise.workanalysis.bean.WorkInfoForWeek;
import com.cyberwise.workanalysis.bean.WorkInfoForWeekExample;

public interface WorkInfoForWeekMapper {
    long countByExample(WorkInfoForWeekExample example);

    int deleteByExample(WorkInfoForWeekExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkInfoForWeek record);

    int insertSelective(WorkInfoForWeek record);

    List<WorkInfoForWeek> selectByExample(WorkInfoForWeekExample example);

    WorkInfoForWeek selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkInfoForWeek record, @Param("example") WorkInfoForWeekExample example);

    int updateByExample(@Param("record") WorkInfoForWeek record, @Param("example") WorkInfoForWeekExample example);

    int updateByPrimaryKeySelective(WorkInfoForWeek record);

    int updateByPrimaryKey(WorkInfoForWeek record);
    
    List<Map<Object,Object>> findIndex(@Param("beginDate")Date beginDate,@Param("endDate")Date endDate);
    
    List<String> findObject();
}