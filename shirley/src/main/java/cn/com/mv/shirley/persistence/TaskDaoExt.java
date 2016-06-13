package cn.com.mv.shirley.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.mv.shirley.entity.Task;

@Repository
public interface TaskDaoExt {
	List<Task> queryAll(); 
}

