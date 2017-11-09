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
			for (int j = 0; j < masters.getMasterById(i).getOrders().length; j++) {
				if (masters.getMasterById(i).getOrders() != null
						&& masters.getMasterById(i).getOrders()[j] == (order)
						&& masters.getMasterById(i).getOrders()[j].getStatus() == (StatusOrder.Opened)) {
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
				for (int j = 0; j < masters.getMasterById(i).getOrders().length; j++) {
					if (!masters.getMasterById(i).getOrders()[j].getDateOfPlannedStart().after(date)
							&& !masters.getMasterById(i).getOrders()[j].getDateOfCompletion()
									.before(date)) {
						count++;
					}
				}
			}
		return count;
	}
	
	public OrderList getAllOrders() {
		OrderList allOrders = new OrderList(1);
		int count =0;
		for (int i = 0; i < masters.getListOfMasters().length; i++) {
			if (masters.getMasterById(i).getOrders() != null) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().length; j++) {
					Order temp = masters.getMasterById(i).getOrders()[j];
					if(!temp.equals(null)) {
					temp.setId(count++);
					allOrders.add(temp);
					}
				}
			}
		}
		return allOrders;
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
				for (int j = 0; j < masters.getMasterById(i).getOrders().length; j++) {
					if (!masters.getMasterById(i).getOrders()[j].getDateOfCompletion().after(date)
							&& !masters.getMasterById(i).getOrders()[j].getDateOfCompletion()
									.before(date)) {
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
