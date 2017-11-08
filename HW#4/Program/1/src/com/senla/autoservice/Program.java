package com.senla.autoservice;


import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.comparator.order.SortedByPrice;
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
		facade.showAllFreePlaces();
		facade.showCurrentOrdersPrice();
		facade.showMastersByAlpha();
		facade.showOrdersByDateOfCompletion();
		facade.writeDataIntoFiles(pathToMasters, pathToPlaces, pathToWorks);	
		}
}
