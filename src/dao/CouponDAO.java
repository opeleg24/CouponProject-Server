package com.omri.coupon.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.omri.coupon.beans.Coupon;
import com.omri.coupon.connectionPool.jdbcConnection;
import com.omri.coupon.enums.CouponType;
import com.omri.coupon.enums.ErrorType;
import com.omri.coupon.exception.ApplicationException;
import com.omri.coupon.interfaceDao.ICouponDao;



public class CouponDAO implements ICouponDao {

	@Override

	public void createCoupon(Coupon coupon) throws ApplicationException  {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		try{
			//Getting a connection to the MySql Data Base	
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "INSERT INTO Coupon (couponTitle,companyId,startDate,endDate,couponAmount,couponType,message,price,image) VALUES (?,?,?,?,?,?,?,?,?)";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			//coupon id is primary key and auto incremented
			preparedStatement.setString(1, coupon.getCouponTitle());
			preparedStatement.setLong(2, coupon.getCompanyId());
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date StartD=sdf.parse(coupon.getStartDate());
			java.sql.Date sqStartD=new java.sql.Date(StartD.getTime());
			preparedStatement.setDate(3, sqStartD);
			SimpleDateFormat sdf1= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date EndD=sdf1.parse(coupon.getEndDate());
			java.sql.Date sqEndD=new java.sql.Date(EndD.getTime());
			preparedStatement.setDate(4, sqEndD);
			preparedStatement.setLong(5, coupon.getCouponAmount());
			preparedStatement.setString(6,coupon.getCouponType().name());
			preparedStatement.setString(7,coupon.getMessage());
			preparedStatement.setDouble(8, coupon.getPrice());
			preparedStatement.setString(9, coupon.getImage());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"CREATE COUPON FAILED");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}
	//	this function is for the company client
	public void createCouponByComapnyId(Coupon coupon,long companyId) throws ApplicationException  {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;

		try{
			//Getting a connection to the MySql Data Base	
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "INSERT INTO Coupon (couponTitle,companyId,startDate,endDate,couponAmount,couponType,message,price,image) VALUES (?,?,?,?,?,?,?,?,?)";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			//coupon id is primary key and auto incremented
			preparedStatement.setString(1, coupon.getCouponTitle());
			preparedStatement.setLong(2,companyId);
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date StartD=sdf.parse(coupon.getStartDate());
			java.sql.Date sqStartD=new java.sql.Date(StartD.getTime());
			preparedStatement.setDate(3, sqStartD);
			SimpleDateFormat sdf1= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date EndD=sdf1.parse(coupon.getEndDate());
			java.sql.Date sqEndD=new java.sql.Date(EndD.getTime());
			preparedStatement.setDate(4, sqEndD);
			preparedStatement.setLong(5, coupon.getCouponAmount());
			preparedStatement.setString(6,coupon.getCouponType().name());
			preparedStatement.setString(7,coupon.getMessage());
			preparedStatement.setDouble(8, coupon.getPrice());	
			preparedStatement.setString(9, coupon.getImage());	
			


			//Executing the update
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Faild");
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"CREATE COUPON FAILED");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}

