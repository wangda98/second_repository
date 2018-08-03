package com.cyberwise.workanalysis.bean;

import java.util.Date;

public class WorkInfoForWeek {
    private Integer id;

    private String realname;

    private String account;

    private Double weekCapacity;

    private Double consumedWeekCapacity;

    private Double cpl;

    private Double ptt;

    private Date weekStartDate;

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
        this.realname = realname == null ? null : realname.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Double getWeekCapacity() {
        return weekCapacity;
    }

    public void setWeekCapacity(Double weekCapacity) {
        this.weekCapacity = weekCapacity;
    }

    public Double getConsumedWeekCapacity() {
        return consumedWeekCapacity;
    }

    public void setConsumedWeekCapacity(Double consumedWeekCapacity) {
        this.consumedWeekCapacity = consumedWeekCapacity;
    }

    public Double getCpl() {
        return cpl;
    }

    public void setCpl(Double cpl) {
        this.cpl = cpl;
    }

    public Double getPtt() {
        return ptt;
    }

    public void setPtt(Double ptt) {
        this.ptt = ptt;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }
}