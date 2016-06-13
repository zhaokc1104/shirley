package cn.com.mv.shirley.persistence.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.com.mv.shirley.entity.Task;
import cn.com.mv.shirley.entity.TaskVO;
import cn.com.mv.shirley.persistence.TaskDaoExt;

@Repository
public class TaskDaoExtImpl implements TaskDaoExt {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Task> queryAll() {
		Query query = entityManager.createNativeQuery("SELECT" +
				"	t.id," +
				"	t.NAME," +
				"	t.DESC," +
				"	t.project_id," +
				"	t.biz_data," +
				"	t.create_time," +
				"	t.update_time," +
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
				"	t.id = s.task_id");
		
		List<TaskVO> list = query.getResultList();
		System.out.println(list.size());
		for (Object o : list) {
			System.out.println(o);
		}
		
		
		return null;
	}

	
}