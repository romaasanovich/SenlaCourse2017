package com.senla.autoservice.repository;

import java.util.Arrays;

import com.senla.autoservice.bean.Order;
import com.senla.autoservice.utills.ArrayChecker;

public class OrderList {
	private Order[] orders;
	static private int lastID;

	public OrderList(int size) {
		orders = new Order[size];
	}

	public OrderList(Order[] ord) {
		orders = ord;
	}

	static public int getLastID() {
		return lastID;
	}

	public Order[] getListOfOrders() {
		return orders;
	}

	public Order getOrderById(int id) {
		for (int i = 0; i < orders.length; i++) {
			if (orders[i].getId() == id) {
				return orders[i];
			}
		}
		return null;
	}

	public Boolean add(Order obj) {
		if(obj!=null) {
		if (!ArrayChecker.isFreeId(obj.getId(), orders)) {
			return false;
		}
		if (!ArrayChecker.isEnoughtSpace(orders)) {
			orders = Arrays.copyOf(ArrayChecker.resize(orders), orders.length * 2, Order[].class);
		}
		Integer position = ArrayChecker.getFreePosition(orders);
		lastID = position;
		orders[position] = obj;
		return true;
		}
		return false;
	}
}
