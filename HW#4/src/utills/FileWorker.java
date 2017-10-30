package utills;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.*;
import com.danco.training.TextFileWorker;

import garage.Place;
import master.Master;
import order.Order;
import order.OrderList;
import order.StatusOrder;
import order.Work;
import order.WorkList;

public final  class FileWorker {

	static public Place[] readPlaces(String str) {

		TextFileWorker filePlaces = new TextFileWorker(str);
		String[] s = filePlaces.readFromFile();
		Place[] place = new Place[s.length];
		for (int i = 0; i < s.length; i++) {
			Place temp = new Place(s[i]);
			place[i] = temp;
		}
		return place;
	}

	static public Work[] readWorks(String str) {
		TextFileWorker fileMasters = new TextFileWorker(str);
		String[] s = fileMasters.readFromFile();
		Work[] works = new Work[s.length];
		for (int i = 0; i < s.length; i++) {
			String del = "-";
			String[] temp = s[i].split(del);
			Work tempObj = new Work(temp[0], Double.parseDouble(temp[1]));
			works[i] = tempObj;
		}
		return works;
	}

	
	static GregorianCalendar dateToString(String f) {

		DateFormat df = new SimpleDateFormat("dd MM yyyy");
		Date date = null;
		try {
			date = df.parse(f);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();// new GregorianCalendar();
		cal.setTime(date);
		return (GregorianCalendar) cal;

	}

	static public Master[] readMaster(WorkList works, String str) {
		TextFileWorker fileMasters = new TextFileWorker(str);
		String[] s = fileMasters.readFromFile();
		Master[] masters = new Master[s.length];
		for (int i = 0; i < s.length; i++) {
			Order[] ordr = new Order[1];
			Master tempMaster = new Master();
			String[] temp = s[i].split("-");
			tempMaster.setName(temp[0]);
			if (!temp[1].equals("null")) {
				tempMaster.setIsWork(true);
				for (int j = 1, k = 0; j < temp.length; j++) {
					Order ord = new Order();
					ord.setService(works.getService(Integer.parseInt(temp[j])));
					j++;
					ord.setStatus(fromStrToStatus(temp[j]));
					j++;
					ord.setDateOfOrder((GregorianCalendar) dateToString(temp[j]));
					j++;
					ord.setDateOfCompletion((GregorianCalendar) dateToString(temp[j]));
					j++;
					ord.setDatePlannedStarting((GregorianCalendar) dateToString(temp[j]));

					if (ArrayChecker.checkSize(ordr)) {
						ordr[k] = ord;
					} else {
						ArrayChecker.resize(ordr);
						ordr[k] = ord;
						k++;
					}
				}
				OrderList orders = new OrderList(ordr);
				tempMaster.setOrders(orders);
				masters[i] = tempMaster;
			} else {
				tempMaster.setIsWork(false);
				masters[i] = tempMaster;
			}
		}
		return masters;
	}
}
