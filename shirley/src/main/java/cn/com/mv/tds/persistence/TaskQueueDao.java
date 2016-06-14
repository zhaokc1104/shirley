package cn.com.mv.tds.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.tds.entity.TaskQueue;

@Repository
public interface TaskQueueDao extends CrudRepository<TaskQueue, Serializable>{
	
	@Query(value="select q.* from t_sh_task_queue q,"
			+"(select distinct task_id from t_sh_task_executor where worker_id = ?1) e "
			+ "where e.task_id = q.task_id  order by create_time asc limit ?2",nativeQuery=true)
	List<TaskQueue> getTaskQueueByWorkerId(Integer workerId, Integer limit);
}
