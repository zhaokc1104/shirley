package cn.com.mv.tds.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mv.tds.entity.TaskSchedule;
import cn.com.mv.tds.persistence.TaskScheduleDao;

@Service
public class TaskScheduleService {
	
	@Autowired
	private TaskScheduleDao taskScheduleDao;
	
	public void enable(Integer taskId) {
		taskScheduleDao.updateStatusByTaskId(1, taskId);
	}
	
	public void disable(Integer taskId) {
		taskScheduleDao.updateStatusByTaskId(0, taskId);
	}
	
	public List<TaskSchedule> getAvailableSchedule() {
		return taskScheduleDao.getAvailableSchedule();
	}
	
	@Transactional
	public void setRunning(Integer taskId) {
		taskScheduleDao.updateStatusByTaskId(2, taskId);
		taskScheduleDao.updateLastRuntime(new Date(), taskId);
	}
	
}
