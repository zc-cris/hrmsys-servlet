package cn.zc.hrmsys.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.zc.hrmsys.dao.dept.dao.DeptDao;
import cn.zc.hrmsys.dao.dept.dao.IDeptDao;
import cn.zc.hrmsys.pojo.criteria.DeptCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;

class TestDeptDao {

	@Test
	void test() throws SQLException {
		IDeptDao deptDao = new DeptDao();
		//添加部门ok
		//deptDao.addDept(new Dept( "技术部", "这是负责开发的"));
		//deptDao.addDept(new Dept( "前台部", "都是大美女"));
		
		//查询所有部门ok
//		List<Dept> list = deptDao.getAll();
//		System.out.println(list);
		
		//模糊查询部门 ok
//		List<Dept> list = deptDao.getAllWithCriteria(new DeptCriteria("部", null));
//		List<Dept> list = deptDao.getAllWithCriteria(new DeptCriteria("部", false));
//		List<Dept> list = deptDao.getAllWithCriteria(new DeptCriteria("前", true));
//		List<Dept> list = deptDao.getAllWithCriteria(new DeptCriteria(null, true));
//		System.out.println(list);
		
		//修改部门信息   ok
//		Dept dept = new Dept("财务部","管钱的");
//		dept.setDeptId(1001);
//		deptDao.updateDept(dept);
		
		//注销部门		ok
//		deptDao.deleteDeptById(1002);
		
		//根据部门id查询部门	ok
//		Dept dept = deptDao.getById(1002);
//		System.out.println(dept);
		
		//根据部门名字查询有效部门的数量，防止添加和更新部门信息重复   ok
		long count = deptDao.getCountWithName("前台部");
		System.out.println(count);
	}
}
