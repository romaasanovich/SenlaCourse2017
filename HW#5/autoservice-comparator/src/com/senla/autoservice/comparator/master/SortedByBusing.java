package com.senla.autoservice.comparator.master;

import java.util.Comparator;

import com.senla.autoservice.bean.Master;

public class SortedByBusing implements Comparator<Master> {
	public  int compare(Master obj1, Master obj2) {
		if (obj1 != null && obj2 != null) {
			Boolean b1=obj1.getIsWork();
			Boolean b2=obj2.getIsWork();
			return b1.compareTo(b2);
		}
		if (obj1 != null && obj2 == null) {
			return -1;
		} else {
			return 1;
		}
	}
}
