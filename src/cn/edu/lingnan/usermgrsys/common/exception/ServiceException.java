package cn.edu.lingnan.usermgrsys.common.exception;

/**
 * 业务异常处理类
 * @author Administrator
 *
 */
public class ServiceException extends RuntimeException{

	/**
	 * 默认构造方法
	 */
	public ServiceException(){
		
	}
	
	/**
	 * 构造方法
	 * @param message 异常详细信息
	 */
	public ServiceException(String message){
		super(message);
	}
	
	/**
	 * 构造方法
	 * @param reason 异常原因
	 */
	public ServiceException(Throwable reason){
		super(reason);
	}
	
	/**
	 * 构造方法
	 * @param message 异常详细信息
	 * @param reason 异常原因
	 */
	public ServiceException(String message,Throwable reason){
		super(message,reason);
	}
}
