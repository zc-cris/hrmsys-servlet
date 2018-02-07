package cn.zc.hrmsys.dao.job.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.dao.base.dao.BaseDao;
import cn.zc.hrmsys.factory.dao.factory.DaoFactory;
import cn.zc.hrmsys.pojo.criteria.JobCriteria;
import cn.zc.hrmsys.pojo.entity.Job;

public class JobDao extends BaseDao<Job> implements IJobDao {

	
	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (查询所有职位)
	 * @see cn.zc.hrmsys.dao.job.dao.IJobDao#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午2:09:35
	 */
	@Override
	public List<Job> getAll() throws SQLException {
		String sql = "select jobId,jobName,jobDesc,jobState from tb_job";
		return queryList(sql);
	}

	/**
	 * 
	 * @MethodName: getAllWithCriteria
	 * @Description: TODO (模糊查询职位)
	 * @see cn.zc.hrmsys.dao.job.dao.IJobDao#getAllWithCriteria(cn.zc.hrmsys.pojo.criteria.JobCriteria)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午2:09:51
	 */
	@Override
	public List<Job> getAllWithCriteria(JobCriteria JobCriteria) throws SQLException {
		String sql = "select jobId,jobName,jobDesc from tb_job where jobName like ?"
				+ " and jobState = ?";
		return queryList(sql, JobCriteria.getJobName(), JobCriteria.getJobState());
	}

	/**
	 * 
	 * @MethodName: addJob
	 * @Description: TODO (添加一条职位记录)
	 * @see cn.zc.hrmsys.dao.job.dao.IJobDao#addJob(cn.zc.hrmsys.pojo.entity.Job)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午2:04:32
	 */
	@Override
	public void addJob(Job job) throws SQLException {
		String sql = "insert into tb_job (jobName, jobDesc) values (?,?)";
		update(sql, job.getJobName(),job.getJobDesc());
	}

	/**
	 * 
	 * @MethodName: updateJob
	 * @Description: TODO (更新一个职位的信息)
	 * @see cn.zc.hrmsys.dao.job.dao.IJobDao#updateJob(cn.zc.hrmsys.pojo.entity.Job)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午2:06:28
	 */
	@Override
	public void updateJob(Job job) throws SQLException {
		String sql = "update tb_job set jobName=?,jobDesc=? where jobId=?";
		update(sql, job.getJobName(),job.getJobDesc(),job.getJobId());
	}

	/**
	 * 
	 * @MethodName: deleteJobById
	 * @Description: TODO (注销一个职位)
	 * @see cn.zc.hrmsys.dao.job.dao.IJobDao#deleteJobById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午2:07:59
	 */
	@Override
	public void deleteJobById(Integer id) throws SQLException {
		String sql = "update tb_job set jobState = 0 where jobId = ?";
		update(sql, id);
	}

	/**
	 * 
	 * @MethodName: getJobById
	 * @Description: TODO (根据id获取职位)
	 * @see cn.zc.hrmsys.dao.job.dao.IJobDao#getJobById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午2:15:52
	 */
	@Override
	public Job getJobById(Integer id) throws SQLException {
		String sql = "select jobId,jobName,jobDesc,jobState from tb_job where jobId = ?";
		return queryOne(sql, id);
	}

	/**
	 * 
	 * @MethodName: getCountWithName
	 * @Description: TODO (根据职位名字查询有效职位的数量，防止添加和更新职位信息重复)
	 * @see cn.zc.hrmsys.dao.job.dao.IJobDao#getCountWithName(java.lang.String)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日下午2:16:05
	 */
	@Override
	public long getCountWithName(String name) throws SQLException {
		String sql = "select count(jobName) from tb_job where jobName=? and jobState = 1";
		return queryValue(sql, name);
	}

	@Override
	public List<Job> getByDeptId(int id) throws SQLException {
		String sql = "select jobName,jobId from tb_job where deptId = ?";
		return queryList(sql, id);
	}
}
