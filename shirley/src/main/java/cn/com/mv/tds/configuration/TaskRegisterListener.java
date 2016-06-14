package cn.com.mv.tds.configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobDataMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.com.mv.tds.entity.TaskSchedule;
import cn.com.mv.tds.scheduler.GeneralJob;
import cn.com.mv.tds.scheduler.JobScheduler;
import cn.com.mv.tds.service.TaskScheduleService;

public class TaskRegisterListener implements ApplicationListener<ContextRefreshedEvent>{
	
	private static Map<String, Integer> timeRateTable = null;
	
	static {
		timeRateTable = new HashMap<String, Integer>();
		timeRateTable.put("1", 1);//秒
		timeRateTable.put("2", 60); //分钟
		timeRateTable.put("3", 60*60); //小时
		timeRateTable.put("4", 60*60*24); //天
		timeRateTable.put("5", 60*60*24*30); //月
		timeRateTable.put("6", 60*60*24*30*12); //年
	}
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		
		TaskScheduleService taskScheduleService = context.getBean(TaskScheduleService.class);
		JobScheduler jobScheduler = JobScheduler.getInstance();
		List<TaskSchedule> list = taskScheduleService.getAvailableSchedule();
		
		Date now = new Date();
		for (TaskSchedule schedule : list ) {
			//System.out.println(schedule);
			Long delay = 0L; //任务应延迟多久执行,单位为毫秒.默认立即执行
			
			Integer unit = schedule.getUnit();
			Integer interval = schedule.getInterval();
			Date lastRuntime = schedule.getLastRuntime();
			Date startTime = schedule.getStartTime();
			String cron = schedule.getCron();
			// 如果任务从未执行过,计算当前时间与任务开始时间的间隔。
			if (lastRuntime == null) {
				if (startTime != null) {
					if (startTime.getTime() >= now.getTime()) {
						delay = startTime.getTime() - now.getTime();
					}
				}
			} else {
				//计算任务上次执行时间+执行间隔  与 当前时间的间隔。
				Long nextRuntime = lastRuntime.getTime() + getTimeWithUnit(interval, unit);
				if (nextRuntime >= now.getTime()) {
					delay = nextRuntime - now.getTime();
				}
			}
			JobDataMap map = new JobDataMap();
			map.put("taskSchedule", schedule);
			
			try {
				jobScheduler.addJob(map, GeneralJob.class, String.valueOf(schedule.getId()), String.valueOf(schedule.getProjectId()), interval, (int) (delay/1000), cron);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private Integer getTimeWithUnit(Integer value, Integer unit) {
		return timeRateTable.get(String.valueOf(unit)) * value;
	}

}
