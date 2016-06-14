package cn.com.mv.tds.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mv.tds.entity.ExecutorRecord;
import cn.com.mv.tds.entity.Task;
import cn.com.mv.tds.entity.TaskExecutor;
import cn.com.mv.tds.entity.TaskQueue;
import cn.com.mv.tds.entity.TaskSchedule;
import cn.com.mv.tds.persistence.ExecutorRecordDao;
import cn.com.mv.tds.persistence.TaskDao;
import cn.com.mv.tds.persistence.TaskDaoExt;
import cn.com.mv.tds.persistence.TaskExecutorDao;
import cn.com.mv.tds.persistence.TaskQueueDao;
import cn.com.mv.tds.persistence.TaskScheduleDao;
import cn.com.mv.tds.utils.Pageable;

@Service
public class TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private TaskScheduleDao taskScheduleDao;
	
	@Autowired
	private TaskExecutorDao taskExecutorDao;
	
	@Autowired
	private TaskDaoExt taskDaoExt;
	
	@Autowired
	private TaskQueueDao taskQueueDao;
	
	@Autowired
	private ExecutorRecordDao executorRecordDao;
	
	@Transactional
	public void addWithSchedule(Task task, TaskSchedule schedule, Integer workerId) {
		
		Task newTask = taskDao.save(task);
		
		schedule.setTaskId(newTask.getId());
		taskScheduleDao.save(schedule);
		
		TaskExecutor taskExecutor = new TaskExecutor();
		taskExecutor.setTaskId(newTask.getId());
		taskExecutor.setWorkerId(workerId);
		taskExecutorDao.save(taskExecutor);
		
	}
	
	@Transactional
	public void delete(Integer taskId) {
		taskDao.delete(taskId);
		
		taskScheduleDao.deleteByTaskId(taskId);
		
		taskExecutorDao.deleteByTaskId(taskId);
	}
	
	public List<Task> findByProjectId(Pageable pageable, Integer projectId) {
		return taskDaoExt.findByProject(pageable, projectId);
	}
	
	@Transactional
	public void addTaskToQueue(Integer taskId) {
		Task task = taskDao.findOne(taskId);
		
		TaskQueue taskQueue = new TaskQueue();
		taskQueue.setTaskId(taskId);
		taskQueue.setBizData(task.getBizData());
		
		taskQueueDao.save(taskQueue);
	}
	
	@Transactional
	public List<TaskQueue> takeTaskFromQueue(Integer workerId, Integer limit) {
		List<TaskQueue> queueList = taskQueueDao.getTaskQueueByWorkerId(workerId, limit);
		
		List<ExecutorRecord> recordList = new ArrayList<ExecutorRecord>();
				
		for (TaskQueue queue : queueList) {
			ExecutorRecord record = new ExecutorRecord();
			Date now = new Date();
			record.setTaskId(queue.getTaskId());
			record.setStartTime(now);
			record.setCreateTime(now);
			record.setUpdateTime(now);
			record.setStatus(1);
			recordList.add(record);
		}
		taskQueueDao.delete(queueList);
		executorRecordDao.save(recordList);
		return queueList;
	}
}
