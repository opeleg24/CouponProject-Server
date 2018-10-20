package com.omri.coupon.api;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.omri.coupon.beans.Company;
import com.omri.coupon.beans.Coupon;
import com.omri.coupon.beans.Customer;
import com.omri.coupon.controller.CouponController;
import com.omri.coupon.controller.CustomerController;
import com.omri.coupon.enums.ClientType;
import com.omri.coupon.exception.ApplicationException;


@Path ("/customer")
public class CustomerAPI {

	private CustomerController customerController;
	private CouponController couponController;


	public CustomerAPI() {
		super();
		this.couponController=new CouponController();
		this.customerController = new CustomerController();
	}

	@Context HttpServletRequest request;
	//This function gets the companyID from the login session 
	public long getCustomerID(){

		return (long) request.getSession().getAttribute("CustomerId");

	}
	
	@GET
	@Path("getCustomer/")
	public Customer getCustomer() {

		return customerController.getCustomer(getCustomerID());

	}

	@GET
	@Path("/getCouponsByCustomerId")
	public ArrayList<Coupon> getAllCouponsByCustomerId(){

		return couponController.getAllCouponsByCustomerId(getCustomerID());

	}
	
	@GET
	@Path("getAllCouponsByStartPriceAndLimitPriceByCustomerId/")
	public ArrayList<Coupon> getCouponsByStartPriceAndLimitPriceByCustomerId(@QueryParam("startPrice")double startPrice,@QueryParam("limitPrice")double limitPrice) {


		return couponController.getCouponsByStartPriceAndLimitPriceByCustomerId(startPrice, limitPrice,getCustomerID());
	}


	@GET
	@Path("/getCouponsByStartDateStartAndEndDateLimitByCustomerId/")
	public ArrayList<Coupon> getAllCouponsByStartDateStartAndEndDateLimitByCustomerId(@QueryParam("startDateStart")String startDateStart,@QueryParam("startDateLimit")String startDateLimit) throws ParseException {


		return couponController.getAllCouponsByStartDateStartAndEndDateLimitByCustomerId(startDateStart, startDateLimit,getCustomerID());
	}
	 
	@GET
	@Path("/getCouponsByEndDateStartAndEndDateLimiByCustomerId/")
	public ArrayList<Coupon> getAllCouponsByEndDateStartAndEndDateLimitByCustomerId(@QueryParam("endDateStart")String endDateStart,@QueryParam("endDateLimit")String endDateLimit) throws ParseException {

		return couponController.getAllCouponsByEndDateStartAndEndDateLimitByCustomerId(endDateStart, endDateLimit,getCustomerID());
	}

 
	@GET
	@Path("getCouponsByCouponTypeByCustomerId/")
	public ArrayList<Coupon> getAllCouponsByCouponTypeByCustomerId(@QueryParam ("CouponType")String couponType){

		return couponController.getAllCouponsByCouponTypeByCustomerId(couponType,getCustomerID());
	}



	
	@GET
	@Path("/getAllCoupons")
	public ArrayList<Coupon> getAllCoupons(){

		return couponController.getAllCoupons();

	}
	
	@POST
	@Path("purchaseCoupon/{couponId}")
	//	@Consumes(MediaType.APPLICATION_JSON)
	public void purchaseCouponByCustomer(@PathParam("couponId")long couponId) throws ApplicationException{
		//		this means that there are more then one coupon purchased by this customer
		if(couponController.getIsThereMoreThenOneCouponPurchasedByThisCustomer(getCustomerID(), couponId)==true){
			couponController.updateAmountOfPurchasedCouponsByCustomer(getCustomerID(), couponId);
			couponController.updateCouponAmount(couponId);
			System.out.println("now the amount of purchased coupons by this customer will update by 1");
		}
		else{
			//			this means that this is the first purchased of coupon by this customer 
			couponController.purchaseCouponByCustomer(getCustomerID(), couponId);
			couponController.updateAmountOfPurchasedCouponsByCustomer(getCustomerID(), couponId);
			couponController.updateCouponAmount(couponId);
			System.out.println("you just purchased a coupon");
			System.out.println("there are no amount of coupons purchased by this customer"
					+ "and this is the first one");
		}

	}

