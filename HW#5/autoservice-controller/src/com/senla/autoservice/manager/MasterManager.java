package com.senla.autoservice.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.repository.MasterRepository;

public class MasterManager {

	private static final String MASTER_WAS_SUCCESFUL_ADDED = "Master was succesful added";

	private MasterRepository masters;

	public MasterManager() {
		masters = MasterRepository.getInstance();
	}

	public MasterRepository getMasters() {
		return masters;
	}

	public ArrayList<Master> getSortedMasters(Comparator<Master> comp) throws NullPointerException{

			Collections.sort(masters.getListOfMasters(), comp);
			return masters.getListOfMasters();
	}

	public Master getMasterCarriedOutCurrentOrder(Order order) throws NullPointerException {
		for (int i = 0; i < masters.getListOfMasters().size(); i++) {
			for (int j = 0; j < masters.getListOfMasters().get(i).getOrders().size(); j++) {
				Master master = masters.getListOfMasters().get(i);
				if (master.getOrders() != null && master.getOrders().get(j).equals(order)
						&& master.getOrders().get(j).getStatus().equals(StatusOrder.Opened)) {
					return master;
				}
			}
		}
		return null;
	}

	public int getCountOfFreePlacesOnDate(Date date) throws NullPointerException{
		int count = 0;
		for (int i = 0; i < masters.getListOfMasters().size(); i++) {
			Master master = masters.getListOfMasters().get(i);
			if (master.getOrders() != null) {
				for (int j = 0; j < master.getOrders().size(); j++) {
					if (!master.getOrders().get(j).getDateOfPlannedStart().after(date)
							&& !master.getOrders().get(j).getDateOfCompletion().before(date)) {
						count++;
					}
				}
			}
		}
		return count;
	}

	public ArrayList<Order> getAllOrders() throws NullPointerException {
		ArrayList<Order> allOrders = new ArrayList<Order>(1);
		int count = 0;
		for (int i = 0; i < masters.getListOfMasters().size(); i++) {
			Master master = masters.getListOfMasters().get(i);
			if (master.getOrders() != null) {
				for (int j = 0; j < masters.getListOfMasters().get(i).getOrders().size(); j++) {
					Order temp = master.getOrders().get(j);
					if (!temp.equals(null)) {
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
		for (int i = 0; i < MasterRepository.getLastID(); i++) {
			
			if (masters.getMasterById(i).getOrders() == null) {
				return date;
			}
		}
		for (;;) {
			for (int i = 0; i < masters.getListOfMasters().size(); i++) {
				for (int j = 0; j < masters.getMasterById(i).getOrders().size(); j++) {
					if (!masters.getMasterById(i).getOrders().get(j).getDateOfCompletion().after(date)
							&& !masters.getMasterById(i).getOrders().get(j).getDateOfCompletion().before(date)) {
						return date;
					}
				}
			}
			cl.add(Calendar.DAY_OF_YEAR, +1);
			date = (Date) (cl).getTime();
		}
	}

	public String add(Master master) {
		String message;
		masters.add(master);
		message = MASTER_WAS_SUCCESFUL_ADDED;
		return message;
	}
}
