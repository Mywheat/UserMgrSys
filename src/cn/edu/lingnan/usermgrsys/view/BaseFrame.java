package cn.edu.lingnan.usermgrsys.view;

import cn.edu.lingnan.usermgrsys.domain.UserVO;

/**
 * 定义视图层的公共接口
 * @author Administrator
 *
 */
public interface BaseFrame {

	/**
	 * 页面显示
	 */
	public void show();
	/**
	 * 添加用户页面显示
	 * @param type dao常量
	 */
	public void addShow(String type);
	/**
	 * 查询用户页面显示
	 */
	public void searchShow();
	/**
	 * 更新用户页面显示
	 * @param uservo 实体变量
	 * @param type dao常量
	 */
	public void updateShow(UserVO uservo,String type);
}
