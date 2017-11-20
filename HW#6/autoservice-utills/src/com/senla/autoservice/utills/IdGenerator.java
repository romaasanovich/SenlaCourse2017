package com.senla.autoservice.utills;

import java.util.ArrayList;

import com.senla.autoservice.api.Entity;

public class IdGenerator {

	public static <T extends Entity> int getFreeID(ArrayList<T> arr) {
		for (int id = 0;; id++) {
			if (isFreeId(id, arr)) {
				return id;
			}
		}

	}

	private static <T extends Entity> int getCountOfRecords(ArrayList<T> array) {
		int count = 0;
		for (Object item : array) {
			if (item != null) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	private static <T extends Entity> Boolean isFreeId(Integer id, ArrayList<T> arr) {
		Integer countOfRecords = getCountOfRecords(arr);
		if (countOfRecords > 0) {
			for (int i = 0; i < countOfRecords; i++) {
				if (arr.get(i).getId() == id) {
					return false;
				}
			}
		}
		return true;
	}

}
