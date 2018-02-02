package cn.zc.hrmsys.dao.job.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.criteria.JobCriteria;
import cn.zc.hrmsys.pojo.entity.Job;

public interface IJobDao {
	
	List<Job> getAll() throws SQLException;
	
	List<Job> getAllWithCriteria(JobCriteria JobCriteria) throws SQLException;
	
	void addJob(Job job) throws SQLException;

	void updateJob(Job job) throws SQLException;
	
	void deleteJobById(Integer id) throws SQLException;
	
	Job getJobById(Integer id) throws SQLException;
	
	long getCountWithName(String name) throws SQLException;
	
}