	@Override
	//	this function is for the admin client
	public void deleteCoupon(long couponId) {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "DELETE FROM Coupon WHERE couponId=?";;
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,couponId);
			//Executing the update
			preparedStatement.executeUpdate();

		} catch (Exception e) {

		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}
	//	this function is for the company client
	public void deleteCouponByCompanyId(long couponId,long companyId) {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "DELETE FROM Coupon WHERE couponId=? and companyId=?";;
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,couponId);
			preparedStatement.setLong(2,companyId);
			//Executing the update
			preparedStatement.executeUpdate();

		} catch (Exception e) {

		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}

	public void RemoveOldCouponsFromCouponTable() throws ApplicationException	{

		//Creating a current date variable
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date now = Calendar.getInstance().getTime(); 
		java.sql.Date TodayDate=new java.sql.Date(now.getTime());
		
//		String todayDate = df.format(now);
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "DELETE FROM Coupon WHERE endDate < ?";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setDate(1,TodayDate);
			//Executing the update
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"REMOVING COUPONS FAILD");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}
		public void RemoveOldCouponsFromJoinCouponTable() throws ApplicationException	{

			//Creating a current date variable
			SimpleDateFormat dfj = new SimpleDateFormat("dd-MM-yyyy");
			Date today = Calendar.getInstance().getTime(); 
			java.sql.Date TodaysDate=new java.sql.Date(today.getTime());
			
//			String todayDate = df.format(now);
			//Turning on connections
			java.sql.PreparedStatement preparedStatement = null;
			Connection connection = null;
			try {
				//Getting a connection to the MySql Data Base
				connection = jdbcConnection.getConnection();
				//Creating the SQL query
				String sql = "DELETE FROM customer_Coupon WHERE couponId IN(SELECT couponId FROM Coupon WHERE endDate < ?)";
				preparedStatement= connection.prepareStatement(sql);
				//Replacing the question marks in the statement with the following data	
				preparedStatement.setDate(1,TodaysDate);
				//Executing the update
				preparedStatement.executeUpdate();

			} catch (Exception e) {
				throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"REMOVING COUPONS FAILD");
			} finally {
				//Closing the resources
				jdbcConnection.closeResources(connection, preparedStatement);
			}
	}	@Override
	public void updateCoupon(Coupon coupon) throws ApplicationException {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;


		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "UPDATE Coupon SET couponTitle=?,companyId=?,startDate=?,endDate=?,couponAmount=?,couponType=?,message=?,price=?,image=? WHERE couponId=? ";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1,coupon.getCouponTitle());
			preparedStatement.setLong(2,coupon.getCompanyId());
			preparedStatement.setString(3, coupon.getStartDate());
			preparedStatement.setString(4, coupon.getEndDate());
			preparedStatement.setLong(5, coupon.getCouponAmount());
			preparedStatement.setString(6,coupon.getCouponType().name());
			preparedStatement.setString(7,coupon.getMessage());
			preparedStatement.setDouble(8, coupon.getPrice());
			preparedStatement.setString(9, coupon.getImage());
			preparedStatement.setLong(10,coupon.getCouponId());
			//Executing the update
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"UPDATING COMPANY FAILED,CHECK YOUR COUPON ID AGAIN");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}
	//	this is for the company client 
	public void updateCouponByCompanyId(Coupon coupon,long companyId) throws ApplicationException {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;


		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "UPDATE Coupon SET couponTitle=?,startDate=?,endDate=?,couponAmount=?,couponType=?,message=?,price=?,image=? WHERE couponId=? and companyId=?";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1,coupon.getCouponTitle());
			SimpleDateFormat sdf5= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date updateStartD=sdf5.parse(coupon.getStartDate());
			java.sql.Date squpdateStartD=new java.sql.Date(updateStartD.getTime());
			preparedStatement.setDate(2, squpdateStartD);
			SimpleDateFormat sdf6= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date updateEndD=sdf6.parse(coupon.getEndDate());
			java.sql.Date squpdateEndD=new java.sql.Date(updateEndD.getTime());
			preparedStatement.setDate(3, squpdateEndD);
			preparedStatement.setLong(4, coupon.getCouponAmount());
			preparedStatement.setString(5,coupon.getCouponType().name());
			preparedStatement.setString(6,coupon.getMessage());
			preparedStatement.setDouble(7, coupon.getPrice());
			preparedStatement.setString(8, coupon.getImage());
			preparedStatement.setLong(9,coupon.getCouponId());
			preparedStatement.setLong(10,companyId);
			//Executing the update
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"UPDATING COMPANY FAILED,CHECK YOUR COUPON ID AGAIN");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}
	public void updateCouponAmount(long couponId) throws ApplicationException {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "UPDATE Coupon SET couponAmount=couponAmount-1 WHERE couponId=? ";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,couponId);
			//Executing the update
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"UPDATING COMPANY FAILED,CHECK YOUR COUPON ID AGAIN");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}

	@Override
	public Coupon getCoupon(long couponId) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();

		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM Coupon WHERE couponId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, couponId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a coupon object
			coupon = extractCouponFromResultSet(resultSet);

		}
		catch (SQLException e) {
			e.printStackTrace();

		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object
		return coupon;
	}
	// The method will go through the result set and extract the coupon attributes
	private Coupon extractCouponFromResultSet(ResultSet resultSet) throws SQLException {
		Coupon coupon=new Coupon();

		coupon.setCouponId(resultSet.getLong("couponId"));
		coupon.setCouponTitle(resultSet.getString("couponTitle"));
		coupon.setCompanyId(resultSet.getLong("companyId"));
		coupon.setStartDate(resultSet.getString("StartDate"));
		coupon.setEndDate(resultSet.getString("EndDate")); 
		coupon.setCouponAmount(resultSet.getLong("couponAmount"));
		coupon.setCouponType(CouponType.valueOf(resultSet.getString("couponType")));
		coupon.setMessage(resultSet.getString("Message"));
		coupon.setPrice(resultSet.getDouble("Price"));
		coupon.setImage(resultSet.getString("Image"));


		return coupon;
	}

	@Override
	public ArrayList<Coupon> getAllCoupons()  {

		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Coupon> allCoupons = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon";
			preparedStatement = connection.prepareStatement(sql);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a array list object(filled with coupon objects)
			allCoupons = extractAllCouponsFromResultSet(resultSet);
		}catch (SQLException e) {
			e.printStackTrace();

		}

		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement, resultSet);
		}
		return allCoupons;
	}

	public ArrayList<Coupon> extractAllCouponsFromResultSet(ResultSet resultSet)throws SQLException {
		ArrayList<Coupon> allCoupons = new ArrayList<>();
		do {
			Coupon coupon = new Coupon();
			coupon = extractCouponFromResultSet(resultSet);
			allCoupons.add(coupon);
		} while (resultSet.next());

		//Returning that object list
		return allCoupons;

	}
	public ArrayList<Coupon> getAllCouponsByCustomerId(long customerId) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> allCouponsByCustomerId = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE exists (SELECT customerId FROM customer WHERE customerId = ?)";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, customerId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCustomerId method			
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				allCouponsByCustomerId.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object list
		return allCouponsByCustomerId;

	}

	@Override
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerId(long customerId) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> allCouponsByCustomerId = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM Coupon LEFT JOIN customer_Coupon ON "
					+ "coupon.couponId=customer_Coupon.couponId WHERE customer_Coupon.customerId = ?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, customerId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCustomerId method			
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				allCouponsByCustomerId.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object list
		return allCouponsByCustomerId;

	}

	public long getAmountOfPurchasedCouponsByCustomerIdAndCouponId(long customerId,long couponId) throws ApplicationException  {

		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT amountOfPurchasedCoupons FROM customer_coupon WHERE customerId=? AND couponId=?;";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,customerId);
			preparedStatement.setLong(2,couponId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			while (resultSet.next()) {
				long amountOfPurchasedCoupons=resultSet.getLong("amountOfPurchasedCoupons");
				return amountOfPurchasedCoupons;

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


	@Override
	public ArrayList<Coupon> getCouponsByCouponType(String couponType)  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> allCouponsByCouponType = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM Coupon WHERE couponType = ? ";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1, couponType);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a array list object(filled with coupon objects)
			allCouponsByCouponType = extractAllCouponsFromResultSet(resultSet);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object list
		return allCouponsByCouponType;
	}

	@Override
	public ArrayList<Coupon> getCouponsByCompanyId(long companyId)  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> allCouponsByCompanyId = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE companyId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, companyId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a array list object(filled with coupon objects)
			allCouponsByCompanyId = extractAllCouponsFromResultSet(resultSet);
		}
		catch (SQLException e) {
			e.printStackTrace();

		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object list
		return allCouponsByCompanyId;
	}

	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice(double startPrice,double limitPrice,long customerId) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> AllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "select*from customer_Coupon inner join Coupon on coupon.couponId=customer_Coupon.couponId AND price BETWEEN ? AND ? WHERE customerId=?";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setDouble(1, startPrice);
			preparedStatement.setDouble(2, limitPrice);
			preparedStatement.setLong(3, customerId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCustomerIdAndCouponPrice method	
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				AllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object list
		return AllPurchasedCouponsByCustomerIdAndStartPriceAndLimitPrice;

	}
	public ArrayList<Coupon> getAllCouponsByCompanyIdAndStartPriceAndLimitPrice(double startPrice,double limitPrice,long companyId)  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> allCouponsByCompanyIdAndStartPriceAndLimitPrice = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "select*from coupon WHERE price BETWEEN ? AND ? AND companyId=?";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setDouble(1, startPrice);
			preparedStatement.setDouble(2, limitPrice);
			preparedStatement.setDouble(3, companyId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCompanyIdAndCouponTyp method
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				allCouponsByCompanyIdAndStartPriceAndLimitPrice.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object lists
		return allCouponsByCompanyIdAndStartPriceAndLimitPrice;

	}
	public ArrayList<Coupon> getAllCouponsByStartPriceAndLimitPriceByCustomerId(double startPrice,double limitPrice,long customerId)  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> allCouponsByCompanyIdAndStartPriceAndLimitPriceByCustomerId = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT*FROM coupon WHERE price BETWEEN ? AND ? AND exists (SELECT customerId FROM customer WHERE customerId = ?) ";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setDouble(1, startPrice);
			preparedStatement.setDouble(2, limitPrice);
			preparedStatement.setLong(3, customerId);

			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCompanyIdAndCouponTyp method
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				allCouponsByCompanyIdAndStartPriceAndLimitPriceByCustomerId.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object lists
		return allCouponsByCompanyIdAndStartPriceAndLimitPriceByCustomerId;

	}
	public ArrayList<Coupon> getAllCouponsByStartDateStartAndEndDateLimitByCustomerId(String startDateStart,String startDateLimit,long customerId) throws ParseException  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> AllCouponsByStartDateStartAndEndDateLimitByCustomerId  = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "select*from Coupon Where startDate BETWEEN ? AND ? AND exists (SELECT customerId FROM customer WHERE customerId = ?)";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			SimpleDateFormat sd9= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date startDateStartCoupons=sd9.parse(startDateStart);
			java.sql.Date sqstartDateStartCoupons=new java.sql.Date(startDateStartCoupons.getTime());

			preparedStatement.setDate(1, sqstartDateStartCoupons);
			SimpleDateFormat sd10= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date startDateLimitCoupons=sd10.parse(startDateLimit);
			java.sql.Date sqStartDateLimitCoupons=new java.sql.Date(startDateLimitCoupons.getTime());

			preparedStatement.setDate(2, sqStartDateLimitCoupons);
			preparedStatement.setLong(3, customerId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCompanyIdAndCouponTyp method
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				AllCouponsByStartDateStartAndEndDateLimitByCustomerId .add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object lists
		return AllCouponsByStartDateStartAndEndDateLimitByCustomerId ;

	}

	public ArrayList<Coupon> getAllCouponsByEndDateStartAndEndDateLimitByCustomerId(String endDateStart,String endDateLimit,long customerId) throws ParseException  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> AllCouponsByEndDateStartAndEndDateLimitByCustomerId = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "select*from Coupon Where endDate BETWEEN ? AND ? AND exists (SELECT customerId FROM customer WHERE customerId = ?) ";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			SimpleDateFormat ed11= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date limitDateStartCoupons=ed11.parse(endDateStart);
			java.sql.Date sqLimitDateStartCoupons=new java.sql.Date(limitDateStartCoupons.getTime());

			preparedStatement.setDate(1, sqLimitDateStartCoupons);
			SimpleDateFormat sd12= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date LimitDateLimitCoupons=sd12.parse(endDateLimit);
			java.sql.Date sqLimitDateLimitCoupons=new java.sql.Date(LimitDateLimitCoupons.getTime());

			preparedStatement.setDate(2, sqLimitDateLimitCoupons);
			preparedStatement.setLong(3, customerId);


			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCompanyIdAndCouponTyp method
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				AllCouponsByEndDateStartAndEndDateLimitByCustomerId.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object lists
		return AllCouponsByEndDateStartAndEndDateLimitByCustomerId;

	}
	public ArrayList<Coupon> getCouponsByCouponTypeByCustomerId(String couponType,long customerId)  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> allCouponsByCouponTypeByCustomerId = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM Coupon WHERE couponType = ? AND exists (SELECT customerId FROM customer WHERE customerId = ?)  ";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1, couponType);
			preparedStatement.setLong(2, customerId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a array list object(filled with coupon objects)
			allCouponsByCouponTypeByCustomerId = extractAllCouponsFromResultSet(resultSet);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object list
		return allCouponsByCouponTypeByCustomerId;
	}

	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndCouponType(long customerId,String couponType){
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> allCouponsByCustomerIdAndCouponType = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "select*from customer_coupon Right JOIN Coupon on coupon.couponId=customer_coupon.couponId WHERE customerId=? and couponType=?";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, customerId);
			preparedStatement.setString(2, couponType);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCustomerIdAndCouponType method	
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				allCouponsByCustomerIdAndCouponType.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object list
		return allCouponsByCustomerIdAndCouponType;

	}
	public ArrayList<Coupon> getAllCouponsByCompanyIdAndCouponType(long companyId,String couponType){
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> allCouponsByCompanyIdAndCouponType = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "select*from coupon WHERE companyId=? and couponType=?";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, companyId);
			preparedStatement.setString(2, couponType);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCompanyIdAndCouponTyp method	
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				allCouponsByCompanyIdAndCouponType.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object lists
		return allCouponsByCompanyIdAndCouponType;
	}


	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndStartDateStartAndEndDateLimit(String startDateStart,String startDateLimit,long customerId) throws ParseException  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> AllCouponsByCustomerIdAndStartDateStartAndEndDateLimit = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "select*from customer_Coupon inner join Coupon on coupon.couponId=customer_Coupon.couponId AND startDate BETWEEN ? AND ? WHERE customerId=?";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			SimpleDateFormat sdf6= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date startDateStartPurchasedCoupons=sdf6.parse(startDateStart);
			java.sql.Date sqstartDateStartPurchasedCoupons=new java.sql.Date(startDateStartPurchasedCoupons.getTime());

			preparedStatement.setDate(1, sqstartDateStartPurchasedCoupons);
			SimpleDateFormat sdf7= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date startDateLimitPurchasedCoupons=sdf7.parse(startDateLimit);
			java.sql.Date sqStartDateLimitPurchasedCoupons=new java.sql.Date(startDateLimitPurchasedCoupons.getTime());

			preparedStatement.setDate(2, sqStartDateLimitPurchasedCoupons);
			preparedStatement.setLong(3, customerId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCompanyIdAndCouponTyp method
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				AllCouponsByCustomerIdAndStartDateStartAndEndDateLimit.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object lists
		return AllCouponsByCustomerIdAndStartDateStartAndEndDateLimit;

	}
	public ArrayList<Coupon> getAllPurchasedCouponsByCustomerIdAndLimitDateStartAndEndDateLimit(String endDateStart,String endDateLimit,long customerId) throws ParseException  {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Coupon> AllCouponsByCustomerIdAndLimitDateStartAndEndDateLimit = new ArrayList<>();
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "select*from customer_Coupon inner join Coupon on coupon.couponId=customer_Coupon.couponId AND endDate BETWEEN ? AND ? WHERE customerId=?";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			SimpleDateFormat edf7= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date endDateStartPurchasedCoupons=edf7.parse(endDateStart);
			java.sql.Date sqEndDateStartPurchasedCoupons=new java.sql.Date(endDateStartPurchasedCoupons.getTime());

			preparedStatement.setDate(1, sqEndDateStartPurchasedCoupons);

			SimpleDateFormat edf8= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date endDateLimitPurchasedCoupons=edf8.parse(endDateLimit);
			java.sql.Date sqEndDateLimitPurchasedCoupons=new java.sql.Date(endDateLimitPurchasedCoupons.getTime());

			preparedStatement.setDate(2, sqEndDateLimitPurchasedCoupons);
			preparedStatement.setLong(3, customerId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if No return null
			if (!resultSet.next()) {
				return null;
			}
			// The method will add each coupon to the array list allCoupons 
			//	by using the allCouponsByCompanyIdAndCouponTyp method
			do{
				Coupon coupon= new Coupon();
				coupon=extractCouponFromResultSet(resultSet);
				AllCouponsByCustomerIdAndLimitDateStartAndEndDateLimit.add(coupon);
			}while(resultSet.next());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		//Returning that object lists
		return AllCouponsByCustomerIdAndLimitDateStartAndEndDateLimit;

	}
	public ArrayList<Coupon> getAllCouponsByComapnyIdAndStartDateStartAndEndDateLimit(String startDateStart,String startDateLimit,long companyId) throws ParseException  {
	
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Coupon> allCouponsByStartDateLimit = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE startDate BETWEEN ? AND ? AND companyId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data		
			SimpleDateFormat sdf4= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date startDateS=sdf4.parse(startDateStart);
			java.sql.Date sqstartDateS=new java.sql.Date(startDateS.getTime());
			preparedStatement.setDate(1, sqstartDateS);

			SimpleDateFormat sdf5= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date startDateL=sdf5.parse(startDateLimit);
			java.sql.Date sqstartDateL=new java.sql.Date(startDateL.getTime());
			preparedStatement.setDate(2, sqstartDateL);
			preparedStatement.setLong(3, companyId);

			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a array list object(filled with coupon objects)
			allCouponsByStartDateLimit = extractAllCouponsFromResultSet(resultSet);
		}catch (SQLException e) {
			e.printStackTrace();

		}

		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement, resultSet);
		}
		return allCouponsByStartDateLimit;

	}
	
	public ArrayList<Coupon> getAllCouponsByComapnyIdAndEndDateStartAndEndDateLimit(String endDateStart,String endDateLimit,long companyId) throws ParseException  {
	
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Coupon> allCouponsByEndDateLimit = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE endDate BETWEEN ? AND ? AND companyId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data		
			SimpleDateFormat sdf2= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date endDateS=sdf2.parse(endDateStart);
			java.sql.Date sqendDateS=new java.sql.Date(endDateS.getTime());
			preparedStatement.setDate(1, sqendDateS);

			SimpleDateFormat sdf3= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date endDateL=sdf3.parse(endDateLimit);
			java.sql.Date sqendDateL=new java.sql.Date(endDateL.getTime());
			preparedStatement.setDate(2, sqendDateL);
			preparedStatement.setLong(3, companyId);

			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			if (!resultSet.next()) {
				return null;
			}
			//Copying the result data to a array list object(filled with coupon objects)
			allCouponsByEndDateLimit = extractAllCouponsFromResultSet(resultSet);
		}catch (SQLException e) {
			e.printStackTrace();

		}

		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement, resultSet);
		}
		return allCouponsByEndDateLimit;
	}

	public boolean getIsThereMoreThenOneCouponPurchasedByThisCustomer(long customerId,long couponId) throws ApplicationException  {

		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT couponId FROM customer_coupon where couponId=? and customerId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,couponId);
			preparedStatement.setLong(2,customerId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			//		rs.first() && rs.next()
			if (resultSet.first()) {

				return true;
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
		return false;
	}
	public void updateAmountOfPurchasedCouponsByCustomer(long customerId,long couponId) throws ApplicationException {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "UPDATE customer_coupon SET amountOfPurchasedCoupons=amountOfPurchasedCoupons+1 WHERE customerId=? AND couponId=?";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,customerId);
			preparedStatement.setLong(2,couponId);
			//Executing the update
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"UPDATING COMPANY FAILED");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}


	public void purchaseCouponByCustomer(long customerId,long couponId) throws ApplicationException{
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
				
			String sql = "INSERT INTO customer_coupon (customerId,couponId) VALUES (?,?)";	
			//Replacing the question marks in the statement with the following data	
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customerId);
			preparedStatement.setLong(2, couponId);
			//Executing the update
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"PURCHASE COUPON BY CUSTOMER FAILED,CHECK YOUR CUSTOMERID AND COUPON ID");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}

	
	public long getcustomerIdByCouponId(long couponId) throws ApplicationException  {

		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT customerId FROM customer_coupon where couponId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,couponId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if NO return null
			while (resultSet.next()) {
				long customerI=resultSet.getLong("customerId");
				return customerI;

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
	public void deleteCouponPurchaseByCustomer(long customerId,long couponId) throws ApplicationException{
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "DELETE FROM customer_coupon WHERE customerId=? and couponId=?";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, customerId);
			preparedStatement.setLong(2, couponId);
			//Executing the update
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,"DELETE COUPON PURCHASED BY CUSTOMER FAILED,CHECK YOUR CUSTOMERID AND COUPON ID");
		} finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}
	//	admin client
	//	delete process of removing a company by company id
	//	1.remove the coupons that were purchased by this customers that have a company id
	public void deletePurchasedCouponsByCompanyId(long companyId) {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "DELETE FROM customer_coupon WHERE couponId IN (SELECT couponId FROM coupon WHERE companyId=?)";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,companyId);
			//Executing the update
			preparedStatement.executeUpdate();

		} catch (Exception e) {

		} 

		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}
	//	2.remove the  company coupons by company id
	public void deleteCompanyCoupons(long companyId) {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "delete from coupon where companyId=?";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,companyId);
			//Executing the update
			preparedStatement.executeUpdate();

		} catch (Exception e) {

		} 

		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}
	//	3.remove the  company by company id(is in companyDao)


	public boolean isCouponExistByTitle(Coupon coupon) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE couponTitle=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1,coupon.getCouponTitle());
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
	//	admin client
	//	delete process of removing a customer by customer id
	//	1.remove the coupons that were purchased by this customer by customer id
	public void deletePurchasedCouponsByCustomerId(long customerId) {
		//Turning on connections
		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "DELETE FROM customer_coupon WHERE customerId=?";
			preparedStatement= connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1,customerId);
			//Executing the update
			preparedStatement.executeUpdate();

		} catch (Exception e) {

		} 

		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement);
		}
	}

	//	2.remove the  customer by customer id(is in customerDao)


	public boolean isCouponExistByTitle(Coupon coupon,long companyId) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE couponTitle=? and companyId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1,coupon.getCouponTitle());
			preparedStatement.setLong(2,companyId);
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
	//	this function is for the company client
	public boolean isCouponExists(long couponId,long companyId) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE couponId=? and companyId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, couponId);
			preparedStatement.setLong(2, companyId);
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
	//	this function is for the company client
	public boolean isCouponExists(long couponId) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE couponId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, couponId);
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

	public boolean isCouponExists(Coupon coupon) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE couponId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, coupon.getCouponId());
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
	public boolean isCouponExists(Coupon coupon,long companyId) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM coupon WHERE couponId=? AND companyId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, coupon.getCouponId());
			preparedStatement.setLong(2, companyId);
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
	public boolean isCouponAmountOutOfStock(long couponId) 	{
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT *FROM coupon where couponAmount>0 and couponId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, couponId);
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


	public boolean isCouponExpired(long couponId) {
		//Creating a current date variable
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date now = Calendar.getInstance().getTime(); 
		String todayDate = df.format(now);
		java.sql.PreparedStatement preparedStatement = null;
		//Turning on connections
		Connection connection = null;
		//				PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT *FROM coupon where endDate >? and couponId=?";
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setString(1, todayDate);
			preparedStatement.setLong(2, couponId);
			//Executing the update
			resultSet = preparedStatement.executeQuery();
			//Checking if the result set returns value, if Yes return true
			if (resultSet.next()) {
				return true;
			}	
		}
		//				In the next layer(Controller) we will check if this method return true or false
		//				and and we will throw an exception accordingly
		catch (SQLException e) {
			e.printStackTrace();

		}
		finally {
			//Closing the resources
			jdbcConnection.closeResources(connection, preparedStatement,resultSet);
		}
		return false;	
	}




	public boolean isCustomerPurchasedThisCoupon(long customerId,long couponId) {
		//Turning on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//Getting a connection to the MySql Data Base
			connection = jdbcConnection.getConnection();
			//Creating the SQL query
			String sql = "SELECT * FROM customer_coupon WHERE customerId=? and couponId=?";	
			preparedStatement = connection.prepareStatement(sql);
			//Replacing the question marks in the statement with the following data	
			preparedStatement.setLong(1, customerId);
			preparedStatement.setLong(2, couponId);
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

}

