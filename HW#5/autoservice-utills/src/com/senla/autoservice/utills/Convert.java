package com.senla.autoservice.utills;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.senla.autoservice.api.Entity;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.repository.GarageRepository;

public class Convert {
	private final static StatusOrder fromStrToStatus(String status) {
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

	public final static <T extends Entity> String[] getEntityStringArray(ArrayList<T> array) {
		int countOfRecords = ArrayChecker.getCountOfRecords(array);
		String[] response = new String[countOfRecords];
		for (int i = 0; i < countOfRecords; i++) {
			response[i] = array.get(i).toString();
		}
		return response;
	}

	public final static <T extends Entity> String getEntityStringFromArray(ArrayList<T> array) {
		String message = "";
		for (String str : getEntityStringArray(array)) {
			message += (str + "\n");
		}
		return message;
	}
	
	
	public final static Place fromStrToPlace(String line) {
		String[] temp = line.split("-");
		Place place = new Place(Integer.parseInt(temp[0]),temp[1]);
		return place;
	}
	
	public final static Date fromStrToDate(String line) {
		String[] tempDate = line.split(",");
		GregorianCalendar grCal = new GregorianCalendar(Integer.parseInt(tempDate[0]), Integer.parseInt(tempDate[1]),
				Integer.parseInt(tempDate[2]));
		Date date = (Date) (grCal).getTime();
		return date;
	}
	
	public static Master formStringToMaster(String line, GarageRepository placeList) {
		int pos = 0;
		String[] temp = line.split("-");
		int id = Integer.valueOf(temp[0]);
		String name = temp[1];
		if (temp[3].equals("null")) {
			Master master = new Master(id, name, null, null);
			return master;

		} else {
			ArrayList<Work> works = new ArrayList<Work>(Integer.parseInt(temp[3]));
			pos = 4;
			for (int i = 0; i < Integer.parseInt(temp[3]); i++) {
				Work work = new Work();
				work.setId(Integer.parseInt(temp[pos++]));
				work.setNameOfService(temp[pos++]);
				work.setPrice(Double.parseDouble(temp[pos++]));
				works.add(work);
			}
			if (temp[4] != "null") {
				int size = Integer.parseInt(temp[pos++]);
				ArrayList<Order> orders = new ArrayList<Order>(size);
				// pos++;
				for (int i = 0; i < size; i++) {
					Order ord = new Order();
					ord.setId(Integer.parseInt(temp[pos++]));
					ord.setService(works.get(Integer.parseInt(temp[pos++])));
					ord.setPlace(placeList.getPlaceById(Integer.parseInt(temp[pos++])));
					ord.setStatus(Convert.fromStrToStatus(temp[pos++]));
					String[] tempDate = temp[pos++].split(",");
					GregorianCalendar grCal = new GregorianCalendar(Integer.parseInt(tempDate[0]),
							Integer.parseInt(tempDate[1]), Integer.parseInt(tempDate[2]));
					Date date = (Date) (grCal).getTime();
					ord.setDateOfOrder(date);
					tempDate = temp[pos++].split(",");
					GregorianCalendar grCalDateStart = new GregorianCalendar(Integer.parseInt(tempDate[0]),
							Integer.parseInt(tempDate[1]), Integer.parseInt(tempDate[2]));
					ord.setDateOfPlannedStart((grCalDateStart).getTime());

					tempDate = temp[pos++].split(",");
					GregorianCalendar grCalDateCompl = new GregorianCalendar(Integer.parseInt(tempDate[0]),
							Integer.parseInt(tempDate[1]), Integer.parseInt(tempDate[2]));
					ord.setDateOfCompletion((grCalDateCompl).getTime());
					orders.add(ord);
				}
				Master master = new Master(id, name, works, orders);
				for (Order order : master.getOrders()) {
					order.setMaster(master);
				}
				return master;
			} else {
				Master master = new Master(id, name, works, null);
				return master;
			}
		}

	}

}
