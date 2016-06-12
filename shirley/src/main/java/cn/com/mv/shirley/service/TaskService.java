package cn.com.mv.shirley.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mv.shirley.entity.Task;
import cn.com.mv.shirley.entity.TaskExecutor;
import cn.com.mv.shirley.entity.TaskSchedule;
import cn.com.mv.shirley.persistence.TaskDao;
import cn.com.mv.shirley.persistence.TaskExecutorDao;
import cn.com.mv.shirley.persistence.TaskScheduleDao;

@Service
public class TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private TaskScheduleDao taskScheduleDao;
	
	@Autowired
	private TaskExecutorDao taskExecutorDao;
	
	
	@Transactional
	public void addWithSchedule(Task task, TaskSchedule schedule, Long workerId) {
		
		Task newTask = taskDao.save(task);
		
		schedule.setTaskId(newTask.getId());
		taskScheduleDao.save(schedule);
		
		TaskExecutor taskExecutor = new TaskExecutor();
		taskExecutor.setTaskId(newTask.getId());
		taskExecutor.setWorkerId(workerId);
		taskExecutorDao.save(taskExecutor);
		
	}
	
	@Transactional
	public void delete(Long taskId) {
		taskDao.delete(taskId);
		
		taskScheduleDao.deleteByTaskId(taskId);
		
		taskExecutorDao.deleteByTaskId(taskId);
	}
	
	
	
}
