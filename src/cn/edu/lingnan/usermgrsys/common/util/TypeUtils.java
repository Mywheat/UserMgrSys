package cn.edu.lingnan.usermgrsys.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.lingnan.usermgrsys.common.exception.DateException;

/**
 * 类型转换公有类
 * @author Administrator
 *
 */
public class TypeUtils {

	/**
	 * 字符串转换成日期类型
	 * @param str 指定的字符串
	 * @return 返回转换格式之后的日期类型
	 */
	public static Date strToDate(String str){
		Date date = null;
		//设置要格式化的诶器格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		try {
			//调用parse方法，将字符串解析成指定格式的日期类型
			date = sdf.parse(str);
//			System.out.println(date);
		} catch (ParseException e) {
			//将异常封装成自定义异常
			throw new DateException("字符串转换为日期出错",e); 
		}
		return date;
	}
	
	/**
	 * 日期转换成字符串
	 * @param date 指定格式的日期
	 * @return 转换之后返回的字符串
	 */
	public static String dateToStr(Date date){
		String str = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		str = sdf.format(date);
		return str;
	}
	
	/**
	 * 邮箱格式判断
	 * @param str  指定ָ的邮箱
	 * @return 返回邮箱格式是否正确
	 */
	public static boolean checkMail(String str){
		boolean flag = false;
		//邮箱正确格式的正则表达式
		String standard = "\\w+@\\w+\\.(com)|(cn)";
		//编译正则表达式
		Pattern standard1 = Pattern.compile(standard);
		//正则表达式匹配
		Matcher check = standard1.matcher(str);
		//正则表达式大小写都可以
		//Pattern pat = Pattern.compile(standard,Pattern.CASE_INSENSITIVE);	
		if(check.find()){
			flag = true;
//			System.out.println(str);
		}else{
			System.out.println("                             邮箱格式错误，请重新输入正确的邮箱");
		}
		return flag;
	}
}
