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
