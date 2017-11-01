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
import order.Work;
import order.WorkManager;
import utills.ArrayChecker;
import utills.Outputer;

public class Autoservice {
	GarageManager garageManager;
	MasterManager masterManager;
	OrderManager orderManager;
	WorkManager workManager;

	Autoservice() { 
		garageManager = new GarageManager();
		masterManager = new MasterManager();
		orderManager = new OrderManager();
		workManager= new WorkManager();
		
	}
	
	public void add(Entity entity) {
		if (entity instanceof Place) {
			Outputer.print(garageManager.add((Place)entity));
		} else if (entity instanceof Master) {
			Outputer.print(masterManager.add((Master) entity));
		} else if (entity instanceof Order) {
			Outputer.print(orderManager.add((Order) entity));
		}
		else if (entity instanceof Work) {
			Outputer.print(workManager.add((Work) entity));
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
	
	public void showCloseFreeDate() {
		MastersList masters = masterManager.getMasters();
		Outputer.print(orderManager.getFreeDate(masters));
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

		for (String line : masterFileWorker.readFromFile()) {
			add(new Master(line,garageManager.getPlaces()));
		}
		for (String line : garageFileWorker.readFromFile()) {
			add(new Place(line));
		}
		for (String line : workFileWorker.readFromFile()) {
			add(new Work(line));
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
