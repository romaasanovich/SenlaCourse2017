package com.senla.autoservice.facade;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.autoservice.api.IGarageManager;
import com.senla.autoservice.api.IMasterManager;
import com.senla.autoservice.api.IOrderManager;
import com.senla.autoservice.api.RepositoryType;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.api.constants.Constants;
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
import com.senla.autoservice.properties.Prop;
import com.senla.autoservice.repository.GarageRepository;
import com.senla.autoservice.repository.MasterRepository;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.DependencyInjector;
import com.senla.autoservice.utills.IdGenerator;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Serializer;

public class Autoservice {

	private static final String NO_ANY_PLACES = "Error. There is no any place\n";
	private static final String NO_ANY_ORDERS = "Error. There is no any orders\n";
	private static final String NO_ANY_ORDER = "Error. There is no any order\n";
	private static final String NO_ANY_MASTERS = "Error. There is no any masters\n";
	private static final String NO_ANY_MASTER = "Error. There is no any master\n";
	private static final String FILE_NOT_FOUND = "Error. File not found\n";
	private static final String UNV_FUNCTION = "Error. This function is unvailable\n";
	private static final String SCS_CHANGE = "Successful changed\n";
	private static final String LOGGER_MSG = "Exception";
	private static final String IMPORT_DONE = "Import Done \n";
	private static final String SER_DONE = "Ser. Done \n";
	private static final String DESER_DONE = "Deser. Done \n";
	private static final String SUCCESS = "Success";
	private static final String ERROR_WRONG_FIELD = "Wrong field!\n";

	private GarageManager garageManager;
	private MasterManager masterManager;
	private OrderManager orderManager;
	private Prop prop;
	private static final Logger logger = Logger.getLogger(Autoservice.class.getName());

	private static Autoservice instance;

	private Autoservice() {
		try {
			final Handler fileHandler = new FileHandler(Constants.LOG_PATH);
			logger.setUseParentHandlers(false);
			logger.addHandler(fileHandler);
		} catch (final IOException e) {
			Printer.printMessage(Constants.NO_LOGGER_FILE);
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, e);
		}
		prop = new Prop();

		orderManager = DependencyInjector.getInstance(IOrderManager.class);
		garageManager = DependencyInjector.getInstance(IGarageManager.class);
		masterManager = DependencyInjector.getInstance(IMasterManager.class);
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
	public synchronized String addPlace(String name) {
		String temp = prop.getProp("toAddPlaces");
		Boolean flag = Boolean.parseBoolean(temp);
		if (flag) {
			int id = IdGenerator.getFreeID(garageManager.getPlaces().getPlaces());
			return garageManager.add(new Place(id, name));
		} else {
			return UNV_FUNCTION;
		}
	}

	public synchronized String addMaster(String name) {
		int id = IdGenerator.getFreeID(masterManager.getMasters().getListOfMasters());
		return masterManager.add(new Master(id, name, null, null));
	}

	public synchronized String addOrderToMaster(int id, Order order) {
		if (((Master) masterManager.getMasters().findById(id)).addOrder(order)) {
			return orderManager.add(order);
		} else {
			return "Error!!!";
		}
	}

	public synchronized String addWorkToMaster(int id, Work work) {
		((Master) masterManager.getMasters().findById(id)).addWork(work);
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

	////////////////// Clone Order/////////////////

	public synchronized Order cloneOrder(int id) {
		Order ord = null;
		try {
			ord = orderManager.cloneOrder(id);
			return ord;
		} catch (CloneNotSupportedException ex) {
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, ex);
			return null;
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

	/////// ChangeStatus///////////
	public synchronized String changeStatus(int id, StatusOrder status) {
		Boolean flag = true;
		if (status == StatusOrder.Deleted) {
			String temp = prop.getProp("toDeleteOrders");
			flag = Boolean.parseBoolean(temp);
		}
		if (flag) {
			orderManager.changeStatusOfOrder(id, status);
			return SCS_CHANGE;
		} else
			return UNV_FUNCTION;

	}

	////////////// get Data////////////
	public String getCloseFreeDate() {
		return masterManager.getFreeDate().toString();
	}

	///////////////////////////////////// File IO//////////////////////////////////

	public void Serialize() {
		Serializer.serialize(masterManager.getMasters(), prop.getProp("masterPath"));
		Serializer.serialize(garageManager.getPlaces(), prop.getProp("placePath"));
	}

	public String Deserialize() {
		MasterRepository newMaster = Serializer.deserialMaster(prop.getProp("masterPath"));
		if (newMaster == null) {
			return FILE_NOT_FOUND;
		} else {
			for (Master master : newMaster.getListOfMasters()) {
				masterManager.add(master);
			}
			ArrayList<Order> allOrd = masterManager.getAllOrders();
			for (Order ord : allOrd) {
				if (ord != null) {
					orderManager.add(ord);
				}
			}
		}
		GarageRepository newPlaces = Serializer.deserialPlaces(prop.getProp("placePath"));
		if (newPlaces == null) {
			return FILE_NOT_FOUND;
		}
		for (Place place : newPlaces.getPlaces()) {
			garageManager.add(place);
		}
		return DESER_DONE;
	}

	public synchronized String exportToCSV(final RepositoryType type) {
		String message = "";
		try {
			switch (type) {
			case MasterRepository:
				masterManager.exportToCSV();
				break;
			case GarageRepository:
				garageManager.exportToCSV();
				break;
			}
			message = SUCCESS;
		} catch (final IOException e) {
			logger.log(Level.SEVERE, LOGGER_MSG, e);
			message = FILE_NOT_FOUND;
		} catch (final NoSuchFieldException e) {
			logger.log(Level.SEVERE, LOGGER_MSG, e);
			message = ERROR_WRONG_FIELD;
		} catch (final IllegalAccessException ignored) {
		}
		return message;
	}

	public synchronized String importFromCSV(final RepositoryType type)
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		String message;
		try {
			switch (type) {
			case MasterRepository:
				masterManager.importFromCSV();
				break;
			case GarageRepository:
				garageManager.importFromCSV();
				break;
			}
			message = SUCCESS;
		} catch (final IOException e) {
			logger.log(Level.SEVERE, LOGGER_MSG, e);
			message = FILE_NOT_FOUND;
		}
		return message;
	}

}
