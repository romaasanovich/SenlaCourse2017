package order;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import master.Master;
import master.MastersList;
import utills.ArrayChecker;

public class OrderList {
	private Order[] orders;
	static private int lastID;

	public OrderList(int size) {
		orders = new Order[size];
	}

	public OrderList(Order[] ord) {
		orders = ord;
	}

	static public int getLastID() {
		return lastID;
	}

	public void changeStatusOfOrder(int id, StatusOrder status) {
		getOrderById(id).setStatus(status);
	}

	public Order[] getListOfOrders() {
		return orders;
	}
	
	public Order[] getSortedOrder(Comparator<Order> comp){
		Order [] temp =getListOfOrders();
		Arrays.sort(temp, comp);
		return temp;
	}

	public Order getOrderById(int id) {
		for (int i = 0; i < orders.length; i++) {
			if (orders[i].getId() == id) {
				return orders[i];
			}
		}
		return null;
	}

	public Boolean add(Order obj) {
		if (!ArrayChecker.isFreeId(obj.getId(), orders)) {
			return false;
		}
		if (!ArrayChecker.isEnoughtSpace(orders)) {
			orders = Arrays.copyOf(ArrayChecker.resize(orders),
					orders.length*2, Order[].class);
		}
		Integer position = ArrayChecker.getFreePosition(orders);
		lastID=position;
		orders[position] = obj;
		return true;
	}
	
	Order[] getAllOrders(MastersList masters) {
		Order[] allOrders= new Order[orders.length];
		int pos =0;
		for(int i=0;i<masters.getListOfMasters().length;i++) {
			for(int j=0;j<masters.getMasterById(i).getOrders().getListOfOrders().length;j++) {
				allOrders[pos]=masters.getMasterById(i).getOrders().getOrderById(j);
				pos++;
			}
		}
		return allOrders;
	}
	
	
	public Order[] getAllSortedOrder(MastersList masters, Comparator<Order> comp){
		Order [] temp = getAllOrders(masters);
		Arrays.sort(temp, comp);
		return temp;
	}
	
	public  Order [] getCurrentOrders(Comparator<Order> comp, MastersList masters) {
		Order[] allOrders =getAllOrders(masters);
		Order[] temp = new Order[allOrders.length];
		int pos=0;
		for(Order order:allOrders) {
			if(order.getStatus().equals(StatusOrder.Opened)) {
				temp[pos]=order;
				pos++;
			}
		}
		Arrays.sort(temp, comp);
		return temp;
	}

	
	
	public Order getOrderCarriedOutCurrentMaster(Master master, MastersList masters) {
		for (int i = 0; i < masters.getListOfMasters().length; i++) {
			if (masters.getMasterById(i).equals(master)) {
				return masters.getMasterById(i).getOrders().getOrderById(OrderList.getLastID());
			}
		}
		return null;
	}

	public Order[] getOdersForPeriodOfTime(StatusOrder status, GregorianCalendar fDate, GregorianCalendar sDate,
			MastersList masters) {
		Order[] allOrders =getAllOrders(masters);
		Order[] temp = new Order[allOrders.length];
		int pos=0;
		for (int i = 0; i < masters.getListOfMasters().length; i++)
			for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
				if (masters.getMasterById(i).getOrders().getOrderById(j).getStatus() == status
						&& masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().after(fDate)
						&& masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().before(sDate)) {
					temp[pos]= masters.getMasterById(i).getOrders().getOrderById(j);
					pos++;
				}
			}
		return temp;
	}

	public GregorianCalendar getFreeDate(MastersList masters) {
		GregorianCalendar date = new GregorianCalendar();
		for (;;) {
			for (int i = 0; i < masters.getListOfMasters().length; i++)
				for (int j = 0; j < masters.getMasterById(i).getOrders().getListOfOrders().length; j++) {
					if (!masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().after(date)
							&& !masters.getMasterById(i).getOrders().getOrderById(j).getDateOfOrder().before(date)) {
						return date;
					}
				}
			date.add(Calendar.DAY_OF_YEAR, +1);
		}
	}
	
	
}
