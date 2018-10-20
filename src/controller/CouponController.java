package com.omri.coupon.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.omri.coupon.beans.Coupon;
import com.omri.coupon.dao.CouponDAO;
import com.omri.coupon.enums.ClientType;
import com.omri.coupon.enums.ErrorType;
import com.omri.coupon.exception.ApplicationException;


public class CouponController{

	private CouponDAO couponDao;

	public CouponController() {
		super();
		this.couponDao = new CouponDAO();
	}

	public void createCoupon(Coupon coupon) throws ApplicationException {
		//A call for a validation method
		validateCreateCoupon(coupon);
		//A call for a createCoupon method
		couponDao.createCoupon(coupon);
	}
	//	this is for the company client
	public void createCouponByCompanyId(Coupon coupon,long companyId) throws ApplicationException {
		//A call for a validation method
		validateCreateCouponByCompanyId(coupon,companyId);
		//A call for a createCoupon method
		couponDao.createCouponByComapnyId(coupon, companyId);
	}


	public void deleteCoupon(long couponId) throws ApplicationException{
		//A call for a validation method
		validateIsCouponExists(couponId);
		//A call for a deleteCoupon method
		couponDao.deleteCoupon(couponId);
	}
	//	this is for the company client
	public void deleteCouponByCompanyId(long couponId,long companyId) throws ApplicationException{
		//A call for a validation method
		validateIsCouponExists(couponId,companyId);
		//A call for a deleteCoupon method
		couponDao.deleteCouponByCompanyId(couponId, companyId);
	}
	public void updatingCoupon(Coupon coupon) throws ApplicationException{
		//A call for a validation method
		validateIsCouponExists(coupon);
		//A call for a updateCoupon method;
		couponDao.updateCoupon(coupon);
	}
	//	this is for the company client
	public void updateCouponByCompanyId(Coupon coupon,long companyId) throws ApplicationException{
		//A call for a validation method
		validateIsCouponExistsByCompanyId(coupon,companyId);
		//A call for a updateCoupon method;
		couponDao.updateCouponByCompanyId(coupon, companyId);
	}

	public Coupon getCoupon(long couponId){

		return couponDao.getCoupon(couponId);
	}

	public ArrayList<Coupon> getAllCoupons(){

		return	couponDao.getAllCoupons();
	}

	public ArrayList<Coupon> getAllCouponsByCompanyId(long companyId){

		return couponDao.getCouponsByCompanyId(companyId);
	}
	public ArrayList<Coupon> getAllCouponsByCustomerId(long customerId) {

		return couponDao.getAllCouponsByCustomerId(customerId);
	}
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndCouponType(long customerId,String couponType){

		return couponDao.getAllPurchasedCouponsByCustomerIdAndCouponType(customerId, couponType);
	}
	public ArrayList<Coupon> getAllCouponsByCompanyIdAndCouponType(long companyId,String couponType) {

		return couponDao.getAllCouponsByCompanyIdAndCouponType(companyId, couponType);
	}


	public ArrayList<Coupon> getAllCouponsByCompanyIdAndStartPriceAndLimitPrice(double startPrice,double limitPrice,long companyId) {

		return couponDao.getAllCouponsByCompanyIdAndStartPriceAndLimitPrice(startPrice, limitPrice, companyId);
	}

	public ArrayList<Coupon> getCouponsByStartPriceAndLimitPriceByCustomerId(double startPrice,double limitPrice,long customerId) {

		return couponDao.getAllCouponsByStartPriceAndLimitPriceByCustomerId(startPrice, limitPrice,customerId);
	}
	public ArrayList<Coupon> getAllCouponsByStartDateStartAndEndDateLimitByCustomerId(String startDateStart,String startDateLimit,long customerId) throws ParseException{
		//		the validation is the date that was entered by the client OK will be in the client side

		return couponDao.getAllCouponsByStartDateStartAndEndDateLimitByCustomerId(startDateStart, startDateLimit,customerId);
	}
	public ArrayList<Coupon> getAllCouponsByEndDateStartAndEndDateLimitByCustomerId(String endDateStart,String endDateLimit,long customerId) throws ParseException{
		//		the validation is the date that was entered by the client OK will be in the client side

		return couponDao.getAllCouponsByEndDateStartAndEndDateLimitByCustomerId(endDateStart, endDateLimit,customerId);
	}


