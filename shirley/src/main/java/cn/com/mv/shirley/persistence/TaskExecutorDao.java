package cn.com.mv.shirley.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.shirley.entity.TaskExecutor;

@Repository
public interface TaskExecutorDao extends CrudRepository<TaskExecutor, Serializable>{
	void deleteByTaskId(Long taskId);
}
