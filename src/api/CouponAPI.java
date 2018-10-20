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
import com.omri.coupon.beans.Customer;
import com.omri.coupon.beans.UserLoginDetails;
import com.omri.coupon.controller.CompanyController;
import com.omri.coupon.controller.CouponController;
import com.omri.coupon.controller.CustomerController;
import com.omri.coupon.enums.CouponType;
import com.omri.coupon.exception.ApplicationException;


@Path ("/coupons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CouponAPI {
	
	private CouponController couponController;
	private CompanyController companyController;
	private CustomerController customerController;
	
	public CouponAPI() {
			this.couponController = new CouponController();
			this.companyController = new CompanyController();
			this.customerController=new CustomerController();
	}




//	this is for the Administrator client (admin panel).
	@GET
	@Path("/getAllCompanies")
	public ArrayList<Company> getAllCompanies(){
	
        return companyController.getAllCompanies();
		
	}
	
//	this is for the Administrator client (admin panel).
	@GET
	@Path("/getAllCustomers")
	public ArrayList<Customer> getAllCustomers() {
		
		return	customerController.getAllCustomers();
		
		}
//	This function is for the Administrator client (admin panel).
	@POST
	@Path("/createCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCompany(Company company) throws ApplicationException{
		
		
			
			companyController.createCompany(company);
		
	}
//	This function is for the Administrator client (admin panel).
	@PUT
	@Path("/updateCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCompany(Company company) throws ApplicationException{
		System.out.println(company.getCompanyId());
		System.out.println(company);
		
		companyController.updatingCompany(company);
		
	}
	//	This function is for the Administrator client (admin panel).
	@GET
	@Path("/getCustomerByCustomerId/{customerId}")
	public Customer getCustomerByCompanyId(@PathParam ("customerId") long customerId) {
		System.out.println("This is the customer id: "+customerId);

		return customerController.getCustomer(customerId);

	}
//	This function is for the Administrator client (admin panel).
	@POST
	@Path("/createCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCustomer(Customer customer) throws ApplicationException{
		System.out.println(customer);

		customerController.createCustomer(customer);

	}
//	this function is for the Administrator client (admin panel).
	@GET
	@Path("getAllCouponsByStartPriceAndLimitPriceByCustomerId/")
	public ArrayList<Coupon> getCouponsByStartPriceAndLimitPriceByCustomerId(@QueryParam ("customerId") long customerId,@QueryParam("startPrice")double startPrice,@QueryParam("limitPrice")double limitPrice) {
	
		
		return couponController.getCouponsByStartPriceAndLimitPriceByCustomerId(startPrice, limitPrice,customerId);
	}
	
//	this function is for the Administrator client (admin panel). 
	@GET
	@Path("/getCouponsByStartDateStartAndEndDateLimitByCustomerId/")
	public ArrayList<Coupon> getAllCouponsByStartDateStartAndEndDateLimitByCustomerId(@QueryParam ("customerId") long customerId,@QueryParam("startDateStart")String startDateStart,@QueryParam("startDateLimit")String startDateLimit) throws ParseException {
		
		
		return couponController.getAllCouponsByStartDateStartAndEndDateLimitByCustomerId(startDateStart, startDateLimit,customerId);
	}
////	this function is for the Administrator client (admin panel). 
	@GET
	@Path("/getCouponsByEndDateStartAndEndDateLimiByCustomerId/")
	public ArrayList<Coupon> getAllCouponsByEndDateStartAndEndDateLimitByCustomerId(@QueryParam ("customerId") long customerId,@QueryParam("endDateStart")String endDateStart,@QueryParam("endDateLimit")String endDateLimit) throws ParseException {
		
		return couponController.getAllCouponsByEndDateStartAndEndDateLimitByCustomerId(endDateStart, endDateLimit,customerId);
	}
//	
//	
////	this function is for the Administrator client (admin panel). 
	@GET
	@Path("getCouponsByCouponTypeByCustomerId/")
	public ArrayList<Coupon> getAllCouponsByCouponTypeByCustomerId(@QueryParam ("CouponType")String couponType,@QueryParam ("customerId") long customerId){
		
		return couponController.getAllCouponsByCouponTypeByCustomerId(couponType,customerId);
	}
//	This function is for the Administrator client (admin panel).
	@GET
	@Path("/getCompanyByCompanyId/{companyId}")
	public Company getCompanyByCompanyId(@PathParam ("companyId") long companyId) {
		System.out.println("This is the company id:"+companyId);
		
		return companyController.getCompany(companyId);
		
	}
//	This function is for the Administrator client (admin panel).
	@DELETE
	@Path("/deletePurchasedCouponsAndCompanyCouponsAndCompanyByCompanyId/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCompany(@PathParam ("companyId") long companyId) throws ApplicationException{
		System.out.println("This is the company id: "+companyId);
		companyController.deletePurchasedCouponsAndCompanyCouponsAndCompanyByCompanyId(companyId);
	}
	//	This function is for the Administrator client (admin panel).
	@DELETE
	@Path("/deleteCustomerAndCustomerPurchasedCouponsByCustomerId/{customerId}")
	public void deleteCustomerAndCustomerPurchasedCouponsByCustomerId(@PathParam ("customerId") long customerId) throws ApplicationException{

		customerController.deleteCustomerAndCustomerPurchasedCouponsByCustomerId(customerId);

	}
	//	This function is for the Administrator client (admin panel).
	@PUT
	@Path("/updateCustomerOnlyCustomerPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomerOnlyCustomerPassword(Customer customer) throws ApplicationException{

		customerController.updateCustomerOnlyCustomerPassword(customer);

	}

	
//	this function is for the Administrator client (company panel).
//	using companyId as QueryParam because this is not his API
	@GET
	@Path("/getAllCouponsByCompanyId/")
	public ArrayList<Coupon> getAllCouponsByCompanyId(@QueryParam ("companyId") long companyId){
		System.out.println("This is the company id: "+companyId);
		
		return couponController.getAllCouponsByCompanyId(companyId);
	
	}
//	this function is for the Administrator client (company panel).
	@POST
	@Path("/createCouponByCompanyId")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCouponByCompanyId(Coupon coupon) throws ApplicationException{
		System.out.println("The actual auto incerment in done in the DB, that's why the couponId=0");
		System.out.println(coupon);
		couponController.createCoupon(coupon);
	}
//	this function is for the Administrator client (company panel).
	@DELETE
	@Path("deleteCouponAndDeleteCouponsPurchasedByCustomer/{couponId}")
	public void deleteCouponByCompanyIdAndDeleteCouponsPurchasedByCustomer(@PathParam("couponId")long couponId,@QueryParam ("companyId") long companyId) throws ApplicationException{
		System.out.println("this is the couponId that i'm deleting"+couponId);
		System.out.println("first delete coupon/s purchased by customers");
		couponController.deleteCouponPurchaseByCustomer(couponId);
		System.out.println("now delete the coupon, coupon id: "+couponId);
		System.out.println("this is the companyid"+companyId);
		couponController.deleteCouponByCompanyId(couponId, companyId);
		
}
//	this function is for the Administrator client (company panel).
	@PUT
	@Path("/updateCouponByCompanyId/")
	
	public void updateCouponByCompanyId(@QueryParam ("companyId") long companyId,Coupon coupon) throws ApplicationException{
		System.out.println("The actual auto incerment in done in the DB, that's why the couponId=0");
		System.out.println(coupon);
		System.out.println("This is the company id: "+companyId);
		couponController.updateCouponByCompanyId(coupon, companyId);;
		
	}
//	this function is for the Administrator client (company panel).
	@GET
	@Path("/getCouponsByCompanyAndCouponType/")
	public ArrayList<Coupon> getAllCouponsByCompanyIdAndCouponType(@QueryParam ("companyId") long companyId,@QueryParam("CouponType")String couponType) {
		return couponController.getAllCouponsByCompanyIdAndCouponType(companyId, couponType);
	}
//	this function is for the Administrator client (company panel).
	@GET
	@Path("/getCouponsByCompanyIdAndStartPriceAndLimitPrice/")
	public ArrayList<Coupon> getAllCouponsByCompanyIdAndStartPriceAndLimitPrice(@QueryParam ("companyId") long companyId,@QueryParam("startPrice")double startPrice,@QueryParam("limitPrice")double limitPrice) {
		
		return couponController.getAllCouponsByCompanyIdAndStartPriceAndLimitPrice(startPrice, limitPrice, companyId);
	}
	
//	this function is for the Administrator client (company panel).
	@GET
	@Path("/getCouponsByCompanyIdAndStartDateStartAndEndDateLimit/")
	public ArrayList<Coupon> getAllCouponsByComapnyIdAndStartDateStartAndEndDateLimit(@QueryParam ("companyId") long companyId,@QueryParam("startDateStart")String startDateStart,@QueryParam("startDateLimit")String startDateLimit) throws ParseException {
		System.out.println(startDateStart);
		System.out.println(startDateLimit);
		return couponController.getAllCouponsByComapnyIdAndStartDateStartAndEndDateLimit(startDateStart, startDateLimit, companyId);
	}
//	this function is for the Administrator client (company panel).
	@GET
	@Path("/getCouponsByCompanyIdAndEndDateStartAndEndDateLimit/")
	public ArrayList<Coupon> getCouponsByCompanyIdAndEndDateStartAndEndDateLimit(@QueryParam ("companyId") long companyId,@QueryParam("endDateStart")String endDateStart,@QueryParam("endDateLimit")String endDateLimit) throws ParseException {
		return couponController.getAllCouponsByComapnyIdAndEndDateStartAndEndDateLimit(endDateStart, endDateLimit, companyId);
	}
//	this function is for the Administrator client (company panel).
	@GET
	@Path("/getCompany/")
	public Company getCompany(@QueryParam ("companyId") long companyId) {
		System.out.println("This is the company Id: "+companyId);
		
		return companyController.getCompany(companyId);
		
	}

//	this function is for the Administrator client (customer panel).
	@POST
	@Path("purchaseCoupon/")
//	@Consumes(MediaType.APPLICATION_JSON)
	public void purchaseCouponByCustomer(@QueryParam ("customerId") long customerId,@QueryParam ("couponId") long couponId) throws ApplicationException{
//		this means that there are more then one coupon purchased by this customer
		if(couponController.getIsThereMoreThenOneCouponPurchasedByThisCustomer(customerId, couponId)==true){
			couponController.updateAmountOfPurchasedCouponsByCustomer(customerId, couponId);
			couponController.updateCouponAmount(couponId);
			System.out.println("now the amount of purchased coupons by this customer will update by 1");
		}
		else{
//			this means that this is the first purchased of coupon by this customer 
		couponController.purchaseCouponByCustomer(customerId, couponId);
		couponController.updateAmountOfPurchasedCouponsByCustomer(customerId, couponId);
		couponController.updateCouponAmount(couponId);
		System.out.println("you just purchased a coupon");
		System.out.println("there are no amount of coupons purchased by this customer"
				+ "and this is the first one");
		}
		
	}
//	this function is for the Administrator client (customer panel).
	@GET
	@Path("getPurchasedCouponsByCustomerId/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerId(@QueryParam ("customerId") long customerId) {
		System.out.println("This customer: "+customerId+" purchased this coupon/s");

		return couponController.getAllPurchasedCouponsByCustomerId(customerId);
	}
//	this function is for the Administrator client (customer panel).
	@GET
	@Path("getAmountOfPurchasedCouponsByCustomerIdAndCouponId/")
//	here I have converted the AmountOfPurchasedCouponsByCustomerIdAndCouponId form long to string for MediaType.TEXT_PLAIN present
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String getAmountOfPurchasedCouponsByCustomerIdAndCouponId(@QueryParam ("customerId") long customerId,@QueryParam ("couponId") long couponId) throws ApplicationException {
		System.out.println("Amount Of Purchased Coupons ByCustomerId And CouponId"+couponController.getAmountOfPurchasedCouponsByCustomerIdAndCouponId(customerId, couponId));
		System.out.println("this is the coupon id "+couponId);
		String AmountOfPurchasedCouponsByCustomerIdAndCouponId = Long.toString(couponController.getAmountOfPurchasedCouponsByCustomerIdAndCouponId(customerId, couponId));
		return AmountOfPurchasedCouponsByCustomerIdAndCouponId;
	}
//	this function is for the Administrator client (customer panel).
	@GET
	@Path("getPurchasedCouponsByCustomerIdCouponType/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndCouponType(@QueryParam ("customerId") long customerId,@QueryParam ("CouponType")String couponType){
		System.out.println("This is the coupontype: "+couponType);
		return couponController.getAllPurchasedCouponsByCustomerIdAndCouponType(customerId, couponType);
	}
//	this function is for the Administrator client (customer panel).
	@GET
	@Path("getAllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice(@QueryParam ("customerId") long customerId,@QueryParam("startPrice")double startPrice,@QueryParam("limitPrice")double limitPrice) {
		System.out.println("This is the start price of AllPurchasedCouponsByCustomerId "+startPrice);
		System.out.println("This is the end price of AllPurchasedCouponsByCustomerId"+limitPrice);
		
		return couponController.getAllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice(startPrice, limitPrice,customerId);
	}
//	this function is for the Administrator client (customer panel).
	@GET
	@Path("/getCouponsByCustomerIdAndStartDateStartAndEndDateLimit/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndStartDateStartAndEndDateLimit(@QueryParam ("customerId") long customerId,@QueryParam("startDateStart")String startDateStart,@QueryParam("startDateLimit")String startDateLimit) throws ParseException {
		System.out.println("This is the start date for All Purchased Coupons(startDate) By CustomerId: "+startDateStart);
		System.out.println("This is the limit date for All Purchased Coupons(startDate) By CustomerId: "+startDateLimit);
		
		return couponController.getAllPurchasedCouponsByCustomerIdAndStartDateStartAndEndDateLimit(startDateStart, startDateLimit,customerId);
	}
//	this function is for the Administrator client (customer panel).
	@GET
	@Path("/getCouponsByCustomerIdAndEndDateStartAndEndDateLimit/")
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndLimitDateStartAndEndDateLimit(@QueryParam ("customerId") long customerId,@QueryParam("endDateStart")String endDateStart,@QueryParam("endDateLimit")String endDateLimit) throws ParseException {
		System.out.println("This is the start date for All Purchased Coupons(endDate) By CustomerId: "+endDateStart);
		System.out.println("This is the limit date for All Purchased Coupons(endDate)By CustomerId: "+endDateLimit);
		return couponController.getAllPurchasedCouponsByCustomerIdAndLimitDateStartAndEndDateLimit(endDateStart, endDateLimit,customerId);
	}
	
	
	
	
	
//	this function is for the Administrator client (customer panel).
	@GET
	@Path("/getCouponsByCustomerId/")
	public ArrayList<Coupon> getAllCouponsByCustomerId(@QueryParam ("customerId") long customerId){
		
		return couponController.getAllCouponsByCustomerId(customerId);
	
	}
	@GET
	@Path("/getAdminIdSession/")
	public void getCompanyIdSessionAndInvalidateSession(@Context HttpServletRequest request,@Context HttpServletRequest response) throws ApplicationException{
		

			System.out.println("Admin ID Session: "+request.getSession().getAttribute("AdminId"));
			request.getSession().invalidate();
			if(request.getSession().getAttribute("AdminId")==null){
				System.out.println("This Session is now invalidate");
			}

	}
	
}
