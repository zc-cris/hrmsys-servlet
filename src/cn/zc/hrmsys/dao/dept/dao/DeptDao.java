package cn.zc.hrmsys.dao.dept.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.dao.base.dao.BaseDao;
import cn.zc.hrmsys.pojo.criteria.DeptCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;
import cn.zc.hrmsys.util.JdbcUtils;

/**
 * 	
 * @ClassName：DeptDao.java
 * @Description：TODO (部门dao层的数据库操作)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.dao.DeptDao
 * @Author：zc-cris
 * @Create Date：2018年1月31日上午9:35:29
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class DeptDao extends BaseDao<Dept> implements IDeptDao {

	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (查询所有部门)
	 * @see cn.zc.hrmsys.dao.dept.dao.IDeptDao#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午9:21:01
	 */
	@Override
	public List<Dept> getAll() throws SQLException {
		String sql = "select deptId,deptName,deptDesc,deptState from tb_dept where deptState = 1";
		return queryList(sql);
	}

	/**
	 * 
	 * @MethodName: getAllWithCriteria
	 * @Description: TODO (模糊查询部门)
	 * @see cn.zc.hrmsys.dao.dept.dao.IDeptDao#getAllWithCriteria(cn.zc.hrmsys.pojo.criteria.DeptCriteria)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午9:21:16
	 */
	@Override
	public List<Dept> getAllWithCriteria(DeptCriteria deptCriteria) throws SQLException {
		String sql = "select deptId,deptName,deptDesc,deptState from tb_dept where deptName like ?"
				+ " and deptState = ?";
		return queryList(sql, deptCriteria.getDeptName(), deptCriteria.getDeptState());
	}

	/**
	 * 
	 * @MethodName: addDept
	 * @Description: TODO (增加部门)
	 * @see cn.zc.hrmsys.dao.dept.dao.IDeptDao#addDept(cn.zc.hrmsys.pojo.entity.Dept)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午9:21:45
	 */
	@Override	
	public void addDept(Dept dept) throws SQLException {
		String sql = "insert into tb_dept ( deptName, deptDesc) values (?,?)";
		update(sql, dept.getDeptName(),dept.getDeptDesc());
	}

	/**
	 * 
	 * @MethodName: updateDept
	 * @Description: TODO (更新部门信息)
	 * @see cn.zc.hrmsys.dao.dept.dao.IDeptDao#updateDept(cn.zc.hrmsys.pojo.entity.Dept)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午9:22:23
	 */
	@Override
	public void updateDept(Dept dept) throws SQLException {
		String sql = "update tb_dept set deptName=?,deptDesc=?,deptState=? where deptId=?";
		update(sql, dept.getDeptName(),dept.getDeptDesc(),dept.getDeptState(),dept.getDeptId());
	}

	/**
	 * 
	 * @MethodName: deleteDeptById
	 * @Description: TODO (注销部门)
	 * @see cn.zc.hrmsys.dao.dept.dao.IDeptDao#deleteDeptById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午9:29:37
	 */
	@Override
	public void deleteDeptById(Integer id) throws SQLException {
		String sql = "update tb_dept set deptState = 0 where deptId = ?";
		update(sql, id);
	}

	/**
	 * 
	 * @MethodName: getById
	 * @Description: TODO (根据id查询部门)
	 * @see cn.zc.hrmsys.dao.dept.dao.IDeptDao#getById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午9:29:57
	 */
	@Override
	public Dept getById(Integer id) throws SQLException {
		String sql = "select deptId,deptName,deptDesc,deptState from tb_dept where deptId = ?";
		return queryOne(sql, id);
	}

	/**
	 * 
	 * @MethodName: getCountWithName
	 * @Description: TODO (根据部门名字查询有效部门的数量，防止添加和更新部门信息重复)
	 * @see cn.zc.hrmsys.dao.dept.dao.IDeptDao#getCountWithName(java.lang.String)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午9:32:55
	 */
	@Override
	public long getCountWithName(String name) throws SQLException {
		String sql = "select count(deptName) from tb_dept where deptName=? and deptState = 1";
		return queryValue(sql, name);
	}

	@Override
	public void deleteUsersByIds(String[] values) throws SQLException {
		String sql = "update tb_dept set deptState = 0 where deptId = ?";
		
		Object[][] params = new Object[values.length][];	//高维也就是行数确定执行sql语句的次数，低维也就是列数是给？赋值
		for(int i =0;i<params.length;i++) {
			params[i] = new Object[]{values[i]};		//给低维也就是列数“？”赋值，有几个？低维就有几个元素，决定每条SQL语句的参数个数
		}
		JdbcUtils.getQueryRunner().batch(sql, params);
	}
}
