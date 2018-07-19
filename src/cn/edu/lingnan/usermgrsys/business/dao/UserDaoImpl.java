package cn.edu.lingnan.usermgrsys.business.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import cn.edu.lingnan.usermgrsys.common.exception.DAOException;
import cn.edu.lingnan.usermgrsys.common.util.DBUtils;
import cn.edu.lingnan.usermgrsys.common.util.TypeUtils;
import cn.edu.lingnan.usermgrsys.domain.UserVO;

/**
 * 实例化用户接口
 * 
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {

	/**
	 * 数据库连接
	 */
	private Connection conn;

	/**
	 * 构造方法
	 * 
	 * @param conn
	 *            数据库连接
	 */
	public UserDaoImpl(Connection conn) {
		// 给属性附初始值
		this.conn = conn;
	}

	/**
	 * 实例化用户登录
	 */
	public UserVO login(String userID, String password) {
		ResultSet rs = null; // 声明用户对象结果集，用于保存数据库查询结果
		PreparedStatement ps = null; // 声明预编译的声明对象变量，用于进行数据库操作的载体（一般有参数的都用预编译）
		UserVO uservo = null; // 声明用户对象变量，用于保存从结果中提取出来的数据
		try {
			// 调用连接对象的preparestatement方法，得到预编译对象，赋值给预编译对象变量
			ps = conn
					.prepareStatement("select * from t_user where userID=? and password=? and status='0'");
			ps.setString(1, userID);
			ps.setString(2, password);
			// 使用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = ps.executeQuery();
			if (rs.next()) {
				// 创建一个新用户对象，赋值给用户对象变量
				uservo = new UserVO();
				/*
				 * 调用结果集对象的getXXX方法，取出各字段的值 然后再调用用户对象的setXXX方法，给属性赋值
				 * 然后新创建的对象中包含了查询结果中的所有字段的值
				 */
				uservo.setNum(rs.getInt("num"));
				uservo.setUserID(rs.getString("UserID"));
				uservo.setUserName(rs.getString("UserName"));
				uservo.setPassword(rs.getString("Password"));
				uservo.setPower(rs.getString("Power"));
				uservo.setEmail(rs.getString("Mail"));
				uservo.setBirth(rs.getDate("Birth"));
				uservo.setStatus(rs.getString("Status"));
			}
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DAOException("用户登录查询失败", e);
		} finally {
			// 调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, ps);
		}
		/*
		 * 返回用户对象，如果查询结果不为空,该对象中封装了查询结果中的数据 如果查询结果为空，该对象为空值null
		 */
		return uservo;
	}

	/**
	 * 实例化用户注册
	 */
	public boolean addUser(UserVO uservo) {
		PreparedStatement ps = null;
		boolean flag = false;
		/*
		 * 调用结果集对象的getXXX方法，取出各字段的值 然后赋值给各个变量字段 再通过sql语句进行注册
		 */
		try {
			// 注册的sql语句
			ps = conn
					.prepareStatement("insert into t_user values(numseq.nextval,?,?,?,?,?,?,?)");
			/*
			 * 调用预编译对象的setXXX方法，给？号赋值
			 */
			ps.setString(1, uservo.getUserID());
			ps.setString(2, uservo.getUserName());
			ps.setString(3, uservo.getPassword());
			ps.setString(4, uservo.getPower());
			ps.setString(5, uservo.getEmail());
			// 将java.util.Date类型转换为java.sql.Date类型
			ps.setDate(6, new java.sql.Date(uservo.getBirth().getTime()));
			ps.setString(7, uservo.getStatus());
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			throw new DAOException("用户注册失败", e);
		} finally {
			DBUtils.closeStatement(null, ps);
		}
		return flag;
	}

	/**
	 * 实例化查询所有用户信息(分页查询)
	 */
	public Vector<UserVO> searchAllUser(int pageNum,int pageSize) {
		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			/*firstIndex:起始索引
			 * pageSize:每页显示的数量
			 * orderColumn:排序的字段名
			 * sql:可以是简单的单表查询语句，也可以是复杂的多表联合查询语句
			 * rownum:行号
			 */
			int count = -1; //初始化数据库中数据的数量
			ps1 = conn.prepareStatement("select count(*) as count from t_user where status='0'");
			rs1 = ps1.executeQuery();
			if(rs1.next()){
				count = rs1.getInt("count"); //计算出数据的总数量
			}
			int a = count % pageSize;
			int allPageNum = -1;
			if(a==0){
				allPageNum = count/pageSize;
			}else{
				allPageNum = (count/pageSize)+1;
			}
			if(pageNum>allPageNum){
				System.out.println("没有该页数，请重新输入1~"+allPageNum+"之间的页数");
			}			
			ps = conn.prepareStatement("select * from (select t2.*,rownum rn from (select t1.* from t_user t1 order by userid) t2) " +
					"where rn>? and rn<=?");
			ps.setInt(1, (pageNum-1)*pageSize);
			ps.setInt(2, pageNum*pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				/*
				 * UserVO uservo = new UserVO()这条语句一定一定要放在while语句里面
				 * 因为每次new的对象都是不一样的，如果在循环外面的话，循环的时候后面的对象会覆盖掉前面的对象
				 */
				UserVO uservo = new UserVO();
				uservo.setNum(rs.getInt("num"));
				uservo.setUserID(rs.getString("userID"));
				uservo.setUserName(rs.getString("userName"));
				uservo.setPassword(rs.getString("password"));
				uservo.setPower(rs.getString("power"));
				uservo.setEmail(rs.getString("mail"));
				uservo.setBirth(rs.getDate("birth"));
				uservo.setStatus(rs.getString("status"));
				v.add(uservo);
			}
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DAOException("查询所有用户失败", e);
		} finally {
			// 调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, ps);
			DBUtils.closeStatement(rs1, ps1);
		}
		return v;
	}

	/**
	 * 实例化根据用户编号查询用户信息(精准查询)
	 */
	@Override
	public UserVO searchUserByID(String userID) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		UserVO uservo = new UserVO();
		try {
			ps = conn.prepareStatement("select * from t_user where userid = '"
					+ userID + "' and status = '0'");
			rs = ps.executeQuery();
			if (rs.next()) {
				uservo.setNum(rs.getInt("num"));
				uservo.setUserID(rs.getString("userID"));
				uservo.setUserName(rs.getString("userName"));
				uservo.setPassword(rs.getString("password"));
				uservo.setPower(rs.getString("power"));
				uservo.setEmail(rs.getString("mail"));
				uservo.setBirth(rs.getDate("birth"));
				uservo.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DAOException("根据用户编号查询失败", e);
		} finally {
			// 调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, ps);
		}
		return uservo;
	}

	/**
	 * 实例化根据用户名查询用户信息(模糊查询)
	 */
	@Override
	public Vector<UserVO> searchUserByName(String userName) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try {
			String userName1 = "%" + userName + "%"; // 指定的模糊查询用户名
			ps = conn
					.prepareStatement("select * from t_user where username like '"
							+ userName1 + "'");
			rs = ps.executeQuery();
			while (rs.next()) {
				UserVO uservo = new UserVO();
				uservo.setNum(rs.getInt("num"));
				uservo.setUserID(rs.getString("userID"));
				uservo.setUserName(rs.getString("userName"));
				uservo.setPassword(rs.getString("password"));
				uservo.setPower(rs.getString("power"));
				uservo.setEmail(rs.getString("mail"));
				uservo.setBirth(rs.getDate("birth"));
				uservo.setStatus(rs.getString("status"));
				v.add(uservo);
			}
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DAOException("根据用户名查询用户失败", e);
		} finally {
			// 调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, ps);
		}
		return v;
	}

	/**
	 * 实例化更新用户信息
	 */
	@Override
	public boolean updateUser(UserVO uservo) {
		boolean flag = false;
		PreparedStatement ps = null;
		int i = -1;
		try {
			ps = conn
					.prepareStatement("update t_user set num=?,username=?,password=?,"
							+ "power =?,mail=?,birth=?,status=? where userid=?");		
			ps.setInt(1, uservo.getNum());
			ps.setString(2, uservo.getUserName());
			ps.setString(3, uservo.getPassword());
			ps.setString(4, uservo.getPower());
			ps.setString(5, uservo.getEmail());		
			ps.setDate(6, new java.sql.Date(uservo.getBirth().getTime()));
			ps.setString(7, uservo.getStatus());
			ps.setString(8, uservo.getUserID());
			i = ps.executeUpdate(); // 返回的是更新的条数
			if (i > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			// 将异常封装成自定义异常
			throw new DAOException("a.用户更新失败", e);
		} finally {
			// 调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(null, ps);
		}
		return flag;
	}

	/**
	 * 实例化删除用户信息
	 */
	@Override
	public boolean deleteUser(String userID) {
		PreparedStatement ps = null;
		boolean flag = false;
		int i = -1;
		try{
			ps = conn.prepareStatement("update t_user set status='1' where userID=?");
			ps.setString(1, userID);
			i = ps.executeUpdate();
			if(i>0){
				flag = true;
			}else{
				flag = false;
			}
		}catch(SQLException e){
			throw new DAOException("a.用户删除失败", e);
		}finally{
			DBUtils.closeStatement(null, ps);
		}
		return flag;
	}

}
