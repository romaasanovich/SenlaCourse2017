package com.senla.autoservice.manager;

import java.util.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.repository.MasterRepository;
import com.senla.autoservice.repository.OrderList;
import com.senla.autoservice.utills.ArrayChecker;

public class OrderManager {
	private OrderList orders;

	private static final String ORDER_WAS_SUCCESFUL_ADDED = "Order was succesful added";

	public OrderManager() {
		orders = new OrderList(Constants.ARRAY_SIZE);
	}

	public void setOrderList(OrderList orderList) {
		this.orders = orderList;
	}

	public OrderList getOrders() {
		return orders;
	}

	public void changeStatusOfOrder(int id, StatusOrder status) {
		orders.getOrderById(id).setStatus(status);
	}

	public OrderList getSortedOrder(Comparator<Order> comp) {
		Arrays.sort(orders.getListOfOrders(), comp);
		return orders;
	}

	public OrderList getAllOrders(MasterRepository masters) {
		OrderList allOrders = new OrderList(orders.getListOfOrders().length);
		for (int i = 0; i < masters.getListOfMasters().length; i++) {
			if (masters.getMasterById(i).getOrders().getListOfOrders() != null) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
					allOrders.add(masters.getMasterById(i).getOrders().getOrderById(j));
				}
			}
		}
		return allOrders;
	}

	public OrderList getAllSortedOrder(MasterRepository masters, Comparator<Order> comp) {
		OrderList temp = getAllOrders(masters);
		if (temp != null) {
			Arrays.sort(temp.getListOfOrders(), comp);
			return temp;
		}
		return null;
	}

	public OrderList getCurrentOrders(Comparator<Order> comp, MasterRepository masters) {
		OrderList allOrders = getAllOrders(masters);
		OrderList temp = new OrderList(1);
		for (Order order : allOrders.getListOfOrders()) {
			if (ArrayChecker.getCountOfRecords(allOrders.getListOfOrders()) == 0) {
				break;
			} else if (order != null && order.getStatus().equals(StatusOrder.Opened)) {
				temp.add(order);
			}
		}
		if (ArrayChecker.getCountOfRecords(temp.getListOfOrders()) != 0) {
			Arrays.sort(temp.getListOfOrders(), comp);
			return temp;
		}
		return null;
	}

	public Order getOrderCarriedOutCurrentMaster(Master master, MasterRepository masters) {
		for (int i = 0; i < masters.getListOfMasters().length; i++) {
			if (masters.getMasterById(i).equals(master)) {
				return masters.getMasterById(i).getOrders().getOrderById(OrderList.getLastID());
			}
		}
		return null;
	}

	public OrderList getOdersForPeriodOfTime(StatusOrder status, Date fDate, Date sDate, MasterRepository masters,
			Comparator<Order> comp) {
		OrderList temp = new OrderList(1);
		for (int i = 0; i < masters.getListOfMasters().length; i++)
			if (masters.getMasterById(i).getOrders() != null) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
					if (masters.getMasterById(i).getOrders().getOrderById(j).getStatus() == status
							&& masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().after(fDate)
							&& masters.getMasterById(i).getOrders().getOrderById(j).getDateOfCompletion().before(sDate)) {
						temp.add(masters.getMasterById(i).getOrders().getOrderById(j));
					}
				}
			}
		Arrays.sort(temp.getListOfOrders(), comp);
		return temp;
	}

	public Date getFreeDate(MasterRepository masters) {
		GregorianCalendar cl = new GregorianCalendar();
		Date date = (Date) (cl).getTime();
		for (int i = 0; i < masters.getListOfMasters().length; i++) {
			if (masters.getMasterById(i).getOrders() == null) {
				return date;
			}
		}
		for (;;) {
			for (int i = 0; i < masters.getListOfMasters().length; i++) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
					if (!masters.getMasterById(i).getOrders().getOrderById(j).getDateOfCompletion().after(date)
							&& !masters.getMasterById(i).getOrders().getOrderById(j).getDateOfCompletion().before(date)) {
						return date;
					}
				}
			}
			cl.add(Calendar.DATE, +1);
			date = (Date) (cl).getTime();
		}
	}

	public String add(Order order) {
		String message;
		if (orders.add(order)) {
			message = ORDER_WAS_SUCCESFUL_ADDED;
		} else {
			message = Constants.ERROR_THIS_ID_IS_NOT_FREE;
		}
		return message;
	}
}
