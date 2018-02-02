package cn.zc.hrmsys.service.emp.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.criteria.EmpCriteria;
import cn.zc.hrmsys.pojo.entity.Emp;

public interface IEmpService {
	
	List<Emp> getAllWithCriteria(EmpCriteria empCriteria) throws SQLException;
	
	void update(Emp emp) throws SQLException;
	
	void add(Emp emp) throws SQLException;
	
	void deleteById(Integer id) throws SQLException;

}
