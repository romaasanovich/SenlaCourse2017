package com.senla.autoservice.facade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.senla.autoservice.repository.MasterRepository;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.IdGenerator;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Serializer;
import com.senla.autoservie.properties.Prop;

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
		String temp = prop.getProp("toAddPlaces");
		Boolean flag = Boolean.parseBoolean(temp);
		if (flag) {
			int id = IdGenerator.getFreeID(garageManager.getPlaces().getPlaces());
			return garageManager.add(new Place(id, name));
		} else {
			return UNV_FUNCTION;
		}
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

	////////////////// Clone Order/////////////////

	public Order cloneOrder(int id) {
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
	public String changeStatus(int id, StatusOrder status) {
		Boolean flag = true;
		if (status == StatusOrder.Deleted) {
			String temp = prop.getProp("toDeleteOrders");
			flag = Boolean.parseBoolean(temp);
		}
		if (flag) {
			orderManager.changeStatusOfOrder(id,status);
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

	public String exportMaster() {
		String pathMaster = prop.getProp("masterCsvPath");
		ArrayList<Master> masters = masterManager.getMasters().getListOfMasters();
		try (FileWriter writer = new FileWriter(pathMaster, false)) {
			writer.write(Convert.getEntityStringFromArray(masters));
			return IMPORT_DONE;
		} catch (IOException io) {
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, io);
			return FILE_NOT_FOUND;
		}
	}

	public String exportPlaces() {
		String pathPlace = prop.getProp("placeCsvPath");
		ArrayList<Place> places = garageManager.getPlaces().getPlaces();
		try (FileWriter writer = new FileWriter(pathPlace, false)) {
			writer.write(Convert.getEntityStringFromArray(places));
			return IMPORT_DONE;
		} catch (IOException io) {
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, io);
			return FILE_NOT_FOUND;
		}
	}

	public void importPlaces() {
		String pathPlace = prop.getProp("placeCsvPath");
		try (BufferedReader br = new BufferedReader(new FileReader(pathPlace))) {
			String line;
			Boolean flag = true;
			while ((line = br.readLine()) != null) {
				if (flag) {
					flag = false;
					continue;
				}
				try {
					final String[] fields = line.split(";");
					if (garageManager.getPlaces().isFreeId(Integer.valueOf(fields[0]))) {
						garageManager.getPlaces().add(fields);
					} else {
						garageManager.getPlaces().update(fields);
					}
				} catch (final NumberFormatException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException ex) {
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, ex);
		}
	}

	public void importMasters() {
		String pathPlace = prop.getProp("masterCsvPath");
		try (BufferedReader br = new BufferedReader(new FileReader(pathPlace))) {
			String line;
			Boolean flag = true;
			while ((line = br.readLine()) != null) {
				if (flag) {
					flag = false;
					continue;
				}
				try {
					final String[] fields = line.split(";");
					if (masterManager.getMasters().isFreeId(Integer.valueOf(fields[0]))) {
						masterManager.getMasters().add(fromStringToMaster(fields, garageManager.getPlaces()));
					} else {
						masterManager.getMasters().update(fromStringToMaster(fields, garageManager.getPlaces()));
					}
				} catch (final NumberFormatException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException ex) {
			logger.log(Level.SEVERE, Constants.LOGGER_MSG, ex);
		}

	}

	private static Master fromStringToMaster(String temp[], GarageRepository placeList) {
		int pos = 0;
		// String[] temp = line.split(";");
		int id = Integer.valueOf(temp[0]);
		String name = temp[1];
		if (temp[3].equals("null")) {
			Master master = new Master(id, name, null, null);
			return master;

		} else {
			ArrayList<Work> works = new ArrayList<Work>(Integer.parseInt(temp[3]));
			pos = 4;
			for (int i = 0; i < Integer.parseInt(temp[3]); i++) {
				Work work = new Work();
				work.setId(Integer.parseInt(temp[pos++]));
				work.setNameOfService(temp[pos++]);
				work.setPrice(Double.parseDouble(temp[pos++]));
				works.add(work);
			}
			if (temp[4] != "null") {
				int size = Integer.parseInt(temp[pos++]);
				ArrayList<Order> orders = new ArrayList<Order>(size);
				// pos++;
				for (int i = 0; i < size; i++) {
					Order ord = new Order();
					ord.setId(Integer.parseInt(temp[pos++]));
					ord.setService(works.get(Integer.parseInt(temp[pos++])));
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
					orders.add(ord);
				}
				Master master = new Master(id, name, works, orders);
				for (Order order : master.getOrders()) {
					order.setMaster(master);
				}
				return master;
			} else {
				Master master = new Master(id, name, works, null);
				return master;
			}
		}

	}

	private static Master fromStringToMaster(String line, GarageRepository placeList) {
		int pos = 0;
		String[] temp = line.split(";");
		int id = Integer.valueOf(temp[0]);
		String name = temp[1];
		if (temp[3].equals("null")) {
			Master master = new Master(id, name, null, null);
			return master;

		} else {
			ArrayList<Work> works = new ArrayList<Work>(Integer.parseInt(temp[3]));
			pos = 4;
			for (int i = 0; i < Integer.parseInt(temp[3]); i++) {
				Work work = new Work();
				work.setId(Integer.parseInt(temp[pos++]));
				work.setNameOfService(temp[pos++]);
				work.setPrice(Double.parseDouble(temp[pos++]));
				works.add(work);
			}
			if (temp[4] != "null") {
				int size = Integer.parseInt(temp[pos++]);
				ArrayList<Order> orders = new ArrayList<Order>(size);
				// pos++;
				for (int i = 0; i < size; i++) {
					Order ord = new Order();
					ord.setId(Integer.parseInt(temp[pos++]));
					ord.setService(works.get(Integer.parseInt(temp[pos++])));
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
					orders.add(ord);
				}
				Master master = new Master(id, name, works, orders);
				for (Order order : master.getOrders()) {
					order.setMaster(master);
				}
				return master;
			} else {
				Master master = new Master(id, name, works, null);
				return master;
			}
		}

	}


}
