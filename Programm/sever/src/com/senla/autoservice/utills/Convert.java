package com.senla.autoservice.utills;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.senla.autoservice.api.AEntity;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Place;
import com.sun.org.apache.regexp.internal.recompile;

public class Convert {
	public final static StatusOrder fromStrToStatus(String status) {
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

	public final static <T extends AEntity> String[] getEntityStringArray(ArrayList<T> array) {
		String[] response = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			response[i] = array.get(i).toString();
		}
		return response;
	}

	public final static <T extends AEntity> String getEntityStringFromArray(ArrayList<T> array) {
		String message = "";
		for (String str : getEntityStringArray(array)) {
			message += (str + "\n");
		}
		return message;
	}
	
	public final static boolean fromIntToBooleanSQL(String str) {
		if(str.equals("1")) {
			return true;
		}
		else return false;
	}
	
	public final static Place fromStrToPlace(String line) {
		String[] temp = line.split(";");
		int id =Integer.parseInt(temp[0]);
		Place place = new Place(id,temp[1]);
		return place;
	}
	
	public final static Date fromStrToDate(String line) {
		String[] tempDate = line.split(".");
		GregorianCalendar grCal = new GregorianCalendar(Integer.parseInt(tempDate[0]), Integer.parseInt(tempDate[1]),
				Integer.parseInt(tempDate[2]));
		Date date = (Date) (grCal).getTime();
		return date;
	}
	
	public final static String fromDateToStr(Date date) {
		@SuppressWarnings("deprecation")
		String result = date.getYear()+","+ date.getMonth()+","+date.getDay();
		return result;
	}
		
	

}
