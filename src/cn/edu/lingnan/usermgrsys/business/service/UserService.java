package cn.edu.lingnan.usermgrsys.business.service;

import java.util.Vector;

import cn.edu.lingnan.usermgrsys.domain.UserVO;

/**
 * 用户service接口
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 用户登录
	 * @param userID 用户编号
	 * @param password 用户密码
	 * @return 返回用户信息
	 */
	public UserVO login(String userID, String password);
	/**
	 * 用户注册
	 * @param uservo 用户注册信息
	 * @return 返回布尔值
	 */
	public boolean addShow(UserVO uservo);
	/**
	 * 查询指定页的用户（分页查询）
	 * @param pageNum （指定要查询的页数）
	 * @param pageSize 每一页指定的大小
	 * @return 返回该页用户的所有信息
	 */
	public Vector<UserVO> searchAllUser(int pageNum,int pageSize);
	/**
	 * 根据用户编号查询用户信息
	 * @param userID 指定的用户编号
	 * @return 返回的用户信息
	 */
	public UserVO searchUserByID(String userID);
	/**
	 * 根据用户名查询用户信息
	 * @param Name 指定的用户名
	 * @return 返回的用户信息
	 */
	public Vector<UserVO> searchUserByName(String userName);
	/**
	 * 更新用户信息
	 * @param uservo 指定更新的用户信息
	 * @return 返回布尔值
	 */
	public boolean updateUser(UserVO uservo);
	/**
	 * 删除用户信息
	 * @param UserID 指定删除的用户ID
	 * @return 返回布尔值
	 */
	public boolean deleteUser(String userID);
}
