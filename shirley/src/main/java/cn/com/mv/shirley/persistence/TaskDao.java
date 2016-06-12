package cn.com.mv.shirley.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.shirley.entity.Task;

@Repository
public interface TaskDao extends CrudRepository<Task, Serializable>{
	
	List<Task> queryAll(); 
	
}