	@GET
	@Path("getPurchasedCouponsByCustomerId/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerId() {
		System.out.println("This customer: "+getCustomerID()+" purchased this coupon/s");

		return couponController.getAllPurchasedCouponsByCustomerId(getCustomerID());
	}
	
	@GET
	@Path("getAmountOfPurchasedCouponsByCustomerIdAndCouponId/{couponId}")
	//	here I have converted the AmountOfPurchasedCouponsByCustomerIdAndCouponId form long to string for MediaType.TEXT_PLAIN present
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String getAmountOfPurchasedCouponsByCustomerIdAndCouponId(@PathParam("couponId")long couponId) throws ApplicationException {
		System.out.println("Amount Of Purchased Coupons ByCustomerId And CouponId"+couponController.getAmountOfPurchasedCouponsByCustomerIdAndCouponId(getCustomerID(), couponId));
		System.out.println("this is the coupon id "+couponId);
		String AmountOfPurchasedCouponsByCustomerIdAndCouponId = Long.toString(couponController.getAmountOfPurchasedCouponsByCustomerIdAndCouponId(getCustomerID(), couponId));
		return AmountOfPurchasedCouponsByCustomerIdAndCouponId;
	}
	
	@GET
	@Path("getPurchasedCouponsByCustomerIdCouponType/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndCouponType(@QueryParam ("CouponType")String couponType){
		System.out.println("This is the coupontype: "+couponType);
		return couponController.getAllPurchasedCouponsByCustomerIdAndCouponType(getCustomerID(), couponType);
	}
	
	@GET
	@Path("getAllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice(@QueryParam("startPrice")double startPrice,@QueryParam("limitPrice")double limitPrice) {
		System.out.println("This is the start price of AllPurchasedCouponsByCustomerId "+startPrice);
		System.out.println("This is the end price of AllPurchasedCouponsByCustomerId"+limitPrice);

		return couponController.getAllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice(startPrice, limitPrice,getCustomerID());
	}
	
	@GET
	@Path("/getAllPurchasedCouponsByCustomerIdAndStartDateStartAndEndDateLimit/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndStartDateStartAndEndDateLimit(@QueryParam("startDateStart")String startDateStart,@QueryParam("startDateLimit")String startDateLimit) throws ParseException {
		System.out.println("This is the start date for All Purchased Coupons(startDate) By CustomerId: "+startDateStart);
		System.out.println("This is the limit date for All Purchased Coupons(startDate) By CustomerId: "+startDateLimit);

		return couponController.getAllPurchasedCouponsByCustomerIdAndStartDateStartAndEndDateLimit(startDateStart, startDateLimit,getCustomerID());
	}


	@GET
	@Path("/getCouponsByCustomerIdAndEndDateStartAndEndDateLimit/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndLimitDateStartAndEndDateLimit(@QueryParam("endDateStart")String endDateStart,@QueryParam("endDateLimit")String endDateLimit) throws ParseException {
		System.out.println("This is the start date for All Purchased Coupons(endDate) By CustomerId: "+endDateStart);
		System.out.println("This is the limit date for All Purchased Coupons(endDate)By CustomerId: "+endDateLimit);
		return couponController.getAllPurchasedCouponsByCustomerIdAndLimitDateStartAndEndDateLimit(endDateStart, endDateLimit, getCustomerID());
	}

	@GET
	@Path("/getCustomerIdSession/")
	public void getCompanyIdSessionAndInvalidateSession(@Context HttpServletRequest request,@Context HttpServletRequest response) throws ApplicationException{


		System.out.println("Customer ID Session: "+request.getSession().getAttribute("CustomerId"));
		request.getSession().invalidate();
		if(request.getSession().getAttribute("CustomerId")==null){
			System.out.println("This Session is now invalidate");
		}


	}







}



