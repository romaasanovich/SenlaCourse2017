package com.senla.autoservice.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

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
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.ExceptionLogger;
import com.senla.autoservice.utills.IdGenerator;
import com.senla.autoservie.properties.Prop;

public class Autoservice {

	private static final String NO_ANY_PLACES = "Error. There is no any place\n";
	private static final String NO_ANY_ORDERS = "Error. There is no any orders\n";
	private static final String NO_ANY_ORDER = "Error. There is no any order\n";
	private static final String NO_ANY_MASTERS = "Error. There is no any masters\n";
	private static final String NO_ANY_MASTER = "Error. There is no any master\n";
	private static final String UNV_FUNCTION = "Error. This function is unvailable\n";
	private static final String SCS_CHANGE = "Successful changed\n";

	GarageManager garageManager;
	MasterManager masterManager;
	OrderManager orderManager;
	WorkManager workManager;
	ExceptionLogger log = new ExceptionLogger();
	Prop prop = new Prop();

	private static Autoservice instance;

	private Autoservice() {
		garageManager = new GarageManager();
		masterManager = new MasterManager();
		orderManager = new OrderManager();
		workManager = new WorkManager();

		prop.getProp();
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

	public Place getCurPlace(int id) {
		return garageManager.getPlaces().getPlaceById(id);
	}

	///// add/////////

	public String addPlace(String name) {
		String temp = prop.getProp().getProperty("toAddPlaces");
		Boolean flag = Boolean.parseBoolean(temp);
		if (flag) {
			int id = IdGenerator.getFreeID(garageManager.getPlaces().getPlaces());
			return (garageManager.add(new Place(id, name)));
		} else {
			return UNV_FUNCTION;
		}
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
		} catch (NullPointerException e) {
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

	////////////////// Clone Order/////////////////

	public Order cloneOrder(int id) throws CloneNotSupportedException {
		Order ord = null;
		ord = orderManager.cloneOrder(id);
		return ord;
	}

	/////// ChangeStatus///////////
	public String changeStatusOpened(int id) {
		orderManager.changeStatusOfOrder(id, StatusOrder.Opened);
		return SCS_CHANGE;
	}

	public String changeStatusClosed(int id) {
		orderManager.changeStatusOfOrder(id, StatusOrder.Closed);
		return SCS_CHANGE;
	}

	public String changeStatusBroned(int id) {
		orderManager.changeStatusOfOrder(id, StatusOrder.Broned);
		return SCS_CHANGE;
	}

	public String changeStatusDeleted(int id) {
		String temp = prop.getProp().getProperty("toDeleteOrders");
		Boolean flag = Boolean.parseBoolean(temp);
		if (flag) {
			orderManager.changeStatusOfOrder(id, StatusOrder.Deleted);
			return SCS_CHANGE;
		} else
			return UNV_FUNCTION;
	}

	////////////// Show Date////////////
	public String showCloseFreeDate() {

		return (masterManager.getFreeDate().toString());
	}

	///////////////////////////////////// File IO//////////////////////////////////
	public void serialMaster() {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(prop.getProp().getProperty("masterPath")))) {
			oos.writeObject(masterManager.getMasters().getListOfMasters());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void deserialMaster() {
		ArrayList<Master> newMaster;
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(prop.getProp().getProperty("masterPath")))) {
			newMaster = (ArrayList<Master>) ois.readObject();
			for (Master master : newMaster) {
				masterManager.add(master);
			}
			ArrayList<Order> allOrd = masterManager.getAllOrders();
			for (Order ord : allOrd) {
				if (ord != null) {
					orderManager.add(ord);
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void serialPlaces() {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(prop.getProp().getProperty("placePath")))) {
			oos.writeObject(garageManager.getPlaces().getPlaces());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void deserialPlaces() {
		ArrayList<Place> newPlaces;
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(prop.getProp().getProperty("placePath")))) {
			newPlaces = (ArrayList<Place>) ois.readObject();
			for (Place place : newPlaces) {
				garageManager.add(place);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
