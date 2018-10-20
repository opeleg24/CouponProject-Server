package com.omri.coupon.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.omri.coupon.beans.UserLoginDetails;
import com.omri.coupon.controller.CompanyController;
import com.omri.coupon.controller.CouponController;
import com.omri.coupon.controller.CustomerController;
import com.omri.coupon.enums.ClientType;
import com.omri.coupon.exception.ApplicationException;


@Path("/login")
public class LoginAPI {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void Login(@Context HttpServletRequest request,@Context HttpServletRequest response,UserLoginDetails userLoginDetails) throws ApplicationException{
		CompanyController companyController=new CompanyController();
		CustomerController customerController=new CustomerController();
		CouponController couponController=new CouponController();

			if(userLoginDetails.getLoginClientType().equals("COMPANY")){
				long CompanyId= companyController.getcompanyIdBycompanyNameAndcompanyPassword(
						userLoginDetails.getLoginName(), userLoginDetails.getLoginPassword());
						request.getSession().setAttribute("CompanyId", CompanyId);
						companyController.loginCompany(userLoginDetails.getLoginName(),userLoginDetails.getLoginPassword());
				
						System.out.println("Company ID Session: "+request.getSession().getAttribute("CompanyId"));
			
			}
			if(userLoginDetails.getLoginClientType().equals("CUSTOMER")){
				customerController.loginCustomer(userLoginDetails.getLoginName(), userLoginDetails.getLoginPassword());
				long CustomerId= customerController.getcustomerIdBycustomerNameAndcustomerPassword
						(userLoginDetails.getLoginName(), userLoginDetails.getLoginPassword());
						request.getSession().setAttribute("CustomerId", CustomerId);
					
						System.out.println("Customer ID Session: "+request.getSession().getAttribute("CustomerId"));
				
			}
	
			if(userLoginDetails.getLoginClientType().equals("ADMINISTRATOR")){
				couponController.loginAdmin(userLoginDetails.getLoginName(), userLoginDetails.getLoginPassword());
//				The id of the session will always be 1 ,because there is no admin table or column then its a default 1.
				long AdminId = 1;
				request.getSession().setAttribute("AdminId", AdminId);
						
			}


	}
}
