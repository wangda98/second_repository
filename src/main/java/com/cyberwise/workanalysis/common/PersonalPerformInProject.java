package com.cyberwise.workanalysis.common;

import java.util.Date;

public class PersonalPerformInProject {
	private Integer project;
	private Date statiTime;
	private String projectName;
	private String realname;
	private double perProjectCapacity;
	private Integer weekNo;

	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}

	public Date getStatiTime() {
		return statiTime;
	}

	public void setStatiTime(Date statiTime) {
		this.statiTime = statiTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public double getPerProjectCapacity() {
		return perProjectCapacity;
	}

	public void setPerProjectCapacity(double perProjectCapacity) {
		this.perProjectCapacity = perProjectCapacity;
	}

	public Integer getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(Integer weekNo) {
		this.weekNo = weekNo;
	}

	@Override
	public String toString() {
		return "PersonalPerformInProject [project=" + project + ", statiTime=" + statiTime + ", projectName="
				+ projectName + ", realname=" + realname + ", perProjectCapacity=" + perProjectCapacity + ", weekNo="
				+ weekNo + "]";
	}

}
