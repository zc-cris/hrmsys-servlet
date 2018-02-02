package cn.zc.hrmsys.service.emp.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.dao.emp.dao.EmpDao;
import cn.zc.hrmsys.dao.emp.dao.IEmpDao;
import cn.zc.hrmsys.factory.dao.factory.DaoFactory;
import cn.zc.hrmsys.pojo.criteria.EmpCriteria;
import cn.zc.hrmsys.pojo.entity.Emp;

public class EmpService implements IEmpService{

	IEmpDao empDao = (IEmpDao) DaoFactory.newInstance().getDaoEntity("EmpDao");
	
	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (模糊查询员工记录)
	 * @see cn.zc.hrmsys.service.emp.service.IEmpService#getAll(cn.zc.hrmsys.pojo.criteria.EmpCriteria)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:02:55
	 */
	@Override
	public List<Emp> getAllWithCriteria(EmpCriteria empCriteria) throws SQLException {
		return empDao.getWithUserCriteria(empCriteria);
	}

	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (更新一条员工记录)
	 * @see cn.zc.hrmsys.service.emp.service.IEmpService#update(cn.zc.hrmsys.pojo.entity.Emp)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:03:28
	 */
	@Override
	public void update(Emp emp) throws SQLException {
		empDao.updateEmp(emp);
	}

	/**
	 * 
	 * @MethodName: add
	 * @Description: TODO (新增一条员工记录)
	 * @see cn.zc.hrmsys.service.emp.service.IEmpService#add(cn.zc.hrmsys.pojo.entity.Emp)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:04:17
	 */
	@Override
	public void add(Emp emp) throws SQLException {
		empDao.addEmp(emp);
	}

	/**
	 * 
	 * @MethodName: deleteById
	 * @Description: TODO (注销一条员工记录)
	 * @see cn.zc.hrmsys.service.emp.service.IEmpService#deleteById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午7:05:06
	 */
	@Override
	public void deleteById(Integer id) throws SQLException {
		empDao.deleteEmpById(id);
	}

}
