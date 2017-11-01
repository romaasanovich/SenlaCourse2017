package comparator.order;

import java.sql.Date;
import java.util.Comparator;

import order.Order;

public class SortedByDateOfCompletion implements Comparator<Order> {
	public int compare(Order obj1, Order obj2) {
		Date str1 = (Date) obj1.getDateOfCompletion();
		Date str2 = (Date) obj2.getDateOfCompletion();
		return str1.compareTo(str2);
	}
}
