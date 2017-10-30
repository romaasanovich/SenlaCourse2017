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
import utills.FileWorker;
import utills.Outputer;
import java.util.Comparator;

import java.util.GregorianCalendar;

import com.danco.training.TextFileWorker;

import comparator.SortedByAlphabet;
import comparator.SortedByPrice;

public class Program {
	public static void main(String[] s) {
		// GarageManager garageManager = new GarageManager();

		/*Place garage[] = new Place[] { new Place("place1"), new Place("place2"), new Place("place3"), new Place("place4") };
		GaragePlaces garages = new GaragePlaces(garage);
		GarageManager garageManager = new GarageManager(garages);*/
		
		Place[] garage=FileWorker.readPlaces("D:\\Senla\\HW#4\\bin\\Places.txt");
		GaragePlaces garages = new GaragePlaces(garage);
		GarageManager garageManager = new GarageManager(garages);
		
		Work[] work=FileWorker.readWorks("D:\\Senla\\HW#4\\bin\\Works.txt");
		WorkList works = new WorkList(work);
		Master[] master= FileWorker.readMaster(works,"D:\\Senla\\HW#4\\bin\\Masters.txt");
		//Work[] works = new Work[] { new Work("service1", 1.2), new Work("service", 11.0), new Work("service3", 2.0),
		//		new Work("service4", 2.3) };

	/*	Order[] order = new Order[] {
				new Order(works[2], garageManager, StatusOrder.Broned, new GregorianCalendar(2017, 10, 15),
						new GregorianCalendar(2017, 10, 18), new GregorianCalendar(2017, 10, 19))
				/*new Order(works[1], garageManager, StatusOrder.Broned, new GregorianCalendar(2017, 10, 15),
						new GregorianCalendar(2017, 10, 20), new GregorianCalendar(2017, 10, 21)),
				new Order(works[3], garageManager, StatusOrder.Broned, new GregorianCalendar(2017, 10, 15),
						new GregorianCalendar(2017, 10, 22), new GregorianCalendar(2017, 10, 23)) };};*/
		/*OrderList orders = new OrderList(order);
		OrderManager orderManager = new OrderManager(orders);*/

	//	Master[] master = new Master[] { new Master("master1", orders), new Master("master2", null),
		//		new Master("master3", null), new Master("master4", null) };
		MastersList masters = new MastersList(master);
		MasterManager masterManager = new MasterManager(masters);
		//OrderManager orderManager = new OrderManager();
		//Autoservice auto = new Autoservice(garageManager, masterManager, orderManager);
		//Outputer.showArray(garageManager.getGarage().getPlaces());
		
		Outputer.showArray(masterManager.getMasters(new SortedByAlphabet()).getListOfMasters());
		//Outputer.showArray(garageManager.getFreePlaces().getPlaces());
		//Outputer.showArray(orderManager.getCurrentOrders(new SortedByPrice()).getListOfOrders());
		//Outputer.showEntity(orderManager.getOrderCarriedOutCurrentMaster(masters.getMaster(0), masters));
		//Outputer.showEntity(masterManager.getMasterCarriedOutCurrentOrder(orders.getOrder(0)));
		
		
		
	}
}
