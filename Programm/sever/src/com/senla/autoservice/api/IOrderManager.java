package com.senla.autoservice.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.dao.OrderDao;

public interface IOrderManager extends IManager {

	public OrderDao getOrders();
	public String changeStatusOfOrder(int id, StatusOrder status) throws SQLException;
	public String getSortedOrder(String comp) throws SQLException;
	public String getCurrentOrders(String comp) throws SQLException;
	public String getOrderCarriedOutCurrentMaster(int id) throws SQLException;
	public String cloneOrder(int id) throws SQLException;
	public String add(int idService, int idMaster, int idPlace, StatusOrder status, String orderDate,
			String plannedStartDate, String completionDate) throws SQLException;
	public void importFromCSV() throws Exception;
	public void exportToCSV() throws Exception;
	public String getOdersForPeriodOfTime(String fDate, String sDate)throws SQLException;
	public String getCountOfFreePlacesOnDate(String date) throws NullPointerException, SQLException;
}
