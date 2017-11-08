package com.senla.autoservice.comparator.order;

import java.util.Comparator;
import java.util.Date;

import com.senla.autoservice.bean.Order;

public class SortedByPrice implements Comparator<Order> {

	public int compare(Order obj1, Order obj2) {
		if (obj1 != null && obj2 != null) {
			Double d1 = (Double) obj1.getService().getPrice();
			Double d2 = (Double) obj2.getService().getPrice();
			return d1.compareTo(d2);
		}
		if (obj1 != null && obj2 == null) {
			return -1;
		} else {
			return 1;
		}
	}

}
