package cn.edu.lingnan.usermgrsys.common.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Vector;

import org.junit.Test;

import cn.edu.lingnan.usermgrsys.business.dao.UserDao;
import cn.edu.lingnan.usermgrsys.business.dao.UserDaoImpl;
import cn.edu.lingnan.usermgrsys.common.util.DBUtils;
import cn.edu.lingnan.usermgrsys.common.util.TypeUtils;
import cn.edu.lingnan.usermgrsys.domain.UserVO;

public class UserDaoImplTest {

	@Test
	public void testUserDaoImpl() {
		Connection conn = DBUtils.getConnection();
		UserDao udi = new UserDaoImpl(conn);
		System.out.println("aaa测试成功啦。。。。。。");
	}

	/**
	 * 登录测试
	 */
	@Test
	public void testLogin() {
		Connection conn = DBUtils.getConnection();
		UserDao udi = new UserDaoImpl(conn);
		UserVO uservo = udi.login("u01", "u01");
		String UserID = uservo.getUserID();
		String Password = uservo.getPassword();
		System.out.println(UserID+" "+Password);
		
	}
	
	/**
	 * 注册测试
	 */
	@Test
	public void testRegister() {
		Connection conn = DBUtils.getConnection();
		UserDao udi = new UserDaoImpl(conn);
		UserVO uservo = new UserVO();
		uservo.setUserID("u05");
		uservo.setUserName("Henry");
		uservo.setPassword("u05");
		uservo.setPower("普通用户");
		uservo.setEmail("555@qq.com");
		uservo.setBirth(TypeUtils.strToDate("2016-10-10"));
		uservo.setStatus("0");
		boolean flag = udi.addUser(uservo);
		System.out.println(flag);
	}
	
	/**
	 * 查询所有用户信息测试（分页查询）
	 */
	@Test
	public void testSearchAllUser() {
		Connection conn = DBUtils.getConnection();
		Vector<UserVO> v = new Vector<UserVO>();
		UserDao udi = new UserDaoImpl(conn);
		v = udi.searchAllUser(2, 3);
		for(UserVO uservo : v){
			System.out.println(uservo.getNum()+"\t"+uservo.getUserID()+"\t"+uservo.getUserName()
					+"\t"+uservo.getPassword()+"\t"+uservo.getPower()+"\t"+uservo.getEmail()+"\t"
					+uservo.getBirth()+"\t"+uservo.getStatus());
	    }
	}
	
	/**
	 * 根据用户编号查询用户信息测试（精准查询）
	 */
	@Test
	public void testSearchUserByID() {
		Connection conn = DBUtils.getConnection();
		UserDao udi = new UserDaoImpl(conn);
		UserVO uservo = null;
		uservo = udi.searchUserByID("u02");
		System.out.println(uservo.getNum() + "\t" + uservo.getUserID() + "\t"
				+ uservo.getUserName() + "\t" + uservo.getPassword() + "\t"
				+ uservo.getPower() + "\t" + uservo.getEmail() + "\t"
				+ uservo.getBirth() + "\t" + uservo.getStatus());
	}
	
	/**
	 * 根据用户名查询用户信息测试（模糊查询）
	 */
	@Test
	public void testSearchUserByName() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Connection conn = DBUtils.getConnection();
		UserDao udi = new UserDaoImpl(conn);
		Vector<UserVO> v = new Vector<UserVO>();
		System.out.print("请输入用户名：");
		String userName;
		try {
			userName = br.readLine();
			v = udi.searchUserByName(userName);
			for(UserVO uservo : v){
				System.out.println(uservo.getNum()+"\t"+uservo.getUserID()+"\t"+uservo.getUserName()
						+"\t"+uservo.getPassword()+"\t"+uservo.getPower()+"\t"+uservo.getEmail()+"\t"
						+uservo.getBirth()+"\t"+uservo.getStatus());
		    }
		} catch (IOException e) {
			System.out.println("根据用户名查询用户失败");
			e.printStackTrace();
		} 
		
	}
}
