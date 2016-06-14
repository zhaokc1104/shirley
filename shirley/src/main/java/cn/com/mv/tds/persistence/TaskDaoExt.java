package cn.com.mv.tds.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.mv.tds.entity.Task;
import cn.com.mv.tds.utils.Pageable;

@Repository
public interface TaskDaoExt {
	List<Task> findByProject(Pageable pageable, Integer projectId); 
}

