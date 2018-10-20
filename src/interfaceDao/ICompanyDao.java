package com.omri.coupon.interfaceDao;

import java.util.ArrayList;
import java.util.Set;

import com.omri.coupon.beans.Company;
import com.omri.coupon.exception.ApplicationException;




public interface ICompanyDao {
	public void createCompany(Company company) throws ApplicationException ;
	public void deleteCompany(long companyId) throws ApplicationException ;
	public void updateCompany(Company company) throws ApplicationException;
	public Company getCompany(long companyId) throws ApplicationException  ;
	public ArrayList<Company> getAllCompanies() throws ApplicationException;
	public boolean loginCompany(String compName,String compPassword) throws ApplicationException;
	
	

}
