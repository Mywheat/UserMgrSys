package cn.edu.lingnan.usermgrsys.domain;

import java.util.Date;

/**
 * 用户实体类
 * @author Administrator
 *
 */
public class UserVO {

	private int Num;
	private String UserID;
	private String UserName;
	private String Password;
	private String Power;
	private String Email;
	private Date Birth;
	private String Tel;
	private String Adderss;
	private String Status;
	//获取自增序列
	public int getNum() {
		return Num;
	}
	public void setNum(int num) {
		Num = num;
	}
	
	//获取用户编号
	public String getUserID() {
		return UserID;
	}	
	//设置用户编号
	public void setUserID(String userID) {
		UserID = userID;
	}	
	
	//获取用户名
	public String getUserName() {
		return UserName;
	}	
	//设置用户名
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	//获取用户密码
	public String getPassword() {
		return Password;
	}	
	//设置用户密码
	public void setPassword(String password) {
		Password = password;
	}
	
	//获取用户权限
	public String getPower() {
		return Power;
	}
	//设置用户权限
	public void setPower(String power) {
		Power = power;
	}
	
	//获取用户邮箱
	public String getEmail() {
		return Email;
	}
	//设置用户邮箱
	public void setEmail(String email) {
		Email = email;
	}
	
	//获取用户生日
	public Date getBirth() {
		return Birth;
	}
	//设置用户生日
	public void setBirth(Date birth) {
		Birth = birth;
	}
	
	//获取用户联系方式
	public String getTel() {
		return Tel;
	}
	//设置用户联系方式
	public void setTel(String tel) {
		this.Tel = tel;
	}
	
	//获取用户地址
	public String getAdderss() {
		return Adderss;
	}
	//设置用户地址
	public void setAdderss(String adderss) {
		this.Adderss = adderss;
	}
	
	//获取用户状态
	public String getStatus() {
		return Status;
	}
	//设置用户状态
	public void setStatus(String status) {
		this.Status = status;
	}
		
}
