package cn.com.mv.tds.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.tds.entity.TaskSchedule;

@Repository
public interface TaskScheduleDao extends CrudRepository<TaskSchedule, Serializable>{
	
	void deleteByTaskId(Integer taskId);
	
	@Modifying
	@Query("update TaskSchedule s set s.status =?1 where s.taskId=?2")
	void updateStatusByTaskId(Integer status, Integer taskId);
	
	@Query(value="select s.*,t.project_id project_id from t_sh_task_schedule s,t_sh_task t  where t.id=s.task_id and s.`status` != 0 and NOW() <= IFNULL(end_time,DATE_ADD(NOW(),INTERVAL 1 DAY))",nativeQuery=true)
	List<TaskSchedule> getAvailableSchedule();
	
	@Modifying
	@Query("update TaskSchedule s set s.lastRuntime =?1 where s.taskId=?2 ")
	void updateLastRuntime(Date lastRuntime, Integer taskId);
}
