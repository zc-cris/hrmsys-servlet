package cn.zc.hrmsys.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.zc.hrmsys.dao.emp.dao.EmpDao;
import cn.zc.hrmsys.dao.emp.dao.IEmpDao;
import cn.zc.hrmsys.pojo.criteria.EmpCriteria;
import cn.zc.hrmsys.pojo.entity.Emp;

class TestEmpDao {

	private IEmpDao empDao = new EmpDao();
	
	@Test			//ok
	void testGetWithUserCriteria() throws SQLException {
		List<Emp> list = empDao.getWithUserCriteria(new EmpCriteria(1001, null, null, null, null));
		System.out.println(list);
	}

	@Test		//ok
	void testGetByIdCard() throws SQLException {
		Emp emp = empDao.getWithIdCard(125);
		System.out.println(emp);
	}
	
	@Test		//ok
	void testDeleteEmpById() throws SQLException {
		empDao.deleteEmpById(3);
	}

	@Test			//ok
	void testAddEmp() throws SQLException {
		empDao.addEmp(new Emp("张三丰", true, "1213", "123@qq.com", 1, 1233455666, "北京", 1001, 1002));
		empDao.addEmp(new Emp("张四狗", true, "1232", "1333@qq.com", 2, 125666, "上海", 1001, 1002));
	}

	@Test		//ok
	void testUpdateEmp() throws SQLException {
		empDao.updateEmp(new Emp(2, "666", "44@163.com", 3, "成都", 1221, 1002));
	}

	@Test  		//ok
	void testGetById() throws SQLException {
		Emp emp = empDao.getById(2);
		System.out.println(emp);
	}

}
