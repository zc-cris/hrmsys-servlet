package cn.zc.hrmsys.dao.base.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 	
 * @ClassName：IBaseDao.java
 * @Description：TODO (dao基类，定义所有dao类的共同方法)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.dao.baseDao
 * @Author：zc-cris
 * @Create Date：2018年1月30日下午9:35:22
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public interface IBaseDao<T> {

	/**
	 * 
	 * @MethodName: queryValue
	 * @Description: TODO (定义了查询特定数据的方法，例如：查询某一行数据的某个字段，或者是某张表的记录数)
	 * @param sql
	 * @param params
	 * @return
	 * @Return Type: E
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午9:41:30
	 * @since
	 * @throws
	 */
	<E> E queryValue(String sql, Object...params ) throws SQLException;
	
	/**
	 * 
	 * @MethodName: queryList
	 * @Description: TODO (定义了查询某张表多条记录的方法)
	 * @param sql
	 * @param params
	 * @return
	 * @Return Type: List<T>
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午9:42:43
	 * @since
	 * @throws
	 */
	List<T> queryList(String sql, Object...params ) throws SQLException;
	
	/**
	 * 
	 * @MethodName: queryOne
	 * @Description: TODO (定义了查询某张表一条记录的方法)
	 * @param sql
	 * @param params
	 * @return
	 * @Return Type: T
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午9:43:49
	 * @since
	 * @throws
	 */
	T queryOne(String sql, Object...params ) throws SQLException;
	
	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (定义了更新，删除，增加方法)
	 * @param sql
	 * @param params
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午9:44:15
	 * @since
	 * @throws
	 */
	void update(String sql, Object... params) throws SQLException;
}
