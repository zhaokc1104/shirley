package cn.com.mv.tds.scheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.mv.tds.entity.TaskSchedule;
import cn.com.mv.tds.service.TaskScheduleService;
import cn.com.mv.tds.service.TaskService;
import cn.com.mv.tds.utils.SpringUtils;



public class GeneralJob implements Job{
    
    private Logger LOG = LoggerFactory.getLogger(this.getClass());
    
    
	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
		
		
		TaskScheduleService taskScheduleService = SpringUtils.getBean(TaskScheduleService.class);
		TaskService taskService = SpringUtils.getBean(TaskService.class);
				
		TaskSchedule taskSchedule = (TaskSchedule) dataMap.get("taskSchedule");
		
		if (taskSchedule == null) {
			LOG.error("NO TaskSchedule Entity Provide , Give up the Schedule");
			return ;
		}
		
		taskScheduleService.setRunning(taskSchedule.getId());
		taskService.addTaskToQueue(taskSchedule.getTaskId());
	}

}
