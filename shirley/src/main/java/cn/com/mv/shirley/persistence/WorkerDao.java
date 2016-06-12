package cn.com.mv.shirley.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.shirley.entity.Worker;

@Repository
public interface WorkerDao extends CrudRepository<Worker, Serializable>{
	
}
