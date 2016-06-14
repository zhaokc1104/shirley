package cn.com.mv.tds.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.com.mv.tds.entity.ExecutorRecord;

@Repository
public interface ExecutorRecordDao extends CrudRepository<ExecutorRecord, Serializable> {

}
