package cn.edu.lingnan.usermgrsys.common.test;

import java.util.Date;




import org.junit.Test;

import cn.edu.lingnan.usermgrsys.common.util.TypeUtils;


public class TypeUtilsTest {

	@Test
	public void testCheckMail() {
		TypeUtils.checkMail("zhangsan@qq.com");	
		TypeUtils.checkMail("hahaha");
		
	}
	
	@Test
	public void testStrToDate(){
		Date date = TypeUtils.strToDate("2018-07-16");
		System.out.println(date);
	}
	
	@Test
	public void testDateToStr(){
		Date date = TypeUtils.strToDate("2018-07-16");
//		Date date = new Date(); //获得系统时间
		String str = TypeUtils.dateToStr(date);
		System.out.println(str);
	}
	
	
}
