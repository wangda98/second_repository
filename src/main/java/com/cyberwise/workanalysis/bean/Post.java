package com.cyberwise.workanalysis.bean;

public class Post {
	private Integer id;
	private String postName;
	private Integer postLevel;// 2组长，3工程师
	private Integer postType;
	private Integer parentPostId;
	private Integer removeFlag;
	private Integer disableForStati;// 0算 1不算
	private Integer professionalId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Integer getPostLevel() {
		return postLevel;
	}

	public void setPostLevel(Integer postLevel) {
		this.postLevel = postLevel;
	}

	public Integer getPostType() {
		return postType;
	}

	public void setPostType(Integer postType) {
		this.postType = postType;
	}

	public Integer getParentPostId() {
		return parentPostId;
	}

	public void setParentPostId(Integer parentPostId) {
		this.parentPostId = parentPostId;
	}

	public Integer getRemoveFlag() {
		return removeFlag;
	}

	public void setRemoveFlag(Integer removeFlag) {
		this.removeFlag = removeFlag;
	}

	public Integer getDisableForStati() {
		return disableForStati;
	}

	public void setDisableForStati(Integer disableForStati) {
		this.disableForStati = disableForStati;
	}

	public Integer getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(Integer professionalId) {
		this.professionalId = professionalId;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", postName=" + postName + ", postLevel=" + postLevel + ", postType=" + postType
				+ ", parentPostId=" + parentPostId + ", removeFlag=" + removeFlag + ", disableForStati="
				+ disableForStati + ", professionalId=" + professionalId + "]";
	}

}
