package com.senla.autoservice.api.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.api.bean.AEntity;
import com.senla.autoservice.api.dao.IOrderDao;

public interface IOrderManager <T extends  AEntity> extends IManager {

	public IOrderDao getOrders();
	public String changeStatusOfOrder(int id, StatusOrder status) throws SQLException;
	public ArrayList<T> getSortedOrder(String comp) throws SQLException;
	public ArrayList<T> getCurrentOrders(String comp) throws SQLException;
	public AEntity getOrderCarriedOutCurrentMaster(int id) throws SQLException;
	public String cloneOrder(int id) throws SQLException;
	public String add(int idService, int idMaster, int idPlace, StatusOrder status, String orderDate,
			String plannedStartDate, String completionDate) throws SQLException;
	public ArrayList<T> getOdersForPeriodOfTime(String fDate, String sDate)throws SQLException;
	public String getCountOfFreePlacesOnDate(String date) throws NullPointerException, SQLException;
}
