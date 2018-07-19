package cn.edu.lingnan.usermgrsys.controller;

import java.util.Vector;

import cn.edu.lingnan.usermgrsys.business.service.UserService;
import cn.edu.lingnan.usermgrsys.business.service.UserServiceImpl;
import cn.edu.lingnan.usermgrsys.domain.UserVO;

public class UserController {

	UserService us = UserServiceImpl.getInstance(); //声明用户service接口对象，用于业务处理
	/**
	 * 用户登录controller类
	 * @param UserID 用户编号
	 * @param Password 用户密码
	 * @return 返回登录的用户信息
	 */
	public UserVO doLogin(String UserID,String Password){
		UserVO uservo = null;
		try{
			//调用用户service接口中的login方法，进行用户登录操作
			uservo = us.login(UserID, Password);
		}catch(Exception e){
			//显示异常信息
			System.out.println("登录错误"+e.getMessage());
		}
		return uservo;
	}
	
	/**
	 * 添加用户
	 * @param uservo 用户基本信息
	 * @return 返回布尔值
	 */
	public boolean addShow(UserVO uservo){
		boolean flag = false;
		try{
			flag = us.addShow(uservo);
		}catch(Exception e){
			System.out.println("注册失败"+e.getMessage());
		}
		
		return flag;
	}
	
	/**
	 * 查询所有用户
	 * @return 返回查询所有用户的信息
	 */
	public Vector<UserVO> searchAllUserShow(int pageNum,int pageSize){
		Vector<UserVO> v = new Vector<UserVO>();
		try{
			v = us.searchAllUser(pageNum, pageSize);
		}catch(Exception e){
			//显示异常信息
			System.out.println("查询所有用户错误"+e.getMessage());
		}		
		return v;
	}
	
	/**
	 * 根据用户编号查询用户信息
	 * @param userID 指定的用户编号
	 * @return 返回的用户信息
	 */
	public UserVO searchUserByID(String userID){
		UserVO uservo = null;
		try{
			uservo = us.searchUserByID(userID);
		}catch(Exception e){
			//显示异常信息
			System.out.println("根据用户编号查询用户失败"+e.getMessage());
		}
		return uservo;
	}
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName 指定的用户名
	 * @return 返回的用户信息
	 */
	public Vector<UserVO> searchUserByName(String userName){
		Vector<UserVO> v = new Vector<UserVO>();
		try{
			v = us.searchUserByName(userName);
		}catch(Exception e){
			//显示异常信息
			System.out.println("根据用户编号查询用户失败"+e.getMessage());
		}
		return v;
	}
	
	/**
	 * 更新用户信息
	 * @param uservo 指定更新的用户信息
	 * @return 返回布尔值
	 */
	public boolean updateUser(UserVO uservo){
		boolean flag = false;
		try{
			flag = us.updateUser(uservo);
		}catch(Exception e){
			//显示异常信息
			System.out.println("c.用户更新失败"+e.getMessage());
		}		
		return flag;
	}
	
	/**
	 * 删除用户信息
	 * @param userID 指定删除用户的用户编号
	 * @return 返回布尔值
	 */
	public boolean deleteUser(String userID){
		boolean flag = false;
		try{
			flag = us.deleteUser(userID);
		}catch(Exception e){
			//显示异常信息
			System.out.println("c.用户删除失败"+e.getMessage());
		}
		return flag;
	}
}
