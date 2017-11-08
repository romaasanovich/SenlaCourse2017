package com.senla.autoservice.bean;

import com.senla.autoservice.api.Entity;
import com.senla.autoservice.repository.OrderList;
import com.senla.autoservice.repository.WorkList;

public class Master extends Entity {
	private boolean isWork;
	private String name;
	private WorkList works;
	private OrderList orders;

	public Master(Integer id, String name, WorkList works, OrderList orders) {
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

	public OrderList getOrders() {
		return orders;
	}

	public void setOrders(OrderList orders) {
		this.orders = orders;
	}

	public WorkList getWorks() {
		return works;
	}

	public void setWorks(WorkList works) {
		this.works = works;
	}

	@Override

	public String toString() {
		String message;
		message = getId() + "-" + getName() + "-" + getIsWork() + "-";
		if (getWorks().getListOfServices() != null) {
			message += getWorks().getListOfServices().length;
			for (Work work : this.works.getListOfServices()) {
				message += work;
			}
		} else {
			message += "null";
		}

		message += "-";

		if (getOrders().getListOfOrders() != null) {
			message += getOrders().getListOfOrders().length;
			for (Order order : this.orders.getListOfOrders()) {
				message += order;
			}
		} else {
			message += "null";
		}

		return message;
	}

}
