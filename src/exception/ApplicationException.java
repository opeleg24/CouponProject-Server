package com.omri.coupon.exception;

import com.omri.coupon.enums.ErrorType;

public class ApplicationException extends Exception {
	
	
	private ErrorType errorType;
	private String message;
	
	
	public ApplicationException(Exception e,ErrorType errorType,String message){
		super(message,e);
		this.errorType=errorType;
		this.message=message;
		
	}
	public ApplicationException(ErrorType errorType,String message){
		super(message);
		this.errorType=errorType;
		this.message=message;
		
	}

	
	public ErrorType getErrorType() {
		return errorType;
	}
	public String getMessage() {
		return message;
	}
	
}
