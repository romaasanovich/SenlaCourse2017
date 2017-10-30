package master;

import java.util.Arrays;
import java.util.Comparator;

import autoService.Constants;
import garage.Place;
import order.Order;
import order.OrderList;
import order.StatusOrder;

public class MasterManager {

	private static final String NO_ANY_MASTERS = "Error. There is no any masters\n";
	private static final String	MASTER_WAS_SUCCESFUL_ADDED = "Master was succesful added";

	private MastersList masters;

	public MasterManager() {
		masters = new MastersList(Constants.ARRAY_SIZE);
	}

	public void setMasters(MastersList masters) {
		this.masters = masters;
	}

	public MastersList getMasters() {
		return masters;
	}

	public String getSortedMasters(Comparator<Master> comp) {
		Master[] temp = masters.getSortedMasters(comp);
		if (temp == null || temp.length == 0) {
			return NO_ANY_MASTERS;
		}
		StringBuilder sb = new StringBuilder();
		for (Master master : temp) {
			if (master == null) {
				break;
			}
			sb.append(master).append("\n");
		}
		if (sb.length() == 0 || sb == null) {
			return NO_ANY_MASTERS;
		}
		return sb.toString();
	}
	
	public String getMasterCarriedOutOnOrder(Order order) {
		Master master = masters.getMasterCarriedOutCurrentOrder(order);
		if(master==null)
		{
			return Constants.ERROR_NO_SUCH_RECORD;
		}
		else return master.toString();
	}
	
	public String add(Master master) {
		String message;
		if (masters.add(master)) {
			message = MASTER_WAS_SUCCESFUL_ADDED;
		} else {
			message = Constants.ERROR_THIS_ID_IS_NOT_FREE;
		}
		return message;
	}
}
