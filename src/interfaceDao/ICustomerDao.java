package com.omri.coupon.interfaceDao;

import java.util.ArrayList;

import com.omri.coupon.beans.Customer;
import com.omri.coupon.exception.ApplicationException;


public interface ICustomerDao {
	public void createCustomer(Customer customer) throws ApplicationException;
	public void deleteCustomer(long customerId) throws ApplicationException;
	public void updateCustomer(Customer customer) throws ApplicationException;
	public Customer getCustomer(long customerId) throws ApplicationException;
	public ArrayList<Customer> getAllCustomers() throws ApplicationException;
	public boolean loginCustomer(String customerName,String CustPassword) throws ApplicationException;


}
