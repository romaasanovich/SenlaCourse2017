package com.senla.autoservice.bean;

import java.util.ArrayList;

import com.senla.autoservice.api.Entity;

public class Master extends Entity {
	private boolean isWork;
	private String name;
	private ArrayList<Work> works;
	private ArrayList<Order> orders;

	public Master(Integer id, String name, ArrayList<Work> works, ArrayList<Order> orders) {
		super(id);
		this.orders = orders;
		this.works = works;
		setName(name);
		if (orders == null) {
			setIsWork(false);
		} else {
			setIsWork(true);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIsWork(boolean isWork) {
		this.isWork = isWork;
	}

	public boolean getIsWork() {
		return isWork;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Work> getWorks() {
		return works;
	}

	public void setWorks(ArrayList<Work> works) {
		this.works = works;
	}

	public Boolean addOrder(Order obj){
		if(obj !=null) {
		orders.add(obj);
		return true;
		}
		else return false;
	}
	
	public Boolean addWork(Work obj)  {
		if(obj !=null) {
		works.add(obj);
		return true;
		}
		else return false;

	}
	
	@Override

	public String toString() {
		String message;
		message = getId() + "-" + getName() + "-" + getIsWork() + "-";
		if (getWorks() != null) {
			message += getWorks().size();
			for (Work work : this.works) {
				message += work;
			}
		} else {
			message += "null";
		}

		message += "-";

		if (getOrders() != null) {
			message += getOrders().size();
			for (Order order : this.orders) {
				message += order;
			}
		} else {
			message += "null";
		}

		return message;
	}

}
