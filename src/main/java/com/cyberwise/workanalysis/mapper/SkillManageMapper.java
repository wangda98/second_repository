package com.cyberwise.workanalysis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyberwise.workanalysis.bean.PostProfessional;
import com.cyberwise.workanalysis.bean.Skill;
import com.cyberwise.workanalysis.bean.SkillBigItem;
import com.cyberwise.workanalysis.bean.SkillSmallItem;

public interface SkillManageMapper {
	List<PostProfessional> findAllPostProfessional();

	List<Skill> findAllSkill();

	List<Skill> findSkillByPostId(Integer postId);

	Skill findSkillById(Integer id);

	Integer findBigItemCountById(Integer id);

	Integer findSmallItemCountById(Integer id);

	void deleteSkill(Integer id);

	Integer addSkill(Skill skill);

	Integer addBigItem(SkillBigItem bigItem);

	Integer addSmallItem(SkillSmallItem smallItem);

	void updateStatus(@Param("id") Integer id, @Param("status") Integer status);

	void updateStatusAndUseTimes(@Param("id") Integer id, @Param("status") Integer status,
			@Param("useTimes") Integer useTimes);

	void updateUseTimes(@Param("id") Integer id, @Param("useTimes") Integer useTimes);

	Integer findStatusByPostId(Integer postId);

	void updateSkill(Skill skill);

	void deleteBigItem(SkillBigItem bigItem);

	void updateSmallItem(SkillSmallItem smallItem);

	void deleteSmallItem(Integer id);
}
