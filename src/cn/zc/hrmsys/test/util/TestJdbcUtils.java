package cn.zc.hrmsys.test.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import cn.zc.hrmsys.util.JdbcUtils;

class TestJdbcUtils {

	/**
	 * 
	 * @MethodName: testConnection
	 * @Description: TODO (测试数据库连接是否正常)
	 * @throws SQLException
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午9:20:42
	 * @since
	 * @throws 
	 */
	@Test
	void testConnection() throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		System.out.println(conn);
	}
	/**
	 * 
	 * @MethodName: testQueryRunne
	 * @Description: TODO (测试是否可以通过工具类拿到QueryRunner类)
	 * @throws SQLException
	 * @Return Type: void
	 * @Author: zc-cris
	 * @Create Date：2018年1月30日下午9:21:22
	 * @since
	 * @throws
	 */
	@Test
	void testQueryRunne() throws SQLException{
		QueryRunner runner = JdbcUtils.getQueryRunner();
		System.out.println(runner);
	}

}
