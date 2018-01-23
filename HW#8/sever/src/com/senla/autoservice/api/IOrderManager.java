package com.senla.autoservice.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.repository.OrderRepository;

public interface IOrderManager extends IManager {

	public OrderRepository getOrders();
	public void changeStatusOfOrder(int id, StatusOrder status);
	public ArrayList<Order> getSortedOrder(Comparator<Order> comp);
	public ArrayList<Order> getAllSortedOrder(Comparator<Order> comp);
	public ArrayList<Order> getCurrentOrders(Comparator<Order> comp);
	public Order getOrderCarriedOutCurrentMaster(Master master);
	public ArrayList<Order> getOdersForPeriodOfTime(StatusOrder status, Date fDate, Date sDate);
	public Order cloneOrder(int id) throws CloneNotSupportedException;
	public String add(Order order);
	public void importFromCSV() throws Exception;
	public void exportToCSV() throws Exception;
	public String deserializeOrders();
}
