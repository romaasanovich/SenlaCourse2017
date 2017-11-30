package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.bean.Order;

public class OrderRepository extends ARepository {
	private ArrayList<Order> orders;
	private static OrderRepository instance;
	static private int lastID;

	public OrderRepository() {
		orders = new ArrayList<Order>();
	}

	public static OrderRepository getInstance() {
		if (instance == null) {
			instance = new OrderRepository();
		}
		return instance;
	}

	public OrderRepository(ArrayList<Order> ord) {
		orders = ord;
	}

	static public int getLastID() {
		return lastID;
	}

	public ArrayList<Order> getListOfOrders() {
		return orders;
	}

	public Order getOrderById(int id) {
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getId() == id) {
				return orders.get(i);
			}
		}
		return null;
	}

	public void add(Order obj) {
		orders.add(obj);
		lastID = orders.size() - 1;
	}
}
