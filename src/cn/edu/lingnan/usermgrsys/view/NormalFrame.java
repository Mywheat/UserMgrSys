package cn.edu.lingnan.usermgrsys.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.edu.lingnan.usermgrsys.common.util.TypeUtils;
import cn.edu.lingnan.usermgrsys.domain.UserVO;

public class NormalFrame extends IndexFrame{

	UserVO uservo = null; //用户对象
	/**
	 * 带参数的构造器，用于初始化user属性
	 * @param uservo
	 */
	public NormalFrame(UserVO uservo){
		this.uservo = uservo;
	}
	
	/**
	 * 普通用户管理页面
	 */
	public void show(){
		//声明缓冲处理流对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			//显示普通用户登录成功后的操作界面
			System.out.println();
			System.out.println("**************************************");
			System.out.println("                         欢迎进入普通用户页面");
			System.out.println("                           ☞1.查看个人信息");
			System.out.println("                           ☞2.修改个人信息");
			System.out.println("                           ☞3.退出该界面");
			System.out.println("**************************************");
			//声明变量，用于接收从控制台输入的操作选项数字
			int i = -1;
			//读取用户控制台输入，如果输入值正确，中断循环，否则提示错误信息，再重新输入
			while(true){
				try {
					System.out.print("请选择：");
					i = Integer.parseInt(br.readLine());
					break;
				} catch (IOException e) {
					System.out.println("输入错误，请重新输入数字1~3：");
				}
			}
			/**
			 * 判断用户输入值
			 */
			switch(i){
			case 1:
				this.searchShow();
				break;
			case 2:
				String type = "修改个人信息";
				this.updateShow(uservo,type);
				break;
			case 3:
				IndexFrame fm= new IndexFrame();
				fm.show();
			default:
				System.out.println("请重新输入数字1~3");				
			}
		}
	}
	
	/**
	 * 查看普通用户个人信息页面
	 */
	public void searchShow(){		
		System.out.println();
		System.out.println("**************************************");
		System.out.println("--------------用户个人信息----------------");
		System.out.println("                                自增编号："+uservo.getNum());
		System.out.println("                                用户编号："+uservo.getUserID());
		System.out.println("                                用户名："+uservo.getUserName());
		System.out.println("                                用户密码："+uservo.getPassword());
		System.out.println("                                用户权限："+uservo.getPower());
		System.out.println("                                用户邮箱："+uservo.getEmail());
		System.out.println("                                出生日期："+uservo.getBirth());
		System.out.println("                                用户状态："+uservo.getStatus());
		System.out.println("**************************************");
	}
	
	/**
	 * 普通用户修改个人信息
	 */
	public void updateShow(UserVO uservo,String type){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("**************************************");
		System.out.println("--------------用户个人信息----------------");
		System.out.println(uservo.getNum() + "\t" + uservo.getUserID()
				+ "\t" + uservo.getUserName() + "\t" + uservo.getPassword()
				+ "\t" + uservo.getPower() + "\t" + uservo.getEmail()
				+ "\t" + uservo.getBirth() + "\t" + uservo.getStatus());
		try {
			System.out.println("--------------修改个人信息----------------");
			System.out.print("                                修改userName用户名：");
			uservo.setUserName(br.readLine());
			System.out.print("                                修改password用户密码：");
			uservo.setPassword(br.readLine());
			System.out.print("                                修改Email用户邮箱：");
			while(true){
				String email = br.readLine();
				if(TypeUtils.checkMail(email)){
					uservo.setEmail(email);
					break;
				}
			}		
			System.out.print("                                修改date出生日期：");
			uservo.setBirth(TypeUtils.strToDate(br.readLine()));
			System.out.println("**************************************");
		} catch (IOException e) {
			System.out.println("用户修改个人信息失败");
			e.printStackTrace();
		}		
	}
	
}
