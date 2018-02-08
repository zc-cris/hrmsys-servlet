package cn.zc.hrmsys.dao.user.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.criteria.UserCriteria;
import cn.zc.hrmsys.pojo.entity.Dept;
import cn.zc.hrmsys.pojo.entity.User;

/**
 * 	
 * @ClassName：IUserDao.java
 * @Description：TODO (定义用户dao层操作的接口)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.dao.UserDao
 * @Author：zc-cris
 * @Create Date：2018年1月31日上午9:36:51
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public interface IUserDao {
	
	List<User> getAll(int start) throws SQLException;
	
	List<User> getWithUserCriteria(UserCriteria userCriteria) throws SQLException;
	
	void addUser(User user) throws SQLException;
	
	void updateUser(User user) throws SQLException;
	
	void deleteUserById(Integer id) throws SQLException;
	
	User getById(Integer id) throws SQLException;
	
	long getCountWithName(String name) throws SQLException;

	User getUser(User user) throws SQLException;

	void deleteUsersByIds(String[] values) throws SQLException;

	long getAllCount() throws SQLException;
}
