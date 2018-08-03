package com.cyberwise.workanalysis.common;

import java.util.Date;
import java.util.List;

//com.cyberwise.workanalysis.common.GroupUser
public class GroupUser {
	private Integer id;
	private String groupName;
	private Integer groupType;
	private Integer removedFlag;
	private Date createdTime;
	private Date lastMofidiedTime;
	List<User> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public Integer getRemovedFlag() {
		return removedFlag;
	}

	public void setRemovedFlag(Integer removedFlag) {
		this.removedFlag = removedFlag;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastMofidiedTime() {
		return lastMofidiedTime;
	}

	public void setLastMofidiedTime(Date lastMofidiedTime) {
		this.lastMofidiedTime = lastMofidiedTime;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", groupName=" + groupName + ", groupType=" + groupType + ", removedFlag="
				+ removedFlag + ", createdTime=" + createdTime + ", lastMofidiedTime=" + lastMofidiedTime + ", users="
				+ users + "]";
	}

}
