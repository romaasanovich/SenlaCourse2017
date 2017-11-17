package com.senla.autoservice.utills;

import java.util.ArrayList;

import com.senla.autoservice.api.Entity;
import com.senla.autoservice.utills.ArrayChecker;

public class IdGenerator {

	public static <T extends Entity> int getFreeID(ArrayList<T> arr) {
		for (int id = 0;; id++) {
			if (ArrayChecker.isFreeId(id, arr)) {
				return id;
			}
		}

	}

}
