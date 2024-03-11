package com.wipro.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServlet;

public class DatabaseConnection {
	
	private static final Logger logger = LogManager.getLogger(DatabaseConnection.class);
	private static final String HOST = "localhost"; // hostname of database
	private static final String PORT = "3306"; // port for database
	private static final String DB = "lms"; // database name
	private static final String USER = "pushkar"; // username for database
	private static final String PASSWORD = "P@ssword1234"; // password for the database

	private static Connection connection;

	// Private constructor to prevent instantiation
	private DatabaseConnection() {
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (connection == null) {
			synchronized (DatabaseConnection.class) {
				String jdbcUrl = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB;
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcUrl, USER, PASSWORD);
				logger.info("Successfully connected");
			}

		}
		return connection;
	}
	
	/*
	 * public static void main(String[] args) throws ClassNotFoundException,
	 * SQLException { getConnection();
	 * 
	 * }
	 */
}
