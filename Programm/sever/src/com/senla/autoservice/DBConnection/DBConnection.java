package com.senla.autoservice.DBConnection;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.facade.FacadeMessage;

public class DBConnection implements Closeable {
	private static final Logger logger = Logger.getLogger(DBConnection.class.getName());
	
	// JDBC URL, username and password of MySQL server
	private static String url; 
	private static String user;
	private static String password; 

	// JDBC variables for opening and managing connection
	private static  Connection con;
	private static Statement stmt;
	private static ResultSet rs;

	private static DBConnection instance;
	
	private DBConnection() {
		try {
			new DBConfig();
			 Class.forName("com.mysql.jdbc.Driver");
			url = DBConfig.getProp("url");
			password= DBConfig.getProp("password");
			user =DBConfig.getProp("user");
			con = (Connection) DriverManager.getConnection(url, user, password);

			stmt = (Statement) con.createStatement();

		} catch (SQLException e) {
			logger.error(Constants.LOGGER_MSG, e);
		} catch (ClassNotFoundException e) {
			logger.error(Constants.LOGGER_MSG, e);
		}
	}

	public Connection getCon() {
		return this.con;
	}
	
	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return (DBConnection) instance;
	}
	
	public ResultSet executeQuery(String query) throws SQLException{
		 rs = stmt.executeQuery(query);
		 return rs;	
	}
	
	public int executeUpdate(String query) throws SQLException {
		 int rs = stmt.executeUpdate(query);
		 return rs;	
	}
@Override

public void close() {
	
}



	
}
