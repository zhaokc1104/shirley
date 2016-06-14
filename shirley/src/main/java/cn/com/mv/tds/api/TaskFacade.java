package cn.com.mv.tds.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.mv.tds.common.web.ResCode;
import cn.com.mv.tds.common.web.utils.WebUtil;
import cn.com.mv.tds.entity.Task;
import cn.com.mv.tds.entity.TaskQueue;
import cn.com.mv.tds.entity.TaskSchedule;
import cn.com.mv.tds.service.TaskScheduleService;
import cn.com.mv.tds.service.TaskService;
import cn.com.mv.tds.utils.PageUtils;

@Controller
@RequestMapping(value="/api/task")
public class TaskFacade {
	
	private static final SimpleDateFormat timeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskFacade.class);
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskScheduleService taskScheduleService;
	
	@RequestMapping(value="/addWithSchedule")
	@ResponseBody
	public void addTaskWithSchedule(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String jsonpCallback = request.getParameter("jsonpCallback");
			
			Integer projectId = Integer.valueOf(request.getParameter("projectId"));
			String taskName = request.getParameter("taskName");
			String taskDesc = request.getParameter("taskDesc");
			String taskBizData = request.getParameter("taskBizData");
			
			Integer scheduleType = Integer.valueOf(request.getParameter("scheduleType"));
			Integer scheduleInterval = Integer.valueOf(request.getParameter("scheduleInterval"));
			Integer scheduleUnit = Integer.valueOf(request.getParameter("scheduleUnit"));
			String scheduleCron = request.getParameter("scheduleCron");
			Date scheduleStartTime = timeFmt.parse(request.getParameter("scheduleStartTime"));
			Date scheduleEndTime = timeFmt.parse(request.getParameter("scheduleEndTime"));
			
			Integer workerId = Integer.valueOf(request.getParameter("workerId"));
			
			Task task = new Task(); 
			task.setName(taskName);
			task.setDescription(taskDesc);
			task.setBizData(taskBizData);
			task.setProjectId(projectId);
			
			TaskSchedule taskSchedule = new TaskSchedule();
			taskSchedule.setCron(scheduleCron);
			taskSchedule.setType(scheduleType);
			taskSchedule.setInterval(scheduleInterval);
			taskSchedule.setUnit(scheduleUnit);
			taskSchedule.setStartTime(scheduleStartTime);
			taskSchedule.setEndTime(scheduleEndTime);
			
			taskService.addWithSchedule(task, taskSchedule, workerId);
			
			WebUtil.responseJson(response, ResCode.SUCCESS, jsonpCallback);
		} catch (Exception e) {
			LOG.error("addTaskWithSchedule Failed", e);
		}
	}
	
	@RequestMapping(value="/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonpCallback = request.getParameter("jsonpCallback");
			Integer taskId = Integer.valueOf(request.getParameter("taskId"));
			
			taskService.delete(taskId);
			WebUtil.responseJson(response, ResCode.SUCCESS, jsonpCallback);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/enable")
	public void enable(HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonpCallback = request.getParameter("jsonpCallback");
			Integer taskId = Integer.valueOf(request.getParameter("taskId"));
			taskScheduleService.enable(taskId);
			WebUtil.responseJson(response, ResCode.SUCCESS, jsonpCallback);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/disable")
	public void disable(HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonpCallback = request.getParameter("jsonpCallback");
			Integer taskId = Integer.valueOf(request.getParameter("taskId"));
			
			taskScheduleService.disable(taskId);
			WebUtil.responseJson(response, ResCode.SUCCESS, jsonpCallback);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		String jsonpCallback = request.getParameter("jsonpCallback");
		String paramProjectId = request.getParameter("projectId");
		String result = null;
		Integer projectId = null;
		List<Task> taskList = null;
		if (StringUtils.isEmpty(paramProjectId)) {
			result = ResCode.INVALID_PARAM;
		} else {
			projectId = Integer.valueOf(paramProjectId);
		}
		if (result == null) {
			taskList = taskService.findByProjectId(PageUtils.getPageable(request), projectId);
			result = ResCode.SUCCESS;
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("result", result);
		map.put("data", taskList);
		
		try {
			WebUtil.responseJson(response, map, jsonpCallback);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/take")
	@ResponseBody
	public void take(HttpServletRequest request, HttpServletResponse response) {
		String jsonpCallback = request.getParameter("jsonpCallback");
		String limit = request.getParameter("limit");
		String paramWorkerId = request.getParameter("workerId");
		String sign = request.getParameter("sign");
		String result = null;
		Integer workerId = null;
		List<TaskQueue> resultList = null;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if (StringUtils.isEmpty(paramWorkerId)) {
			result = ResCode.INVALID_PARAM;
		} else {
			workerId = Integer.valueOf(paramWorkerId);
		}
		
		if (result == null) {
			result = ResCode.SUCCESS;
			resultList = taskService.takeTaskFromQueue(workerId, limit == null ? null : Integer.valueOf(limit));
		}
		
		resultMap.put("result", result);
		resultMap.put("data", resultList);
		try {
			WebUtil.responseJson(response, resultMap, jsonpCallback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/init")
	public void init() {
		for (int i = 1 ;i <= 35 ;i++) {
			Task task = new Task();
			task.setCreateTime(new Date());
			task.setUpdateTime(new Date());
			task.setName("任务"+i);
			task.setDescription("任务描述"+i);
			task.setBizData("{id:"+i+"}");
			
			TaskSchedule s = new TaskSchedule();
			s.setInterval(100);
			s.setUnit(1);
			s.setType(1);
			s.setCreateTime(new Date());
			s.setUpdateTime(new Date());
			s.setStatus(0);
			s.setLastRuntime(new Date());
			
			taskService.addWithSchedule(task, s, 1);
		}
	}
}
