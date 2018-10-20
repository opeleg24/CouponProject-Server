package com.omri.coupon.login;

import com.omri.coupon.controller.CompanyController;
import com.omri.coupon.controller.CouponController;
import com.omri.coupon.controller.CustomerController;
import com.omri.coupon.enums.ClientType;
import com.omri.coupon.enums.ErrorType;
import com.omri.coupon.exception.ApplicationException;

public class LoginUtils {
	
public static boolean login(String UserName,String Password,ClientType ClientType) throws ApplicationException{
	
	CompanyController companyController=new CompanyController();
	CustomerController customerController=new CustomerController();
	CouponController couponController=new CouponController();
	switch(ClientType){
	case COMPANY:
		if(companyController.loginCompany(UserName, Password)){
			return true;
		}
		break;
	case CUSTOMER:
		if(customerController.loginCustomer(UserName, Password)){
			return true;
		}
		break;
	case ADMINISTRATOR:
		if(couponController.loginAdmin(UserName, Password)){
			return true;
			
		}
		break;
	}
	
	return false;
}
	

}

