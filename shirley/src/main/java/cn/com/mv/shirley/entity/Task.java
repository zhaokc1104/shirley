package cn.com.mv.shirley.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_sh_task")

public class Task implements Serializable {
	
	private static final long serialVersionUID = 3424235333732209929L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String desc;
	
	private Long projectId;
	
	private String bizData;
	
	private Date createTime = new Date();
	
	private Date updateTime =  new Date();
	
	@OneToOne
	private TaskSchedule taskSchedule;

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

	public TaskSchedule getTaskSchedule() {
		return taskSchedule;
	}

	public void setTaskSchedule(TaskSchedule taskSchedule) {
		this.taskSchedule = taskSchedule;
	}
	
	
}
