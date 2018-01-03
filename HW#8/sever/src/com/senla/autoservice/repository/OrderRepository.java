package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.api.ARepository;
import com.senla.autoservice.bean.Order;

public class OrderRepository extends ARepository {
	private static OrderRepository instance;
	static private int lastID;

	public OrderRepository() {
		repository = new ArrayList<Order>();
	}

	public static OrderRepository getInstance() {
		if (instance == null) {
			instance = new OrderRepository();
		}
		return instance;
	}

	public OrderRepository(ArrayList<Order> ord) {
		repository = ord;
	}

	static public int getLastID() {
		return lastID;
	}

	public ArrayList<Order> getListOfOrders() {
		return (ArrayList<Order>) repository;
	}

	/*public Order getOrderById(int id) {
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getId() == id) {
				return orders.get(i);
			}
		}
		return null;
	}
*/
	/*public void add(AEntity obj) {
		orders.add((Order) obj);
		lastID = orders.size() - 1;
	}*/
}
