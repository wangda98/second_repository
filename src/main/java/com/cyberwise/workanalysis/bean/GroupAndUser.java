package com.cyberwise.workanalysis.bean;

import java.util.Date;

public class GroupAndUser {
    private Integer id;

    private Integer groupId;

    private Integer userId;

    private Integer isLeader;

    private Integer removedFlag;

    private Date createTime;

    private Date leaveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
    }

    public Integer getRemovedFlag() {
        return removedFlag;
    }

    public void setRemovedFlag(Integer removedFlag) {
        this.removedFlag = removedFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }
}