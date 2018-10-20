package com.omri.coupon.test;

import java.sql.*;

import com.omri.coupon.connectionPool.jdbcConnection;



public class Tester {

	public static void main(String[] args) {
		
			try {
				//Getting a connection to the MySql Data Base
				Connection connection=jdbcConnection.getConnection();
				
				//Creating a statement
				Statement myStat=connection.createStatement();
				
				//Creating the SQL query
				ResultSet myRs=myStat.executeQuery("select* from coupon");
		
				//Process the results set
				while(myRs.next()){
					
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

	}

