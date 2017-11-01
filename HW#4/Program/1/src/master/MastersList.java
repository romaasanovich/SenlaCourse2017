package master;

import java.util.Arrays;
import java.util.Comparator;

import order.Order;
import order.OrderList;
import order.StatusOrder;
import utills.ArrayChecker;

public class MastersList {
	private Master[] masters;
	static private int lastID;

	public MastersList(int size) {
		masters = new Master[size];
	}

	static public int getLastID() {
		return lastID;
	}

	public Master[] getListOfMasters() {
		return masters;
	}
	
	public Master[] getSortedMasters(Comparator <Master> comp) {
		Master [] temp =getListOfMasters();
		Arrays.sort(temp,comp);
		return temp;
	}

	public Master getMasterById(int id) {
		for (int i = 0; i < masters.length; i++) {
			if (masters[i].getId() == id) {
				return masters[i];
			}
		}
		return null;
	}
	
	public Master getMasterCarriedOutCurrentOrder(Order order) {
		for(int i =0;i<getListOfMasters().length;i++) {
			if(getMasterById(i).getOrders().getOrderById(OrderList.getLastID())== (order) || getMasterById(i).getOrders().getOrderById(MastersList.getLastID()).getStatus()==(StatusOrder.Opened)) {
				return getMasterById(i);
			}
		}
		return null;
	}
	
	
	public Boolean add(Master obj) {
		if (!ArrayChecker.isFreeId(obj.getId(), masters)) {
			return false;
		}
		if (!ArrayChecker.isEnoughtSpace(masters)) {
			masters = Arrays.copyOf(ArrayChecker.resize(masters),
					masters.length*2, Master[].class);
		}
		Integer position = ArrayChecker.getFreePosition(masters);
		lastID=position;
		masters[position] = obj;
		return true;
	}
}
