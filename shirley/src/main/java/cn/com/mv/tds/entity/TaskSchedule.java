package cn.com.mv.tds.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_sh_task_schedule")
public class TaskSchedule implements Serializable {
	
	private static final long serialVersionUID = -1936782649639323732L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer taskId;
	
	private Integer type;
	
	@Column(name="\"interval\"")
	private Integer interval;
	
	private Integer unit;
	
	private String cron;
	
	private Date lastRuntime;
	
	private Date startTime;
	
	private Date endTime;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer status;
	
	private Integer projectId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public Date getLastRuntime() {
		return lastRuntime;
	}

	public void setLastRuntime(Date lastRuntime) {
		this.lastRuntime = lastRuntime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "TaskSchedule [id=" + id + ", taskId=" + taskId + ", type="
				+ type + ", interval=" + interval + ", unit=" + unit
				+ ", cron=" + cron + ", lastRuntime=" + lastRuntime
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", status=" + status + ", projectId=" + projectId + "]";
	}

	
	
}
