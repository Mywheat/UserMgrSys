package cn.edu.lingnan.usermgrsys.common.exception;

/**
 * Data日期异常处理类
 * @author Administrator
 *
 */
public class DateException extends ServiceException{

	/**
	 * 默认构造方法
	 */
	public DateException(){
		
	}
	
	/**
	 * 构造方法
	 * @param message 异常详细信息
	 */
	public DateException(String message){
		super(message);
	}
	
	/**
	 * 构造方法
	 * @param reason 异常原因
	 */
	public DateException(Throwable reason){
		super(reason);
	}
	
	/**
	 * 构造方法
	 * @param message 异常详细信息
	 * @param reason 异常原因
	 */
	public DateException(String message,Throwable reason){
		super(message,reason);
	}
}
