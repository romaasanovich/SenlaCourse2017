package com.senla.autoservice.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.senla.autoservice.api.ADao;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.api.constants.SqlQuery;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.csvimporexport.CsvExportImport;
import com.senla.autoservice.properties.Prop;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.SQLParser;

public class OrderDao extends ADao {
	private static ADao instance;
	static private int lastID;
	CsvExportImport<Order> importExport;
	
	private OrderDao() {
	}

	public static OrderDao getInstance() {
		if (instance == null) {
			instance = new OrderDao();
		}
		return (OrderDao) instance;
	}

	public ArrayList<Order> getListOfOrders(String comp,Connection con) throws SQLException {
		ArrayList<Order> orders = new ArrayList<Order>();
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_ORDER_ALL_INFO+ "(" + comp + ")");
		while (rs.next()) {
			orders.add(parseOrderFromSql(rs));
		}
		return orders;
	}

	public ArrayList<Order> getListOfCurrentOrders(String comp,Connection con) throws SQLException {
		ArrayList<Order> orders = new ArrayList<Order>();
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_CUR_ORDERS + "(" + comp + ")");
		while (rs.next()) {
			orders.add(parseOrderFromSql(rs));
		}
		return orders;

	}

	public Order getOrderCurrentMaster(int id, Connection con) throws SQLException {
		Order order = null;
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_ORDER_CUR_MASTER + id);
		while (rs.next()) {
			order =parseOrderFromSql(rs);
		}
		return order;
	}

	public ArrayList<Order> getOdersForPeriodOfTime(String fDate, String sDate,Connection con) throws SQLException {
		ArrayList<Order> orders = new ArrayList<Order>();
		PreparedStatement ps= (PreparedStatement) con.prepareStatement(SqlQuery.ADD_PLACE);
		ps.setString(1,fDate);
		ps.setString(2,sDate);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			orders.add(parseOrderFromSql(rs));
		}
		return orders;
	}	
	
	public int getCountOfFreePlacesOnDate(String date,Connection con) throws SQLException {
		int result;
		ResultSet rs = con.createStatement().executeQuery(String.format(SqlQuery.GET_COUNT_PLACE));
		result = rs.getInt(1);
		rs = con.createStatement().executeQuery(String.format(SqlQuery.GET_COUNT_OF_ORDERS_ON_DATE, date));
		result = result - rs.getInt(1);
		return result;
	}

	public String add(int idService, int idMaster, int idPlace, StatusOrder status, String orderDate,
			String plannedStartDate, String completionDate,Connection con) throws SQLException {
		ResultSet rs = con.createStatement().executeQuery(SqlQuery.GET_ID_ORDER);
		rs.next();
		int id = rs.getInt(1);
		PreparedStatement ps= (PreparedStatement) con.prepareStatement(SqlQuery.ADD_PLACE);
		ps.setInt(1,id);
		ps.setInt(2,idService);
		ps.setInt(3,idMaster);
		ps.setInt(4,idPlace);
		ps.setString(5,status.toString());
		ps.setString(6, orderDate);
		ps.setString(7, plannedStartDate);
		ps.setString(8, completionDate);
		int res = ps.executeUpdate();
		return Constants.SUCCESS;
	}

	public String changeStatusOfOrder(int id, StatusOrder status,Connection con) throws SQLException {
		PreparedStatement ps= (PreparedStatement) con.prepareStatement(SqlQuery.CHANGE_STATUS);
		ps.setString(0,status.toString());
		ps.setInt(1,id);
		ps.executeUpdate();
		String result = Constants.SUCCESS;
		return result;
		
	}
	
	public String cloneOrder(int id,Connection con) throws SQLException {
		ResultSet rs = con.createStatement().executeQuery(String.format(SqlQuery.GET_ORDER_BY_ID +id));
		String result = "";
		ArrayList<String> data = new ArrayList<>();
		data = SQLParser.parseOrderFromSqlToString(rs);
		result = add(Integer.parseInt(data.get(1)), Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)), Convert.fromStrToStatus(data.get(4)), data.get(5), data.get(6), data.get(7),con);
		return result;
	}
	
	private static Order parseOrderFromSql(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		Work work = new Work(rs.getInt("idService"), rs.getString("nameOfService"), rs.getDouble("price"),rs.getInt("idMaster"));
		Master master = new Master(rs.getInt("idMaster"), rs.getString("nameMaster"),
				Convert.fromIntToBooleanSQL(rs.getString("isWork")));
		Place place = new Place(rs.getInt("idPlace"), rs.getString("placeName"),
				Convert.fromIntToBooleanSQL(rs.getString("isBusy")));
		StatusOrder status = Convert.fromStrToStatus(rs.getString("status"));
		Date dateOfOrder = rs.getDate("orderDate");
		Date dateOfPlannedStart = rs.getDate("plannedStartDate");
		Date dateOfCompletion = rs.getDate("completionDate");
		Order order = new Order(id, master, work, place, status, dateOfOrder, dateOfCompletion, dateOfPlannedStart);
		return order;
	}

	
	public void importOrdersToCSV(Connection con) throws SQLException, IOException {
		ArrayList<Order> masters = getListOfOrders("id", con);
		String path =Prop.getProp("orderCsvPath");
		importExport.importToCsv(masters, path);
	}

	
}
