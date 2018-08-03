package com.cyberwise.workanalysis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cyberwise.workanalysis.bean.WorkCapacity2;
import com.cyberwise.workanalysis.bean.WorkCapacity2Example;
import com.cyberwise.workanalysis.common.GroupUser;
import com.cyberwise.workanalysis.common.PersonalPerformInProject;
import com.cyberwise.workanalysis.common.PersonalWorkAnalysis;
import com.cyberwise.workanalysis.common.PostInfo;
import com.cyberwise.workanalysis.common.UserPostInfo;

public interface WorkCapacity2Mapper {
	long countByExample(WorkCapacity2Example example);

	int deleteByExample(WorkCapacity2Example example);

	int deleteByPrimaryKey(Integer id);

	int insert(WorkCapacity2 record);

	int insertSelective(WorkCapacity2 record);

	List<WorkCapacity2> selectByExample(WorkCapacity2Example example);

	WorkCapacity2 selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") WorkCapacity2 record, @Param("example") WorkCapacity2Example example);

	int updateByExample(@Param("record") WorkCapacity2 record, @Param("example") WorkCapacity2Example example);

	int updateByPrimaryKeySelective(WorkCapacity2 record);

	int updateByPrimaryKey(WorkCapacity2 record);

	// 自定义查询
	List<WorkCapacity2> findAllProject(String date);

	List<Map<String, Object>> findWeekCapacity(String sql);

	List<WorkCapacity2> findWeekData();

	List<PersonalWorkAnalysis> findPersonalWorkAnalysis(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("names") String names);

	List<PersonalWorkAnalysis> findWeekOfPersonalWorkAnalysis(@Param("startDate") String startDate,
			@Param("endDate") String endDate);

	List<PersonalWorkAnalysis> findAverageline(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("positionId") Integer positionId);

	List<PostInfo> findOperatingPost();

	List<UserPostInfo> findUserPostInfo();

	List<PersonalPerformInProject> findPersonalWorkCapacityInProject(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("names") String names);

	List<PersonalPerformInProject> findWeekOfPersonalWorkCapacityInProject(@Param("startDate") String startDate,
			@Param("endDate") String endDate);

	List<GroupUser> findGroups();

}