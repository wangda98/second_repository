package com.cyberwise.workanalysis.common;

//com.cyberwise.workanalysis.bean.User
public class User {
	private Integer id;
	private String realname;
	private Integer isLeader;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", realname=" + realname + ", isLeader=" + isLeader + "]";
	}

}
