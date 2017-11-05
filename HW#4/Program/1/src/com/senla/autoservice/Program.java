package com.senla.autoservice;


import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.facade.Autoservice;

public class Program {
	private static final Autoservice facade = new Autoservice();

	public static void main(String[] args) {
		
		String pathToMasters;
		String pathToPlaces;
		String pathToWorks;
		
		if (args.length > 0) {
			pathToMasters = args[0];
			pathToPlaces = args[1];
			pathToWorks = args[2];
		} else {
			pathToMasters = Constants.PATH_TO_MASTERS;
			pathToPlaces = Constants.PATH_TO_PLACES;
			pathToWorks = Constants.PATH_TO_WORKS;
		}
		facade.readDataFromFiles(pathToPlaces, pathToMasters, pathToWorks);
		//fillIn();
		facade.showAllFreePlaces();
		facade.showCurrentOrdersPrice();
		facade.showMastersByAlpha();
		facade.showOrdersByDateOfCompletion();
		GregorianCalendar fDate=new GregorianCalendar();
		fDate.add(Calendar.DAY_OF_YEAR, -2);
		GregorianCalendar sDate=new GregorianCalendar();
		sDate.add(Calendar.DAY_OF_YEAR, +10);
		//facade.showOrdersForPeriodTime(StatusOrder.Broned, masters, new SortedByPrice(),fDate.getTime(), sDate.getTime());
		//Outputer.printMessage(masters.getMasterById(0).toString());
		facade.writeDataIntoFiles(pathToMasters, pathToPlaces, pathToWorks);
		
		}

	/*static void fillIn() {
		fillWorks();
		fillPlaces();
		fillMasters();
	}

	static void fillWorks() {
		facade.add(new Work(0, "work1", 1.2));
		facade.add(new Work(1, "work2", 2.5));
		facade.add(new Work(2, "work3", 2.4));
		facade.add(new Work(3, "work4", 10.2));
		facade.add(new Work(4, "work5", 11.5));
	}

	static void fillPlaces() {
		facade.add(new Place(0, "place1"));
		facade.add(new Place(1, "place2"));
		facade.add(new Place(2, "place3"));
		facade.add(new Place(3, "place4"));
		facade.add(new Place(4, "place5"));
	}

	static OrderList createOrders1() {
		OrderList ord = new OrderList(2);
		ord.add(new Order(0, facade.workManager.getWorks().findById(3),
				facade.garageManager.getFreePlaces().getFreePlace(), StatusOrder.Broned, 1, 3));
		ord.add(new Order(1, facade.workManager.getWorks().findById(0),
				facade.garageManager.getFreePlaces().getFreePlace(), StatusOrder.Broned, 4, 5));
		return ord;
	}

	static void fillMasters() {
		facade.add(new Master(0, "master1", createOrders1()));
		facade.add(new Master(1, "master2", null));
		facade.add(new Master(2, "master3", null));
		facade.add(new Master(3, "master4", null));
		facade.add(new Master(4, "master5", null));
	}
*/
}
