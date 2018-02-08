package cn.zc.hrmsys.service.user.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.pojo.criteria.UserCriteria;
import cn.zc.hrmsys.pojo.entity.User;

public interface IUserService {

	List<User> getAllWithCriteria(UserCriteria criteria) throws SQLException;
	
	User getUser(User user) throws SQLException;
	
	void update(User newUser, User oldUser) throws SQLException;
	
	void add(User user) throws SQLException;
	
	void deleteById(Integer id) throws SQLException;

	void deleteByIds(String[] values) throws NumberFormatException, SQLException;

	List<User> getAll(int start) throws SQLException;

	User getUserById(Integer integer) throws SQLException;

	int getCountByName(String name) throws SQLException;

	long getAllCount() throws SQLException;
	
}
