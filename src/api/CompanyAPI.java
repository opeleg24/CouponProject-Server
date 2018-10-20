package com.omri.coupon.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.omri.coupon.beans.Company;
import com.omri.coupon.beans.Coupon;
import com.omri.coupon.controller.CompanyController;
import com.omri.coupon.controller.CouponController;
import com.omri.coupon.exception.ApplicationException;


@Path ("/company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CompanyAPI {
	
	private CouponController couponController;
	private CompanyController companyController;
	
	
	public CompanyAPI() {
		super();
		this.couponController=new CouponController();
		this.companyController = new CompanyController();
	}
@Context HttpServletRequest request;
	
//This function gets the companyID from the login session 
	public long getCompanyID(){
		
		return (long) request.getSession().getAttribute("CompanyId");
	
	}

	@PUT
	@Path("/updateCouponByCompanyId")
	
	public void updateCoupon(Coupon coupon) throws ApplicationException{
		System.out.println("The actual auto incerment in done in the DB, that's why the couponId=0");
	
		System.out.println(coupon);
		couponController.updateCouponByCompanyId(coupon, getCompanyID());;
		
	}

	@POST
	@Path("/createCouponByCompanyId")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCoupon(Coupon coupon) throws ApplicationException{
		System.out.println("The actual auto incerment in done in the DB, that's why the couponId=0");
		System.out.println(coupon);
		couponController.createCouponByCompanyId(coupon, getCompanyID());
	}

	@GET
	@Path("/getCompany")
	public Company getCompany() {
		System.out.println(getCompanyID());
		
		return companyController.getCompany(getCompanyID());
		
		
	}
 
	@GET
	@Path("/getCouponsByCompany")
	public ArrayList<Coupon> getAllCouponsByCompany(){
	
			return couponController.getAllCouponsByCompanyId(getCompanyID());

	
	}

	@GET
	@Path("/getCouponsByCompanyAndCouponType/")
	public ArrayList<Coupon> getAllCouponsByCompanyIdAndCouponType(@QueryParam("CouponType")String couponType) {
		return couponController.getAllCouponsByCompanyIdAndCouponType(getCompanyID(), couponType);
	}

	@GET
	@Path("/getCouponsByCompanyIdAndStartDateStartAndEndDateLimit/")
	public ArrayList<Coupon> getAllCouponsByComapnyIdAndStartDateStartAndEndDateLimit(@QueryParam("startDateStart")String startDateStart,@QueryParam("startDateLimit")String startDateLimit) throws ParseException {
		System.out.println(startDateStart);
		System.out.println(startDateLimit);
		return couponController.getAllCouponsByComapnyIdAndStartDateStartAndEndDateLimit(startDateStart, startDateLimit, getCompanyID());
	}

	@GET
	@Path("/getCouponsByCompanyIdAndEndDateStartAndEndDateLimit/")
	public ArrayList<Coupon> getCouponsByCompanyIdAndEndDateStartAndEndDateLimit(@QueryParam("endDateStart")String endDateStart,@QueryParam("endDateLimit")String endDateLimit) throws ParseException {
		return couponController.getAllCouponsByComapnyIdAndEndDateStartAndEndDateLimit(endDateStart, endDateLimit, getCompanyID());
	}

	@GET
	@Path("/getCouponsByCompanyIdAndStartPriceAndLimitPrice/")
	public ArrayList<Coupon> getAllCouponsByCompanyIdAndStartPriceAndLimitPrice(@QueryParam("startPrice")double startPrice,@QueryParam("limitPrice")double limitPrice) {
		System.out.println(startPrice);
		System.out.println(limitPrice);
		return couponController.getAllCouponsByCompanyIdAndStartPriceAndLimitPrice(startPrice, limitPrice, getCompanyID());
	}

	@DELETE
	@Path("deleteCouponAndDeleteCouponsPurchasedByCustomer/{couponId}")
	public void deleteCouponByCompanyIdAndDeleteCouponsPurchasedByCustomer(@PathParam("couponId")long couponId) throws ApplicationException{
		System.out.println("this is the coupnId that i'm deleting"+couponId);
		System.out.println("first delete coupon/s purchased by customers");
		couponController.deleteCouponPurchaseByCustomer(couponId);
		System.out.println("now delete the coupon, coupon id: "+couponId);
		couponController.deleteCouponByCompanyId(couponId, getCompanyID());
		
}
	
	@GET
	@Path("/getCompanyIdSession/")
	public void getCompanyIdSessionAndInvalidateSession(@Context HttpServletRequest request,@Context HttpServletRequest response) throws ApplicationException{
		
			
			System.out.println("Company ID Session: "+request.getSession().getAttribute("CompanyId"));
			request.getSession().invalidate();
			if(request.getSession().getAttribute("CompanyId")==null){
				System.out.println("This Session is now invalidate");
			}

	}



}
