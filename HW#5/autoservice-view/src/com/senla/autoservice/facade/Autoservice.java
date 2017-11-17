package com.senla.autoservice.facade;

import java.util.ArrayList;
import java.util.Date;

import com.danco.training.TextFileWorker;
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
import com.senla.autoservice.manager.WorkManager;
import com.senla.autoservice.repository.GarageRepository;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.ExceptionLogger;
import com.senla.autoservice.utills.FileIO;
import com.senla.autoservice.utills.IdGenerator;

public class Autoservice {

	private static final String NO_ANY_PLACES = "Error. There is no any place\n";
	private static final String NO_ANY_ORDERS = "Error. There is no any orders\n";
	private static final String NO_ANY_ORDER = "Error. There is no any order\n";
	private static final String NO_ANY_MASTERS = "Error. There is no any masters\n";
	private static final String NO_ANY_MASTER = "Error. There is no any master\n";

	GarageManager garageManager;
	MasterManager masterManager;
	OrderManager orderManager;
	WorkManager workManager;
	ExceptionLogger log = new ExceptionLogger();

	private static Autoservice instance;

	private Autoservice() {
		garageManager = new GarageManager();
		masterManager = new MasterManager();
		orderManager = new OrderManager();
		workManager = new WorkManager();
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
		return (garageManager.add(new Place(id, name)));
	}

	public String addMaster(String name) {
		int id = IdGenerator.getFreeID(masterManager.getMasters().getListOfMasters());
		return (masterManager.add(new Master(id, name, null, null)));
	}

	public String addOrderToMaster(int id, Order order) {
		if (masterManager.getMasters().getMasterById(id).addOrder(order)) {
			return (orderManager.add(order));
		} else {
			return ("Error!!!");
		}
	}

	public String addWorkToMaster(int id, Work work) {
		masterManager.getMasters().getMasterById(id).addWork(work);
		return ("Work is added");
	}

	////// Show Places////////////////

	public String showAllFreePlaces() {
		ArrayList<Place> places = new ArrayList<>();
		try {
			places = garageManager.getFreePlaces();
			return (Convert.getEntityStringFromArray(places));
		}
		catch (NullPointerException e) {
			log.write(NO_ANY_PLACES, e);
			return (NO_ANY_PLACES);
		}

	}

	public String showCountOfFreePlacesOnDate(Date date) {
		int count = masterManager.getCountOfFreePlacesOnDate(date);
		if (count == 0) {
			return (NO_ANY_PLACES);
		} else {
			return ("Count " + count);
		}
	}

	////////// Show Orders////////////

