package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.api.AEntity;
import com.senla.autoservice.api.ARepository;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.utills.IdGenerator;

public class MasterRepository extends ARepository {
	/**
	 * 
	 */
	private static final String ORDER_WAS_SUCCESFUL_ADDED = "Order was succesful added";
	private static final long serialVersionUID = -5247493779773385231L;
	private static MasterRepository instance;

	static private int lastID;

	private  MasterRepository() {
		repository = new ArrayList<Master>();
	}
	
	public static MasterRepository getInstance() {
		if(instance == null) {
			instance = new MasterRepository();
		} 
		return instance;
	}

	static public int getLastID() {
		return lastID;
	}
	
	public ArrayList<Master> getListOfMasters() {
		return (ArrayList<Master>) repository;
	}

	
	public String addOrderToMaster(int id, Order order) {
		String message;
		order.setId(null);	
		((Master) findById(id)).getOrders().add(order);
		message = ORDER_WAS_SUCCESFUL_ADDED;
		return message;
	}
	
	public void add(AEntity obj) {
		repository.add((Master) obj);
	}
}
