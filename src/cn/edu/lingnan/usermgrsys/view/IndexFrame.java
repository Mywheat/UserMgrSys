package cn.edu.lingnan.usermgrsys.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.edu.lingnan.usermgrsys.common.exception.DateException;
import cn.edu.lingnan.usermgrsys.common.util.TypeUtils;
import cn.edu.lingnan.usermgrsys.controller.UserController;
import cn.edu.lingnan.usermgrsys.domain.UserVO;

public class IndexFrame implements BaseFrame {

	/**
	 * 用户管理系统首页面
	 */
	@Override
	public void show() {
		//声明缓冲处理流对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//循环操作
		while (true) {
			//用户登录注册页面
			System.out.println("**************************************");
			System.out.println("                         欢迎使用用户管理系统 ");
			System.out.println("                              ☞1.用户注册");
			System.out.println("                              ☞2.用户登录");
			System.out.println("                              ☞3.退出系统");		
			System.out.println("**************************************");
			System.out.print("请选择:");
			int i = -1;
			//读取用户控制台输入，如果输入正确，中断循环，否则提示错误信息再重新输入
			while (true) {
				try {
					i = Integer.parseInt(br.readLine());
					break; //中断该循环，进入下一步操作：i值判断
				} catch (IOException e) {
					//出现异常时，提示错误信息，再重新输入
					System.out.println("输入错误，请重新输入数字1~3：");
				}
			}
			/**
			 * 判断用户输入值
			 */
			switch (i) {
			case 1:
				String type = "注册";
				this.addShow(type);
				break; //中断switch
			case 2:
				this.loginShow();
				break;
			case 3:
				System.out.println("感谢您的使用！");
				System.exit(0); // 退出当前页面
			default:
				System.out.println("请重新输入数字1~3");
			}
		}

	}

	/**
	 * 登录页面
	 */
	public void loginShow() {
		//声明缓冲处理流对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("**************************************");
		System.out.println("                            欢迎您的登录！");
		System.out.print("                            用户编号:");
		String UserID;
		try {
			//以行为单位，读取输入的各个值，赋值给用户对象的各个属性
			UserID = br.readLine();
			System.out.print("                            用户密码:");
			String Password = br.readLine();
//			System.out.println("*************");
			UserController uc = new UserController();
			UserVO uservo = uc.doLogin(UserID, Password); //如果返回值不为空，登录成功
			if(uservo != null){
				//调用主界面判断权限
				AdminFrame m = new AdminFrame(uservo);  //在AdminFrame里面进行权限判断
				m.loginSuccShow();
				System.exit(0);//退出当前界面
			}else{
				System.out.println("登录失败，请重新登录");
			}
		} catch (IOException e) {
			System.out.println("用户登录页面报错");
		}
						
	}
	
	/**
	 * 用户注册页面
	 */
	public void addShow(String type){
		UserVO uservo = new UserVO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if("注册".equals(type)){
			System.out.println("**************************************");
			System.out.println("                             欢迎您的注册");
		}else if("添加用户".equals(type)){
			System.out.println("**************************************");
			new AdminFrame(uservo).addShow(type); //记得要把uservo的值传递过去
		}
		try {
			uservo.setPower("普通用户");
			uservo.setStatus("0");
			System.out.print("                             用户编号:");
			uservo.setUserID(br.readLine());
			System.out.print("                             用户名:");
			uservo.setUserName(br.readLine());
			System.out.print("                             用户密码:");
			uservo.setPassword(br.readLine());
			while(true){
				System.out.print("                             用户邮箱:");
				String email = br.readLine();
				if(TypeUtils.checkMail(email)){
					uservo.setEmail(email);
					break;
				}
			}
			System.out.print("                             出生日期:");
			uservo.setBirth(TypeUtils.strToDate(br.readLine()));
			System.out.println("**************************************");
			UserController uc = new UserController();
			boolean flag = uc.addShow(uservo);
			if(flag){
					System.out.println("                                用户注册成功");
			}else{
					System.out.println("                                用户注册失败");
			}
		} catch(Exception e){
			System.out.println("用户注册失败"+e.getMessage());
		}
	}

	@Override
	public void searchShow() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateShow(UserVO uservo, String type) {
		// TODO Auto-generated method stub

	}

}