	public String showOrdersByOrderDate() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByDateOfOrder());
		try {
			return (Convert.getEntityStringFromArray(orders));
		} catch (NullPointerException ex) {
			log.write(NO_ANY_ORDERS, ex);
			return (NO_ANY_ORDERS);
		}

	}

	public String showOrdersByDateOfCompletion() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByDateOfCompletion());
		try {
			return (Convert.getEntityStringFromArray(orders));
		} catch (NullPointerException ex) {
			log.write(NO_ANY_ORDERS, ex);
			return (NO_ANY_ORDERS);
		}
	}

	public String showOrdersByDateOfStart() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByPlannedStarting());
		try {
			return ((Convert.getEntityStringFromArray(orders)));
		} catch (NullPointerException ex) {
			log.write(NO_ANY_ORDERS, ex);
			return (NO_ANY_ORDERS);
		}
	}

	public String showOrdersByPrice() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByPrice());
		try {
			return ((Convert.getEntityStringFromArray(orders)));
		} catch (NullPointerException ex) {
			log.write(NO_ANY_ORDERS, ex);
			return (NO_ANY_ORDERS);
		}
	}

	///// Show Current Orders////////////

	public String showCurrentOrdersByDateOfOrder() {
		ArrayList<Order> orders = orderManager.getCurrentOrders(new SortedByDateOfOrder());
		try {
			return ((Convert.getEntityStringFromArray(orders)));
		} catch (NullPointerException ex) {
			log.write(NO_ANY_ORDERS, ex);
			return (NO_ANY_ORDERS);
		}
	}

	public String showCurrentOrdersByDateOfCompletion() {
		ArrayList<Order> orders = orderManager.getCurrentOrders(new SortedByDateOfCompletion());
		try {
			return ((Convert.getEntityStringFromArray(orders)));
		} catch (NullPointerException ex) {
			log.write(NO_ANY_ORDERS, ex);
			return (NO_ANY_ORDERS);
		}
	}

	public String showCurrentOrdersPrice() {
		ArrayList<Order> orders = orderManager.getCurrentOrders(new SortedByPrice());
		try {
			return ((Convert.getEntityStringFromArray(orders)));
		} catch (NullPointerException ex) {
			log.write(NO_ANY_ORDERS, ex);
			return (NO_ANY_ORDERS);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	public String showOrderCarriedOutByMaster(Master master) {
		Order order = orderManager.getOrderCarriedOutCurrentMaster(master);
		try {
			return (order.toString());
		} catch (NullPointerException ex) {
			log.write(NO_ANY_ORDER, ex);
			return (NO_ANY_ORDER);
		}
	}

	public String showOrdersForPeriodTime(StatusOrder status, Date fDate, Date sDate) {
		ArrayList<Order> orders = orderManager.getOdersForPeriodOfTime(status, fDate, sDate);
		try {
			return ((Convert.getEntityStringFromArray(orders)));
		} catch (NullPointerException ex) {
			log.write(NO_ANY_ORDERS, ex);
			return (NO_ANY_ORDERS);
		}
	}

	///////////// Show Masters///////////////////

	public String showMastersByBusying() {
		ArrayList<Master> masters = masterManager.getSortedMasters(new SortedByBusing());
		try {
			return ((Convert.getEntityStringFromArray(masters)));
		} catch (NullPointerException e) {
			log.write(NO_ANY_MASTERS, e);
			return (NO_ANY_MASTERS);
		}
	}

	public String showMastersByAlpha() {
		ArrayList<Master> masters = masterManager.getSortedMasters(new SortedByAlphabet());
		try {
			return ((Convert.getEntityStringFromArray(masters)));
		} catch (NullPointerException e) {
			log.write(NO_ANY_MASTERS, e);
			return (NO_ANY_MASTERS);
		}
	}

	public String showMasterCarriedOutOrder(Order order) {
		Master master = masterManager.getMasterCarriedOutCurrentOrder(order);
		try {
			return (master.toString());
		} catch (NullPointerException e) {
			log.write(NO_ANY_MASTER, e);
			return (NO_ANY_MASTER);
		}
	}

	////////////// Show Data////////////
	public String showCloseFreeDate() {
		return (masterManager.getFreeDate().toString());
	}

	///////////////////////////////////// File IO//////////////////////////////////

	public void writeDataIntoFiles(String pathToMasters, String pathToPlaces) {
		/*
		 * TextFileWorker masterFileWorker = new TextFileWorker(pathToMasters);
		 * TextFileWorker placeFileWorker = new TextFileWorker(pathToPlaces);
		 * TextFileWorker workFileWorker = new TextFileWorker(pathToServices);
		 */

		ArrayList<Master> master = masterManager.getMasters().getListOfMasters();
		ArrayList<Place> place = garageManager.getPlaces().getPlaces();

		FileIO.writeToFile(pathToMasters, Convert.getEntityStringArray(master));
		FileIO.writeToFile(pathToPlaces, Convert.getEntityStringArray(place));
	}

	public void readDataFromFiles(String pathToMasters, String pathToPlaces) {
		TextFileWorker garageFileWorker = new TextFileWorker(pathToPlaces);
		TextFileWorker masterFileWorker = new TextFileWorker(pathToMasters);

		for (String line : garageFileWorker.readFromFile()) {
			addPlaceFromFile(Convert.fromStrToPlace(line));
		}
		GarageRepository garages = garageManager.getPlaces();
		for (String line : masterFileWorker.readFromFile()) {
			addMasterFromFile(Convert.formStringToMaster(line, garages));
		}

		ArrayList<Order> allOrd = masterManager.getAllOrders();

		for (Order ord : allOrd) {
			if (ord != null) {
				orderManager.add(ord);
			}
		}

	}

	private void addPlaceFromFile(Place place) {
		garageManager.add(place);
	}

	private void addMasterFromFile(Master master) {
		masterManager.add(master);
	}

}
