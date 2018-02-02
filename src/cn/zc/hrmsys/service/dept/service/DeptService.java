package cn.zc.hrmsys.service.dept.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.MyException.DeptException;
import cn.zc.hrmsys.dao.dept.dao.IDeptDao;
import cn.zc.hrmsys.factory.dao.factory.DaoFactory;
import cn.zc.hrmsys.pojo.criteria.DeptCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;

public class DeptService implements IDeptService {

	IDeptDao deptDao = (IDeptDao) DaoFactory.newInstance().getDaoEntity("DeptDao");
	
	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (获取所有的部门信息)
	 * @see cn.zc.hrmsys.service.dept.service.IDeptService#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:05:24
	 */
	@Override
	public List<Dept> getAll() throws SQLException {
		return deptDao.getAll();
	}
	/**
	 * 
	 * @MethodName: getAllWithCriteria
	 * @Description: TODO (模糊查询部门记录)
	 * @see cn.zc.hrmsys.service.dept.service.IDeptService#getAllWithCriteria(cn.zc.hrmsys.pojo.criteria.DeptCriteria)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:05:36
	 */
	@Override
	public List<Dept> getAllWithCriteria(DeptCriteria deptCriteria) throws SQLException{
		return deptDao.getAllWithCriteria(deptCriteria);
	}
	
	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (更新一条部门记录)
	 * @see cn.zc.hrmsys.service.dept.service.IDeptService#update(cn.zc.hrmsys.pojo.entity.Dept)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:05:54
	 */
	@Override
	public void update(Dept dept) throws SQLException {
		long count = deptDao.getCountWithName(dept.getDeptName());
		if(count>0) {
			throw new DeptException("用户名重复，请换个名字");
		}
		deptDao.updateDept(dept);
	}

	/**
	 * 
	 * @MethodName: add
	 * @Description: TODO (新增一条部门信息记录)
	 * @see cn.zc.hrmsys.service.dept.service.IDeptService#add(cn.zc.hrmsys.pojo.entity.Dept)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:06:10
	 */
	@Override
	public void add(Dept dept) throws SQLException {
		long count = deptDao.getCountWithName(dept.getDeptName());
		if(count>0) {
			throw new DeptException("用户名重复，请换个名字");
		}
		deptDao.addDept(dept);
	}

	/**
	 * 
	 * @MethodName: deleteById
	 * @Description: TODO (注销一条部门信息记录)
	 * @see cn.zc.hrmsys.service.dept.service.IDeptService#deleteById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:06:28
	 */
	@Override
	public void deleteById(Integer id) throws SQLException {
		deptDao.deleteDeptById(id);
	}

}
