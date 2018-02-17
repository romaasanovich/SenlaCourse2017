package com.senla.autoservice.DBConnector;

import java.io.Closeable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBConnector implements Closeable {
	private static final Logger logger = LogManager.getRootLogger();


	private static String url;
	private static String user;
	private static String password;

	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs;

	private static DBConnector instance;

	private DBConnector() {
		connect();
	}

	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		}
		return (DBConnector) instance;
	}

	private void connect() {
		new DBConfig();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = DBConfig.getProp("url");
			password = DBConfig.getProp("password");
			user = DBConfig.getProp("user");
			con = (Connection) DriverManager.getConnection(url, user, password);
			stmt = (Statement) con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e);
		}
	}

	public Connection getConnnection(){
		try {
			if (con.isClosed()) {
				connect();
				return con;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return con;
	}
	
	/*public ResultSet executeQuery(String query) throws SQLException {
		rs = stmt.executeQuery(query);
		return rs;
	}

	public int executeUpdate(String query) throws SQLException {
		int rs = stmt.executeUpdate(query);
		return rs;
	}*/

	@Override
	public void close() {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}

}
