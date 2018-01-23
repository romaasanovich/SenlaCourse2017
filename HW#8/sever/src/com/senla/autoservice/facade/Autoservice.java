package com.senla.autoservice.facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.senla.autoservice.api.IGarageManager;
import com.senla.autoservice.api.IMasterManager;
import com.senla.autoservice.api.IOrderManager;
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
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.DependencyInjector;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Serializer;

public class Autoservice {

	private IGarageManager garageManager;
	private IMasterManager masterManager;
	private IOrderManager orderManager;
	private static final Logger logger = Logger.getLogger(Autoservice.class.getName());
	private static Autoservice instance;

	private Autoservice() {
		try {
			final Handler fileHandler = new FileHandler(Constants.LOG_PATH);
			logger.setUseParentHandlers(false);
			logger.addHandler(fileHandler);
			new Prop();
		} catch (final IOException e) {
			Printer.printMessage(Constants.NO_LOGGER_FILE);
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, e);
		}
		orderManager = DependencyInjector.getInstance(OrderManager.class);
		garageManager = DependencyInjector.getInstance(GarageManager.class);
		masterManager = DependencyInjector.getInstance(MasterManager.class);
		/*orderManager =  new OrderManager();
		garageManager =new GarageManager();
		masterManager = new MasterManager();*/
		deserialize();
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

	public synchronized String addPlace(String name) {
		String temp = Prop.getProp("toAddPlaces");
		Boolean flag = Boolean.parseBoolean(temp);
		if (flag) {
			return garageManager.add(new Place(0, name));
		} else {
			return FacadeMessage.UNV_FUNCTION;
		}
	}

	public synchronized String addMaster(String name) {

		return masterManager.add(new Master(0, name, null, null));
	}

	public synchronized String addOrderToMaster(int id, Order order) {
		if (order != null) {
			masterManager.addOrderToMaster(id, order);
			return orderManager.add(order);
		} else {
			return FacadeMessage.ERROR;
		}
	}

	public synchronized String addWorkToMaster(int id, Work work) {
		if (work != null) {
			return masterManager.addWorkToMaster(id, work);
		} else {
			return FacadeMessage.ERROR;
		}
	}

	public String getAllFreePlaces() {
		ArrayList<Place> places = new ArrayList<>();
		places = garageManager.getFreePlaces();
		if (places != null) {
			return Convert.getEntityStringFromArray(places);
		} else
			return FacadeMessage.NO_ANY_PLACES;
	}

	public String getCountOfFreePlacesOnDate(Date date) {
		int count = masterManager.getCountOfFreePlacesOnDate(date);
		if (count == 0) {
			return FacadeMessage.NO_ANY_PLACES;
		} else {
			return "Count " + count;
		}
	}

