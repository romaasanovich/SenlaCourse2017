package com.senla.autoservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.senla.autoservice.api.ADao;
import com.senla.autoservice.api.AEntity;
import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.api.constants.SqlQuery;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.utills.SQLParser;

public class MasterDao extends ADao {
	/**
	 * 
	 */
	private static final String ORDER_WAS_SUCCESFUL_ADDED = "Order was succesful added";
	private static final long serialVersionUID = -5247493779773385231L;
	private static ADao instance;	
	com.senla.autoservice.DBConnection.DBConnection sqlConnection;

	


	static private int lastID;

	private  MasterDao() {
		sqlConnection = com.senla.autoservice.DBConnection.DBConnection.getInstance();
	}
	
	public static MasterDao getInstance() {
		if(instance == null) {
			instance = new MasterDao();
		} 
		return (MasterDao)instance;
	}

	static public int getLastID() {
		return lastID;
	}
	
	public String getListOfMasters(String comp) throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(SqlQuery.GET_MASTERS+"("+comp+")");
		String result = "";
		while(rs.next()) {
			result+=SQLParser.parseMasterFromSql(rs)+"\n";
		}
		return result;
	}
	
	public String getMasterCarriedOutOrder(int idOrder) throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(SqlQuery.GET_MASTER_ON_ORDER+idOrder);
		String result = "";
		while(rs.next()) {
			result+=SQLParser.parseMasterFromSql(rs)+"\n";
		}
		return result;
	}
	
	public String addOrderToMaster(int id, Order order) {
		String message;
		order.setId(null);	
		((Master) findById(id)).getOrders().add(order);
		message = ORDER_WAS_SUCCESFUL_ADDED;
		return message;
	}
	
	public String add(String name) throws SQLException {
		ResultSet rs =sqlConnection.executeQuery(SqlQuery.GET_ID_MASTER);
		int id = 0;
		while(rs.next()) {
		id = rs.getInt("count(id)");
		}
		sqlConnection.executeUpdate(SqlQuery.ADD_MASTER+"("+String.valueOf(id)+","+name+","+0+")");
		return Constants.SUCCESS;
	}
	
	
	public void add(AEntity obj) {
		repository.add((Master) obj);
	}
}
