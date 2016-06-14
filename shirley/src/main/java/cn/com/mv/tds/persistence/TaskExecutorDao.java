package cn.com.mv.tds.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.tds.entity.TaskExecutor;

@Repository
public interface TaskExecutorDao extends CrudRepository<TaskExecutor, Serializable>{
	void deleteByTaskId(Integer taskId);
}
