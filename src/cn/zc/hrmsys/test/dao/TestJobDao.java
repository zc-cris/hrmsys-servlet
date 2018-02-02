package cn.zc.hrmsys.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.zc.hrmsys.dao.job.dao.IJobDao;
import cn.zc.hrmsys.dao.job.dao.JobDao;
import cn.zc.hrmsys.pojo.criteria.JobCriteria;
import cn.zc.hrmsys.pojo.entity.Job;

class TestJobDao {

	IJobDao JobDao = new JobDao();
	@Test			//ok
	void testGetAll() throws SQLException {
		List<Job> list = JobDao.getAll();
		System.out.println(list);
	}

	@Test			//ok	
	void testGetAllWithCriteria() throws SQLException {
//		List<Job> jobs = JobDao.getAllWithCriteria(new JobCriteria("前台", null));
//		List<Job> jobs = JobDao.getAllWithCriteria(new JobCriteria(null, true));
//		List<Job> jobs = JobDao.getAllWithCriteria(new JobCriteria(null, false));
		List<Job> jobs = JobDao.getAllWithCriteria(new JobCriteria("va", true));
		System.out.println(jobs);
	}

	@Test		//ok
	void testAddJob() throws SQLException {
//		JobDao.addJob(new Job("java工程师", "负责java后台的开发"));
//		JobDao.addJob(new Job("python工程师", "负责算法分析"));
	}

	@Test		//ok
	void testUpdateJob() throws SQLException {
		Job job = new Job("前台", "都是大美女");
		job.setJobId(1002);
		JobDao.updateJob(job);
	}

	@Test		//ok
	void testDeleteJobById() throws SQLException {
		JobDao.deleteJobById(1002);
	}

	@Test		//ok
	void testGetJobById() throws SQLException {
		Job job = JobDao.getJobById(1001);
		System.out.println(job);
	}

	@Test		//ok
	void testGetCountWithName() throws SQLException {
//		long count = JobDao.getCountWithName("java工程师");
		long count = JobDao.getCountWithName("前台");
		System.out.println(count);
	}
}
