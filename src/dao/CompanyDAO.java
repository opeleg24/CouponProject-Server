package com.omri.coupon.dao;
import java.sql.*;
import java.util.ArrayList;
import com.omri.coupon.beans.Company;
import com.omri.coupon.beans.Coupon;
import com.omri.coupon.connectionPool.jdbcConnection;
import com.omri.coupon.enums.CouponType;
import com.omri.coupon.enums.ErrorType;
import com.omri.coupon.exception.ApplicationException;
import com.omri.coupon.interfaceDao.ICompanyDao;




public class CompanyDAO implements ICompanyDao {
	
	@Override
	public void createCompany(Company company) throws ApplicationException {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
	try {
		//Getting a connection to the MySql Data Base
		connection = jdbcConnection.getConnection();
		//Creating the SQL query
		String sql = "INSERT INTO Company (companyName, companyPassword,companyEmail) VALUES (?,?,?)";
		preparedStatement= connection.prepareStatement(sql);
		//Replacing the question marks in the statement with the following data	
		//company id is primary key and auto incremented
		preparedStatement.setString(1, company.getCompanyName());
		preparedStatement.setString(2, company.getCompanyPassword());
		preparedStatement.setString(3, company.getCompanyEmail());
		//Executing the update
		preparedStatement.executeUpdate();
	} catch (Exception e) {
		throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"CREATE COMPANY FAILED");
		//Closing the resources
	} finally {
		jdbcConnection.closeResources(connection, preparedStatement);
	}
	}
	
//	admin client
//	delete process of removing a company by company id
//	this function is being called after removing the company coupons and removing the coupons purchased by customers.
	@Override
	public void deleteCompany(long companyId)throws ApplicationException{
			// Turning on connections
			java.sql.PreparedStatement preparedStatement = null;
			Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "DELETE FROM Company WHERE companyId=?";;
			preparedStatement= connection.prepareStatement(sql);
			//	Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,companyId);
			//Executing the update
			preparedStatement.executeUpdate();
			
			} catch (Exception e) {
				
				//Closing the resources
			} finally {
				jdbcConnection.closeResources(connection, preparedStatement);
			}
	}
	@Override
	public void updateCompany(Company company)throws ApplicationException{
			// Turning on connections
			java.sql.PreparedStatement preparedStatement = null;
			Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "UPDATE Company SET companyName=?,companyPassword=?,companyEmail=? WHERE companyId=? ";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1,company.getCompanyName());
			preparedStatement.setString(2,company.getCompanyPassword());
			preparedStatement.setString(3,company.getCompanyEmail());
			preparedStatement.setLong(4,company.getCompanyId());
			//Executing the update
			preparedStatement.executeUpdate();
			} catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"UPDATING COMPANY FAILED,CHECK YOUR COMPANY ID AGAIN");
			} finally {
			//Closing the resources
				jdbcConnection.closeResources(connection, preparedStatement);
			}
	}
	
	@Override
	public Company getCompany(long companyId) {
			//Turning on connections
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			Company company = new Company();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM company WHERE companyId = ? ";
			preparedStatement = connection.prepareStatement(sql);
//			Replacing the question marks in the statement with the following data
			preparedStatement.setLong(1, companyId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			if (!resultSet.next()) {
				return null;
			}
//			Copying the result data to a company object
			company = extractCompanyFromResultSet(resultSet);
		}
		catch (SQLException e) {
		e.printStackTrace();
		
		}
		//Closing the resources
		finally {
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object
		return company;
		}
		// This method will extract from the result set the company object
	private Company extractCompanyFromResultSet(ResultSet resultSet) throws SQLException {
		
		Company company=new Company();
		company.setCompanyId(resultSet.getLong("companyId"));
		company.setCompanyName(resultSet.getString("companyName"));
		company.setCompanyPassword(resultSet.getString("companyPassword"));
		company.setCompanyEmail(resultSet.getString("companyEmail"));
		
		return company;
	}
	
	public long getcompanyIdBycompanyNameAndcompanyPassword(String companyName, String companyPassword) throws ApplicationException  {
		
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
	try {
		//Getting a connection to the MySql Data Base
		connection = jdbcConnection.getConnection();
    	//Creating the SQL query
		String sql = "SELECT companyId FROM company WHERE companyName=? and companyPassword=?";
		preparedStatement = connection.prepareStatement(sql);
		//Replacing the question marks in the statement with the following data	
		preparedStatement.setString(1,companyName);
		preparedStatement.setString(2, companyPassword);
		//Executing the update
		resultSet = preparedStatement.executeQuery();
		//Checking if the result set returns value, if NO return null
		 while (resultSet.next()) {
			 long companyId=resultSet.getLong("companyId");
			 return companyId;
			
		}
		
	}
	catch (SQLException e) {
	e.printStackTrace();

	}
	finally {
		//Closing the resources
		jdbcConnection.closeResources(connection, preparedStatement,resultSet);
	}
	
	//Returning that object
	return 0;
}
// The method will go through the result set and extract the coupon attributes
private long extractCompanyIdFromResultSet(ResultSet resultSet) throws SQLException {
	long companyId=resultSet.getLong("companyId");

	
	return companyId;
}
	public ArrayList<Company> getAllCompanies() {
			//Turning on connections
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ArrayList<Company> allCompanies = new ArrayList<>();
			ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM company";
			preparedStatement = connection.prepareStatement(sql);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a array list object(filled with company objects)
			allCompanies = extractAllCompaniesFromResultSet(resultSet);
		}

		catch (SQLException e) {
			e.printStackTrace();
			
		}

		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement, resultSet);
		}
			//Returning that object list
		return allCompanies;
	}
			// The method will add each company to the array list allCompanies 
			//by using the allcompanisextractCompanyFromResultSet method
	public ArrayList<Company> extractAllCompaniesFromResultSet(ResultSet resultSet)throws SQLException {
		ArrayList<Company> allCompanies = new ArrayList<>();
		do {
			Company company = new Company();
			company = extractCompanyFromResultSet(resultSet);
			allCompanies.add(company);
		} while (resultSet.next());

		return allCompanies;
	}

	public boolean loginCompany(String companyName, String companyPassword)  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		//Getting a connection to the MySql Data Base
		connection = jdbcConnection.getConnection();
		//Creating the SQL query
		String sql = "SELECT * FROM company WHERE companyName=? and companyPassword=? ";
		preparedStatement = connection.prepareStatement(sql);
		//Replacing the question marks in the statement with the following data
		preparedStatement.setString(1, companyName);
		preparedStatement.setString(2, companyPassword);
		//Executing the update
		resultSet = preparedStatement.executeQuery();
		
		//Checking if the result set returns value, if Yes return true
		if (resultSet.next()) {
			return true;
		}	
		}
