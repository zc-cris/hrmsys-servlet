package cn.zc.hrmsys.dao.dept.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.dao.base.dao.IBaseDao;
import cn.zc.hrmsys.pojo.criteria.DeptCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;

/**
 * 	
 * @ClassName：IDeptDao.java
 * @Description：TODO (定义deptDao所有方法的接口并继承IBaseDao接口)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.dao.DeptDao
 * @Author：zc-cris
 * @Create Date：2018年1月30日下午11:49:15
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public interface IDeptDao{
	
	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (定义查询所有Dept的方法)
	 * @return
	 * @Return Type: List<Dept>
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午11:46:21
	 * @since
	 * @throws
	 */
	List<Dept> getAll() throws SQLException;
	
	/**
	 * 
	 * @MethodName: getAllWithCriteria
	 * @Description: TODO (定义根据条件对象来查询dept的方法)
	 * @param deptCriteria
	 * @return
	 * @Return Type: List<Dept>
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午11:46:40
	 * @since
	 * @throws
	 */
	List<Dept> getAllWithCriteria(DeptCriteria deptCriteria) throws SQLException;
	
	/**
	 * 
	 * @MethodName: addDept
	 * @Description: TODO (定义添加dept的方法)
	 * @param dept
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午11:47:02
	 * @since
	 * @throws
	 */
	void addDept(Dept dept) throws SQLException;
	
	/**
	 * 
	 * @MethodName: updateDept
	 * @Description: TODO (定义更新dept的方法)
	 * @param dept
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午11:47:23
	 * @since
	 * @throws
	 */
	void updateDept(Dept dept) throws SQLException;
	
	/**
	 * 
	 * @MethodName: deleteDeptById
	 * @Description: TODO (定义根据id删除dept的方法)
	 * @param dept
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午11:47:56
	 * @since
	 * @throws
	 */
	void deleteDeptById(Integer id) throws SQLException;
	
	/**
	 * 
	 * @MethodName: getById
	 * @Description: TODO (定义根据id查询dept的方法)
	 * @param id
	 * @return
	 * @Return Type: Dept
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午11:48:16
	 * @since
	 * @throws
	 */
	Dept getById(Integer id) throws SQLException;
	
	/**
	 * 
	 * @MethodName: getCountWithName
	 * @Description: TODO (定义根据dept的名字查询对应的数量的方法)
	 * @return
	 * @Return Type: long
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午11:48:39
	 * @since
	 * @throws
	 */
	long getCountWithName(String name) throws SQLException;
}
