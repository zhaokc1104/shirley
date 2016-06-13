package cn.com.mv.shirley.entity;

import java.util.Date;

import javax.persistence.Entity;

public class TaskVO {
	
	private Long id;
	
	private String name;
	
	private String desc;
	
	private Long projectId;
	
	private String bizData;
	
	private Date createTime = new Date();
	
	private Date updateTime =  new Date();
	
	private Long schId;
	
	private Date schStartTime;
	
	private Date schEndTime;
	
	private Integer schInterval;
	
	private Integer schUnit;
	
	private Date schLasRuntime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getBizData() {
		return bizData;
	}

	public void setBizData(String bizData) {
		this.bizData = bizData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getSchId() {
		return schId;
	}

	public void setSchId(Long schId) {
		this.schId = schId;
	}

	public Date getSchStartTime() {
		return schStartTime;
	}

	public void setSchStartTime(Date schStartTime) {
		this.schStartTime = schStartTime;
	}

	public Date getSchEndTime() {
		return schEndTime;
	}

	public void setSchEndTime(Date schEndTime) {
		this.schEndTime = schEndTime;
	}

	public Integer getSchInterval() {
		return schInterval;
	}

	public void setSchInterval(Integer schInterval) {
		this.schInterval = schInterval;
	}

	public Integer getSchUnit() {
		return schUnit;
	}

	public void setSchUnit(Integer schUnit) {
		this.schUnit = schUnit;
	}

	public Date getSchLasRuntime() {
		return schLasRuntime;
	}

	public void setSchLasRuntime(Date schLasRuntime) {
		this.schLasRuntime = schLasRuntime;
	}
	
}
