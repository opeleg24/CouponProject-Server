package com.omri.coupon.enums;


public enum ErrorType {
	
	GENERAL_ERROR(602)
	,TITLE_ALREADY_EXISTS(603),
	COUPON_NOT_EXISTS(604),
	COUPON_OUT_OF_STOCK(605),
	COUPON_EXPIRED(606),
	COMPANY_NOT_EXISTS(607),
	FAIL_TO_LOGIN(608),
	NAME_ALREADY_EXISTS(609),
	CUSTOMER_NOT_EXISTS(610);

	private int number;
	
	private ErrorType(int number){
		this.number=number;
	}
	
	public int getNumber(){
		return number;
		
	}
	
	public ErrorType fromString(final String s){
		return ErrorType.valueOf(s);
	}
	
	
	
	
	
	
	
}




