package comparator.order;

import java.util.Date;
import java.util.Comparator;

import order.Order;

public class SortedByPlannedStarting implements Comparator<Order>{
	public int compare(Order obj1, Order obj2) {
		Date str1 = (Date) obj1.getDateOfPlannedStart();
		Date str2 = (Date) obj2.getDateOfPlannedStart();
		return str1.compareTo(str2);
	}
	}
