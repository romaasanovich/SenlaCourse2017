package com.senla.autoservice.utills;

import java.util.Date;
import java.util.GregorianCalendar;

import com.senla.autoservice.api.Entity;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.repository.GaragePlaces;
import com.senla.autoservice.repository.OrderList;
import com.senla.autoservice.repository.WorkList;

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

	public final static String[] getEntityStringArray(Entity[] array) {
		int countOfRecords = ArrayChecker.getCountOfRecords(array);
		String[] response = new String[countOfRecords];
		for (int i = 0; i < countOfRecords; i++) {
			response[i] = array[i].toString();
		}
		return response;
	}

	public static Master formStringToMaster(String line, GaragePlaces placeList) {
		int pos = 0;
		String[] temp = line.split("-");
		int id = Integer.valueOf(temp[0]);
		String name = temp[1];
		Boolean isWork = Boolean.parseBoolean(temp[2]);
		if (temp[3].equals("null")) {
			WorkList works = new WorkList(null);
			OrderList orders = new OrderList(null);
			Master master = new Master(id, name, works, orders);
			return master;

		} else {
			Work[] works = new Work[Integer.parseInt(temp[3])];
			pos = 4;
			for (int i = 0; i < Integer.parseInt(temp[3]); i++) {
				Work work = new Work();
				work.setId(Integer.parseInt(temp[pos++]));
				work.setNameOfService(temp[pos++]);
				work.setPrice(Double.parseDouble(temp[pos++]));
				works[i] = work;
			}
			WorkList workRep = new WorkList(works);
			if (!temp[4].equals(null)) {
				Order[] orders = new Order[Integer.parseInt(temp[pos++])];
				for (int i = 0; i < orders.length; i++) {
					Order ord = new Order();
					ord.setId(Integer.parseInt(temp[pos++]));
					ord.setService(workRep.getService(Integer.parseInt(temp[pos++])));
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

					orders[i] = ord;
				}

				OrderList orderList = new OrderList(orders);				
				Master master = new Master(id, name, workRep, orderList);
				for(Order order : master.getOrders().getListOfOrders()) {
					order.setMaster(master);
				}
				return master;
			} else {
				OrderList orderList = new OrderList(null);
				Master master = new Master(id, name, workRep, orderList);
				return master;
			}
		}

	}

}
