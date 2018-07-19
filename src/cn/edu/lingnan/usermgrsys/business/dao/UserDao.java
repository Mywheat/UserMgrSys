package cn.edu.lingnan.usermgrsys.business.dao;

import java.util.Vector;

import cn.edu.lingnan.usermgrsys.common.dao.BaseDao;
import cn.edu.lingnan.usermgrsys.domain.UserVO;

/**
 * 用户dao接口
 * @author Administrator
 *
 */
public interface UserDao extends BaseDao{

	/**
	 * 用户登录
	 * @param userID 用户编号
	 * @param password 用户密码
	 * @return 返回用户信息
	 */
	public UserVO login(String userID, String password);
	/**
	 * 添加用户(包括用户注册和管理员添加用户)
	 * @param uservo 添加用户的信息
	 * @return 返回布尔值
	 */
	public boolean addUser(UserVO uservo);
	/**
	 * 查询所有用户信息(分页查询) 
	 * @param pageNum 指定的页号
	 * @param pageSize 指定页面大小
	 * @return 返回该页面的所有用户信息
	 */
	public Vector<UserVO> searchAllUser(int pageNum,int pageSize); 
	/**
	 * 根据用户编号查询用户信息(精准查询)
	 * @param userID 指定的用户编号
	 * @return 返回该用户的信息
	 */
	public UserVO searchUserByID(String userID);
	/**
	 * 根据用户名查询用户信息(模糊查询)
	 * @param UserName 指定的用户名
	 * @return 返回该用户的信息
	 */
	public Vector<UserVO> searchUserByName(String userName);
	/**
	 * 更新用户信息
	 * @param uservo 指定更新用户的信息
	 * @return 返回布尔值
	 */
	public boolean updateUser(UserVO uservo);
	/**
	 * 删除用户信息
	 * @param userID 指定删除的用户编号
	 * @return 返回布尔值
	 */
	public boolean deleteUser(String userID);
}
