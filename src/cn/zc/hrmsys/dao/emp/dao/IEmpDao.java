package cn.zc.hrmsys.dao.emp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.criteria.EmpCriteria;
import cn.zc.hrmsys.pojo.entity.Emp;
import cn.zc.hrmsys.pojo.entity.User;

public interface IEmpDao {
	
	List<Emp> getWithUserCriteria(EmpCriteria empCriteria) throws SQLException;
	
	void deleteEmpById(Integer id) throws SQLException;
	
	void addEmp(Emp emp) throws SQLException;
	
	void updateEmp(Emp emp) throws SQLException;
	
	Emp getById(Integer id) throws SQLException;

	Emp getWithIdCard(Integer id) throws SQLException;

}
