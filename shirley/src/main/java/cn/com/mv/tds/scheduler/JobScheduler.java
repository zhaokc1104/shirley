package cn.com.mv.tds.scheduler;

import java.util.Date;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


public class JobScheduler {
    
    private static final String JOB_NAME_PRE = "JOB_";
    
    private static final String GROUP_NAME_PRE = "JOB_GROUP_";
    
    private static final String TRIGGER_NAME_PRE = "TRIGGER_NAME_";
    
    private static final String TGROUP_NAME_PRE = "TGROUP_NAME_";
    
    private static JobScheduler jobScheduler = null;
    
    private SchedulerFactory schedulerFactory;
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    private JobScheduler() {
    	schedulerFactory = new StdSchedulerFactory();  
    }
    
    public static JobScheduler getInstance() {
        if (jobScheduler == null) {
            jobScheduler =  new JobScheduler();
        } 
        return jobScheduler;
    }
    
    public void start() throws Exception {           
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
    }
    
    public void stop() throws Exception{
        
        log.info("[JobScheduler|stop] 正在关闭任务调度器. . .");
              
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.shutdown(true);
        
        log.info("[JobScheduler|stop] 任务调度器已关闭");
    }
    
    /**
    * <p>Title: </p>
    * <p>Description: 添加一个任务到调度中</p>
    * @param 
    * @param 
    * @return void
     */
    public void addJob(JobDataMap jobData,Class jobClass, String taskId, String taskGroupId ,Integer interval, Integer delay, String cron) throws Exception {
         
    	Scheduler scheduler = schedulerFactory.getScheduler();
    	
        JobDetail jobDetail = (JobDetail) newJob(jobClass)
        		.usingJobData(jobData).withIdentity(taskId, taskGroupId).build();        
        
        if (StringUtils.isEmpty(cron)) {
        	//普通调度
        	Date startTime = DateBuilder.nextGivenSecondDate(null, delay);
        	Trigger trigger = newTrigger().withIdentity(taskId, taskGroupId).startAt(startTime)
            		.withSchedule(simpleSchedule().withIntervalInSeconds(interval)).build();
        	scheduler.scheduleJob(jobDetail, trigger);
        } else {
        	//使用cron表达式调度
        	CronTrigger trigger = newTrigger().withIdentity(taskId, taskGroupId).withSchedule(cronSchedule(cron))
        	        .build();
        	scheduler.scheduleJob(jobDetail, trigger);
        }
        scheduler.start();
    }
    
    /**
    * <p>Title: </p>
    * <p>Description: 从调度中移除一个任务</p>
    * @param 
    * @param 
    * @return void
     */
    public void deleteJob(String taskId, String taskGroupId) throws Exception{              
        Scheduler scheduler = schedulerFactory.getScheduler();
        //scheduler.deleteJob(Jobkey.)deleteJob(taskId, taskGroupId);
        
    }
    
    
    /**
    * <p>Title: </p>
    * <p>Description: 判断指定任务是否在运行</p>
    * @param 
    * @param 
    * @return boolean
    */
    public boolean isJobRunning( ) throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();        
        Scheduler scheduler = schedulerFactory.getScheduler();     
       /* if (scheduler.isStarted()) {
            //Integer id = transform.getId();
            //JobDetail jobDetail = scheduler.getJobDetail(JOB_NAME_PRE+id, GROUP_NAME_PRE + id);
            return jobDetail != null; 
        } */
        return false;
    }
    
    public boolean isStarted() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();        
            Scheduler scheduler = schedulerFactory.getScheduler();
            return scheduler.isStarted();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
