package comparator.order;

import java.sql.Date;
import java.util.Comparator;

import order.Order;

public class SortedByDateOfOrder implements Comparator<Order> {
	public int compare(Order obj1, Order obj2) {
		Date str1 = (Date) obj1.getDateOfOrder();
		Date str2 = (Date) obj2.getDateOfOrder();
		return str1.compareTo(str2);
	}
}
