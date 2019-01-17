package com.door.match.exception;

/**
 * 
 * @ClassName : BusinessException
 * @Description: 业务异常
 * @Date : 2018年5月13日 下午6:21:30
 */
public class BusinessException extends BasicException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		this(ExceptionEnum.业务异常.getMessage());
	}

	public BusinessException(String message) {
		super(message, ExceptionEnum.业务异常.getCode());
	}

}
