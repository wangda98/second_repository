package com.cyberwise.workanalysis.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersonSkill {
	private Integer id;
	private String name;
	private String version;// 版本号
	@JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
	private Date createTime;// 创建时间
	@JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
	private Date updateTime;// 更新时间
	private String createPerson;// 创建人
	private Integer useTimes;// 使用次数
	private Integer status;// 状态 1正常 2停用 3草稿
	private Integer removeFlag;// 删除标记 0正常 1删除
	private Integer postId;// 关联的项目id
	private Integer skillId;// 技能表ID
	private List<PersonSkillBigItem> personBigItems;
	// 表格数据
	private Integer smallItemNum;// 评分小项数
	private Integer bigItemNum;// 评分大项数
	private Integer totalScore;// 项目总分
	private String postName;// 项目名称
	private Integer myTotalScore;// 我的总分

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

	public List<PersonSkillBigItem> getPersonBigItems() {
		return personBigItems;
	}

	public void setPersonBigItems(List<PersonSkillBigItem> personBigItems) {
		this.personBigItems = personBigItems;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Integer getUseTimes() {
		return useTimes;
	}

	public void setUseTimes(Integer useTimes) {
		this.useTimes = useTimes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRemoveFlag() {
		return removeFlag;
	}

	public void setRemoveFlag(Integer removeFlag) {
		this.removeFlag = removeFlag;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getSmallItemNum() {
		return smallItemNum;
	}

	public void setSmallItemNum(Integer smallItemNum) {
		this.smallItemNum = smallItemNum;
	}

	public Integer getBigItemNum() {
		return bigItemNum;
	}

	public void setBigItemNum(Integer bigItemNum) {
		this.bigItemNum = bigItemNum;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Integer getMyTotalScore() {
		return myTotalScore;
	}

	public void setMyTotalScore(Integer myTotalScore) {
		this.myTotalScore = myTotalScore;
	}

	@Override
	public String toString() {
		return "PersonSkill [id=" + id + ", name=" + name + ", version=" + version + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createPerson=" + createPerson + ", useTimes=" + useTimes
				+ ", status=" + status + ", removeFlag=" + removeFlag + ", postId=" + postId + ", skillId=" + skillId
				+ ", personBigItems=" + personBigItems + ", smallItemNum=" + smallItemNum + ", bigItemNum=" + bigItemNum
				+ ", totalScore=" + totalScore + ", postName=" + postName + ", myTotalScore=" + myTotalScore + "]";
	}

}
