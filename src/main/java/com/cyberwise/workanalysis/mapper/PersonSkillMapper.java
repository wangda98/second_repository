package com.cyberwise.workanalysis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyberwise.workanalysis.bean.PersonSkill;
import com.cyberwise.workanalysis.bean.PersonSkillBigItem;
import com.cyberwise.workanalysis.bean.PersonSkillSmallItem;

public interface PersonSkillMapper {
	List<PersonSkill> findAllPersonSkill();

	List<PersonSkill> findPersonSkillByNameAndPostId(@Param("name") String name, @Param("postId") Integer postId);

	List<PersonSkill> findPersonSkillByName(String name);

	List<PersonSkill> findPersonSkillByNameWithDate(@Param("name") String name, @Param("startDate") String startDate,
			@Param("endDate") String endDate);

	PersonSkill findPersonSkillById(Integer id);

	Integer findSkillIdByPostId(Integer postId);

	void addPersonSkill(PersonSkill personSkill);

	void addPersonSkillBig(PersonSkillBigItem bigItem);

	void addPersonSkillSmall(PersonSkillSmallItem smallItem);

	void updateUseTimes(@Param("id") Integer id, @Param("useTimes") Integer useTimes);

	void updateMyScore(@Param("id") Integer smallId, @Param("myScore") Integer myScore);

	void updateUpdateTime(@Param("id") Integer id, @Param("time") Date updateTime);
}
