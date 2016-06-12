package cn.com.mv.shirley.api;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.mv.shirley.common.web.utils.WebUtil;
import cn.com.mv.shirley.entity.Task;
import cn.com.mv.shirley.entity.TaskSchedule;
import cn.com.mv.shirley.service.TaskScheduleService;
import cn.com.mv.shirley.service.TaskService;

@Controller
@RequestMapping(value="/api/task")
public class TaskFacade {
	
	private static final SimpleDateFormat timeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mi:ss");
	
	@Autowired
	private TaskService taskService;
	
	private TaskScheduleService taskScheduleService;
	
	@RequestMapping(value="/addWithSchedule")
	@ResponseBody
	public void addTaskWithSchedule(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String jsonpCallback = request.getParameter("jsonpCallback");
			
			Long projectId = Long.valueOf(request.getParameter("projectId"));
			String taskName = request.getParameter("taskName");
			String taskDesc = request.getParameter("taskDesc");
			String taskBizData = request.getParameter("taskBizData");
			
			Integer scheduleType = Integer.valueOf(request.getParameter("scheduleType"));
			Integer scheduleInterval = Integer.valueOf(request.getParameter("scheduleInterval"));
			Integer scheduleUnit = Integer.valueOf(request.getParameter("scheduleUnit"));
			String scheduleCron = request.getParameter("scheduleCron");
			Date scheduleStartTime = timeFmt.parse(request.getParameter("scheduleStartTime"));
			Date scheduleEndTime = timeFmt.parse(request.getParameter("scheduleEndTime"));
			
			Long workerId = Long.valueOf(request.getParameter("workerId"));
			
			Task task = new Task(); 
			task.setName(taskName);
			task.setDesc(taskDesc);
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
			
			WebUtil.responseJson(response, "success", jsonpCallback);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonpCallback = request.getParameter("jsonpCallback");
			Long taskId = Long.valueOf(request.getParameter("taskId"));
			
			taskService.delete(taskId);
			WebUtil.responseJson(response, "success", jsonpCallback);
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
			Long taskId = Long.valueOf(request.getParameter("taskId"));
			taskScheduleService.enable(taskId);
			WebUtil.responseJson(response, "success", jsonpCallback);
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
			Long taskId = Long.valueOf(request.getParameter("taskId"));
			
			taskScheduleService.disable(taskId);
			WebUtil.responseJson(response, "success", jsonpCallback);
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
		
	}
	
}
