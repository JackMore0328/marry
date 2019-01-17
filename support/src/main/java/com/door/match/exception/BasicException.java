package com.door.match.exception;

/**
 * 
 * @ClassName : BasicException
 * @Description: 基础异常，所有的异常实现均要继承该异常
 */
public class BasicException extends RuntimeException {
	private static final long serialVersionUID = -8694599150707362141L;


	public static final Integer CODE_BUZ_ERROR = 100;
	public static final Integer CODE_AUTH_ERROR = 200;

	private Integer code;
	private String message;
	private String data;


	public BasicException(Integer code,String message) {
		this.code=code;
		this.message=message;
		this.data=null;
	}

	public BasicException(String message, String errorCode) {
		super(message);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
