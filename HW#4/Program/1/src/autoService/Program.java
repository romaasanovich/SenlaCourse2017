package autoService;

import garage.GarageManager;
import garage.GaragePlaces;
import garage.Place;
import master.Master;
import master.MasterManager;
import master.MastersList;
import order.Order;
import order.OrderList;
import order.OrderManager;
import order.StatusOrder;
import order.Work;
import order.WorkList;
import utills.Outputer;
import java.util.Comparator;
import comparator.*;

import java.util.GregorianCalendar;

import com.danco.training.TextFileWorker;

import comparator.SortedByAlphabet;
import comparator.SortedByPrice;

public class Program {
	private static final Autoservice facade = new Autoservice();

	public static void main(String[] args) {
		
		String pathToMasters;
		String pathToPlaces;
		String pathToWorks;
		
		if (args.length > 0) {
			pathToMasters = args[0];
			pathToPlaces = args[1];
			pathToWorks = args[2];
		} else {
			pathToMasters = Constants.PATH_TO_MASTERS;
			pathToPlaces = Constants.PATH_TO_PLACES;
			pathToWorks = Constants.PATH_TO_WORKS;
		}
		//facade.readDataFromFiles(pathToPlaces, pathToMasters, pathToWorks);
		fillIn();
		facade.showAllPlaces();
		facade.showMasters(new SortedByAlphabet());
		facade.showCloseFreeDate();
		
	}
	
	static void fillIn() {
		fillWorks();
		fillMasters();
		fillPlaces();
	}
	
	static void fillWorks() {
		facade.add(new Work(0, "work1", 1.2));
		facade.add(new Work(1, "work2", 2.5));
		facade.add(new Work(2, "work3", 2.4));
		facade.add(new Work(3, "work4", 10.2));
		facade.add(new Work(4, "work5", 11.5));
	}
	
	static void fillPlaces() {
		facade.add(new Place(0,"place1"));
		facade.add(new Place(1,"place2"));
		facade.add(new Place(2,"place3"));
		facade.add(new Place(3,"place4"));
		facade.add(new Place(4,"place5"));
	}
	
	static void fillMasters() {
		facade.add(new Master(0,"master1",null));
		facade.add(new Master(1,"master2",null));
		facade.add(new Master(2,"master3",null));
		facade.add(new Master(3,"master4",null));
		facade.add(new Master(4,"master5",null));
	}
	
	
}