//		In the next layer(Controller) we will check if this method return true or false
//		and and we will throw an exception accordingly
		catch (SQLException e) {
			e.printStackTrace();
			}
			finally {
		//Closing the resources
				jdbcConnection.closeResources(connection, preparedStatement,resultSet);
			}
		return false;
			
			}
	public boolean isCompanyExistByName(Company company) {
		// Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		//Getting a connection to the MySql Data Base
		connection = jdbcConnection.getConnection();
		//Creating the SQL query
		String sql = "SELECT * FROM company WHERE companyName=?";
		preparedStatement = connection.prepareStatement(sql);
		//Replacing the question marks in the statement with the following data
		preparedStatement.setString(1,company.getCompanyName());
		//Executing the update
		resultSet = preparedStatement.executeQuery();
		//Checking if the result set returns value, if Yes return true
		if (resultSet.next()) {
			return true;
		}	
		}
//		In the next layer(Controller) we will check if this method return true or false
//		and and we will throw an exception accordingly
		catch (SQLException e) {
			e.printStackTrace();
			
			}
		//Closing the resources
			finally {
				jdbcConnection.closeResources(connection, preparedStatement,resultSet);
			}
		return false;
			
	}
	public void updateCompanyEverythingOtherThenUpdateCompanyName(Company company)throws ApplicationException{
			// Turning on connections
			java.sql.PreparedStatement preparedStatement = null;
			Connection connection = null;
		
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "UPDATE Company SET companyPassword=?,companyEmail=? WHERE companyId=? ";
		//	Replacing the question marks in the statement with the following data
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1,company.getCompanyPassword());
			preparedStatement.setString(2,company.getCompanyEmail());
			preparedStatement.setLong(3,company.getCompanyId());
			//Executing the update
			preparedStatement.executeUpdate();
			} catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"UPDATING COMPANY FAILED");
			//Closing the resources
			} finally {
				jdbcConnection.closeResources(connection, preparedStatement);
			}
	}
	
	public boolean isCompanyLoggedInDetailsCorrect(String companyName, String companyPassword) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		//Getting a connection to the MySql Data Base
		connection = jdbcConnection.getConnection();
		//Creating the SQL query
		String sql = "SELECT * FROM company WHERE companyName=? and companyPassword=? ";
		preparedStatement = connection.prepareStatement(sql);
		//Replacing the question marks in the statement with the following data
		preparedStatement.setString(1, companyName);
		preparedStatement.setString(2, companyPassword);
		//Executing the update
		resultSet = preparedStatement.executeQuery();
		//Checking if the result set returns value and if Yes return true 
		
		if (resultSet.next()) {
			return true;
		}	
		}
//		In the next layer(Controller) we will check if this method return true or false
//		and and we will throw an exception accordingly
		catch (SQLException e) {
			e.printStackTrace();
		
			}
			finally {
		//Closing the resources
				jdbcConnection.closeResources(connection, preparedStatement,resultSet);
			}
		return false;
			
	}
	public boolean isCompanyExists(Company company)  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		//Getting a connection to the MySql Data Base
		connection = jdbcConnection.getConnection();
		//Creating the SQL query
		String sql = "SELECT * FROM company WHERE companyId=?";
		preparedStatement = connection.prepareStatement(sql);
		//Replacing the question marks in the statement with the following data
		preparedStatement.setLong(1, company.getCompanyId());
		//Executing the update
		resultSet = preparedStatement.executeQuery();
		//Checking if the result set returns value, if Yes return true
		if (resultSet.next()) {
			return true;
		}	
		}
		//In the next layer(Controller) we will check if this method return true or false
		//and and we will throw an exception accordingly
		catch (SQLException e) {
			e.printStackTrace();
			}
			finally {
				jdbcConnection.closeResources(connection, preparedStatement,resultSet);
			}
		//Closing the resources
		return false;
			
	}
	public boolean isCompanyExists(long companyId)  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		//Getting a connection to the MySql Data Base
		connection = jdbcConnection.getConnection();
		//Creating the SQL query
		String sql = "SELECT * FROM company WHERE companyId=?";
		preparedStatement = connection.prepareStatement(sql);
		//Replacing the question marks in the statement with the following data
		preparedStatement.setLong(1, companyId);
		//Executing the update
		resultSet = preparedStatement.executeQuery();
		//Checking if the result set returns value, if Yes return true
		if (resultSet.next()) {
			return true;
		}	
		}
		//In the next layer(Controller) we will check if this method return true or false
		//and and we will throw an exception accordingly
		catch (SQLException e) {
			e.printStackTrace();
			}
			finally {
				jdbcConnection.closeResources(connection, preparedStatement,resultSet);
			}
		//Closing the resources
		return false;
			
	}

	

}
