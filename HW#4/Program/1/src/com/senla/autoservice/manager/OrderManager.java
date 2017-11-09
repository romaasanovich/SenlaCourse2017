package com.senla.autoservice.manager;

import java.util.Date;
import java.util.Arrays;
import java.util.Comparator;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.repository.OrderList;
import com.senla.autoservice.utills.ArrayChecker;

public class OrderManager {
	private OrderList orders;

	private static final String ORDER_WAS_SUCCESFUL_ADDED = "Order was succesful added";

	public OrderManager() {
		orders = new OrderList(1);
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

	public OrderList getAllSortedOrder(Comparator<Order> comp) {
		if (orders != null) {
			Arrays.sort(orders.getListOfOrders(), comp);
			return orders;
		}
		return null;
	}

	public OrderList getCurrentOrders(Comparator<Order> comp) {
		OrderList temp = new OrderList(1);
		for (Order order : orders.getListOfOrders()) {
			if (ArrayChecker.getCountOfRecords(orders.getListOfOrders()) == 0) {
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

	public Order getOrderCarriedOutCurrentMaster(Master master) {
		for (int i = 0; i < orders.getListOfOrders().length; i++) {
			if (orders.getOrderById(i).getMaster().equals(master)
					&& orders.getOrderById(i).getStatus().equals(StatusOrder.Opened)) {
				return orders.getOrderById(i);
			}
		}
		return null;
	}

	public OrderList getOdersForPeriodOfTime(StatusOrder status, Date fDate, Date sDate, Comparator<Order> comp) {
		OrderList temp = new OrderList(1);
		Order[] a= orders.getListOfOrders();
		for (int i = 0; i < a.length; i++) {
			if (!a[i].equals(null)) {
				if (a[i].getStatus().equals(status)
						&& a[i].getDateOfOrder().after(fDate)
						&& a[i].getDateOfCompletion().before(sDate)) {
					temp.add(a[i]);
				}
			}
		}
		Arrays.sort(temp.getListOfOrders(), comp);
		return temp;
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
