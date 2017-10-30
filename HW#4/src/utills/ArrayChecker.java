package utills;

import autoService.Entity;

public final class ArrayChecker {

	public static Boolean isEnoughtSpace(Entity[] array) {
		return array[array.length - 1] == null;
	}

	public static boolean checkSize(Entity[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				return true;
			}
		}
		return false;
	}

	public static Entity[] resize(Entity[] source) {
		Integer newSize = source.length * 2;
		Entity[] temp = new Entity[newSize];
		System.arraycopy(source, 0, temp, 0, source.length);
		return temp;
	}

	public static Integer getFreePosition(Entity[] arr) {
		Integer pos = null;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	public static Integer getCountOfFreePosition(Entity[] arr) {
		Integer count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}
	
	public static Integer getCountOfRecords(Object[] array) {
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
	
	public static Boolean isFreeId(Integer id, Entity[] arr) {
		Integer countOfRecords = getCountOfFreePosition(arr);
		if (countOfRecords > 0) {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].getId() == id) {
					return false;
				}
			}
		}
		return true;
	}

}
