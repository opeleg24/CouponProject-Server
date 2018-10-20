package com.omri.coupon.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorBean {
	
	
	private String message;
	
	public ErrorBean(String message) {
		super();
		this.message = message;
	}
	

	public ErrorBean() {
		super();
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorBean [message=" + message + "]";
	}

	

	

	
	
	
}
