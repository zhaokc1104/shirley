package cn.com.mv.shirley.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.shirley.entity.ExecutorRecord;

@Repository
public interface ExecutorRecordDao extends CrudRepository<ExecutorRecord, Serializable> {

}
