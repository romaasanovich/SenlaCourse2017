package com.senla.autoservice.api.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.api.bean.AEntity;
import com.senla.autoservice.api.dao.IOrderDao;

public interface IOrderManager <T extends  AEntity> extends IManager {

	public IOrderDao getOrders();
	public String changeStatusOfOrder(int id, StatusOrder status) throws Exception;
	public ArrayList<T> getSortedOrder(String comp) throws Exception;
	public ArrayList<T> getCurrentOrders(String comp) throws Exception;
	public AEntity getOrderCarriedOutCurrentMaster(int id) throws Exception;
	public String cloneOrder(int id) throws Exception;
	public String add(int idService, int idMaster, int idPlace, StatusOrder status, Date orderDate,
			Date plannedStartDate, Date completionDate) throws Exception;
	public ArrayList<T> getOdersForPeriodOfTime(Date fDate, Date sDate)throws Exception;
	public String getCountOfFreePlacesOnDate(String date) throws NullPointerException, Exception;
}
