package com.cyberwise.workanalysis.bean;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
public class Group {
    @Override
	public String toString() {
		return "Group [id=" + id + ", groupName=" + groupName + ", groupType=" + groupType + ", removedFlag="
				+ removedFlag + ", createdTime=" + createdTime + ", lastMofidiedTime=" + lastMofidiedTime + "]";
	}

	private Integer id;

    private String groupName;

    private Integer groupType;

    private Integer removedFlag;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
    private Date createdTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
    private Date lastMofidiedTime;

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
        this.groupName = groupName == null ? null : groupName.trim();
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
}