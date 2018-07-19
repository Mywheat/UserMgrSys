package cn.edu.lingnan.usermgrsys.common.test;

import java.sql.Connection;



import org.junit.Test;

import cn.edu.lingnan.usermgrsys.common.util.DBUtils;

public class DBUtilsTest {
	
	@Test
	public void testGetConnection() {
		Connection connection = DBUtils.getConnection();
		System.out.print(connection);
	}
}
