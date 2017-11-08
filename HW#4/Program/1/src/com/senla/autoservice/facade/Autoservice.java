package com.senla.autoservice.facade;

import java.util.Comparator;
import java.util.Date;

import com.danco.training.TextFileWorker;
import com.senla.autoservice.api.Entity;
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
import com.senla.autoservice.repository.GaragePlaces;
import com.senla.autoservice.repository.OrderList;
import com.senla.autoservice.repository.WorkList;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.Outputer;

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

	public Autoservice() {
		garageManager = new GarageManager();
		masterManager = new MasterManager();
		orderManager = new OrderManager();
		workManager = new WorkManager();
	}

	public void add(Entity entity) {
		if (entity instanceof Place) {
			Outputer.printMessage(garageManager.add((Place) entity));
		} else if (entity instanceof Master) {
			Outputer.printMessage(masterManager.add((Master) entity));
		} else if (entity instanceof Order) {
			Outputer.printMessage(orderManager.add((Order) entity));
		} else if (entity instanceof Work) {
			Outputer.printMessage(workManager.add((Work) entity));
		}
	}
	/////add/////////
	public void  addPlace(Place place) {
		Outputer.printMessage(garageManager.add(place));
	}
	
	public void  addMaster(Master master) {
		Outputer.printMessage(masterManager.add(master));
	}
	
	public void addOrderToMaster(int id, Order order) {
		if(masterManager.getMasters().getMasterById(id) !=null) {
		masterManager.getMasters().getMasterById(id).getOrders().add(order);
		Outputer.printMessage(orderManager.add(order));
		}
		else {
			Outputer.printMessage("Wrong Id");
		}
	}
	public void addWorkToMaster(int id, Work work) {
		if(masterManager.getMasters().getMasterById(id) !=null) {
		masterManager.getMasters().getMasterById(id).getWorks().add(work);
		Outputer.printMessage("Work is added");
		}
		else {
			Outputer.printMessage("Wrong Id");
		}
				
	}
	
	////// Show Places////////////////

	public void showAllFreePlaces() {
		if (garageManager.getFreePlaces().getPlaces() == null
				|| garageManager.getFreePlaces().getPlaces().length == 0) {
			Outputer.printMessage(NO_ANY_PLACES);
		} else {
			Outputer.printArray(garageManager.getFreePlaces().getPlaces());
		}
	}

	public void showCountOfFreePlacesOnDate(Date date) {
		if (masterManager.getCountOfFreePlacesOnDate(date) == 0) {
			Outputer.printMessage(NO_ANY_PLACES);
		} else {
			Outputer.printMessage(Integer.toString(masterManager.getCountOfFreePlacesOnDate(date)));
		}
	}

	////////// Show Orders////////////

	public void showOrdersByOrderDate() {

		if (orderManager.getAllSortedOrder(new SortedByDateOfOrder()) == null
				|| orderManager.getAllSortedOrder(new SortedByDateOfOrder()).getListOfOrders().length == 0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		} else
			Outputer.printArray(orderManager.getAllSortedOrder(new SortedByDateOfOrder()).getListOfOrders());
	}

	public void showOrdersByDateOfCompletion() {
		if (orderManager.getAllSortedOrder(new SortedByDateOfCompletion()) == null
				|| orderManager.getAllSortedOrder(new SortedByDateOfCompletion()).getListOfOrders().length == 0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		} else
			Outputer.printArray(orderManager.getAllSortedOrder(new SortedByDateOfCompletion()).getListOfOrders());
	}

	public void showOrdersByDateOfStart() {
		if (orderManager.getAllSortedOrder(new SortedByPlannedStarting()) == null
				|| orderManager.getAllSortedOrder(new SortedByPlannedStarting()).getListOfOrders().length == 0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		} else
			Outputer.printArray(orderManager.getAllSortedOrder(new SortedByPlannedStarting()).getListOfOrders());
	}

	public void showOrdersByPrice() {
		if (orderManager.getAllSortedOrder(new SortedByPrice()) == null
				|| orderManager.getAllSortedOrder(new SortedByPrice()).getListOfOrders().length == 0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		} else
			Outputer.printArray(orderManager.getAllSortedOrder(new SortedByPrice()).getListOfOrders());
	}

	///// Show Current Orders////////////

	public void showCurrentOrdersByDateOfOrder() {

		if (orderManager.getCurrentOrders(new SortedByDateOfOrder()) == null
				|| orderManager.getCurrentOrders(new SortedByDateOfOrder()).getListOfOrders().length == 0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		} else
			Outputer.printArray(orderManager.getCurrentOrders(new SortedByDateOfOrder()).getListOfOrders());
	}

	public void showCurrentOrdersByDateOfCompletion() {
		if (orderManager.getCurrentOrders(new SortedByDateOfCompletion()) == null
				|| orderManager.getCurrentOrders(new SortedByDateOfCompletion()).getListOfOrders().length == 0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		} else
			Outputer.printArray(orderManager.getCurrentOrders(new SortedByDateOfCompletion()).getListOfOrders());
	}

	public void showCurrentOrdersPrice() {
		if (orderManager.getCurrentOrders(new SortedByPrice()) == null
				|| orderManager.getCurrentOrders(new SortedByPrice()).getListOfOrders().length == 0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		} else
			Outputer.printArray(orderManager.getCurrentOrders(new SortedByPrice()).getListOfOrders());
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	public void showOrderCarriedOutByMaster(Master master) {
		if (orderManager.getOrderCarriedOutCurrentMaster(master) == null) {
			Outputer.printMessage(NO_ANY_ORDER);
		}
		Outputer.printMessage(orderManager.getOrderCarriedOutCurrentMaster(master).toString());
	}

	public void showOrdersForPeriodTime(StatusOrder status, Comparator<Order> comp, Date fDate, Date sDate) {
		if (orderManager.getOdersForPeriodOfTime(status, fDate, sDate, comp) == null
				|| orderManager.getOdersForPeriodOfTime(status, fDate, sDate, comp).getListOfOrders().length == 0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		} else
			Outputer.printArray(orderManager.getOdersForPeriodOfTime(status, fDate, sDate, comp).getListOfOrders());
	}

	///////////// Show Masters///////////////////

	public void showMastersByBusying() {
		if (masterManager.getSortedMasters(new SortedByBusing()) == null
				|| masterManager.getSortedMasters(new SortedByBusing()).getListOfMasters().length == 0) {
			Outputer.printMessage(NO_ANY_MASTERS);
		} else
			Outputer.printArray(masterManager.getSortedMasters(new SortedByBusing()).getListOfMasters());
	}

	public void showMastersByAlpha() {
		if (masterManager.getSortedMasters(new SortedByAlphabet()) == null
				|| masterManager.getSortedMasters(new SortedByAlphabet()).getListOfMasters().length == 0) {
			Outputer.printMessage(NO_ANY_MASTERS);
		} else
			Outputer.printArray(masterManager.getSortedMasters(new SortedByAlphabet()).getListOfMasters());
	}

	public void showMasterCarriedOutOrder(Order order) {
		if (masterManager.getMasterCarriedOutCurrentOrder(order) == null) {
			Outputer.printMessage(NO_ANY_MASTER);
		} else
			Outputer.printMessage(masterManager.getMasterCarriedOutCurrentOrder(order).toString());
	}

	////////////// Show Data////////////
	public void showCloseFreeDate() {
		Outputer.printMessage(masterManager.getFreeDate().toString());
	}

	///////////////////////////////////// File IO//////////////////////////////////

	public void writeDataIntoFiles(String pathToMasters, String pathToPlaces, String pathToServices) {
		TextFileWorker masterFileWorker = new TextFileWorker(pathToMasters);
		TextFileWorker placeFileWorker = new TextFileWorker(pathToPlaces);
		TextFileWorker workFileWorker = new TextFileWorker(pathToServices);

		masterFileWorker.writeToFile(Convert.getEntityStringArray(masterManager.getMasters().getListOfMasters()));
		placeFileWorker.writeToFile(Convert.getEntityStringArray(garageManager.getPlaces().getPlaces()));
		workFileWorker.writeToFile(Convert.getEntityStringArray(workManager.getWorks().getListOfServices()));
	}

	public void readDataFromFiles(String pathToPlaces, String pathToMasters, String pathToWork) {
		TextFileWorker garageFileWorker = new TextFileWorker(pathToPlaces);
		TextFileWorker workFileWorker = new TextFileWorker(pathToWork);
		TextFileWorker masterFileWorker = new TextFileWorker(pathToMasters);

		for (String line : garageFileWorker.readFromFile()) {
			add(new Place(line));
		}
		GaragePlaces garages = garageManager.getPlaces();
		for (String line : workFileWorker.readFromFile()) {
			add(new Work(line));
		}
		//WorkList works = workManager.getWorks();
		for (String line : masterFileWorker.readFromFile()) {
			add(Convert.formStringToMaster(line, garages));
		}
		
		OrderList allOrd = masterManager.getAllOrders();
		
		for(Order ord: allOrd.getListOfOrders()) {
			if(ord != null) {
			add(ord);
			}
		}
	}
	
}
