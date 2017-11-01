package autoService;

import java.util.Comparator;
import java.util.Date;

import com.danco.training.TextFileWorker;

import garage.GarageManager;
import garage.GaragePlaces;
import garage.Place;
import master.Master;
import master.MasterManager;
import master.MastersList;
import order.Order;
import order.OrderManager;
import order.StatusOrder;
import utillites.ArrayChecker;
import utillites.Outputer;
import work.Work;
import work.WorkList;
import work.WorkManager;

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
		workManager= new WorkManager();
		
	}
	
	public void add(Entity entity) {
		if (entity instanceof Place) {
			Outputer.printMessage(garageManager.add((Place)entity));
		} else if (entity instanceof Master) {
			Outputer.printMessage(masterManager.add((Master) entity));
		} else if (entity instanceof Order) {
			Outputer.printMessage(orderManager.add((Order) entity));
		}
		else if (entity instanceof Work) {
			Outputer.printMessage(workManager.add((Work) entity));
		}
	}
	
	public void showAllFreePlaces() {
		if(garageManager.getFreePlaces().getPlaces() == null || garageManager.getFreePlaces().getPlaces().length==0)
		{
			Outputer.printMessage(NO_ANY_PLACES);
		}
		else {
		Outputer.printArray(garageManager.getFreePlaces().getPlaces());
		}
	}
	

	
	
	public void showOrders(MastersList masters , Comparator<Order> comp) {
		masters = masterManager.getMasters();
		if (orderManager.getAllSortedOrder(masters, comp)==null || orderManager.getAllSortedOrder(masters, comp).getListOfOrders().length==0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		}
		else
		Outputer.printArray(orderManager.getAllSortedOrder(masters, comp).getListOfOrders());
	}
	
	
	public void showMasters(Comparator<Master> comp) {
		if(masterManager.getSortedMasters(comp)==null ||masterManager.getSortedMasters(comp).getListOfMasters().length==0) {
			Outputer.printMessage(NO_ANY_MASTERS);
		}
		else
		Outputer.printArray(masterManager.getSortedMasters(comp).getListOfMasters());
	}
	
	
	public void showCurrentOrders(MastersList masters , Comparator<Order> comp) {
		if (orderManager.getCurrentOrders(comp, masters)==null || orderManager.getCurrentOrders(comp, masters).getListOfOrders().length==0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		}
		else
		Outputer.printArray(orderManager.getCurrentOrders(comp, masters).getListOfOrders());
	}
	
	public void showOrderCarriedOutByMaster(MastersList masters, Master master) {
		if (orderManager.getOrderCarriedOutCurrentMaster(master, masters)==null) {
			Outputer.printMessage(NO_ANY_ORDER);
		}
		Outputer.printMessage(orderManager.getOrderCarriedOutCurrentMaster(master, masters).toString());
	}
	
	public void showMasterCarriedOutOrder(Order order) {
		if(masterManager.getMasterCarriedOutCurrentOrder(order)==null) {
			Outputer.printMessage(NO_ANY_MASTER);
		}
		else
		Outputer.printMessage(masterManager.getMasterCarriedOutCurrentOrder(order).toString());
	}
	
	public void showOrdersForPeriodTime(StatusOrder status, MastersList masters , Comparator<Order> comp,Date fDate,Date sDate) {
		masters = masterManager.getMasters();
		if (orderManager.getOdersForPeriodOfTime(status, fDate, sDate, masters,comp)==null || orderManager.getOdersForPeriodOfTime(status, fDate, sDate, masters,comp).getListOfOrders().length==0) {
			Outputer.printMessage(NO_ANY_ORDERS);
		}
		else
		Outputer.printArray(orderManager.getOdersForPeriodOfTime(status, fDate, sDate, masters,comp).getListOfOrders());
	}
	
	public void showCountOfFreePlacesOnDate( MastersList masters,Date date) {
		if(garageManager.getCountOfFreePlacesOnDate(date, masters)==0)
		{
			Outputer.printMessage(NO_ANY_PLACES);
		}
		else {
		Outputer.printMessage(Integer.toString(garageManager.getCountOfFreePlacesOnDate(date, masters)));
		}
	}
	
	public void showCloseFreeDate(MastersList masters) {
		masters = masterManager.getMasters();
		Outputer.printMessage(orderManager.getFreeDate(masters).toString());
	}
	
	
	public void writeDataIntoFiles(String pathToCustomers, String pathToRooms, String pathToServices) {
		TextFileWorker masterFileWorker = new TextFileWorker(pathToCustomers);
		TextFileWorker placeFileWorker = new TextFileWorker(pathToRooms);
		TextFileWorker workFileWorker = new TextFileWorker(pathToServices);

		masterFileWorker.writeToFile(getEntityStringArray(masterManager.getMasters().getListOfMasters()));
		placeFileWorker.writeToFile(getEntityStringArray(garageManager.getPlaces().getPlaces()));
		workFileWorker.writeToFile(getEntityStringArray(workManager.getWorks().getListOfServices()));
	}

	public void readDataFromFiles(String pathToPlaces, String pathToMasters, String pathToWork) {
		TextFileWorker garageFileWorker = new TextFileWorker(pathToPlaces);
		TextFileWorker workFileWorker = new TextFileWorker(pathToWork);
		TextFileWorker masterFileWorker = new TextFileWorker(pathToMasters);

		for (String line : garageFileWorker.readFromFile()) {
			add(new Place(line));
		}
		GaragePlaces garages =garageManager.getPlaces();
		for (String line : workFileWorker.readFromFile()) {
			add(new Work(line));
		}
		WorkList works = workManager.getWorks();
		for (String line : masterFileWorker.readFromFile()) {
			add(new Master(line,garages,works));
		}
		
	

	}

	private String[] getEntityStringArray(Entity[] array) {
		int countOfRecords = ArrayChecker.getCountOfRecords(array);
		String[] response = new String[countOfRecords];
		for (int i = 0; i < countOfRecords; i++) {
			response[i] = array[i].toString();
		}
		return response;
	}
	
	
	
}