	public String getOrdersByOrderDate() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByDateOfOrder());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return FacadeMessage.NO_ANY_ORDERS;
		}

	}

	public String getOrdersByDateOfCompletion() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByDateOfCompletion());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return FacadeMessage.NO_ANY_ORDERS;
		}
	}

	public String getOrdersByDateOfStart() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByPlannedStarting());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return FacadeMessage.NO_ANY_ORDERS;
		}
	}

	public String getOrdersByPrice() {
		ArrayList<Order> orders = orderManager.getAllSortedOrder(new SortedByPrice());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return FacadeMessage.NO_ANY_ORDERS;
		}
	}

	public String getCurrentOrdersByDateOfOrder() {
		ArrayList<Order> orders = orderManager.getCurrentOrders(new SortedByDateOfOrder());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return FacadeMessage.NO_ANY_ORDERS;
		}
	}

	public String getCurrentOrdersByDateOfCompletion() {
		ArrayList<Order> orders = orderManager.getCurrentOrders(new SortedByDateOfCompletion());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return FacadeMessage.NO_ANY_ORDERS;
		}
	}

	public String getCurrentOrdersPrice() {
		ArrayList<Order> orders = orderManager.getCurrentOrders(new SortedByPrice());
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return FacadeMessage.NO_ANY_ORDERS;
		}
	}

	public String getOrderCarriedOutByMaster(Master master) {
		Order order = orderManager.getOrderCarriedOutCurrentMaster(master);
		if (order != null) {
			return order.toString();
		} else {
			return FacadeMessage.NO_ANY_ORDER;
		}
	}

	public String getOrdersForPeriodTime(StatusOrder status, Date fDate, Date sDate) {
		ArrayList<Order> orders = orderManager.getOdersForPeriodOfTime(status, fDate, sDate);
		if (orders != null) {
			return Convert.getEntityStringFromArray(orders);
		} else {
			return FacadeMessage.NO_ANY_ORDERS;
		}
	}

	public synchronized String cloneOrder(Integer id) {
		Order ord = null;
		try {
			ord = orderManager.cloneOrder(id);
			if (ord != null) {
				addOrderToMaster(ord.getMaster().getId(), ord);
				return FacadeMessage.DONE;
			} else
				return Constants.CLONED_IMPOSSIBLE;
		} catch (CloneNotSupportedException ex) {
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, ex);
		}
		return FacadeMessage.ERROR;

	}

	public String getMastersByBusying() {
		ArrayList<Master> masters = masterManager.getSortedMasters(new SortedByBusing());
		if (masters != null) {
			return Convert.getEntityStringFromArray(masters);
		} else {
			return FacadeMessage.NO_ANY_MASTERS;
		}
	}

	public String getMastersByAlpha() {
		ArrayList<Master> masters = masterManager.getSortedMasters(new SortedByAlphabet());
		if (masters != null) {
			return Convert.getEntityStringFromArray(masters);
		} else {
			return FacadeMessage.NO_ANY_MASTERS;
		}
	}

	public String getMasterCarriedOutOrder(Order order) {
		Master master = masterManager.getMasterCarriedOutCurrentOrder(order);
		if (master != null) {
			return master.toString();
		} else {
			return FacadeMessage.NO_ANY_MASTER;
		}
	}

	public synchronized String changeStatus(int id, StatusOrder status) {
		Boolean flag = true;
		if (status == StatusOrder.Deleted) {
			String temp = Prop.getProp("toDeleteOrders");
			flag = Boolean.parseBoolean(temp);
		}
		if (flag) {
			orderManager.changeStatusOfOrder(id, status);
			return FacadeMessage.SCS_CHANGE;
		} else
			return FacadeMessage.UNV_FUNCTION;

	}

	public String exit() {
		serialize();
		return FacadeMessage.EXIT;
	}

	public String getCloseFreeDate() {
		return masterManager.getFreeDate().toString();
	}

	public void serialize() {
		Serializer.serialize(masterManager.getMasters(), Prop.getProp("masterPath"));
		Serializer.serialize(garageManager.getPlaces(), Prop.getProp("placePath"));
		Serializer.serialize(orderManager.getOrders(), Prop.getProp("orderPath"));
	}

	public String deserialize() {
		garageManager.deserializePlaces();
		masterManager.deserializeMasters();
		orderManager.deserializeOrders();
		return FacadeMessage.DESER_DONE;
	}

	public synchronized String exportMasterCSV() throws Exception {

		String message = "";
		try {
			masterManager.exportToCSV();
			message = FacadeMessage.SUCCESS;
		} catch (final IOException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.FILE_NOT_FOUND;
		} catch (final NoSuchFieldException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.ERROR_WRONG_FIELD;
		} catch (final IllegalAccessException ignored) {
		}
		return message;

	}

	public synchronized String exportPlaceCSV() throws Exception {

		String message = "";
		try {
			garageManager.exportToCSV();
			message = FacadeMessage.SUCCESS;
		} catch (final IOException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.FILE_NOT_FOUND;
		} catch (final NoSuchFieldException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.ERROR_WRONG_FIELD;
		} catch (final IllegalAccessException ignored) {
		}
		return message;

	}

	public synchronized String exportOrderCSV() throws Exception {

		String message = "";
		try {
			orderManager.exportToCSV();
			message = FacadeMessage.SUCCESS;
		} catch (final IOException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.FILE_NOT_FOUND;
		} catch (final NoSuchFieldException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.ERROR_WRONG_FIELD;
		} catch (final IllegalAccessException ignored) {
		}
		return message;

	}

	public synchronized String importMasterCSV() throws Exception {

		String message = "";
		try {
			masterManager.importFromCSV();
			message = FacadeMessage.SUCCESS;
		} catch (final IOException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.FILE_NOT_FOUND;
		} catch (final NoSuchFieldException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.ERROR_WRONG_FIELD;
		} catch (final IllegalAccessException ignored) {
		}
		return message;

	}

	public synchronized String importPlaceCSV() throws Exception {

		String message = "";
		try {
			garageManager.importFromCSV();
			message = FacadeMessage.SUCCESS;
		} catch (final IOException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.FILE_NOT_FOUND;
		} catch (final NoSuchFieldException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.ERROR_WRONG_FIELD;
		} catch (final IllegalAccessException ignored) {
		}
		return message;

	}

	public synchronized String importOrderCSV() throws Exception {

		String message = "";
		try {
			orderManager.importFromCSV();
			message = FacadeMessage.SUCCESS;
		} catch (final IOException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.FILE_NOT_FOUND;
		} catch (final NoSuchFieldException e) {
			logger.log(Level.SEVERE, FacadeMessage.LOGGER_MSG, e);
			message = FacadeMessage.ERROR_WRONG_FIELD;
		} catch (final IllegalAccessException ignored) {
		}
		return message;

	}

}
