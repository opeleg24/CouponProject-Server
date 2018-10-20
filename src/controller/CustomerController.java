package com.omri.coupon.controller;

import java.util.ArrayList;
import java.util.List;

import com.omri.coupon.beans.Customer;
import com.omri.coupon.dao.CouponDAO;
import com.omri.coupon.dao.CustomerDAO;
import com.omri.coupon.enums.ClientType;
import com.omri.coupon.enums.ErrorType;
import com.omri.coupon.exception.ApplicationException;


public class CustomerController {
	private CustomerDAO customerDao;
	private CouponDAO couponDao;

	public CustomerController() {
		super();
		this.customerDao = new CustomerDAO();
		this.couponDao=new CouponDAO();
}
		public void createCustomer(Customer customer)throws ApplicationException{
		//A call for a validation method
		validateCreateCustomer(customer);
		//A call for a createCustomer method
		customerDao.createCustomer(customer);
	
	}

//			this is for the admin
//			process of deleting a customer by customer id
//			1.remove the coupons that were purchased by this customers that have a customer id
//			2.remove the customer 
		public void deleteCustomerAndCustomerPurchasedCouponsByCustomerId(long customerId)throws ApplicationException{
		//A call for a validation method	
		validateIsCustomerExists(customerId);
		//A call for a delete Purchased Coupons By Customer id method
		couponDao.deletePurchasedCouponsByCustomerId(customerId);
		//A call for a deleteCustomer method
		customerDao.deleteCustomer(customerId);
			
		}
	
		public void updateCustomerOnlyCustomerPassword(Customer customer)throws ApplicationException{
		//A call for a validation method	
		validateIsCustomerExists(customer);
		//A call for a updateCustomerALLOtherThanCustomerName method
		customerDao.updateCustomerOnlyCustomerPassword(customer);
		}
		
		public Customer getCustomer(long cusomerId) {
	
		return customerDao.getCustomer(cusomerId);
		}
		
		public ArrayList<Customer> getAllCustomers() {
	
		return	customerDao.getAllCustomers();
		}
		public long getcustomerIdBycustomerNameAndcustomerPassword(String customerName, String customerPassword) throws ApplicationException{
			
			return customerDao.getcustomerIdBycustomerNameAndcustomerPassword(customerName, customerPassword);
		}
		
		public boolean loginCustomer(String UserName,String Password) throws ApplicationException{
		//A call for a validation method	
		validateloginCustomer(UserName,Password);
		//A call for a LoginCustomer method
		return customerDao.loginCustomer(UserName,Password);
		}
		//Validation method that will check with the Date Base 
		private void validateCreateCustomer(Customer customer) throws ApplicationException {
			//checks if there is already a customer with the same name
			if(customerDao.isCustomersExistByName(customer)){
				throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, "Please pick another customer name, this name is already taken.");
			}
		}
		private void validateIsCustomerExists(Customer customer) throws ApplicationException {
			//checks if this customer already exists
			if(!customerDao.isCustomersExist(customer)){
				throw new ApplicationException(ErrorType.CUSTOMER_NOT_EXISTS, "This Customer Does Not Exist.");
			}
		}
		//Validation method that will check with the Date Base 
		private void validateIsCustomerExists(long customerId) throws ApplicationException {
			//checks if this customer already exists
			if(!customerDao.isCustomersExist(customerId)){
				throw new ApplicationException(ErrorType.CUSTOMER_NOT_EXISTS, "This Customer Does Not Exist.");
			}
		}
		
		//Validation method that will check with the Date Base 
		private void validateloginCustomer(String UserName,String Password) throws ApplicationException {
			//checks if customer logged in details are correct 
			if(!customerDao.loginCustomer(UserName, Password)){
				throw new ApplicationException(ErrorType.FAIL_TO_LOGIN,"Error Login deatils not correct ");
			}
			
		}
	
	
	}
	
