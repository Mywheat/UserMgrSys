package cn.edu.lingnan.usermgrsys.common.dao;

import java.sql.Connection;

import cn.edu.lingnan.usermgrsys.business.dao.UserDaoImpl;
import cn.edu.lingnan.usermgrsys.common.exception.ServiceException;

public class DaoFactory {

	/**
	 * 获得用户dao对象的工厂方法
	 * @param conn 数据库连接对象
	 * @param Type dao常量
	 * @return dao接口
	 */
	public static BaseDao getDao(Connection conn,String Type){
		if("user".equals(Type)){
			//返回dao实例对象
			return new UserDaoImpl(conn);
		}else
		{
			throw new ServiceException("dao工厂方法出错");
		}
	}
}
