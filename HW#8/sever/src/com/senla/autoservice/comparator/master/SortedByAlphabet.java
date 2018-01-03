package com.senla.autoservice.comparator.master;

import java.util.Comparator;

import com.senla.autoservice.bean.Master;

public class SortedByAlphabet implements Comparator<Master> {
	public int compare(Master obj1, Master obj2) {
		if (obj1 != null && obj2 != null) {
			return obj1.getName().compareTo(obj2.getName());
		}
		if (obj1 != null && obj2 == null) {
			return -1;
		} else {
			return 1;
		}
	}
}
