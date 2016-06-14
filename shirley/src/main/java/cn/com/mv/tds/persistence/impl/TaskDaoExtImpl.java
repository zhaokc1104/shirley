package cn.com.mv.tds.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.com.mv.tds.entity.Task;
import cn.com.mv.tds.entity.TaskSchedule;
import cn.com.mv.tds.persistence.TaskDaoExt;
import cn.com.mv.tds.utils.PageUtils;
import cn.com.mv.tds.utils.Pageable;

@Repository
public class TaskDaoExtImpl implements TaskDaoExt {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Task> findByProject(Pageable pageable, Integer projectId) {
		String sql = "SELECT" +
				"	t.id id," +
				"	t.NAME name," +
				"	t.description description," +
				"	t.project_id project_id," +
				"	t.biz_data biz_data," +
				"	t.create_time create_time," +
				"	t.update_time update_time," +
				"	s.id sch_id," +
				"	s.start_time sch_start_time," +
				"	s.end_time sch_end_time," +
				"	s.`status` sch_status," +
				"	s.`interval` sch_interval," +
				"	s.unit sch_unit," +
				"	s.last_runtime sch_last_runtime " +
				"FROM " +
				"	t_sh_task t," +
				"	t_sh_task_schedule s " +
				"WHERE " +
				"	t.id = s.task_id and t.project_id="+projectId+" order by t.update_time desc";
				
		Query query = entityManager.createNativeQuery(PageUtils.pageable( pageable, sql));
		
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		
		List<Map<String,Object>> list = query.getResultList();
				
		List<Task> taskList = new ArrayList<Task>();
		for (Map<String,Object> o : list) {
			Task t = new Task();
			t.setId((Integer) o.get("id"));
			t.setName((String) o.get("name"));
			t.setProjectId((Integer) o.get("project_id"));
			t.setDescription((String) o.get("description"));
			t.setBizData((String) o.get("biz_data"));
			t.setCreateTime((Date) o.get("create_time"));
			t.setUpdateTime((Date) o.get("update_time"));
			TaskSchedule schedule = new TaskSchedule();
			schedule.setId((Integer) o.get("sch_id"));
			schedule.setInterval((Integer) o.get("sch_interval"));
			schedule.setUnit((Integer) o.get("sch_unit"));
			schedule.setTaskId((Integer) o.get("id"));
			schedule.setStatus((Integer) o.get("sch_status"));
			schedule.setLastRuntime((Date) o.get("sch_last_runtime"));
			schedule.setStartTime((Date) o.get("sch_start_time"));
			schedule.setEndTime((Date) o.get("sch_end_time"));
			t.setTaskSchedule(schedule);
			taskList.add(t);
		}		
		return taskList;
	}

	
}