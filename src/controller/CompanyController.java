package com.omri.coupon.controller;


import java.util.ArrayList;
import java.util.List;

import com.omri.coupon.beans.Company;
import com.omri.coupon.dao.CompanyDAO;
import com.omri.coupon.dao.CouponDAO;
import com.omri.coupon.enums.ClientType;
import com.omri.coupon.enums.ErrorType;
import com.omri.coupon.exception.ApplicationException;


public class CompanyController{

	private CompanyDAO companyDao;
	private CouponDAO couponDao;
	
	public CompanyController() {
		super();
		this.companyDao = new CompanyDAO();
		this.couponDao=new CouponDAO();
	}
	
	public void createCompany(Company company) throws ApplicationException{
		//A call for a validation method
		validateCreateCompany(company);
		//A call for a createCompany method
		companyDao.createCompany(company);
	}
//	this is for the admin
//	delete process of removing a company by company id
//	remove the coupons that were purchased by this customers that have a company id
	public void deletePurchasedCouponsAndCompanyCouponsAndCompanyByCompanyId(long companyId) throws ApplicationException{
		//A call for a validation method
		validateIsCompanyExists(companyId);
		System.out.println(companyId);
		//A call for a delete method
//		1.remove the coupons that were purchased by this customers that have a company id
		couponDao.deletePurchasedCouponsByCompanyId(companyId);
//		2.remove the company coupons by company id
		couponDao.deleteCompanyCoupons(companyId);
//		3.remove company by company id
		companyDao.deleteCompany(companyId);
		
	}

	public void updatingCompany(Company company) throws ApplicationException{
		//A call for a validation method
		validateIsCompanyExists(company);
		//A call for a updateCompanyALLOtherThenUpdateCompanyName method
		companyDao.updateCompanyEverythingOtherThenUpdateCompanyName(company);
	}

	public Company getCompany(long companyId) {
		
		return companyDao.getCompany(companyId);
	}
	public long getcompanyIdBycompanyNameAndcompanyPassword(String companyName, String companyPassword) throws ApplicationException{
		
		return companyDao.getcompanyIdBycompanyNameAndcompanyPassword(companyName, companyPassword);
	}
	public ArrayList<Company> getAllCompanies() {
		
        return companyDao.getAllCompanies();
	}
	public boolean loginCompany(String UserName,String Password) throws ApplicationException{
		//A call for a validation method
		validateloginCompany(UserName,Password);
		return companyDao.loginCompany(UserName,Password);
	}
	//Validation method that will check with the Date Base 
	private void validateloginCompany(String UserName,String Password) throws ApplicationException  {
		//checks if this client has authorization for this action
		if(!companyDao.loginCompany(UserName, Password)){
			throw new ApplicationException(ErrorType.FAIL_TO_LOGIN,"Error Login deatils not correct");
		}
		
	}
	//Validation method that will check with the Date Base 
	private void validateCreateCompany(Company company) throws ApplicationException {
		//checks if there is already a company exists with this name
		if(companyDao.isCompanyExistByName(company)){
			throw new ApplicationException(ErrorType.TITLE_ALREADY_EXISTS,"Please pick another company name, this name is already taken.");
		}
	}
	//Validation method that will check with the Date Base 
	private void validateIsCompanyExists(Company company) throws ApplicationException{
		//checks if there is already a company exists
		if(!companyDao.isCompanyExists(company)){
			throw new ApplicationException(ErrorType.COMPANY_NOT_EXISTS,"This Company Does Not Exist.");
			}
			
		}
	private void validateIsCompanyExists(long companyId) throws ApplicationException {
		// TODO Auto-generated method stub
		if(!companyDao.isCompanyExists(companyId)){
			throw new ApplicationException(ErrorType.COMPANY_NOT_EXISTS,"This Company Does Not Exist.");
			}
		
	}
	
}
