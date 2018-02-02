package cn.zc.hrmsys.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.zc.hrmsys.dao.dept.dao.DeptDao;
import cn.zc.hrmsys.dao.dept.dao.IDeptDao;
import cn.zc.hrmsys.dao.user.dao.IUserDao;
import cn.zc.hrmsys.dao.user.dao.UserDao;
import cn.zc.hrmsys.pojo.criteria.DeptCriteria;
import cn.zc.hrmsys.pojo.criteria.UserCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;
import cn.zc.hrmsys.pojo.entity.User;

class TestUserDao {

	@Test
	void test() throws SQLException {
		IUserDao userDao = new UserDao();
		//添加用户	ok
//		userDao.addUser(new User("james", "123"));
//		userDao.addUser(new User("张大帅", "222"));
		
		//查询所有用户		ok
//		List<User> list = userDao.getAll();
//		System.out.println(list);
		
		//模糊查询用户		 ok
//		List<User> list = userDao.getWithUserCriteria(new UserCriteria("james", null));
//		List<User> list = userDao.getWithUserCriteria(new UserCriteria("james", false));
//		List<User> list = userDao.getWithUserCriteria(new UserCriteria("张大帅", false));
//		List<User> list = userDao.getWithUserCriteria(new UserCriteria("张大帅", true));
//		List<User> list = userDao.getWithUserCriteria(new UserCriteria(null, true));
//		System.out.println(list);
		
		//修改用户信息   	ok
//		User user = new User("重庆吴一凡", "666");
//		user.setUserId(1);
//		userDao.updateUser(user);
		
		//注销用户		ok
//		userDao.deleteUserById(2);
		
		//根据用户id查询用用户	ok
//		User user = userDao.getById(1);
//		System.out.println(user);
		
		//根据用户名字查询有效用户的数量，防止添加和更新用户信息重复    	ok
//		long count = userDao.getCountWithName("张大帅");
//		System.out.println(count);
		User user = new User("admin", "123");
		User user2 = userDao.getUser(user);
		System.out.println(user2 == null);
	}

}
