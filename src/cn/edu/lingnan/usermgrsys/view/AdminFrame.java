package cn.edu.lingnan.usermgrsys.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import cn.edu.lingnan.usermgrsys.common.util.TypeUtils;
import cn.edu.lingnan.usermgrsys.controller.UserController;
import cn.edu.lingnan.usermgrsys.domain.UserVO;

public class AdminFrame extends NormalFrame{

	/**
	 * 带参数的构造器，用于初始化user属性
	 * @param uservo
	 */
	public AdminFrame(UserVO uservo){
		super(uservo);
	}
	/**
	 * 登录成功提示页面
	 */
	public void loginSuccShow(){
		System.out.println("     "+uservo.getUserID()+"欢迎您"+"    "+"您的权限是："+uservo.getPower());
		System.out.println("**************************************");
		//判断用户权限
		if(uservo.getPower().equals("管理员")){
			this.show();
		}else if(uservo.getPower().equals("普通用户")){
			new NormalFrame(uservo).show();
		}
	}
	/**
	 * 管理员管理页面
	 */
	public void show(){
		//声明缓冲处理流对象，用于接收控制台输入的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			//显示管理员登录成功后的操作界面
			System.out.println();
			System.out.println("**************************************");
			System.out.println("                        欢迎进入管理员管理页面");
			System.out.println("                             ☞1.添加用户");
			System.out.println("                             ☞2.删除用户");
			System.out.println("                             ☞3.查询用户");
			System.out.println("                             ☞4.更新用户");
			System.out.println("                             ☞5.退出该界面");
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
					System.out.println("输入错误，请重新输入数字1~5：");
				}
			}
			/**
			 * 判断用户输入值
			 */
			String type = null;
			switch(i){
			case 1:
				type = "添加用户";
				this.addShow(type);
				break;
			case 2:
				this.deleteShow();
				break;
			case 3:
				this.searchShow();
				break;
			case 4:
				type = "更新用户";
				this.updateShow(uservo,type);
				break;
			case 5:
				IndexFrame fm= new IndexFrame();
				fm.show();
			default:
				System.out.println("请重新输入数字1~5");				
			}
		}
	}
	
	/**
	 * 管理员添加用户页面
	 */
	public void addShow(String type){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserVO uservo = new UserVO();
		try {
			System.out.println("**************************************");
			System.out.println("                          管理员添加用户信息");
			System.out.print("                             用户编号:");
			uservo.setUserID(br.readLine());
			System.out.print("                             用户名:");
			uservo.setUserName(br.readLine());
			System.out.print("                             用户密码:");
			uservo.setPassword(br.readLine());
			System.out.print("                             用户权限:");
			uservo.setPower(br.readLine());
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
			System.out.print("                             用户状态:");
			uservo.setStatus(br.readLine());
			System.out.println("**************************************");
			UserController uc = new UserController();
			boolean flag = uc.addShow(uservo);
			if(flag){
				System.out.println("                                用户添加成功!");
			}else{
				System.out.println("                                用户添加失败，请重新进行添加用户操作");
			}
		}catch (IOException e) {
			System.out.println("用户添加失败"+e.getMessage());
		}
	}
	
	/**
	 * 管理员查询用户页面
	 */
	public void searchShow(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			//显示管理员查询用户操作方式的子页面
			System.out.println();
			System.out.println("**************************************");
			System.out.println("                     请选择一种方式进行用户查询");
			System.out.println("                         ☞1.查询所有用户（分页查询）");
			System.out.println("                         ☞2.按用户编号查询（精准查询）");
			System.out.println("                         ☞3.按用户名查询（模糊查询）");
			System.out.println("                         ☞4.退出该界面");
			System.out.println("**************************************");
			//声明变量，用于接收从控制台输入的操作选项数字
			int i = -1;
			while(true){
				try {
					System.out.print("请选择：");
					i = Integer.parseInt(br.readLine());
					break;
				}  catch (IOException e) {
					System.out.println("输入错误，请重新输入数字1~4：");
				}
				
			}
			switch(i){
			case 1:
				searchAllUser();
				break;
			case 2:
				searchUserByID();
				break;
			case 3:
				searchUserByName();
				break;
			case 4:
				show();
			default:
				System.out.println("请重新输入数字1~4");				
			}
		}
	}
	
	/**
	 * 管理员查询所有用户的实现方法(分页查询)
	 */
	public void searchAllUser() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();		
		Vector<UserVO> v = new Vector<UserVO>();		
		try {
			System.out.println();
			System.out.println("**************************************");
			System.out.println("                        查询所有用户（分页查询）");
			System.out.print("                     请输入需要查询的页号：");
			int pageNum = Integer.parseInt(br.readLine());
			System.out.print("                     请输入每页的大小：");
			int pageSize = Integer.parseInt(br.readLine());
			v = uc.searchAllUserShow(pageNum, pageSize);
			System.out.println("------------所有用户的信息显示--------------");
			for(UserVO uservo : v){
				System.out.println(uservo.getNum()+"\t"+uservo.getUserID()+"\t"+uservo.getUserName()
						+"\t"+uservo.getPassword()+"\t"+uservo.getPower()+"\t"+uservo.getEmail()+"\t"
						+uservo.getBirth()+"\t"+uservo.getStatus());
			}
			System.out.println("**************************************");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 管理员根据用户编号查询用户的实现方法(精准查询)
	 */
	public void searchUserByID(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();
		UserVO uservo = null;
		System.out.println();
		System.out.println("**************************************");
		System.out.println("                  根据用户ID查询用户（精准查询）");
		String userID;
		try {
			System.out.print("                     请输入要查询的用户编号：");
			userID = br.readLine();
			System.out.println("--------------用户信息显示----------------");
			uservo = uc.searchUserByID(userID);
			System.out.println("                                自增编号："+uservo.getNum());
			System.out.println("                                用户编号："+uservo.getUserID());
			System.out.println("                                用户名："+uservo.getUserName());
			System.out.println("                                用户密码："+uservo.getPassword());
			System.out.println("                                用户权限："+uservo.getPower());
			System.out.println("                                用户邮箱："+uservo.getEmail());
			System.out.println("                                出生日期："+uservo.getBirth());
			System.out.println("                                用户状态："+uservo.getStatus());
			System.out.println("**************************************");
		} catch (IOException e) {
			System.out.println("根据用户编号查询用户失败"+e);
		}		
	}
	
	/**
	 * 管理员根据用户名查询用户的实现方法(模糊查询)
	 */
	public void searchUserByName(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();
		Vector<UserVO> v = new Vector<UserVO>();
		System.out.println();
		System.out.println("**************************************");
		System.out.println("                  根据用户名查询用户（模糊查询）");
		System.out.print("                     请输入要查询的用户名：");
		String userID;
		try {
			String userName = br.readLine();
			System.out.println("--------------用户信息显示----------------");
			v = uc.searchUserByName(userName);
			for(UserVO uservo : v){
				System.out.println(uservo.getNum()+"\t"+uservo.getUserID()+"\t"+uservo.getUserName()
						+"\t"+uservo.getPassword()+"\t"+uservo.getPower()+"\t"+uservo.getEmail()+"\t"
						+uservo.getBirth()+"\t"+uservo.getStatus());
			}
			System.out.println("**************************************");
		} catch (IOException e) {
			System.out.println("根据用户编号查询用户失败"+e);
		}	
	}
	
	/**
	 * 管理员更新用户信息的实现方法
	 */
	public void updateShow(UserVO uservo,String type){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		UserController uc = new UserController();
		try {
			System.out.println();
			System.out.println("**************************************");
			System.out.print("请输入要修改的用户编号：");
			String userID = br.readLine();
			uservo = uc.searchUserByID(userID);
			System.out.println(uservo.getNum() + "\t" + uservo.getUserID()
					+ "\t" + uservo.getUserName() + "\t" + uservo.getPassword()
					+ "\t" + uservo.getPower() + "\t" + uservo.getEmail()
					+ "\t" + uservo.getBirth() + "\t" + uservo.getStatus());
			System.out.println("--------------更新用户信息----------------");
			System.out.println("修改userName用户名：");
			uservo.setUserName(br.readLine());
			System.out.println("修改password用户密码：");
			uservo.setPassword(br.readLine());
			System.out.println("修改power用户权限：");
			uservo.setPower(br.readLine());
			System.out.println("修改Email用户邮箱：");
			while(true){
				String email = br.readLine();
				if(TypeUtils.checkMail(email)){
					uservo.setEmail(email);
					break;
				}
			}		
			System.out.println("修改date出生日期：");
			uservo.setBirth(TypeUtils.strToDate(br.readLine()));
			System.out.println("修改status用户状态：");
			uservo.setStatus(br.readLine());
			boolean flag = uc.updateUser(uservo);
		    if(flag){
		    	System.out.println("用户更新成功");
		    }else{
		    	System.out.println("用户更新失败");
		    }
			System.out.println("**************************************");		
		} catch (IOException e) {
			System.out.println("更新用户信息失败");
			e.printStackTrace();
		}		
	}
	
	public void deleteShow(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		UserController uc = new UserController();
		try {
			System.out.println();
			System.out.println("**************************************");
			System.out.print("                      请输入要删除用户的用户编号：");
			String userID =br.readLine();
			boolean flag = uc.deleteUser(userID);
		    if(flag){
		    	System.out.println("                                    用户删除成功");
		    }else{
		    	System.out.println("                                    用户删除失败");
		    }
		    System.out.println("**************************************");
		} catch (IOException e) {
			System.out.println("e.删除用户信息失败");
			e.printStackTrace();
		}
	}
}
