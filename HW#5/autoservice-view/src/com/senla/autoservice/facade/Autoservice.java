package com.senla.autoservice.facade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.comparator.master.SortedByAlphabet;
import com.senla.autoservice.comparator.master.SortedByBusing;
import com.senla.autoservice.comparator.order.SortedByDateOfCompletion;
import com.senla.autoservice.comparator.order.SortedByDateOfOrder;
import com.senla.autoservice.comparator.order.SortedByPlannedStarting;
import com.senla.autoservice.comparator.order.SortedByPrice;
import com.senla.autoservice.manager.GarageManager;
import com.senla.autoservice.manager.MasterManager;
import com.senla.autoservice.manager.OrderManager;
import com.senla.autoservice.repository.GarageRepository;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.FileIO;
import com.senla.autoservice.utills.IdGenerator;
import com.senla.autoservice.utills.Printer;

public class Autoservice {

	private static final String NO_ANY_PLACES = "Error. There is no any place\n";
	private static final String NO_ANY_ORDERS = "Error. There is no any orders\n";
	private static final String NO_ANY_ORDER = "Error. There is no any order\n";
	private static final String NO_ANY_MASTERS = "Error. There is no any masters\n";
	private static final String NO_ANY_MASTER = "Error. There is no any master\n";
	private static final String FILE_NOT_FOUND = "Error. File not found\n";
	private static final String LOGGER_MSG = "Exception";
	private static final String NO_LOGGER_FILE = "Cant find logger file";

	private GarageManager garageManager;
	private MasterManager masterManager;
	private OrderManager orderManager;
	private static final Logger logger = Logger.getLogger(Autoservice.class.getName());

	private static Autoservice instance;

	private Autoservice() {
		try {
			final Handler fileHandler = new FileHandler(Constants.LOG_PATH);
			logger.setUseParentHandlers(false);
			logger.addHandler(fileHandler);
		} catch (final IOException e) {
			Printer.printMessage(NO_LOGGER_FILE);
			logger.log(Level.SEVERE, LOGGER_MSG, e);
		}

		garageManager = new GarageManager();
		masterManager = new MasterManager();
		orderManager = new OrderManager();
	}

	public static Autoservice getInstance() {
		if (instance == null) {
			instance = new Autoservice();
		}
		return instance;
	}

	public Order getCurrentOrder(int id) {
		return orderManager.getOrders().getListOfOrders().get(id);
	}

	public Master getCurMaster(int id) {
		return masterManager.getMasters().getListOfMasters().get(id);
	}

	///// add/////////
	public String addPlace(String name) {
		int id = IdGenerator.getFreeID(garageManager.getPlaces().getPlaces());
		return garageManager.add(new Place(id, name));
	}

	public String addMaster(String name) {
		int id = IdGenerator.getFreeID(masterManager.getMasters().getListOfMasters());
		return masterManager.add(new Master(id, name, null, null));
	}

	public String addOrderToMaster(int id, Order order) {
		if (masterManager.getMasters().getMasterById(id).addOrder(order)) {
			return orderManager.add(order);
		} else {
			return "Error!!!";
		}
	}

	public String addWorkToMaster(int id, Work work) {
		masterManager.getMasters().getMasterById(id).addWork(work);
		return "Work is added";
	}

	////// Get Places////////////////

	public String getAllFreePlaces() {
		ArrayList<Place> places = new ArrayList<>();
		places = garageManager.getFreePlaces();
		if (places != null) {
			return Convert.getEntityStringFromArray(places);
		} else
			return NO_ANY_PLACES;
	}

	public String getCountOfFreePlacesOnDate(Date date) {
		int count = masterManager.getCountOfFreePlacesOnDate(date);
		if (count == 0) {
			return NO_ANY_PLACES;
		} else {
			return "Count " + count;
		}
	}

	////////// Get Orders////////////

