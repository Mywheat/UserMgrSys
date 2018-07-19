package cn.edu.lingnan.usermgrsys.common.exception;

/**
 * 邮箱格式判断处理类
 * @author Administrator
 *
 */
public class EmailException extends ServiceException{

	/**
	 * 默认构造方法
	 */
	public EmailException(){
		
	}
	
	/**
	 * 构造方法
	 * @param message 异常详细信息
	 */
	public EmailException(String message){
		super(message);
	}
	
	/**
	 * 构造方法
	 * @param reason 异常原因
	 */
	public EmailException(Throwable reason){
		super(reason);
	}
	
	/**
	 * 构造方法
	 * @param message 异常详细信息
	 * @param reason 异常原因
	 */
	public EmailException(String message,Throwable reason){
		super(message,reason);
	}
}
