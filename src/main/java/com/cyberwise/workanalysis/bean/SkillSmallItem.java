package com.cyberwise.workanalysis.bean;

public class SkillSmallItem {
	private Integer id;
	private String name;// 小项名
	private Integer score;// 分数
	private Integer removeFlag;// 删除标记 0正常 1删除
	private Integer bigId;// 关联的大项ID

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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getRemoveFlag() {
		return removeFlag;
	}

	public void setRemoveFlag(Integer removeFlag) {
		this.removeFlag = removeFlag;
	}

	public Integer getBigId() {
		return bigId;
	}

	public void setBigId(Integer bigId) {
		this.bigId = bigId;
	}

	@Override
	public String toString() {
		return "SkillSmallItem [id=" + id + ", name=" + name + ", score=" + score + ", removeFlag=" + removeFlag
				+ ", bigId=" + bigId + "]";
	}

}