	public String getOrdersByOrderDate() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByDateOfOrder());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return NO_ANY_ORDERS;
		}

	}

	public String getOrdersByDateOfCompletion() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByDateOfCompletion());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return NO_ANY_ORDERS;
		}
	}

	public String getOrdersByDateOfStart() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByPlannedStarting());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return NO_ANY_ORDERS;
		}
	}

	public String getOrdersByPrice() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByPrice());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return NO_ANY_ORDERS;
		}
	}

	///// Get Current Orders////////////

	public String getCurrentOrdersByDateOfOrder() {
		ArrayList<Order> orders = orderManager.getCurrentOrders(new SortedByDateOfOrder());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return NO_ANY_ORDERS;
		}
	}

	public String getCurrentOrdersByDateOfCompletion() {
		ArrayList<Order> orders = orderManager.getCurrentOrders(new SortedByDateOfCompletion());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return NO_ANY_ORDERS;
		}
	}

	public String getCurrentOrdersPrice() {
		ArrayList<Order> orders = orderManager.getCurrentOrders(new SortedByPrice());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return NO_ANY_ORDERS;
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	public String getOrderCarriedOutByMaster(Master master) {
		Order order = orderManager.getOrderCarriedOutCurrentMaster(master);
		if (order != null) {
			return order.toString();
		} else {
			return NO_ANY_ORDER;
		}
	}

	public String getOrdersForPeriodTime(StatusOrder status, Date fDate, Date sDate) {
		ArrayList<Order> orders = orderManager.getOdersForPeriodOfTime(status, fDate, sDate);
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return NO_ANY_ORDERS;
		}
	}

	///////////// Get Masters///////////////////

	public String getMastersByBusying() {
		ArrayList<Master> masters = masterManager.getSortedMasters(new SortedByBusing());
		if (masters != null) {
			return Convert.getEntityStringFromArray(masters);
		} else {
			return NO_ANY_MASTERS;
		}
	}

	public String getMastersByAlpha() {
		ArrayList<Master> masters = masterManager.getSortedMasters(new SortedByAlphabet());
		if (masters != null) {
			return Convert.getEntityStringFromArray(masters);
		} else {
			return NO_ANY_MASTERS;
		}
	}

	public String getMasterCarriedOutOrder(Order order) {
		Master master = masterManager.getMasterCarriedOutCurrentOrder(order);
		if (master != null) {
			return master.toString();
		} else {
			return NO_ANY_MASTER;
		}
	}

	////////////// get Data////////////
	public String getCloseFreeDate() {
		return masterManager.getFreeDate().toString();
	}

	///////////////////////////////////// File IO//////////////////////////////////

	public void writeDataIntoFiles(String pathToMasters, String pathToPlaces) {
		ArrayList<Master> master = masterManager.getMasters().getListOfMasters();
		ArrayList<Place> place = garageManager.getPlaces().getPlaces();
		try {
			FileIO.writeToFile(pathToMasters, Convert.getEntityStringArray(master));
			FileIO.writeToFile(pathToPlaces, Convert.getEntityStringArray(place));
		} catch (FileNotFoundException ex) {
			logger.log(Level.SEVERE, LOGGER_MSG, ex);
			Printer.printMessage(FILE_NOT_FOUND);
		}

	}

	public void readDataFromFiles(String pathToMasters, String pathToPlaces) {
		try {
			String[] masterArray = FileIO.readFrom(pathToMasters);
			String[] garageArray = FileIO.readFrom(pathToPlaces);

			for (String line : garageArray) {
				addPlaceFromFile(Convert.fromStrToPlace(line));
			}
			GarageRepository garages = garageManager.getPlaces();
			for (String line : masterArray) {
				addMasterFromFile(Convert.formStringToMaster(line, garages));
			}
			ArrayList<Order> allOrd = masterManager.getAllOrders();

			for (Order ord : allOrd) {
				if (ord != null) {
					orderManager.add(ord);
				}
			}
		} catch (FileNotFoundException ex) {
			Printer.printMessage(FILE_NOT_FOUND);
			logger.log(Level.SEVERE, LOGGER_MSG, ex);
		}
	}

	private void addPlaceFromFile(Place place) {
		garageManager.add(place);
	}

	private void addMasterFromFile(Master master) {
		masterManager.add(master);
	}

}