	public ArrayList<Coupon> getAllCouponsByCouponTypeByCustomerId(String couponType,long customerId){
//this is by the customerID
		return couponDao.getCouponsByCouponTypeByCustomerId(couponType,customerId);
	}

	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice(double startPrice,double limitPrice,long customerId) {

		return couponDao.getAllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice(startPrice, limitPrice,customerId);
	}

	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerId(long customerId) {

		return couponDao.getAllPurchasedCouponsByCustomerId(customerId);
	}
	public ArrayList<Coupon> getAllCouponsByComapnyIdAndStartDateStartAndEndDateLimit(String startDateStart,String startDateLimit,long companyId) throws ParseException{
		//		the validation is the date that was entered by the client OK will be in the client side
		System.out.println(startDateStart);
		System.out.println(startDateLimit);
		return couponDao.getAllCouponsByComapnyIdAndStartDateStartAndEndDateLimit(startDateStart, startDateLimit, companyId);
	}

	public ArrayList<Coupon> getAllCouponsByComapnyIdAndEndDateStartAndEndDateLimit(String endDateStart,String endDateLimit,long companyId) throws ParseException{
		//		the validation is the date that was entered by the client OK will be in the client side

		return couponDao.getAllCouponsByComapnyIdAndEndDateStartAndEndDateLimit(endDateStart, endDateLimit,companyId);
	}
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndStartDateStartAndEndDateLimit(String startDateStart,String startDateLimit,long customerId) throws ParseException{
		//		the validation is the date that was entered by the client OK will be in the client side

		return couponDao.getAllPurchasedCouponsByCustomerIdAndStartDateStartAndEndDateLimit(startDateStart, startDateLimit, customerId);
	}
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndLimitDateStartAndEndDateLimit(String endDateStart,String endDateLimit,long customerId) throws ParseException{
		//		the validation is the date that was entered by the client OK will be in the client side

		return couponDao.getAllPurchasedCouponsByCustomerIdAndLimitDateStartAndEndDateLimit(endDateStart, endDateLimit,customerId);
	}

	public boolean loginAdmin(String UserName, String Password) throws ApplicationException{
		//Checks if administrator logged in details are correct	
		if(UserName.equals("admin")||UserName.equals("Admin")||UserName.equals("ADMIN")&&Password.equals("1234")){
			return true;
		}
		else{
			throw new ApplicationException(ErrorType.FAIL_TO_LOGIN,"Error Login deatils not correct");
		}
	}

	public long getAmountOfPurchasedCouponsByCustomerIdAndCouponId(long customerId,long couponId) throws ApplicationException{

		return couponDao.getAmountOfPurchasedCouponsByCustomerIdAndCouponId(customerId, couponId);
	}
	public long getCustomerId(long couponId) throws ApplicationException{

		return couponDao.getcustomerIdByCouponId(couponId);
	}
	public void deleteCouponPurchaseByCustomer(long couponId) throws ApplicationException{
		//A call for a validation method
		//		validateIsCustomerPurchasedThisCoupon(getCustomerId(couponId),couponId);
		//A call for a deleteCouponPurchaseByCustomer method
		couponDao.deleteCouponPurchaseByCustomer(getCustomerId(couponId), couponId);
	}
	public boolean getIsThereMoreThenOneCouponPurchasedByThisCustomer(long customerId,long couponId) throws ApplicationException{
		//A call for a validation method
		//		validatePurchasingCouponByCustomer(customerId,couponId);
		return couponDao.getIsThereMoreThenOneCouponPurchasedByThisCustomer(customerId, couponId);

	}
	public void updateAmountOfPurchasedCouponsByCustomer(long customerId,long couponId) throws ApplicationException{
		//A call for a validation method
		validatePurchasingCouponByCustomer(customerId,couponId);
		//A call for a purchaseCouponByCustomer method
		couponDao.updateAmountOfPurchasedCouponsByCustomer(customerId, couponId);;

	}
	public void purchaseCouponByCustomer(long customerId,long couponId) throws ApplicationException{
		//A call for a validation method
		validatePurchasingCouponByCustomer(customerId,couponId);
		//A call for a purchaseCouponByCustomer method
		couponDao.purchaseCouponByCustomer(customerId, couponId);
		//Updating current coupon amount
		//		couponDao.updateCouponAmount(couponId);
	}
	public void updateCouponAmount(long couponId) throws ApplicationException{

		couponDao.updateCouponAmount(couponId);
	}


