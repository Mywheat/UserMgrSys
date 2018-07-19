package cn.edu.lingnan.usermgrsys.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.lingnan.usermgrsys.common.exception.DAOException;

/**
 * 数据库公有类
 * @author Administrator
 *
 */
public class DBUtils {

	/**
	 * 数据库连接方法
	 * 
	 * @return 数据库连接conn
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
			String user = "scott";
			String password = "123456";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			//将异常封装成自定义异常
			throw new DAOException("数据库连接失败");
		} catch (ClassNotFoundException e) {
			//将异常封装成自定义异常
			throw new DAOException("数据库连接失败，加载驱动错误",e);
		}
		return conn;
	}

	/**
	 * 开启事务
	 * 
	 * @param conn
	 */
	public static void beginTransation(Connection conn) {
		// 将事务的自动提交模式设为假
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DAOException("开启事务失败",e);
		}
	}

	/**
	 * 提交事务
	 * 
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			// 提交事务，将事务的自动提交模式设为真
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DAOException("提交事务失败",e);
		}
	}

	/**
	 * 回滚事务
	 * 
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			// 回滚事务，将事务的自动提交模式设为真
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DAOException("回滚事务失败",e);
		}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// 将异常封装成自定义异常
				throw new DAOException("数据库连接关闭失败",e);
			}
		}
	}

	/**
	 * 关闭statement
	 * @param rs 结果集对象
	 * @param stmt 声明对象
	 */
	public static void closeStatement(ResultSet rs, Statement stmt) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DAOException("关闭结果集或者声明对象失败",e);
		}

	}

}
