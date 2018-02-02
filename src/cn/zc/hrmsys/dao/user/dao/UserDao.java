package cn.zc.hrmsys.dao.user.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import cn.zc.hrmsys.dao.base.dao.BaseDao;
import cn.zc.hrmsys.pojo.criteria.UserCriteria;
import cn.zc.hrmsys.pojo.entity.User;
import cn.zc.hrmsys.util.JdbcUtils;

public class UserDao extends BaseDao<User> implements IUserDao {

	/**
	 * 
	 * @MethodName: getAll
	 * @Description: TODO (查询所有有效用户,并按照注册时间进行降序排序)
	 * @see cn.zc.hrmsys.dao.user.dao.IUserDao#getAll()
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午10:42:49
	 */
	@Override
	public List<User> getAll() throws SQLException {
		String sql = "select userId,userName,userPwd,userState,regDate from tb_user where userState=1 order by regDate desc";
		return queryList(sql);
	}

	/**
	 * 
	 * @MethodName: getWithUserCriteria
	 * @Description: TODO (模糊查询用户并按照注册时间降序排序)
	 * @see cn.zc.hrmsys.dao.user.dao.IUserDao#getWithUserCriteria(cn.zc.hrmsys.pojo.criteria.UserCriteria)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午10:29:17
	 */
	@Override
	public List<User> getWithUserCriteria(UserCriteria userCriteria) throws SQLException {
		String sql = "select userId,userName,userPwd,userState,regDate from tb_user where userName like ?"
				+ " and userState = ? order by regDate desc";
		return queryList(sql, userCriteria.getUserName(), userCriteria.getUserState());
	}

	/**
	 * 
	 * @MethodName: addUser
	 * @Description: TODO (添加一条用户记录)
	 * @see cn.zc.hrmsys.dao.user.dao.IUserDao#addUser(cn.zc.hrmsys.pojo.entity.User)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午10:20:46
	 */
	@Override
	public void addUser(User user) throws SQLException {
		String sql = "insert into tb_user (userName, userPwd, regDate) values (?,?,?)";
		update(sql, user.getUserName(), user.getUserPwd(), new Date(System.currentTimeMillis()));
	}

	/**
	 * 
	 * @MethodName: updateUser
	 * @Description: TODO (更新用户信息)
	 * @see cn.zc.hrmsys.dao.user.dao.IUserDao#updateUser(cn.zc.hrmsys.pojo.entity.User)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午10:43:51
	 */
	@Override
	public void updateUser(User user) throws SQLException {
		String sql = "update tb_user set userName=?,userPwd=? where userId=?";
		update(sql, user.getUserName(), user.getUserPwd(), user.getUserId());
	}

	/**
	 * 
	 * @MethodName: deleteUserById
	 * @Description: TODO (注销用户)
	 * @see cn.zc.hrmsys.dao.user.dao.IUserDao#deleteUserById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午10:44:05
	 */
	@Override
	public void deleteUserById(Integer id) throws SQLException {
		String sql = "update tb_user set userState = 0 where userId = ?";
		update(sql, id);
	}

	/**
	 * 
	 * @MethodName: getById
	 * @Description: TODO (根据id查询用户)
	 * @see cn.zc.hrmsys.dao.user.dao.IUserDao#getById(java.lang.Integer)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午10:46:54
	 */
	@Override
	public User getById(Integer id) throws SQLException {
		String sql = "select userId,userName,userPwd from tb_user where userId = ?";
		return queryOne(sql, id);
	}

	/**
	 * 
	 * @MethodName: getCountWithName
	 * @Description: TODO (根据用户名字查询有效用户的数量，防止添加和更新用户信息重复   )
	 * @see cn.zc.hrmsys.dao.user.dao.IUserDao#getCountWithName(java.lang.String)
	 * @Author：zc-cris
	 * @Create Date：2018年1月31日上午10:58:40
	 */
	@Override
	public long getCountWithName(String name) throws SQLException {
		String sql = "select count(userName) from tb_user where userName=? and userState = 1";
		return queryValue(sql, name);
	}

	/**
	 * 
	 * @MethodName: getUser
	 * @Description: TODO (根据用户名和密码查询用户用于登录)
	 * @see cn.zc.hrmsys.dao.user.dao.IUserDao#getUser(cn.zc.hrmsys.pojo.entity.User)
	 * @Author：zc-cris
	 * @Create Date：2018年2月2日上午9:44:06
	 */
	@Override
	public User getUser(User user) throws SQLException {
		String sql = "select userName,userState,regDate from tb_user where userName=? and userPwd=? and userState = 1";
		return queryOne(sql, user.getUserName(),user.getUserPwd());
	}

	/**
	 * 
	 * @throws SQLException 
	 * @MethodName: deleteUsersByIds
	 * @Description: TODO (调用dbutils的批处理实现批量注销用户记录的功能)
	 * @see cn.zc.hrmsys.dao.user.dao.IUserDao#deleteUsersByIds(java.lang.String[])
	 * @Author：zc-cris
	 * @Create Date：2018年2月2日上午10:06:06
	 */
	@Override
	public void deleteUsersByIds(String[] values) throws SQLException {
		String sql = "update tb_user set userState = 0 where userId = ?";
		
		Object[][] params = new Object[values.length][];	//高维也就是行数确定执行sql语句的次数，低维也就是列数是给？赋值
		for(int i =0;i<params.length;i++) {
			params[i] = new Object[]{values[i]};		//给低维也就是列数“？”赋值，有几个？低维就有几个元素，决定每条SQL语句的参数个数
		}
		JdbcUtils.getQueryRunner().batch(sql, params);
	}
}
