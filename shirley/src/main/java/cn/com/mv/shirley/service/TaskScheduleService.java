package cn.com.mv.shirley.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mv.shirley.persistence.TaskScheduleDao;

@Service
public class TaskScheduleService {
	
	@Autowired
	private TaskScheduleDao taskScheduleDao;
	
	public void enable(Long taskId) {
		taskScheduleDao.updateStatusByTaskId(1, taskId);
	}
	
	public void disable(Long taskId) {
		taskScheduleDao.updateStatusByTaskId(0, taskId);
	}
	
}
