package com.cyberwise.workanalysis.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AllowedUser {
    private Integer id;

    private String username;

    private Integer removeFlag;

    private Date allowedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(Integer removeFlag) {
        this.removeFlag = removeFlag;
    }

    public Date getAllowedTime() {
        return allowedTime;
    }

    public void setAllowedTime(Date allowedTime) {
        this.allowedTime = allowedTime;
    }
}