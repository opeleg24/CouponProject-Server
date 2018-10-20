package com.omri.coupon.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class UserLoginDetails {
	
	private String loginName;
	private String loginPassword;
	private String loginClientType;
	

	public UserLoginDetails() {

	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getLoginPassword() {
		return loginPassword;
	}


	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}


	public String getLoginClientType() {
		return loginClientType;
	}


	public void setLoginClientType(String loginClientType) {
		this.loginClientType = loginClientType;
	}


	@Override
	public String toString() {
		return "UserLoginDetails [loginName=" + loginName + ", loginPassword=" + loginPassword + ", loginClientType="
				+ loginClientType + "]";
	}

	

}
