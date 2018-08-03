package com.cyberwise.workanalysis.bean;

import java.util.List;

public class SkillBigItem {
	private Integer id;
	private String name;// 大项名
	private Integer type;// 种类 1人际沟通 2基础技能 3高级技能
	private Integer removeFlag;// 删除标记 0正常 1删除
	private Integer skillId;// 关联的大项ID
	private List<SkillSmallItem> smallItems;
	// 表格数据
	private Integer smallItemNum;// 评分小项数
	private Integer totalScore;// 小项总分

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRemoveFlag() {
		return removeFlag;
	}

	public void setRemoveFlag(Integer removeFlag) {
		this.removeFlag = removeFlag;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public List<SkillSmallItem> getSmallItems() {
		return smallItems;
	}

	public void setSmallItems(List<SkillSmallItem> smallItems) {
		this.smallItems = smallItems;
	}

	public Integer getSmallItemNum() {
		return smallItemNum;
	}

	public void setSmallItemNum(Integer smallItemNum) {
		this.smallItemNum = smallItemNum;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	@Override
	public String toString() {
		return "SkillBigItem [id=" + id + ", name=" + name + ", type=" + type + ", removeFlag=" + removeFlag
				+ ", skillId=" + skillId + ", smallItems=" + smallItems + ", smallItemNum=" + smallItemNum
				+ ", totalScore=" + totalScore + "]";
	}

}
