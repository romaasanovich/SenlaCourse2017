package com.senla.autoservice.manager;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;

import com.senla.autoservice.DBConnector.DBConnector;
import com.senla.autoservice.api.IOrderManager;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.csvimporexport.CsvExportImport;
import com.senla.autoservice.dao.OrderDao;

public class OrderManager implements IOrderManager {
	private OrderDao orders;
	private DBConnector con;

	CsvExportImport<Order> importerExporterPlaces = new CsvExportImport<Order>();

	public OrderManager() {
		orders = OrderDao.getInstance();
		con = DBConnector.getInstance();
	}

	public OrderDao getOrders() {
		return orders;
	}

	public String changeStatusOfOrder(int id, StatusOrder status) throws SQLException {
		con.getConnnection().setAutoCommit(false);
		Savepoint savepointOne = con.getConnnection().setSavepoint("SavepointOne");
		try {
			if (!status.equals(StatusOrder.Opened)) {
				MasterManager masterMan = new MasterManager();
				masterMan.changeBusying(id);
				GarageManager gMan = new GarageManager();
				gMan.changeBusying(id);
				return orders.changeStatusOfOrder(id, status, con.getConnnection());

			}
		} catch (SQLException e) {
			System.out.println("SQLException. Executing rollback to savepoint...");
			con.getConnnection().rollback(savepointOne);
		} finally {
			con.getConnnection().setAutoCommit(true);
		}
		return null;
	}

	public ArrayList<Order> getSortedOrder(String comp) throws SQLException {
		return orders.getListOfOrders(comp, con.getConnnection());
	}

	public ArrayList<Order> getCurrentOrders(String comp) throws SQLException {
		return orders.getListOfCurrentOrders(comp, con.getConnnection());
	}

	public Order getOrderCarriedOutCurrentMaster(int id) throws NullPointerException, SQLException {
		return orders.getOrderCurrentMaster(id, con.getConnnection());
	}

	public ArrayList<Order> getOdersForPeriodOfTime(String fDate, String sDate) throws SQLException {
		return orders.getOdersForPeriodOfTime(fDate, sDate, con.getConnnection());
	}

	public String getCountOfFreePlacesOnDate(String date) throws NullPointerException, SQLException {
		String s = "Count:" + String.valueOf(orders.getCountOfFreePlacesOnDate(date, con.getConnnection()));
		return s;
	}

	public String cloneOrder(int id) throws SQLException {
		return orders.cloneOrder(id, con.getConnnection());
	}

	public String add(int idService, int idMaster, int idPlace, StatusOrder status, String orderDate,
			String plannedStartDate, String completionDate) throws SQLException {
		return orders.add(idService, idMaster, idPlace, status, orderDate, plannedStartDate, completionDate,
				con.getConnnection());
	}

	@Override
	public void exportFromCSV() throws Exception {

	}

	@Override
	public void importToCSV() throws Exception {
		orders.importOrdersToCSV(con.getConnnection());
	}

}
