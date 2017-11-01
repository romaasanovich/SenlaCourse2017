package order;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import autoService.Constants;
import master.Master;
import master.MastersList;

public class OrderManager {
	private OrderList orders;

	private static final String NO_ANY_ORDERS = "Error. There is no any orders\n";
	private static final String CLOSE_DATE = "First close date is:";
	private static final String	ORDER_WAS_SUCCESFUL_ADDED = "Order was succesful added";

	public OrderManager() {
		orders = new OrderList(Constants.ARRAY_SIZE);
	}
	

	public void setOrderList(OrderList orderList) {
		this.orders = orderList;
	}

	public OrderList getOrders() {
		return orders;
	}

	public String getSortedOrders(MastersList masters, Comparator<Order> comp) {
		Order[] temp = orders.getAllSortedOrder(masters, comp);
		if (temp == null || temp.length == 0) {
			return NO_ANY_ORDERS;
		}
		StringBuilder sb = new StringBuilder();
		for (Order order : temp) {
			if (order == null) {
				break;
			}
			sb.append(order).append("\n");
		}
		if (sb.length() == 0 || sb == null) {
			return NO_ANY_ORDERS;
		}
		return sb.toString();
	}

	public String getOrderCarriedOutCurrentMaster(Master master, MastersList masters) {
		Order temp = orders.getOrderCarriedOutCurrentMaster(master, masters);
		if (temp == null) {
			return NO_ANY_ORDERS;
		} else
			return temp.toString();
	}

	public String getCurrentSortedOrder(Comparator<Order> comp, MastersList masters) {
		Order[] currentOrders = orders.getCurrentOrders(comp, masters);
		if (currentOrders == null || currentOrders.length == 0) {
			return NO_ANY_ORDERS;
		}
		StringBuilder sb = new StringBuilder();
		for (Order order : currentOrders) {
			if (order == null) {
				break;
			}
			sb.append(order).append("\n");
		}
		if (sb.length() == 0 || sb == null) {
			return NO_ANY_ORDERS;
		}
		return sb.toString();
	}

	public String getOdersForPeriodOfTime(StatusOrder status, GregorianCalendar fDate, GregorianCalendar sDate,
			MastersList masters) {
		Order [] temp =orders.getOdersForPeriodOfTime(status, fDate, sDate, masters);
		if (temp == null || temp.length == 0) {
			return NO_ANY_ORDERS;
		}
		StringBuilder sb = new StringBuilder();
		for (Order order : temp) {
			if (order == null) {
				break;
			}
			sb.append(order).append("\n");
		}
		if (sb.length() == 0 || sb == null) {
			return NO_ANY_ORDERS;
		}
		return sb.toString();
	}
	
	public String getFreeDate(MastersList masters) {
		GregorianCalendar cl =orders.getFreeDate(masters);
		return CLOSE_DATE+cl.toString();
	}
	
	public String add(Order order) {
		String message;
		if (orders.add(order)) {
			message = ORDER_WAS_SUCCESFUL_ADDED;
		} else {
			message = Constants.ERROR_THIS_ID_IS_NOT_FREE;
		}
		return message;
	}
}