	//Validation method that will check with the Date Base 
	private void validatePurchasingCouponByCustomer(long customerId, long couponId)throws ApplicationException {

		//checks if coupon exists 
		if(!couponDao.isCouponExists(couponId)){
			throw new ApplicationException(ErrorType.COUPON_NOT_EXISTS,"This Coupon Does Not Exist.");
		}
		//checks if coupon there are coupons left in stock  
		if(!couponDao.isCouponAmountOutOfStock(couponId)){
			throw new ApplicationException(ErrorType.COUPON_OUT_OF_STOCK,"This Coupon is out of stock.");
		}
		//checks if the coupon date hasn't expired
		if(!couponDao.isCouponExpired(couponId)){
			throw new ApplicationException(ErrorType.COUPON_EXPIRED,"This coupon has expired.");
		}
	}
	//Validation method that will check with the Date Base 
	private void validateIsCustomerPurchasedThisCoupon(long customerId,long couponId) throws ApplicationException {
		//checks if this customer purchased this coupon
		if(!couponDao.isCustomerPurchasedThisCoupon(customerId,couponId)){
			throw new ApplicationException(ErrorType.COUPON_NOT_EXISTS,"This customer didn't purchased this coupon");
		}
	}
	//Validation method that will check with the Date Base 
	private void validateCreateCoupon(Coupon coupon) throws ApplicationException {
		//checks if there is already a coupon with the same name 
		if(couponDao.isCouponExistByTitle(coupon)){
			throw new ApplicationException(ErrorType.TITLE_ALREADY_EXISTS, "Please pick another coupon title, this title is already taken.");
		}
	}
	//Validation method that will check with the Date Base,this function is for the company client 
	private void validateCreateCouponByCompanyId(Coupon coupon,long companyId) throws ApplicationException {
		//checks if there is already a coupon with the same name 
		if(couponDao.isCouponExistByTitle(coupon,companyId)){
			throw new ApplicationException(ErrorType.TITLE_ALREADY_EXISTS, "Please pick another coupon title, this title is already taken.");
		}
	}
	//Validation method that will check with the Date Base ,this function is for the company client
	private void validateIsCouponExists(long couponId,long companyId) throws ApplicationException {
		//checks if this coupon exists
		if(!couponDao.isCouponExists(couponId,companyId)){
			throw new ApplicationException(ErrorType.COUPON_NOT_EXISTS,"This Coupon Does Not Exist.");
		}
	}
	//Validation method that will check with the Date Base ,this function is for the admin client
	private void validateIsCouponExists(long couponId) throws ApplicationException {
		//checks if this coupon exists
		if(!couponDao.isCouponExists(couponId)){
			throw new ApplicationException(ErrorType.COUPON_NOT_EXISTS,"This Coupon Does Not Exist.");
		}
	}
	private void validateIsCouponExists(Coupon coupon) throws ApplicationException {
		//checks if this coupon exists
		if(!couponDao.isCouponExists(coupon)){
			throw new ApplicationException(ErrorType.COUPON_NOT_EXISTS,"This Coupon Does Not Exist.");
		}

	}

	private void validateIsCouponExistsByCompanyId(Coupon coupon,long companyId) throws ApplicationException {
		//checks if this coupon exists
		if(!couponDao.isCouponExists(coupon,companyId)){
			throw new ApplicationException(ErrorType.COUPON_NOT_EXISTS,"This Coupon Does Not Exist.");
		}

	}


}

