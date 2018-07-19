package cn.edu.lingnan.usermgrsys.business.service;

import java.sql.Connection;
import java.util.Vector;

import cn.edu.lingnan.usermgrsys.business.dao.UserDao;
import cn.edu.lingnan.usermgrsys.common.dao.DaoFactory;
import cn.edu.lingnan.usermgrsys.common.exception.DAOException;
import cn.edu.lingnan.usermgrsys.common.exception.ServiceException;
import cn.edu.lingnan.usermgrsys.common.util.DBUtils;
import cn.edu.lingnan.usermgrsys.domain.UserVO;
import cn.edu.lingnan.usermrgsys.common.constant.EnumType;

public class UserServiceImpl implements UserService{

	/**单例模式
	 * 创建用户service私有实例类
	 */
	private static UserService userservice = new UserServiceImpl();
	/**
	 * 默认构造方法私有化
	 */
	private UserServiceImpl(){
		
	}
	/**
	 * 取得用户service实例
	 * @return 实例对象
	 */
	public static UserService getInstance(){
		return userservice;
	}
	
	/**
	 * 用户登录service类
	 */
	public UserVO login(String userID, String password) {
		Connection conn = null; //声明数据库连接对象，用于保存数据库连接对象
		UserVO uservo = null;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//通过dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao的login方法，进行登录操作，然后赋值给登录结果变量
			uservo = userMgrDao.login(userID, password);
		}catch(DAOException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("用户登录错误",e);
		}finally{
			DBUtils.closeConnection(conn);
		}		
		return uservo; //返回用户登录结果		
	}
	
	/**
	 * 用户注册service类,增删改都需要事务事件
	 */
	@Override
	public boolean addShow(UserVO uservo) {
		Connection conn = null;
		boolean flag = false;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//通过dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用数据库工具类的beginTransation方法，开启事务
			DBUtils.beginTransation(conn);
			/*
			 * 方法二：自增序列可以在调用dao中设置一个findMaxNum方法
			 * 取得最大num值，然后再加1赋值给num属性			 
			 * uservo.setNum(userMgrDao.findMaxNum()+1);
			 */
			flag = userMgrDao.addUser(uservo);
			//调用数据库工具类的commit方法，提交事务
			DBUtils.commit(conn);
		}catch(DAOException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn); //报错之后让事务回滚
			throw new ServiceException("用户注册失败",e);
		}finally{
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * 查询所有用户信息(分页查询)
	 */
	public Vector<UserVO> searchAllUser(int pageNum,int pageSize){
		Connection conn = null;
		Vector<UserVO> v = new Vector<UserVO>();
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//通过dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			//调用dao的searchAllUser方法，进行分页查询操作，然后赋值给查询结果变量
			v = userMgrDao.searchAllUser(pageNum, pageSize);
		}catch(DAOException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("查询所有用户失败",e);
		}finally{
			DBUtils.closeConnection(conn);
		}	
		return v;
	}
	
	/**
	 * 根据用户编号查询用户信息(精准查询)
	 */
	@Override
	public UserVO searchUserByID(String userID) {
		Connection conn = null;
		UserVO uservo = null;
		try{
			conn = DBUtils.getConnection();
			UserDao userMgrDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			uservo = userMgrDao.searchUserByID(userID);
		}catch(DAOException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("根据用户编号查询用户失败",e);
		}finally{
			DBUtils.closeConnection(conn);
		}			
		return uservo;
	}
	
	/**
	 * 根据用户名查询用户信息(模糊查询)
	 */
	@Override
	public Vector<UserVO> searchUserByName(String userName) {
		Vector<UserVO> v = new Vector<UserVO>();
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao userMgrDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			v = userMgrDao.searchUserByName(userName);
		}catch(DAOException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("根据用户名查询用户失败",e);
		}finally{
			DBUtils.closeConnection(conn);
		}		
		return v;
	}
	
	/**
	 * 更新用户信息
	 */
	@Override
	public boolean updateUser(UserVO uservo) {
		boolean flag = false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao userMgrDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			DBUtils.beginTransation(conn);
			flag = userMgrDao.updateUser(uservo);
			DBUtils.commit(conn);
		}catch(DAOException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("b.用户更新失败",e);
		}finally{
			DBUtils.closeConnection(conn);
		}	
		return flag;
	}
	
	/**
	 * 删除用户信息
	 */
	@Override
	public boolean deleteUser(String userID) {
		boolean flag = false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao userMgrDao = (UserDao)DaoFactory.getDao(conn, EnumType.USER_DAO);
			DBUtils.beginTransation(conn);
			flag = userMgrDao.deleteUser(userID);
			DBUtils.commit(conn);
		}catch(DAOException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("b.用户删除失败",e);
		}finally{
			DBUtils.closeConnection(conn);
		}		
		return flag;
	}
	
	
		
}
