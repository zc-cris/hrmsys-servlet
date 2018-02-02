package cn.zc.hrmsys.service.user.service;

import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.MyException.UserException;
import cn.zc.hrmsys.dao.user.dao.IUserDao;
import cn.zc.hrmsys.factory.dao.factory.DaoFactory;
import cn.zc.hrmsys.pojo.criteria.UserCriteria;
import cn.zc.hrmsys.pojo.entity.User;

public class UserService implements IUserService {
	
	IUserDao userDao = (IUserDao) DaoFactory.newInstance().getDaoEntity("UserDao");

	/**
	 * 
	 * @MethodName: getAllWithCriteria
	 * @Description: TODO (根据条件查询所有用户记录)
	 * @see cn.zc.hrmsys.service.user.service.IUserService#getAllWithCriteria(cn.zc.hrmsys.pojo.criteria.UserCriteria)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午11:13:48
	 */
	@Override
	public List<User> getAllWithCriteria(UserCriteria criteria) throws SQLException {
		return userDao.getWithUserCriteria(criteria);
	}
	

	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (更新一条用户记录)
	 * @see cn.zc.hrmsys.service.user.service.IUserService#update(cn.zc.hrmsys.pojo.entity.User)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午6:54:30
	 */
	@Override
	public void update(User newUser, User oldUser) throws SQLException {
		if(newUser.getUserName().equals(oldUser.getUserName())) {
			userDao.updateUser(newUser);
		}else {
			UpdateCeckName(newUser);
			userDao.updateUser(newUser);
		}
	}

	/**
	 * @throws SQLException 
	 * 
	 * @MethodName: UpdateCeckName
	 * @Description: TODO (更新时检查用户名)
	 * @param newUser
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月2日下午8:29:08
	 * @since
	 * @throws
	 */
	private void UpdateCeckName(User newUser) throws SQLException,UserException {
		long count = userDao.getCountWithName(newUser.getUserName());
		if(count > 0) {
			throw new UserException("存在相同名字的用户，请更改用户名");
		}
	}


	/**
	 * 
	 * @MethodName: checkName
	 * @Description: TODO (检查用户名不能重复，这是用于登录的帐号)
	 * @param user
	 * @throws SQLException
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年2月1日下午6:55:17
	 * @since
	 * @throws
	 */
	public void AddCeckName(User user) throws SQLException,UserException {
		long count = userDao.getCountWithName(user.getUserName());
		if(count > 0) {
			throw new UserException("存在相同名字的用户，请更改用户名");
		}
	}

	/**
	 * 
	 * @MethodName: add
	 * @Description: TODO (增加一条用户记录)
	 * @see cn.zc.hrmsys.service.user.service.IUserService#add(cn.zc.hrmsys.pojo.entity.User)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午6:58:20
	 */
	@Override
	public void add(User user) throws SQLException {
		AddCeckName(user);
		userDao.addUser(user);
	}

	/**
	 * 
	 * @MethodName: deleteById
	 * @Description: TODO (注销一条用户记录)
	 * @see cn.zc.hrmsys.service.user.service.IUserService#deleteById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午6:58:31
	 */
	@Override
	public void deleteById(Integer id) throws SQLException {
		userDao.deleteUserById(id);
	}

	/**
	 * 
	 * @MethodName: getUser
	 * @Description: TODO (根据用户名和密码查询用户)
	 * @see cn.zc.hrmsys.service.user.service.IUserService#getUser(cn.zc.hrmsys.pojo.entity.User)
	 * @Author：zc-cris
	 * @Create Date：2018年2月1日下午8:28:31
	 */
	@Override
	public User getUser(User user) throws SQLException {
		return userDao.getUser(user);
	}

	/**
	 * 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @MethodName: deleteByIds
	 * @Description: TODO (根据id的数组删除数据)
	 * @see cn.zc.hrmsys.service.user.service.IUserService#deleteByIds(java.lang.String)
	 * @Author：zc-cris
	 * @Create Date：2018年2月2日上午9:34:23
	 */
	@Override
	public void deleteByIds(String[] values) throws NumberFormatException, SQLException {
		userDao.deleteUsersByIds(values);
	}

	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (查询所有用户)
	 * @see cn.zc.hrmsys.service.user.service.IUserService#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年2月2日下午2:40:56
	 */
	@Override
	public List<User> getAll() throws SQLException {
		return userDao.getAll();
	}

	/**
	 * 
	 * @MethodName: getUserById
	 * @Description: TODO (根据id查询到用户数据)
	 * @see cn.zc.hrmsys.service.user.service.IUserService#getUserById(java.lang.String)
	 * @Author：zc-cris
	 * @Create Date：2018年2月2日下午7:52:26
	 */
	@Override
	public User getUserById(Integer userId) throws SQLException {
		return userDao.getById(userId);
	}
}



