package com.senla.autoservice.manager;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.repository.MasterRepository;
import com.senla.autoservice.repository.OrderList;
import com.senla.autoservice.utills.ArrayChecker;

public class MasterManager {

	private static final String MASTER_WAS_SUCCESFUL_ADDED = "Master was succesful added";

	private MasterRepository masters;

	public MasterManager() {
		masters = new MasterRepository(Constants.ARRAY_SIZE);
	}

	public void setMasters(MasterRepository masters) {
		this.masters = masters;
	}

	public MasterRepository getMasters() {
		return masters;
	}

	public MasterRepository getSortedMasters(Comparator<Master> comp) {
		Arrays.sort(masters.getListOfMasters(), comp);
		return masters;
	}

	public Master getMasterCarriedOutCurrentOrder(Order order) {
		for (int i = 0; i < masters.getListOfMasters().length; i++) {
			for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
				if (masters.getMasterById(i).getOrders() != null
						&& masters.getMasterById(i).getOrders().getOrderById(j) == (order)
						&& masters.getMasterById(i).getOrders().getOrderById(j).getStatus() == (StatusOrder.Opened)) {
					return masters.getMasterById(i);
				}
			}
		}
		return null;
	}

	public int getCountOfFreePlacesOnDate(Date date) {
		int count = 0;
		for (int i = 0; i < masters.getListOfMasters().length; i++)
			if (masters.getMasterById(i).getOrders() != null) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
					if (!masters.getMasterById(i).getOrders().getOrderById(j).getDateOfPlannedStart().after(date)
							&& !masters.getMasterById(i).getOrders().getOrderById(j).getDateOfCompletion()
									.before(date)) {
						count++;
					}
				}
			}
		return count;
	}
	
	public OrderList getAllOrders() {
		OrderList allOrders = new OrderList(1);
		for (int i = 0; i < masters.getListOfMasters().length; i++) {
			if (masters.getMasterById(i).getOrders().getListOfOrders() != null) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
					allOrders.add(masters.getMasterById(i).getOrders().getOrderById(j));
				}
			}
		}
		return allOrders;
	}

	public OrderList getAllSortedOrder(Comparator<Order> comp) {
		OrderList temp = getAllOrders();
		if (temp != null) {
			Arrays.sort(temp.getListOfOrders(), comp);
			return temp;
		}
		return null;
	}

	public OrderList getCurrentOrders(Comparator<Order> comp) {
		OrderList allOrders = getAllOrders();
		OrderList temp = new OrderList(1);
		for (Order order : allOrders.getListOfOrders()) {
			if (ArrayChecker.getCountOfRecords(allOrders.getListOfOrders()) == 0) {
				break;
			} else if (order != null && order.getStatus().equals(StatusOrder.Opened)) {
				temp.add(order);
			}
		}
		if (ArrayChecker.getCountOfRecords(temp.getListOfOrders()) != 0) {
			Arrays.sort(temp.getListOfOrders(), comp);
			return temp;
		}
		return null;
	}

	public Order getOrderCarriedOutCurrentMaster(Master master) {
		for (int i = 0; i < masters.getListOfMasters().length; i++) {
			if (masters.getMasterById(i).equals(master)) {
				return masters.getMasterById(i).getOrders().getOrderById(OrderList.getLastID());
			}
		}
		return null;
	}

	public OrderList getOdersForPeriodOfTime(StatusOrder status, Date fDate, Date sDate, Comparator<Order> comp) {
		OrderList temp = new OrderList(1);
		for (int i = 0; i < masters.getListOfMasters().length; i++)
			if (masters.getMasterById(i).getOrders() != null) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
					if (masters.getMasterById(i).getOrders().getOrderById(j).getStatus() == status
							&& masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().after(fDate)
							&& masters.getMasterById(i).getOrders().getOrderById(j).getDateOfCompletion().before(sDate)) {
						temp.add(masters.getMasterById(i).getOrders().getOrderById(j));
					}
				}
			}
		Arrays.sort(temp.getListOfOrders(), comp);
		return temp;
	}

	public Date getFreeDate() {
		GregorianCalendar cl = new GregorianCalendar();
		Date date = (Date) (cl).getTime();
		for (int i = 0; i < masters.getListOfMasters().length; i++) {
			if (masters.getMasterById(i).getOrders() == null) {
				return date;
			}
		}
		for (;;) {
			for (int i = 0; i < masters.getListOfMasters().length; i++) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
					if (!masters.getMasterById(i).getOrders().getOrderById(j).getDateOfCompletion().after(date)
							&& !masters.getMasterById(i).getOrders().getOrderById(j).getDateOfCompletion().before(date)) {
						return date;
					}
				}
			}
			cl.add(Calendar.DATE, +1);
			date = (Date) (cl).getTime();
		}
	}

	

	public String add(Master master) {
		String message;
		if (masters.add(master)) {
			message = MASTER_WAS_SUCCESFUL_ADDED;
		} else {
			message = Constants.ERROR_THIS_ID_IS_NOT_FREE;
		}
		return message;
	}
}
