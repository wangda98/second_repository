package com.cyberwise.workanalysis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberwise.workanalysis.bean.PostProfessional;
import com.cyberwise.workanalysis.bean.Skill;
import com.cyberwise.workanalysis.bean.SkillBigItem;
import com.cyberwise.workanalysis.bean.SkillSmallItem;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.mapper.SkillManageMapper;

@Service
public class SkillManageService {
	@Autowired
	private SkillManageMapper manageMapper;
	private List<Skill> skillList = new ArrayList<>();
	private List<PostProfessional> PostProfessionalList;

	// 获取所有岗位
	public CyberResultInfo getAllPostProfessional() {
		List<PostProfessional> list = null;
		if (PostProfessionalList == null) {
			list = manageMapper.findAllPostProfessional();
			PostProfessionalList = list;
		} else {
			list = PostProfessionalList;
		}
		return CyberResultInfo.ok(list);
	}

	// 获取技能列表
	public CyberResultInfo getSkillList(Integer postId) {
		List<Skill> skills = null;
		if (postId == 0) {
			skills = manageMapper.findAllSkill();
		} else {
			skills = manageMapper.findSkillByPostId(postId);
		}
		skills = fillSkillList(skills);
		skillList = skills;
		// skills.forEach(System.out::println);
		return CyberResultInfo.ok(skills);
	}

	// 填充skillList信息
	private List<Skill> fillSkillList(List<Skill> skills) {
		for (Skill skill : skills) {
			fillSkill(skill);
		}
		return skills;
	}

	// 填充skill信息
	@SuppressWarnings("unchecked")
	private Skill fillSkill(Skill skill) {
		// System.out.println(skill.getId());
		for (SkillBigItem bigItem : skill.getBigItems()) {
			bigItem.setSmallItemNum(bigItem.getSmallItems().size());
			// System.out.println(bigItem.getSmallItems());
			Integer sum = bigItem.getSmallItems().stream().collect(Collectors.summingInt(x -> x.getScore()));
			bigItem.setTotalScore(sum);
		}
		skill.setBigItemNum(skill.getBigItems().size());
		Integer smallItemNum = skill.getBigItems().stream().collect(Collectors.summingInt(x -> x.getSmallItemNum()));
		skill.setSmallItemNum(smallItemNum);
		Integer totalScore = skill.getBigItems().stream().collect(Collectors.summingInt(x -> x.getTotalScore()));
		skill.setTotalScore(totalScore);
		// 添加postName
		CyberResultInfo info = getAllPostProfessional();
		List<PostProfessional> list = (List<PostProfessional>) info.getResult();
		String postName = list.stream().filter(x -> x.getId() == skill.getPostId()).map(x -> x.getProfessional())
				.findFirst().orElse("该专业已删除！");
		skill.setPostName(postName);
		return skill;
	}

	// 获取技能表
	public CyberResultInfo getSkillById(Integer id) {
		Skill skill = null;
		if (skillList != null && skillList.size() > 0) {
			skill = skillList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
			if (skill == null) {
				skill = manageMapper.findSkillById(id);
				skill = fillSkill(skill);
				skillList.add(skill);
			}
		} else {
			skill = manageMapper.findSkillById(id);
			skill = fillSkill(skill);
			skillList.add(skill);
		}
		return CyberResultInfo.ok(skill);
	}

	// 删除技能表
	public CyberResultInfo deleteSkill(Integer id) {
		Integer count = manageMapper.findBigItemCountById(id);
		if (count > 0) {
			return new CyberResultInfo(-1, "还有子项未被删除！", null);
		} else {
			manageMapper.deleteSkill(id);
		}
		return CyberResultInfo.ok();
	}

	// 复制技能表
	public CyberResultInfo copySkill(Integer id) {
		// 添加skill
		Skill skill = (Skill) getSkillById(id).getResult();
		skill.setId(null);
		skill.setStatus(3);
		skill.setUseTimes(0);
		skill.setCreateTime(new Date());
		manageMapper.addSkill(skill);
		// System.out.println(skill.getId());
		// 添加bigitem
		for (SkillBigItem b : skill.getBigItems()) {
			b.setId(null);
			b.setSkillId(skill.getId());
			manageMapper.addBigItem(b);
			// 添加smallitem
			for (SkillSmallItem s : b.getSmallItems()) {
				s.setId(null);
				s.setBigId(b.getId());
				manageMapper.addSmallItem(s);
			}
		}
		return CyberResultInfo.ok();
	}

	// 启用设置
	public CyberResultInfo startSkill(Skill skill) {
		// 查询是否启用
		Integer count = manageMapper.findStatusByPostId(skill.getPostId());
		if (count > 0) {
			return new CyberResultInfo(-1, "当前已有条目启用！", null);
		}
		manageMapper.updateStatusAndUseTimes(skill.getId(), 1, skill.getUseTimes());
		return CyberResultInfo.ok();
	}

	// 停用设置
	public CyberResultInfo stopSkill(Integer id) {
		manageMapper.updateStatus(id, 2);
		return CyberResultInfo.ok();
	}

	// 获取post名称
	public CyberResultInfo getPostName(Integer postId) {
		if (PostProfessionalList == null) {
			getAllPostProfessional();
		}
		// System.out.println(postId);
		String postName = PostProfessionalList.stream().filter(x -> x.getId() == postId).map(x -> x.getProfessional())
				.findFirst().get();
		return CyberResultInfo.ok(postName);
	}

