package com.senla.autoservice.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.repository.OrderRepository;

public class OrderManager {
	private OrderRepository orders;

	private static final String ORDER_WAS_SUCCESFUL_ADDED = "Order was succesful added";

	public OrderManager() {
		orders = OrderRepository.getInstance();
	}

	public OrderRepository getOrders() {
		return orders;
	}

	public void changeStatusOfOrder(int id, StatusOrder status) {
		orders.getListOfOrders().get(id).setStatus(status);
	}

	public ArrayList<Order> getSortedOrder(Comparator<Order> comp) throws NullPointerException {
		Collections.sort(orders.getListOfOrders(), comp);
		return orders.getListOfOrders();
	}

	public ArrayList<Order> getAllSortedOrder(Comparator<Order> comp) throws NullPointerException {
		if (orders != null) {
			Collections.sort(orders.getListOfOrders(), comp);
			return orders.getListOfOrders();
		}
		return null;
	}

	public ArrayList<Order> getCurrentOrders(Comparator<Order> comp) throws NullPointerException {
		ArrayList<Order> temp = new ArrayList<Order>(1);
		for (Order order : orders.getListOfOrders()) {
			if (orders.getListOfOrders().isEmpty()) {
				break;
			} else if (order != null && order.getStatus() == StatusOrder.Opened) {
				temp.add(order);
			}
		}
		if (!temp.isEmpty()) {
			Collections.sort(temp, comp);
			return temp;
		}
		return null;
	}

	public Order getOrderCarriedOutCurrentMaster(Master master) throws NullPointerException {
		for (int i = 0; i < orders.getListOfOrders().size(); i++) {
			if (orders.getListOfOrders() != null) {
				if (orders.getListOfOrders().get(i).getMaster().equals(master)
						&& orders.getListOfOrders().get(i).getStatus().equals(StatusOrder.Opened)) {
					return orders.getListOfOrders().get(i);
				}
			}
		}
		return null;
	}

	public ArrayList<Order> getOdersForPeriodOfTime(StatusOrder status, Date fDate, Date sDate) throws NullPointerException  {
		ArrayList<Order> temp = new ArrayList<Order>();
		ArrayList<Order> a = orders.getListOfOrders();
		for (int i = 0; i < a.size(); i++) {
			if (!a.get(i).equals(null)) {
				if (a.get(i).getStatus().equals(status) && a.get(i).getDateOfOrder().after(fDate)
						&& a.get(i).getDateOfCompletion().before(sDate)) {
					temp.add(a.get(i));
				}
			}
		}

		return temp;

	}

	public Order cloneOrder(int id)  throws CloneNotSupportedException {
		//Order ord =(Order) orders.findById(id).clone();
		return new Order();
	}

	
	public String add(Order order) {
		String message;
		orders.add(order);
		message = ORDER_WAS_SUCCESFUL_ADDED;
		return message;
	}
}
