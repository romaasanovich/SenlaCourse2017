package com.senla.autoservice.comparator.order;

import java.util.Comparator;
import java.util.Date;

import com.senla.autoservice.bean.Order;

public class SortedByDateOfCompletion implements Comparator<Order> {
	public int compare(Order obj1, Order obj2) {
		
		if (obj1 != null && obj2 != null) {
			Date str1 = (Date) obj1.getDateOfCompletion();
			Date str2 = (Date) obj2.getDateOfCompletion();
			return str1.compareTo(str2);
		}
		if (obj1 != null && obj2 == null) {
			return -1;
		} else {
			return 1;
		}
		
	}
}
