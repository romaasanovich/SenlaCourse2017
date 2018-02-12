package com.senla.autoservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.senla.autoservice.api.ADao;
import com.senla.autoservice.api.AEntity;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.api.constants.SqlQuery;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.SQLParser;

public class OrderDao extends ADao {
	private static ADao instance;
	static private int lastID;
	com.senla.autoservice.DBConnection.DBConnection sqlConnection;

	private OrderDao() {
		sqlConnection = com.senla.autoservice.DBConnection.DBConnection.getInstance();
	}

	public static OrderDao getInstance() {
		if (instance == null) {
			instance = new OrderDao();
		}
		return (OrderDao) instance;
	}

	public String getListOfOrders(String comp) throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(SqlQuery.GET_ORDERS + "(" + comp + ")");
		String result = "";
		while (rs.next()) {
			result += SQLParser.parseOrderFromSql(rs) + "\n";
		}
		return result;
	}

	public String getListOfCurrentOrders(String comp) throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(SqlQuery.GET_CUR_ORDERS + "(" + comp + ")");
		String result = "";
		while (rs.next()) {
			result += SQLParser.parseOrderFromSql(rs) + "\n";
		}
		return result;
	}

	public String getOrderCurrentMaster(int id) throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(SqlQuery.GET_ORDER_CUR_MASTER + id);
		String result = "";
		while (rs.next()) {
			result += SQLParser.parseOrderFromSql(rs) + "\n";
		}
		return result;
	}

	public String getOdersForPeriodOfTime(String fDate, String sDate) throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(String.format(SqlQuery.GET_ORD_FOR_PER_TIME, fDate, sDate));
		String result = "";
		while (rs.next()) {
			result += SQLParser.parseOrderFromSql(rs) + "\n";
		}
		return result;
	}
	
	public int getCountOfFreePlacesOnDate(String date) throws SQLException {
		int result;
		ResultSet rs = sqlConnection.executeQuery(String.format(SqlQuery.GET_COUNT_PLACE));
		result = rs.getInt(1);
		rs = sqlConnection.executeQuery(String.format(SqlQuery.GET_COUNT_OF_ORDERS_ON_DATE, date));
		result = result - rs.getInt(1);
		return result;
	}

	public String add(int idService, int idMaster, int idPlace, StatusOrder status, String orderDate,
			String plannedStartDate, String completionDate) throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(SqlQuery.GET_ID_ORDER);
		int id = rs.getInt(1);
		rs = sqlConnection.executeQuery(SqlQuery.ADD_ORDER + "(" + id + "," + idService + "," + idMaster + "," + idPlace
				+ "," + status.toString() + "," + orderDate + "," + plannedStartDate + "," + completionDate + ")");
		return Constants.SUCCESS;
	}

	public String changeStatusOfOrder(int id, StatusOrder status) throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(String.format(SqlQuery.CHANGE_STATUS, status.toString(), id));
		String result = Constants.SUCCESS;
		return result;
		
	}
	
	public String cloneOrder(int id) throws SQLException {
		ResultSet rs = sqlConnection.executeQuery(String.format(SqlQuery.GET_ORDER_BY_ID +id));
		String result = "";
		ArrayList<String> data = new ArrayList<>();
		data = SQLParser.parseOrderFromSqlToString(rs);
		result = add(Integer.parseInt(data.get(1)), Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)), Convert.fromStrToStatus(data.get(4)), data.get(5), data.get(6), data.get(7));
		return result;
	}
	
}
