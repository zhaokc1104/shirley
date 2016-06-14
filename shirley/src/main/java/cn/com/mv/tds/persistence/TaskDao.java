package cn.com.mv.tds.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.tds.entity.Task;

@Repository
public interface TaskDao extends CrudRepository<Task, Serializable>{
	
}
