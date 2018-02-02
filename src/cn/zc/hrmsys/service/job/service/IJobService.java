package cn.zc.hrmsys.service.job.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.entity.Job;

public interface IJobService {
	
	void add(Job job) throws SQLException;
	
	List<Job> getAll() throws SQLException;
	
	void delete(Integer id) throws SQLException;
	
	void update(Job job) throws SQLException;

}
