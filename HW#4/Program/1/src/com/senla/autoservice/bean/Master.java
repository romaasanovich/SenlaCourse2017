package com.senla.autoservice.bean;

import java.util.Arrays;

import com.senla.autoservice.api.Entity;
import com.senla.autoservice.utills.ArrayChecker;

public class Master extends Entity {
	private boolean isWork;
	private String name;
	private Work[] works;
	private Order[] orders;

	public Master(Integer id, String name, Work[] works, Order[] orders) {
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

	public Order[] getOrders() {
		return orders;
	}

	public void setOrders(Order[] orders) {
		this.orders = orders;
	}

	public Work[] getWorks() {
		return works;
	}

	public void setWorks(Work[] works) {
		this.works = works;
	}

	public Boolean addOrder(Order obj) {
		if (!ArrayChecker.isFreeId(obj.getId(),orders )) {
			return false;
		}
		if (!ArrayChecker.isEnoughtSpace(orders)) {
			orders = Arrays.copyOf(ArrayChecker.resize(orders),
					orders.length*2, Order[].class);
		}
		Integer position = ArrayChecker.getFreePosition(orders);
		orders[position] = obj;
		return true;
	}
	
	public Boolean addWork(Work obj) {
		if (!ArrayChecker.isFreeId(obj.getId(),works)) {
			return false;
		}
		if (!ArrayChecker.isEnoughtSpace(works)) {
			works = Arrays.copyOf(ArrayChecker.resize(works),
					works.length*2, Work[].class);
		}
		Integer position = ArrayChecker.getFreePosition(works);
		works[position] = obj;
		return true;
	}
	
	
	@Override

	public String toString() {
		String message;
		message = getId() + "-" + getName() + "-" + getIsWork() + "-";
		if (getWorks() != null) {
			message += getWorks().length;
			for (Work work : this.works) {
				message += work;
			}
		} else {
			message += "null";
		}

		message += "-";

		if (getOrders() != null) {
			message += getOrders().length;
			for (Order order : this.orders) {
				message += order;
			}
		} else {
			message += "null";
		}

		return message;
	}

}
