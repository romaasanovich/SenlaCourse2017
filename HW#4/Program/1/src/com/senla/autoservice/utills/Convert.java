package com.senla.autoservice.utills;

import com.senla.autoservice.api.Entity;
import com.senla.autoservice.api.StatusOrder;

public class Convert {
	public  final static  StatusOrder fromStrToStatus(String status) {
		if (status.equals("Broned")) {
			return StatusOrder.Broned;
		} else if (status.equals("Opened")) {
			return StatusOrder.Opened;
		} else if (status.equals("Canceled")) {
			return StatusOrder.Canceled;
		} else if (status.equals("Closed")) {
			return StatusOrder.Closed;
		} else if (status.equals("Deleted")) {
			return StatusOrder.Deleted;
		} else
			return null;
	}
	
	public final static String[] getEntityStringArray(Entity[] array) {
		int countOfRecords = ArrayChecker.getCountOfRecords(array);
		String[] response = new String[countOfRecords];
		for (int i = 0; i < countOfRecords; i++) {
			response[i] = array[i].toString();
		}
		return response;
	}
	
	
}
