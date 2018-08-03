package com.cyberwise.workanalysis.bean;

import java.util.List;

public class PersonSkillBigItem {
	private Integer id;
	private String name;// 大项名
	private Integer type;// 种类 1人际沟通 2基础技能 3高级技能
	private Integer skillId;// 关联的大项ID
	private Integer personId;// 关联的个人ID
	private Integer bigId;// 关联的大项ID
	private List<PersonSkillSmallItem> personSmallItems;
	// 表格数据
	private Integer smallItemNum;// 评分小项数
	private Integer totalScore;// 小项总分
	private Integer myTotalScore;// 我的总分

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBigId() {
		return bigId;
	}

	public void setBigId(Integer bigId) {
		this.bigId = bigId;
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

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public List<PersonSkillSmallItem> getPersonSmallItems() {
		return personSmallItems;
	}

	public void setPersonSmallItems(List<PersonSkillSmallItem> personSmallItems) {
		this.personSmallItems = personSmallItems;
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

	public Integer getMyTotalScore() {
		return myTotalScore;
	}

	public void setMyTotalScore(Integer myTotalScore) {
		this.myTotalScore = myTotalScore;
	}

	@Override
	public String toString() {
		return "PersonSkillBigItem [id=" + id + ", name=" + name + ", type=" + type + ", skillId=" + skillId
				+ ", personId=" + personId + ", bigId=" + bigId + ", PersonSmallItems=" + personSmallItems
				+ ", smallItemNum=" + smallItemNum + ", totalScore=" + totalScore + ", myTotalScore=" + myTotalScore
				+ "]";
	}

}
