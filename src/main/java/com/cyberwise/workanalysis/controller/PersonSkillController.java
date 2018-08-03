package com.cyberwise.workanalysis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyberwise.workanalysis.bean.PersonSkill;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.service.PersonSkillService;

@Controller
@RequestMapping("/personSkill")
public class PersonSkillController {
	@Autowired
	private PersonSkillService skillService;

	// 按名字岗位查询
	@RequestMapping("/findPersonSkillByName")
	@ResponseBody
	public CyberResultInfo findPersonSkillByName(String name, Integer postId) {
		List<PersonSkill> list = skillService.findPersonSkillByName(name, postId);
		// System.out.println(lists);
		return CyberResultInfo.ok(list);
	}

	// 按ID查询
	@RequestMapping("/findPersonSkillById")
	@ResponseBody
	public CyberResultInfo findPersonSkillById(Integer id) {
		PersonSkill skill = skillService.findPersonSkillById(id);
		return CyberResultInfo.ok(skill);
	}

	// 新增personskill
	@RequestMapping("/addPersonSkill")
	@ResponseBody
	public CyberResultInfo addPersonSkill(String name, Integer postId) {
		return skillService.addPersonSkill(name, postId);
	}

	// 加载统计表
	@RequestMapping("/loadTotal")
	@ResponseBody
	public CyberResultInfo loadTotal(Integer id) {
		return skillService.loadTotal(id);
	}

	// 加载大项表
	@RequestMapping("/loadBigTable")
	@ResponseBody
	public CyberResultInfo loadBigTable(Integer id, Integer skillType) {
		return skillService.loadBigTable(id, skillType);
	}

	// 加载小项表
	@RequestMapping("/loadSmallTable")
	@ResponseBody
	public CyberResultInfo loadSmallTable(Integer id, Integer pbigId) {
		return skillService.loadSmallTable(id, pbigId);
	}

	// 修改分数
	@RequestMapping("/updateMyScore")
	@ResponseBody
	public CyberResultInfo updateMyScore(Integer pskillId, Integer smallId, Integer myScore) {
		return skillService.updateMyScore(pskillId, smallId, myScore);
	}

	// 修改分数
	@RequestMapping("/showPersonChart")
	@ResponseBody
	public CyberResultInfo showPersonChart(String name, String startTime, String endTime) {
		return skillService.findPersonSkillByNameWithDate(name, startTime, endTime);
	}

}
