package master;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import autoService.Entity;
import utills.Convert;
import garage.GaragePlaces;
import order.Order;
import order.OrderList;
import order.WorkList;

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

	public Master(String line, GaragePlaces placeList) {
		super(0);
		String[] temp = line.split(" ");
		setId(Integer.valueOf(temp[0]));
		this.name = temp[1];
		this.isWork = Boolean.parseBoolean(temp[2]);
		Order[] orders = new Order[Integer.parseInt(temp[3])];
		int pos = 4;
		for (int i = 0; i < Integer.parseInt(temp[3]); i++) {
			orders[i].setService(WorkList.getService(Integer.parseInt(temp[pos++])));
			orders[i].setPlace(placeList.getPlaceById(Integer.parseInt(temp[pos++])));
			orders[i].setStatus(Convert.fromStrToStatus(temp[pos++]));
			orders[i].setDateOfOrder(new GregorianCalendar());
			GregorianCalendar cl = new GregorianCalendar();
			cl.add(Calendar.DAY_OF_YEAR, Integer.parseInt(temp[pos++]));
			orders[i].setDateOfCompletion(cl);
		}
		this.orders = new OrderList(orders);
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
