package com.cyberwise.workanalysis.bean;

public class PostUser {
	private Integer id;
	private String realname;// 真名
	private String postName;// 职位名称
	private Integer uid;// 用户ID
	private Integer pid;// 岗位ID
	private Integer joinToStati;// 1加入 0不加入

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

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getJoinToStati() {
		return joinToStati;
	}

	public void setJoinToStati(Integer joinToStati) {
		this.joinToStati = joinToStati;
	}

}
