package com.cyberwise.workanalysis.bean;

public class PersonSkillSmallItem {
	private Integer id;
	private String name;// 小项名
	private Integer score;// 分数
	private Integer pbigId;// 关联的大项ID
	private Integer smallId;// 关联的小项ID
	private Integer myScore;// 我的分数

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

	public Integer getPbigId() {
		return pbigId;
	}

	public void setPbigId(Integer pbigId) {
		this.pbigId = pbigId;
	}

	public Integer getSmallId() {
		return smallId;
	}

	public void setSmallId(Integer smallId) {
		this.smallId = smallId;
	}

	public Integer getMyScore() {
		return myScore;
	}

	public void setMyScore(Integer myScore) {
		this.myScore = myScore;
	}

	@Override
	public String toString() {
		return "PersonSkillSmallItem [id=" + id + ", name=" + name + ", score=" + score + ", pbigId=" + pbigId
				+ ", smallId=" + smallId + ", myScore=" + myScore + "]";
	}

}
