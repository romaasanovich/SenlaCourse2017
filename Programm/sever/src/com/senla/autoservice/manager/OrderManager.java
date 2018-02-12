package com.senla.autoservice.manager;

import java.sql.SQLException;
import java.util.Date;

import com.senla.autoservice.api.IOrderManager;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.api.constants.PropConstants;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.csvimporexport.CsvExportImport;
import com.senla.autoservice.dao.OrderDao;
import com.senla.autoservice.properties.Prop;

public class OrderManager implements IOrderManager {
	private OrderDao orders;

	private static final String ORDER_WAS_SUCCESFUL_ADDED = "Order was succesful added";
	private static final String DESER_DONE = "Deser. Done \n";
	private static final String FILE_NOT_FOUND = "Error. File not found\n";

	CsvExportImport<Order> importerExporterPlaces = new CsvExportImport<Order>();

	public OrderManager() {
		orders = OrderDao.getInstance();
	}

	public OrderDao getOrders() {
		return orders;
	}

	public String changeStatusOfOrder(int id, StatusOrder status) throws SQLException {
		return orders.changeStatusOfOrder(id, status);
	}

	public String getSortedOrder(String comp) throws SQLException {
		return orders.getListOfOrders(comp);
	}

	public String getCurrentOrders(String comp) throws SQLException {
		return orders.getListOfCurrentOrders(comp);
	}

	public String getOrderCarriedOutCurrentMaster(int id) throws NullPointerException, SQLException {
		return orders.getOrderCurrentMaster(id);
	}

	public String getOdersForPeriodOfTime(String fDate, String sDate) throws SQLException {
		return orders.getOdersForPeriodOfTime(fDate, sDate);
	}

	public String getCountOfFreePlacesOnDate(String date) throws NullPointerException, SQLException {
		String s = "Count:" + String.valueOf(orders.getCountOfFreePlacesOnDate(date));
		return s;
	}

	public String cloneOrder(int id) throws SQLException {
		return orders.cloneOrder(id);
	}

	public String add(int idService, int idMaster, int idPlace, StatusOrder status, String orderDate,
			String plannedStartDate, String completionDate) throws SQLException {
		return orders.add(idService, idMaster, idPlace, status, orderDate, plannedStartDate, completionDate);
	}

	@Override
	public void exportToCSV() throws Exception {

		importerExporterPlaces.csvExport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER), orders.getListOfOrders());
	}

	@Override
	public void importFromCSV() throws Exception {
		importerExporterPlaces.csvImport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER), Order.class);
	}

}