	// 加载总表
	public CyberResultInfo loadTotal(Integer id) {
		CyberResultInfo result = getSkillById(id);
		Skill skill = (Skill) result.getResult();
		Object[][] data = new Object[4][3];
		data[0][0] = "人际沟通";
		data[0][1] = skill.getBigItems().stream().filter(x -> x.getType() == 1)
				.collect(Collectors.summingInt(x -> x.getSmallItemNum()));
		data[0][2] = skill.getBigItems().stream().filter(x -> x.getType() == 1)
				.collect(Collectors.summingInt(x -> x.getTotalScore()));
		data[1][0] = "基础技能";
		data[1][1] = skill.getBigItems().stream().filter(x -> x.getType() == 2)
				.collect(Collectors.summingInt(x -> x.getSmallItemNum()));
		data[1][2] = skill.getBigItems().stream().filter(x -> x.getType() == 2)
				.collect(Collectors.summingInt(x -> x.getTotalScore()));
		data[2][0] = "高级技能";
		data[2][1] = skill.getBigItems().stream().filter(x -> x.getType() == 3)
				.collect(Collectors.summingInt(x -> x.getSmallItemNum()));
		data[2][2] = skill.getBigItems().stream().filter(x -> x.getType() == 3)
				.collect(Collectors.summingInt(x -> x.getTotalScore()));
		data[3][0] = "汇总";
		data[3][1] = skill.getBigItems().stream().collect(Collectors.summingInt(x -> x.getSmallItemNum()));
		data[3][2] = skill.getBigItems().stream().collect(Collectors.summingInt(x -> x.getTotalScore()));
		return CyberResultInfo.ok(data);
	}

	// 加载大项目表
	public CyberResultInfo loadBigTable(Integer id, Integer type) {
		CyberResultInfo result = getSkillById(id);
		Skill skill = (Skill) result.getResult();
		List<SkillBigItem> list = skill.getBigItems().stream().filter(x -> x.getType() == type)
				.collect(Collectors.toList());
		return CyberResultInfo.ok(list);
	}

	// 加载小项目表
	public CyberResultInfo loadSmallTable(Integer id, Integer bigId) {
		CyberResultInfo result = getSkillById(id);
		Skill skill = (Skill) result.getResult();
		// System.out.println(id + "----" + bigId);
		SkillBigItem bigItem = skill.getBigItems().stream().filter(x -> x.getId() == bigId).findFirst().get();
		return CyberResultInfo.ok(bigItem.getSmallItems());
	}

	// 添加skill
	public CyberResultInfo addSkill(Skill skill) {
		// 补全信息
		skill.setCreateTime(new Date());
		skill.setUseTimes(0);
		skill.setStatus(3);
		skill.setRemoveFlag(0);
		manageMapper.addSkill(skill);
		String url = "person_skill_manage.html?type=2&postId=" + skill.getPostId() + "&id=" + skill.getId();
		return CyberResultInfo.ok(url);
	}

	// 修改skill
	public CyberResultInfo updateSkill(Skill skill) {
		// 移除缓存
		skillList.removeIf(x -> x.getId() == skill.getId());
		manageMapper.updateSkill(skill);
		String url = "person_skill_manage.html?type=2&postId=" + skill.getPostId() + "&id=" + skill.getId();
		return CyberResultInfo.ok(url);
	}

	// 添加大项
	public CyberResultInfo addBigItem(SkillBigItem bigItem) {
		// 移除缓存
		skillList.removeIf(x -> x.getId() == bigItem.getSkillId());
		bigItem.setRemoveFlag(0);
		bigItem.setId(null);
		manageMapper.addBigItem(bigItem);
		return CyberResultInfo.ok();
	}

	// 删除大项
	public CyberResultInfo removeBigItem(SkillBigItem bigItem) {
		Integer count = manageMapper.findSmallItemCountById(bigItem.getId());
		if (count > 0) {
			return new CyberResultInfo(-1, "删除失败，该项下还有小项！", null);
		}
		manageMapper.deleteBigItem(bigItem);
		// 移除缓存
		skillList.removeIf(x -> x.getId() == bigItem.getSkillId());
		return CyberResultInfo.ok();
	}

	// 添加小项
	public CyberResultInfo addSmallItem(Integer skillId, SkillSmallItem smallItem) {
		// System.out.println(skillId);
		// 移除缓存
		skillList.removeIf(x -> x.getId() == skillId);
		if (smallItem.getScore() == null)
			smallItem.setScore(1);
		smallItem.setRemoveFlag(0);
		smallItem.setId(null);
		manageMapper.addSmallItem(smallItem);
		return CyberResultInfo.ok();
	}

	// 修改小项
	public CyberResultInfo updateSmallItem(Integer skillId, SkillSmallItem smallItem) {
		// 移除缓存
		skillList.removeIf(x -> x.getId() == skillId);
		manageMapper.updateSmallItem(smallItem);
		return CyberResultInfo.ok();
	}

	// 删除小项
	public CyberResultInfo removeSmallItem(Integer skillId, Integer id) {
		// 移除缓存
		skillList.removeIf(x -> x.getId() == skillId);
		manageMapper.deleteSmallItem(id);
		return CyberResultInfo.ok();
	}

	// 按类型查询skill
	@SuppressWarnings("unchecked")
	public CyberResultInfo findSkillByStatus(Integer status) {
		CyberResultInfo result = getSkillList(0);
		List<Skill> skill = (List<Skill>) result.getResult();
		skill = skill.stream().filter(x -> x.getStatus() == status).collect(Collectors.toList());
		return CyberResultInfo.ok(skill);
	}

}
