package autoService;

import java.util.Comparator;
import java.util.GregorianCalendar;

import com.danco.training.TextFileWorker;

import garage.GarageManager;
import garage.Place;
import master.Master;
import master.MasterManager;
import master.MastersList;
import order.Order;
import order.OrderManager;
import order.StatusOrder;
import utills.ArrayChecker;
import utills.Outputer;

public class Autoservice {
	GarageManager garageManager;
	MasterManager masterManager;
	OrderManager orderManager;

	Autoservice(GarageManager garageManager, MasterManager masterManager, OrderManager orderManager) {
		this.garageManager = garageManager;
		this.masterManager = masterManager;
		this.orderManager = orderManager;
	}
	
	public void add(Entity entity) {
		if (entity instanceof Place) {
			Outputer.print(garageManager.add((Place)entity));
		} else if (entity instanceof Master) {
			Outputer.print(masterManager.add((Master) entity));
		} else if (entity instanceof Order) {
			Outputer.print(orderManager.add((Order) entity));
		}
	}
	
	public void showAllPlaces() {
		Outputer.print(garageManager.showFreePlaces());
	}
	
	
	public void showOrders(MastersList masters , Comparator<Order> comp) {
		masters = masterManager.getMasters();
		Outputer.print(orderManager.getSortedOrders(masters, comp));
	}
	
	public void showMasters(Comparator<Master> comp) {
		Outputer.print(masterManager.getSortedMasters(comp));
	}
	
	public void showCurrentOrders(MastersList masters , Comparator<Order> comp) {
		masters = masterManager.getMasters();
		Outputer.print(orderManager.getCurrentSortedOrder(comp, masters));
	}
	
	public void showOrderCarriedOutByMaster(MastersList masters, Master master) {
		masters = masterManager.getMasters();
		Outputer.print(orderManager.getOrderCarriedOutCurrentMaster(master, masters));
	}
	
	public void showMasterCarriedOutOrder(Order order) {
		Outputer.print(masterManager.getMasterCarriedOutOnOrder(order));
	}
	
	public void showOrdersForPeriodTime(StatusOrder status, MastersList masters , Comparator<Order> comp,GregorianCalendar fDate,GregorianCalendar sDate) {
		masters = masterManager.getMasters();
		Outputer.print(orderManager.getOdersForPeriodOfTime(status, fDate, sDate, masters));
	}
	
	public void showFreePlacesOnDate( MastersList masters,GregorianCalendar date) {
		masters = masterManager.getMasters();
		Outputer.print("Count of free places: "+garageManager.getCountOfFreeDate(date, masters));
	}
	
	public void showCloseFreeDate(MastersList masters) {
		masters = masterManager.getMasters();
		Outputer.print(orderManager.getFreeDate(masters));
	}
	
	
	public void writeDataIntoFiles(String pathToCustomers, String pathToRooms, String pathToServices) {
		TextFileWorker masterFileWorker = new TextFileWorker(pathToCustomers);
		TextFileWorker placeFileWorker = new TextFileWorker(pathToRooms);
		TextFileWorker orderFileWorker = new TextFileWorker(pathToServices);

		masterFileWorker.writeToFile(getEntityStringArray(masterManager.getMasters().getListOfMasters()));
		placeFileWorker.writeToFile(getEntityStringArray(garageManager.getPlaces().getPlaces()));
		orderFileWorker.writeToFile(getEntityStringArray(orderManager.getOrders().getListOfOrders()));
	}

	public void readDataFromFiles(String pathToCustomers, String pathToRooms, String pathToServices) {
		TextFileWorker masterFileWorker = new TextFileWorker(pathToMasters);
		TextFileWorker garageFileWorker = new TextFileWorker(pathToPlaces);
		TextFileWorker orderFileWorker = new TextFileWorker(pathToOrder);

		for (String line : masterFileWorker.readFromFile()) {
			add(new Master(line));
		}
		for (String line : garageFileWorker.readFromFile()) {
			add(new Place(line));
		}
		for (String line : serviceFileWorker.readFromFile()) {
			add(new Service(line));
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
