package com.senla.autoservice.manager;

import java.util.Arrays;
import java.util.Comparator;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.repository.MasterRepository;

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
