package master;

import autoService.Entity;
import order.Order;
import order.OrderList;

public class Master extends Entity {
	private boolean isWork;
	private String name;
	private OrderList orders;

	public Master(Integer id, String name, OrderList orders) {
		super(id);
		this.orders = orders;
		setName(name);
		if (orders == null) {
			setIsWork(false);
		} else {
			setIsWork(true);
		}
	}

	public Master(String line) {
		super(0);
		String[] temp = line.split(" ");
		setId(Integer.valueOf(temp[0]));
		this.name = temp[1];
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
		this.orders=orders;
	}
	@Override

	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(getName());
		strBuild.append(": ");
		if (getIsWork()) {
			strBuild.append("is work\n");
		} else {
			strBuild.append("is rest\n");
		}
		return strBuild.toString();
	}
}
