package cn.edu.lingnan.usermgrsys.common.exception;


/**
 * 数据库异常类
 * @author Administrator
 *
 */
public class DAOException extends RuntimeException{

	/**
	 * 默认构造方法
	 */
	public DAOException(){
		
	}
	
	/**
	 * 构造方法
	 * @param message 异常详细信息
	 */
	public DAOException(String message){
		super(message);		
	}
	
	/**
	 * 构造方法
	 * @param reason 异常原因
	 */
	public DAOException(Throwable reason){
		super(reason);
	}
	
	/**
	 * 构造方法
	 * @param message 异常详细信息
	 * @param reason 异常原因
	 */
	public DAOException(String message,Throwable reason){
		super(message,reason);
	}
	
}
