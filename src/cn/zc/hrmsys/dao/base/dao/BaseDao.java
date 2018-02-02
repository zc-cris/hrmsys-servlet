package cn.zc.hrmsys.dao.base.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.zc.hrmsys.util.JdbcUtils;

/**
 * 	
 * @ClassName：BaseDao.java
 * @Description：TODO (实现了dao类通用操作接口的dao基类)
 * @Project Name：hrmsys
 * @Package Name: cn.zc.hrmsys.dao.baseDao
 * @Author：zc-cris
 * @Create Date：2018年1月30日下午11:27:13
 * @version: v1.0
 * @Copyright: zc-cris
 * @mail: 17623887386@163.com
 */
public class BaseDao<T> implements IBaseDao<T> {
	private Class<T> clazz;
	
	/**
	 * 
	 * Creates a new instance of BaseDao
	 * Description: TODO (为clazz成员属性确定对应的类型)
	 */
	public BaseDao() {
		Type superClass = this.getClass().getGenericSuperclass();
		if(superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			Type[] argsTypes = parameterizedType.getActualTypeArguments();
			if(argsTypes != null && argsTypes.length > 0) {
				Type type = argsTypes[0];
				if(type instanceof Class) {
					clazz = (Class<T>) type;
				}
			}
		}
		
	}

	/**
	 * 
	 * @MethodName: queryValue
	 * @Description: TODO (实现了IBaseDao接口的查询特定数据的方法)
	 * @see cn.zc.hrmsys.dao.base.dao.IBaseDao#queryValue(java.lang.String, java.lang.Object[])
	 * @Author：zc-cris
	 * @Create Date：2018年1月30日下午11:33:04
	 */
	@Override
	public <E> E queryValue(String sql, Object... params) throws SQLException {
		return JdbcUtils.getQueryRunner().query(sql, new ScalarHandler<>(), params);
	}

	/**
	 * 
	 * @MethodName: queryList
	 * @Description: TODO (实现了IBaseDao的查询全部数据的方法)
	 * @see cn.zc.hrmsys.dao.base.dao.IBaseDao#queryList(java.lang.String, java.lang.Object[])
	 * @Author：zc-cris
	 * @Create Date：2018年1月30日下午11:35:13
	 */
	@Override
	public List<T> queryList(String sql, Object... params) throws SQLException {
		return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<>(clazz), params);
	}

	/**
	 * 
	 * @MethodName: queryOne
	 * @Description: TODO (实现了IBaseDao接口的查询一条记录的方法)
	 * @see cn.zc.hrmsys.dao.base.dao.IBaseDao#queryOne(java.lang.String, java.lang.Object[])
	 * @Author：zc-cris
	 * @Create Date：2018年1月30日下午11:35:36
	 */
	@Override
	public T queryOne(String sql, Object... params) throws SQLException {
		return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<>(clazz), params);
	}

	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (实现了IBaseDao接口定义的更新数据的方法)
	 * @see cn.zc.hrmsys.dao.base.dao.IBaseDao#update(java.lang.String, java.lang.Object[])
	 * @Author：zc-cris
	 * @Create Date：2018年1月30日下午11:35:41
	 */
	@Override
	public void update(String sql, Object... params) throws SQLException {
		JdbcUtils.getQueryRunner().update(sql, params);
	}

}
