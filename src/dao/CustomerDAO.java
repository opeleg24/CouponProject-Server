package com.omri.coupon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.omri.coupon.beans.Customer;
import com.omri.coupon.connectionPool.jdbcConnection;
import com.omri.coupon.enums.ErrorType;
import com.omri.coupon.exception.ApplicationException;
import com.omri.coupon.interfaceDao.ICustomerDao;



public class CustomerDAO implements ICustomerDao {

	@Override
	public void createCustomer(Customer customer) throws ApplicationException {
		    //Turning on connections
			java.sql.PreparedStatement preparedStatement = null;
			Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base	
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "INSERT INTO Customer (customerName, customerPassword) VALUES (?,?)";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data,	
			//customer id is primary key and auto incremented
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getCustomerPassword());
			//Executing the update
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"CREATE CUSTOMER FAILED");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
		
	}
//this is for the admin client 
	@Override
	public void deleteCustomer(long customerId) throws ApplicationException {
			//Turning on connections
			java.sql.PreparedStatement preparedStatement = null;
			Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base	
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "DELETE FROM Customer WHERE customerId=?";;
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,customerId);
			//Executing the update
			preparedStatement.executeUpdate();
			
			} catch (Exception e) {
				throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"DELETING CUSTOMER FAILED,CHECK YOUR CUSTOMER ID AGAIN");
			} finally {
			//Closing the resources
				jdbcConnection.closeResources(connection, preparedStatement);
			}

	}

	@Override
	public void updateCustomer(Customer customer) throws ApplicationException  {
		 	//Turning on connections
			java.sql.PreparedStatement preparedStatement = null;
			Connection connection = null;
		
		try {
			//Getting a connection to the MySql Data Base	
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "UPDATE Customer SET customerName=?,customerPassword=? WHERE customerId=? ";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1,customer.getCustomerName());
			preparedStatement.setString(2,customer.getCustomerPassword());
			preparedStatement.setLong(3,customer.getCustomerId());
			//Executing the update
			preparedStatement.executeUpdate();
			} catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"UPDATING CUSTOMER FAILED,CHECK YOUR CUSTOMER ID AGAIN");
			} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
			}
	}
	
	@Override
	public Customer getCustomer(long customerId)  {
			//Turning on connections
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			Customer customer = new Customer();
		try {
			//Getting a connection to the MySql Data Base	
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM Customer WHERE customerId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, customerId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a customer object
			customer = extractCustomerFromResultSet(resultSet);
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object
		return customer;
		}
	
	// This method will extract from the result set the customer object
	private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
		
		Customer customer=new Customer();
		customer.setCustomerId(resultSet.getLong("customerId"));
		customer.setCustomerName(resultSet.getString("customerName"));
		customer.setCustomerPassword(resultSet.getString("customerPassword"));
		
		return customer;
		
	}
	@Override
	public ArrayList<Customer> getAllCustomers()   {
			//Turning on connections
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ArrayList<Customer> allCustomers = new ArrayList<>();
			ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base	
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM customer";
			preparedStatement = connection.prepareStatement(sql);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a allCustomer object
			allCustomers = extractAllCustomersFromResultSet(resultSet);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement, resultSet);
		}
		//Returning that object list
		return allCustomers;
	}
		// The method will add each customer to the array list allCustomers
		//	by using the extractCustomerFromResultSet method
	public ArrayList<Customer> extractAllCustomersFromResultSet(ResultSet resultSet)throws SQLException {
		ArrayList<Customer> allCustomers = new ArrayList<>();
		do {
			Customer customer = new Customer();
			customer = extractCustomerFromResultSet(resultSet);
			allCustomers.add(customer);
		} while (resultSet.next());

		return allCustomers;
	}
	public void updateCustomerOnlyCustomerPassword(Customer customer) throws ApplicationException {
		// Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;	
	try {
		//Getting a connection to the MySql Data Base
		connection = jdbcConnection.getConnection();
		//Creating the SQL query
		String sql = "UPDATE customer SET customerPassword=?WHERE customerId=? ";
	//	Replacing the question marks in the statement with the following data
		preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1,customer.getCustomerPassword());
		preparedStatement.setLong(2,customer.getCustomerId());
		
		preparedStatement.executeUpdate();
		} catch (Exception e) {
		throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"UPDATING CUSTOMER FAILED");
		//Closing the resources
		} finally {
			jdbcConnection.closeResources(connection, preparedStatement);
					}
	
	}

	public boolean loginCustomer(String customerName, String CustomerPassword) {
			//Turning on connections
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base	
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM customer WHERE customerName=? and customerPassword=? ";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1, customerName);
			preparedStatement.setString(2, CustomerPassword);
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
	public long getcustomerIdBycustomerNameAndcustomerPassword(String customerName, String customerPassword) throws ApplicationException  {
		
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
	try {
		//Getting a connection to the MySql Data Base
		connection = jdbcConnection.getConnection();
    	//Creating the SQL query
		String sql = "SELECT customerId FROM customer WHERE customerName=? and customerPassword=?";
		preparedStatement = connection.prepareStatement(sql);
		//Replacing the question marks in the statement with the following data	
		preparedStatement.setString(1,customerName);
		preparedStatement.setString(2, customerPassword);
		//Executing the update
		resultSet = preparedStatement.executeQuery();
		//Checking if the result set returns value, if NO return null
		 while (resultSet.next()) {
			 long customerId=resultSet.getLong("customerId");
			 return customerId;
			
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
	
		public boolean isCustomersExistByName(Customer customer) {
				//Turning on connections
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;	
			try {
				//Getting a connection to the MySql Data Base
				connection = jdbcConnection.getConnection();
				//Creating the SQL query
				String sql = "SELECT * FROM customer WHERE customerName=?";
				preparedStatement = connection.prepareStatement(sql);
				//Replacing the question marks in the statement with the following data	
				preparedStatement.setString(1, customer.getCustomerName());
				//Executing the update
				resultSet = preparedStatement.executeQuery();
				//Checking if the result set returns value, if Yes return true
				if (resultSet.next()) {
					return true;
				}	
				}
//			In the next layer(Controller) we will check if this method return true or false
//			and and we will an exception accordingly
				catch (SQLException e) {
					e.printStackTrace();
					}
					finally {
				//Closing the resources
						jdbcConnection.closeResources(connection, preparedStatement,resultSet);
					}
				return false;	
		
	}

		public boolean isCustomersExist(long customerId) {
			//Turning on connections
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;	
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM customer WHERE customerId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, customerId);
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
		public boolean isCustomersExist(Customer customer) {
			//Turning on connections
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;	
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM customer WHERE customerId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, customer.getCustomerId());
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


		public boolean isCustomerLoggedInDetailsCorrect(String customerName,String customerPassword)   {
			//Turning on connections
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM customer WHERE customerName=? and customerPassword=? ";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data
			preparedStatement.setString(1, customerName);
			preparedStatement.setString(2, customerPassword);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value and if Yes return true 
			
			if (resultSet.next()) {
				return true;
			}	
//			In the next layer(Controller) we will check if this method return true or false
//			and and we will throw an exception accordingly
			}
			catch (SQLException e) {
				e.printStackTrace();
				}
				finally {
			//Closing the resources
					jdbcConnection.closeResources(connection, preparedStatement,resultSet);
				}
			return false;
			
		}

}
