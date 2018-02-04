package cn.zc.hrmsys.service.dept.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.criteria.DeptCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;
import cn.zc.hrmsys.pojo.entity.User;

public interface IDeptService {

	List<Dept> getAll() throws SQLException;
	
	void update(Dept dept) throws SQLException;
	
	void add(Dept dept) throws SQLException;
	
	void deleteById(Integer id) throws SQLException;

	List<Dept> getAllWithCriteria(DeptCriteria deptCriteria) throws SQLException;

	void deleteByIds(String[] values) throws NumberFormatException, SQLException;

	Dept getDeptById(Integer valueOf) throws SQLException;

	void update(Dept newDept, Dept oldDept) throws SQLException;
	
}
