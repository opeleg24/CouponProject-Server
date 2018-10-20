package com.omri.coupon.connectionPool;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class jdbcConnection {
	
private static jdbcConnection instance = new jdbcConnection();
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static jdbcConnection getInstance() {
		return instance;
	}

	public static void setInstance(jdbcConnection instance) {
		jdbcConnection.instance = instance;
	}

	public static Connection getConnection() throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project-coupon", "root",
				"R98sc30lb337%");
		
		return connection;
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		closeResources(connection, preparedStatement);
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}


}
