package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.api.AEntity;
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


	public void add(AEntity obj) {
		repository.add((Order) obj);
		lastID = repository.size() - 1;
	}
}
