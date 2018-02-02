package cn.zc.hrmsys.service.job.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.MyException.JobException;
import cn.zc.hrmsys.dao.job.dao.IJobDao;
import cn.zc.hrmsys.factory.dao.factory.DaoFactory;
import cn.zc.hrmsys.pojo.entity.Job;

public class JobService implements IJobService {

	IJobDao JobDao = (IJobDao) DaoFactory.newInstance().getDaoEntity("JobDao");
	
	/**
	 * 
	 * @MethodName: add
	 * @Description: TODO (新增一条职位记录)
	 * @see cn.zc.hrmsys.service.job.service.IJobService#add(cn.zc.hrmsys.pojo.entity.Job)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:00:28
	 */
	@Override
	public void add(Job job) throws SQLException{
		long count = JobDao.getCountWithName(job.getJobName());
		if(count>0) {
			throw new JobException("存在同样名字的职位，请重新更改职位名字");
		}
		JobDao.addJob(job);
	}

	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (获取所有的职位记录)
	 * @see cn.zc.hrmsys.service.job.service.IJobService#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:00:45
	 */
	@Override
	public List<Job> getAll() throws SQLException {
		return JobDao.getAll();
	}

	/**
	 * 
	 * @MethodName: delete
	 * @Description: TODO (注销一条职位记录)
	 * @see cn.zc.hrmsys.service.job.service.IJobService#delete(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:00:59
	 */
	@Override
	public void delete(Integer id) throws SQLException {
		JobDao.deleteJobById(id);
	}

	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (更新一条职位记录)
	 * @see cn.zc.hrmsys.service.job.service.IJobService#update(cn.zc.hrmsys.pojo.entity.Job)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:01:14
	 */
	@Override
	public void update(Job job) throws SQLException {
		long count = JobDao.getCountWithName(job.getJobName());
		if(count>0) {
			throw new JobException("存在同样名字的职位，请重新更改职位名字");
		}
		JobDao.updateJob(job);
	}

}
