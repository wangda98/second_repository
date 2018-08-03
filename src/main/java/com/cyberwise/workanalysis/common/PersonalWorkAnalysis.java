package com.cyberwise.workanalysis.common;

import java.util.Date;

//个人工作pojo
public class PersonalWorkAnalysis {
	private String realname;
	private Date startDate;
	private Date endDate;
	private double weekCapacity;
	private double consumedWeekCapacity;
	private double cpl;
	private double ptt;

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getWeekCapacity() {
		return weekCapacity;
	}

	public void setWeekCapacity(double weekCapacity) {
		this.weekCapacity = weekCapacity;
	}

	public double getConsumedWeekCapacity() {
		return consumedWeekCapacity;
	}

	public void setConsumedWeekCapacity(double consumedWeekCapacity) {
		this.consumedWeekCapacity = consumedWeekCapacity;
	}

	public double getCpl() {
		return cpl;
	}

	public void setCpl(double cpl) {
		this.cpl = cpl;
	}

	public double getPtt() {
		return ptt;
	}

	public void setPtt(double ptt) {
		this.ptt = ptt;
	}

}
