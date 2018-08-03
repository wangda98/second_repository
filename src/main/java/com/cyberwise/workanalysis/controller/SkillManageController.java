package com.cyberwise.workanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyberwise.workanalysis.bean.Skill;
import com.cyberwise.workanalysis.bean.SkillBigItem;
import com.cyberwise.workanalysis.bean.SkillSmallItem;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.service.SkillManageService;

/**
 * 技能管理
 */
@Controller
@RequestMapping("/skill")
public class SkillManageController {
	@Autowired
	private SkillManageService manageService;

	// 获取所有岗位
	@RequestMapping("/getAllPostProfessional")
	@ResponseBody
	public CyberResultInfo getAllPostProfessional() {
		CyberResultInfo resultInfo = manageService.getAllPostProfessional();
		return resultInfo;
	}

	// 获取技能列表
	@RequestMapping("/getSkillList")
	@ResponseBody
	public CyberResultInfo getSkillList(Integer postId) {
		CyberResultInfo resultInfo = manageService.getSkillList(postId);
		return resultInfo;
	}

	// 根据ID获取技能表
	@RequestMapping("/getSkillById")
	@ResponseBody
	public CyberResultInfo getSkillById(Integer id) {
		CyberResultInfo resultInfo = manageService.getSkillById(id);
		return resultInfo;
	}

	// 根据ID删除技能表
	@RequestMapping("/deleteSkill")
	@ResponseBody
	public CyberResultInfo deleteSkill(Integer id) {
		return manageService.deleteSkill(id);
	}

	// 复制技能表
	@RequestMapping("/copySkill")
	@ResponseBody
	public CyberResultInfo copySkill(Integer id) {
		return manageService.copySkill(id);
	}

	// 启用接口
	@RequestMapping("/startSkill")
	@ResponseBody
	public CyberResultInfo startSkill(Skill skill) {
		return manageService.startSkill(skill);
	}

	// 停用接口
	@RequestMapping("/stopSkill")
	@ResponseBody
	public CyberResultInfo stopSkill(Integer id) {
		return manageService.stopSkill(id);
	}

	// 获取post名称
	@RequestMapping("/getPostName")
	@ResponseBody
	public CyberResultInfo getPostName(Integer postId) {
		return manageService.getPostName(postId);
	}

	// 加载总表
	@RequestMapping("/loadTotal")
	@ResponseBody
	public CyberResultInfo loadTotal(Integer id) {
		return manageService.loadTotal(id);
	}

	// 加载大项目表
	@RequestMapping("/loadBigTable")
	@ResponseBody
	public CyberResultInfo loadBigTable(Integer id, Integer type) {
		return manageService.loadBigTable(id, type);
	}

	// 加载小项目表
	@RequestMapping("/loadSmallTable")
	@ResponseBody
	public CyberResultInfo loadSmallTable(Integer id, Integer bigId) {
		return manageService.loadSmallTable(id, bigId);
	}

	// 添加skill
	@RequestMapping("/addSkill")
	@ResponseBody
	public CyberResultInfo addSkill(Skill skill) {
		return manageService.addSkill(skill);
	}

	// 修改skill
	@RequestMapping("/updateSkill")
	@ResponseBody
	public CyberResultInfo updateSkill(Skill skill) {
		return manageService.updateSkill(skill);
	}

	// 添加大项
	@RequestMapping("/addBigItem")
	@ResponseBody
	public CyberResultInfo addBigItem(SkillBigItem bigItem) {
		return manageService.addBigItem(bigItem);
	}

	// 删除大项
	@RequestMapping("/removeBigItem")
	@ResponseBody
	public CyberResultInfo removeBigItem(SkillBigItem bigItem) {
		return manageService.removeBigItem(bigItem);
	}

	// 添加小项
	@RequestMapping("/addSmallItem")
	@ResponseBody
	public CyberResultInfo addSmallItem(Integer skillId, SkillSmallItem smallItem) {
		return manageService.addSmallItem(skillId, smallItem);
	}

	// 修改小项
	@RequestMapping("/updateSmallItem")
	@ResponseBody
	public CyberResultInfo updateSmallItem(Integer skillId, SkillSmallItem smallItem) {
		return manageService.updateSmallItem(skillId, smallItem);
	}

	// 删除小项
	@RequestMapping("/removeSmallItem")
	@ResponseBody
	public CyberResultInfo removeSmallItem(Integer skillId, Integer id) {
		return manageService.removeSmallItem(skillId, id);
	}
	
	// 按类型查询skill
	@RequestMapping("/findSkillByStatus")
	@ResponseBody
	public CyberResultInfo findSkillByStatus(Integer status) {
		return manageService.findSkillByStatus(status);
	}
}
