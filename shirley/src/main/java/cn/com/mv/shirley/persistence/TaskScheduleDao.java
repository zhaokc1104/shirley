package cn.com.mv.shirley.persistence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.shirley.entity.TaskSchedule;

@Repository
public interface TaskScheduleDao extends CrudRepository<TaskSchedule, Serializable>{
	
	void deleteByTaskId(Long taskId);
	
	@Modifying
	@Query("update TaskSchedule s set s.status =?1 where s.taskId=?2")
	void updateStatusByTaskId(Integer status, Long taskId);
}
